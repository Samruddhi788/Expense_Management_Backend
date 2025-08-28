package util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import model.Expense;

public class ExpCSVUtil {
    private static final Path  EXPENSE_CSV=Path.of("data/expenses.csv");

    public static void saveExpense(Expense exp){
        String line=exp.getId()+","+exp.getUserId()+","+exp.getAmount()+","+ exp.getCategory()+","+exp.getDate()+","+exp.getDescription();
        try (BufferedWriter bw=Files.newBufferedWriter(EXPENSE_CSV, StandardOpenOption.CREATE,StandardOpenOption.APPEND)){
            bw.write(line);
            bw.newLine();  
            System.out.println("Expense added successfully!");
        } catch (Exception e) {
            System.out.println("Issue in adding expense"+e.getMessage());
        }
    }

    public static void updateExpense(Expense exp) throws IOException{
       Boolean found=false;
        //String newline=exp.getId()+","+exp.getUserId()+","+exp.getAmount()+","+ exp.getCategory()+","+exp.getDate()+","+exp.getDescription();
       List <String> existingList=Files.readAllLines(EXPENSE_CSV);
       List<String> newlist=new ArrayList<>();
       for(String line:existingList){
        String[] parts=line.split(",");
        if(parts.length<6) continue;
        int id =Integer.parseInt(parts[0]);
        if(id==exp.getId()){
            String upLine=exp.getId()+","+exp.getUserId()+","+exp.getAmount()+","+ exp.getCategory()+","+exp.getDate()+","+exp.getDescription();
            newlist.add(upLine);
            found=true;
        }
        else{
            newlist.add(line);
        }
       }
    
    try(BufferedWriter bw=Files.newBufferedWriter(EXPENSE_CSV, StandardOpenOption.TRUNCATE_EXISTING)){
        for(String line:newlist){
            bw.write(line);
            bw.newLine();
        }
    }
    if(found){
        System.out.println("Expense Updated Successfully");

    }
    else{
        System.out.println("Expense not found ");
    }
    }

    public static void deleteExpense(int id )throws IOException{
        Boolean found=false;
    List<String> existing=Files.readAllLines(EXPENSE_CSV);
    List<String> updated=new ArrayList<>();
    for(String check:existing){
        String[] parts=check.split(",");
        if(parts.length<6) continue;
        int oldId=Integer.parseInt(parts[0]);
        if(id==oldId){
        found=true;
        }
        else{
            updated.add(check);
        }
    }
    try(BufferedWriter bw=Files.newBufferedWriter(EXPENSE_CSV, StandardOpenOption.TRUNCATE_EXISTING)) {
    for(String line:updated){
        bw.write(line);
        bw.newLine();}  
        if(found){
            System.out.println("User deleted Successfully!");

        }   
        else{
            System.out.println("Failed to delete expense");
        }   
    } catch (Exception e) {
    System.out.println("Error"+e.getMessage());
    }


    }
}
