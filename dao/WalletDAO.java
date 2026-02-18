package dao;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class WalletDAO {

    // =========================
    // ADD MONEY
    // =========================
    public boolean addMoney(int userId, double amount) {

        String updateBalanceSQL =
                "UPDATE users SET balance = balance + ? WHERE user_id = ?";

        String transactionSQL =
                "INSERT INTO transactions (user_id, type, amount, description, transaction_time) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {

            // 1️⃣ Update balance
            PreparedStatement ps1 = con.prepareStatement(updateBalanceSQL);
            ps1.setDouble(1, amount);
            ps1.setInt(2, userId);

            int updated = ps1.executeUpdate();
            if (updated == 0) {
                System.out.println("❌ User ID " + userId + " does not exist");
                return false;
            }

            // 2️⃣ Insert transaction
            PreparedStatement ps2 = con.prepareStatement(transactionSQL);
            ps2.setInt(1, userId);
            ps2.setString(2, "CREDIT");
            ps2.setDouble(3, amount);
            ps2.setString(4, "Money added to wallet");
            ps2.setObject(5, LocalDateTime.now());

            ps2.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // =========================
    // TRANSFER MONEY
    // =========================
    public boolean transferMoney(int fromUserId, int toUserId, double amount) {

        String debitSQL =
                "UPDATE users SET balance = balance - ? WHERE user_id = ? AND balance >= ?";

        String creditSQL =
                "UPDATE users SET balance = balance + ? WHERE user_id = ?";

        String transactionSQL =
                "INSERT INTO transactions (user_id, type, amount, description, transaction_time) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); // 🔴 START TRANSACTION

            // 1️⃣ Debit sender
            PreparedStatement debitPs = con.prepareStatement(debitSQL);
            debitPs.setDouble(1, amount);
            debitPs.setInt(2, fromUserId);
            debitPs.setDouble(3, amount);

            int debitRows = debitPs.executeUpdate();
            if (debitRows == 0) {
                System.out.println("❌ Sender has insufficient balance OR user does not exist");
                con.rollback();
                return false;
            }

            // 2️⃣ Credit receiver
            PreparedStatement creditPs = con.prepareStatement(creditSQL);
            creditPs.setDouble(1, amount);
            creditPs.setInt(2, toUserId);

            int creditRows = creditPs.executeUpdate();
            if (creditRows == 0) {
                System.out.println("❌ Receiver user does not exist");
                con.rollback();
                return false;
            }

            // 3️⃣ Sender transaction
            PreparedStatement t1 = con.prepareStatement(transactionSQL);
            t1.setInt(1, fromUserId);
            t1.setString(2, "DEBIT");
            t1.setDouble(3, amount);
            t1.setString(4, "Transfer to user ID " + toUserId);
            t1.setObject(5, LocalDateTime.now());
            t1.executeUpdate();

            // 4️⃣ Receiver transaction
            PreparedStatement t2 = con.prepareStatement(transactionSQL);
            t2.setInt(1, toUserId);
            t2.setString(2, "CREDIT");
            t2.setDouble(3, amount);
            t2.setString(4, "Transfer from user ID " + fromUserId);
            t2.setObject(5, LocalDateTime.now());
            t2.executeUpdate();

            con.commit(); // 🟢 COMMIT
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) con.rollback(); // ✅ SAFE rollback
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException ignored) {}
        }

        return false;
    }
}