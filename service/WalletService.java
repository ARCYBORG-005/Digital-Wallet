package service;

import dao.WalletDAO;

public class WalletService {
    private WalletDAO walletDAO = new WalletDAO();

    public boolean addMoney(int userId, double amount) {
        return walletDAO.addMoney(userId, amount);
    }

    public boolean transferMoney(int fromUserId, int toUserId, double amount) {
        return walletDAO.transferMoney(fromUserId, toUserId, amount);
    }
}