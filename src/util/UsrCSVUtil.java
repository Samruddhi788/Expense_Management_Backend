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
    //always try to use path here an thats it as we have to store data in the respective path 
    private static final Path USER_CSV = Path.of("data/users.csv");
    private static final Path EXPENSE_CSV = Path.of("data/expenses.csv");
    private static final Path BUDGET_CSV = Path.of("data/budgets.csv");
    
    public static void saveUsr(User user){
       String line=user.getName()+","+user.getId()+","+user.getPassword();
       //converting to csv file and work on the same 
       try (BufferedWriter bw=Files.newBufferedWriter(USER_CSV,StandardOpenOption.CREATE,StandardOpenOption.APPEND)){
        //stdopenoption in order to add values to respective files if not presrnt create them esle leave 
        bw.write(line);
        bw.newLine();
        System.out.println("User saved successfully!");  
       } catch (Exception e) {
        System.out.println("Error is file saving:"+e.getMessage());
       }
}
    public static void updateUsr(User upUser) throws IOException{
         //String line=user.getName()+","+user.getId()+","+user.getPassword();
        Boolean found=false;
         List<String> lines=Files.readAllLines(USER_CSV);
         List<String> updates=new ArrayList<>();
      
             for(String line:lines){
                String[] parts=line.split(",");
                if (parts.length<3)continue;
                int id =Integer.parseInt(parts[0]);//we are getting the value of the Integer and thats it 
                if(id==upUser.getId()){
                    String newline =upUser.getId()+","+upUser.getName()+","+upUser.getPassword();
                    updates.add(newline);
                    found=true;
                }
             
             else{
                updates.add(line);
             }
             }
                try ( BufferedWriter bw=  Files.newBufferedWriter(USER_CSV,StandardOpenOption.TRUNCATE_EXISTING)){
             for(String line:updates){
           bw.write(line);
           bw.newLine();
             }
            if(found){
                System.out.println("User added successfully!");
            }
            else{
                System.out.println("User not found please check it again;");
            }
         } catch (Exception e) {
            System.out.println("ERROR :"+e.getMessage());
         }
         
    }
    public static void deleteUsr (int id)throws IOException{
      
        List<String> lines=Files.readAllLines(USER_CSV);
        List<String> remainingLines = new ArrayList<>();
        boolean found = false;
        for(String check:lines){
            String[] parts=check.split(",");
            if(parts.length<3) continue;
            int newId=Integer.parseInt(parts[0]);
            if(id==newId){
             found=true;
            }
            else{
            remainingLines.add(check);
            }
        }
        try (BufferedWriter bw=Files.newBufferedWriter(USER_CSV, StandardOpenOption.TRUNCATE_EXISTING)){
         for(String line:remainingLines){
            bw.write(line);
            bw.newLine();
         }
         if(found){
            System.out.println("User deleted successfully!");
         }
         else{
            System.out.println("User not found");
         }   
        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());
        }

    }

}
