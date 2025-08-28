package test;

import java.time.LocalDate;
import model.Expense;
import util.ExpCSVUtil;

public class TestExpenseCSV {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Expense CSV Test ===");

        // 1. Save new expenses
        Expense e1 = new Expense(201, 101, 500.0, "Food", LocalDate.now(), "Lunch");
        ExpCSVUtil.saveExpense(e1);

        Expense e2 = new Expense(202, 101, 1200.0, "Rent", LocalDate.now(), "Monthly Rent");
        ExpCSVUtil.saveExpense(e2);

        // 2. Update an expense
        Expense e1Updated = new Expense(201, 101, 550.0, "Food", LocalDate.now(), "Lunch updated");
        ExpCSVUtil.updateExpense(e1Updated);

        // 3. Delete an expense
        ExpCSVUtil.deleteExpense(202);

        System.out.println("=== Expense CSV Test Completed ===");
    }
}
