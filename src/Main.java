import java.util.Scanner;
import model.User;
import service.UserValidationService;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean running=true;
        while(running){
                System.out.println("\n===== USER MENU =====");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Update User");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                
                int choice=sc.nextInt();//take input from user
                sc.nextLine();

                switch(choice){
                    case 1:
                    System.out.println("Enter ID:");
                    int regId=sc.nextInt();
                     sc.nextLine(); 
                    System.out.print("Enter Name: ");
                    String regName = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String regPass = sc.nextLine();

                    User newUser = new User(regId, regName, regPass);
                    UserValidationService.registerUsr(newUser);
                    break;

                case 2:
                    // Login
                    System.out.print("Enter ID: ");
                    int logId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String logName = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String logPass = sc.nextLine();
                     User loginUser = new User(logId, logName, logPass);
                    try {
                        UserValidationService.validateUser(loginUser);
                    } catch (Exception e) {
                        System.out.println("Error during login: " + e.getMessage());
                    }
                    break;

                case 3:
                    // Update User
                    System.out.print("Enter ID of user to update: ");
                    int upId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new Name: ");
                    String upName = sc.nextLine();
                    System.out.print("Enter new Password: ");
                    String upPass = sc.nextLine();
                    User upUser = new User(upId, upName, upPass);
                   UserValidationService.updateUser(upUser);
                    break;

                case 4:
                    // Exit
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option! Try again.");
            }

                }
           sc.close();
        }
    }

