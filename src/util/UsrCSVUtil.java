package util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UsrCSVUtil {

    private static final Path USER_CSV = Path.of("data/user.csv");

    // Save a new user
    public static void saveUsr(User user){
        // ID first, then name, then password
        String line = user.getId() + "," + user.getName() + "," + user.getPassword();
        try (BufferedWriter bw = Files.newBufferedWriter(USER_CSV,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(line);
            bw.newLine();
            System.out.println("User saved successfully!");
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // Update an existing user
    public static void updateUsr(User upUser) throws IOException {
        List<String> lines = Files.readAllLines(USER_CSV);
        List<String> updates = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue;
            int id = Integer.parseInt(parts[0]); // ID is first column
            if (id == upUser.getId()) {
                // Write updated line
                String newLine = upUser.getId() + "," + upUser.getName() + "," + upUser.getPassword();
                updates.add(newLine);
                found = true;
            } else {
                updates.add(line);
            }
        }

        try (BufferedWriter bw = Files.newBufferedWriter(USER_CSV, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String line : updates) {
                bw.write(line);
                bw.newLine();
            }
        }

        if (found) {
            System.out.println("User updated successfully!");
        } else {
            System.out.println("User not found, please check the ID.");
        }
    }

    // Delete a user by ID
    public static void deleteUsr(int id) throws IOException {
        List<String> lines = Files.readAllLines(USER_CSV);
        List<String> remainingLines = new ArrayList<>();
        boolean found = false;

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue;
            int userId = Integer.parseInt(parts[0]);
            if (userId == id) {
                found = true; // skip this line
            } else {
                remainingLines.add(line);
            }
        }

        try (BufferedWriter bw = Files.newBufferedWriter(USER_CSV, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String line : remainingLines) {
                bw.write(line);
                bw.newLine();
            }
        }

        if (found) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found, please check the ID.");
        }
    }
}
