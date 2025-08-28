package util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import model.Budget;

public class BudgetCSVUtil {
    private static final Path BUDGET_CSV = Path.of("data/budget.csv");

    // Save a budget
    public static void saveBudget(Budget budget){
        String line = budget.getUserId() + "," + budget.getCategory() + "," + budget.getMonthlyLimit();
        try (BufferedWriter bw = Files.newBufferedWriter(BUDGET_CSV, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(line);
            bw.newLine();
            System.out.println("Budget saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving budget: " + e.getMessage());
        }
    }

    // Update budget
    public static void updateBudget(Budget upBudget) throws IOException {
        List<String> lines = Files.readAllLines(BUDGET_CSV);
        List<String> updates = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue;

            int userId = Integer.parseInt(parts[0]);
            String category = parts[1];

            if (userId == upBudget.getUserId() && category.equalsIgnoreCase(upBudget.getCategory())) {
                // Replace with updated budget
                String newLine = upBudget.getUserId() + "," + upBudget.getCategory() + "," + upBudget.getMonthlyLimit();
                updates.add(newLine);
                found = true;
            } else {
                updates.add(line);
            }
        }

        try (BufferedWriter bw = Files.newBufferedWriter(BUDGET_CSV, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String line : updates) {
                bw.write(line);
                bw.newLine();
            }
        }

        if (found) System.out.println("Budget updated successfully!");
        else System.out.println("Budget not found!");
    }

    // Delete budget
    public static void deleteBudget(int userId, String category) throws IOException {
        List<String> lines = Files.readAllLines(BUDGET_CSV);
        List<String> remainingLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue;

            int uid = Integer.parseInt(parts[0]);
            String cat = parts[1];

            if (uid == userId && cat.equalsIgnoreCase(category)) {
                found = true; // skip this budget
            } else {
                remainingLines.add(line);
            }
        }

        try (BufferedWriter bw = Files.newBufferedWriter(BUDGET_CSV, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String line : remainingLines) {
                bw.write(line);
                bw.newLine();
            }
        }

        if (found) System.out.println("Budget deleted successfully!");
        else System.out.println("Budget not found!");
    }
}
