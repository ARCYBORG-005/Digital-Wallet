package service;

import dao.ExpenseDAO;
import model.Expense;

import java.util.List;

public class ExpenseService {
    private ExpenseDAO expenseDAO = new ExpenseDAO();

    public boolean addExpense(Expense expense) {
        return expenseDAO.addExpense(expense);
    }

    public List<Expense> getUserExpenses(int userId) {
        return expenseDAO.getExpensesByUser(userId);
    }
}