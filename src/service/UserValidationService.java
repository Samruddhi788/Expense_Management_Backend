package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import model.User;
import util.UsrCSVUtil;
import static util.UsrCSVUtil.saveUsr;

public class UserValidationService {
    
    public static boolean  validateUser(User user) throws IOException{
    
    final Path USER_CSV = Path.of("data/user.csv");
    if (!Files.exists(USER_CSV)) {
    System.out.println("No users registered yet. Please register first!");
    return false;
}

    Boolean found=false;
    //String line= user.getId() + "," + user.getName() + "," + user.getPassword();
    List<String> exisiting=Files.readAllLines(USER_CSV); 
    for(String lines:exisiting){
        String[] parts=lines.split(",");
        if(parts.length<3) continue;
        int id=Integer.parseInt(parts[0]);
        String name=parts[1];
        String password=parts[2];
        if(id==user.getId() && name.equals(user.getName()) && password.equals(user.getPassword())){
         System.out.println("Logged in successfully!Welcome back");
         found=true;
         break;
        }
    }
    if(!found){
        System.out.println("Login failed invalid credentials!Please Register before login");
   }
return found;
   
}


public static void registerUsr(User user){
  try {
      saveUsr(user);
  } catch (Exception e) {
    System.out.println("Error is saving the user"+e.getMessage());
  } 
} 
public static void updateUser(User user) {
        try {
            UsrCSVUtil.updateUsr(user);
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }
}