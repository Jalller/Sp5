
import java.util.Scanner;

public class UI {
    static GreenTest greenTest = new GreenTest();
    static ProductManager productManager = new ProductManager();

    public UI() {
        ProductManager.products = ProductManager.loadProducts();

    }

    // switch case menu for admin
    public static void adminMenu() {

        String input = getUserInput("\nVelkommen til GriinBi\n" +
                "\nTast 1 for at vise testede produkter:\n" +
                "Tast 2 for at acceptere eller afvise et produkt:\n" +
                "Tast 3 for at lukke og gemme programmet:\n: ");

        switch (input) {
            case "1":
                // get an overview over the all users tested product
                productManager.printProducts();
                adminMenu();
                break;
            case "2":
                // admin should be able to accept of denied a product
                productManager.adminAcceptOrDeny();
                adminMenu();
                break;
            case "3":
                // log out
                productManager.saveProducts();
                break;
        }

    }

    // switch case menu for users
    public static void userMenu() {
        String input = getUserInput("\nVelkommen til GriinBi:\n" +
                "\nTast 1 for at teste hvor bæredygtigt dit produkt er:\n" +
                "Tast 2 for at lukke og gemme programmet:\n: ");
        switch (input) {
            case "1":
                // test a product
                greenTest.createQuestionnaire();
                userMenu();
                break;
            case "2":
                // log out and save
                productManager.saveProducts();
                break;
        }

    }

    public static void chooseUser() {
        String input = getUserInput("Er du admin(A) eller er du en bruger(B)?\nA/B: ");
        String password = "1234DogLover1234";



        if (input.equalsIgnoreCase("A")) {
            input = getUserInput("\nIndtast password til admin: ");
            // password checker
            if (!input.equals(password)){
                System.out.println("Det indtastede password var forkert\n");
                chooseUser();
                return;
            }

            System.out.println("\nDU ER LOGGET IND SOM ADMIN\n");
            adminMenu();
        } else if (input.equalsIgnoreCase("B")) {
            System.out.println("\nDU ER LOGGET IND SOM BRUGER\n");
            userMenu();
        }

    }


    public static String getUserInput(String msg) {
        System.out.print(msg);
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().trim();
    }

}

