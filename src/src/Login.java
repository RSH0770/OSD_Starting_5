import java.io.*;
import java.util.Scanner;

public class Login {
    private static final String USER_FILE = "users.txt";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String loggedInUser = null;

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("0. Exit");
            System.out.print("Select: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    signUp(sc);
                    break;
                case "2":
                    loggedInUser = login(sc);
                    if (loggedInUser != null) {
                        while (true) {
                            System.out.println("\n1. Log Out");
                            System.out.println("0. Exit");
                            System.out.print("Select: ");
                            String subChoice = sc.nextLine();
                            if (subChoice.equals("1")) {
                                System.out.println("Goodbye, " + loggedInUser + "! You have been logged out.");
                                loggedInUser = null;
                                break;
                            } else if (subChoice.equals("0")) {
                                System.exit(0);
                            } else {
                                System.out.println("Invalid option.");
                            }
                        }
                    }
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }

    private static void signUp(Scanner sc) throws IOException {
        System.out.println("[Sign Up]");
        System.out.print("Enter new ID: ");
        String id = sc.nextLine();
        System.out.print("Enter new Password: ");
        String pw = sc.nextLine();

        BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true));
        writer.write(id + " " + pw);
        writer.newLine();
        writer.close();

        System.out.println("Sign up complete.");
    }

    private static String login(Scanner sc) throws IOException {
        System.out.println("[Log In]");
        while (true) {
            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("Password: ");
            String pw = sc.nextLine();

            BufferedReader reader = new BufferedReader(new FileReader(USER_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2 && parts[0].equals(id) && parts[1].equals(pw)) {
                    reader.close();
                    System.out.println("Login successful. Welcome, " + id + "!");
                    return id;
                }
            }
            reader.close();
            System.out.println("Invalid ID or Password. Please try again.");
        }
    }
}
