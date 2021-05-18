import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager {
    public static ArrayList<Product> products = new ArrayList<>();

    public ProductManager() {

    }



    public void printProducts(){
        for (Product p : products) {
            String tempString = "";
            if(p.getAccepted() == 0){
                tempString = "ubestemt";
            } else if(p.getAccepted() == 1){
                tempString = "accepteret";
            } else if(p.getAccepted() == 2){
                tempString = "afvist";
            }

            System.out.println(p.getName() + " | " + tempString + " | " + p.getScore());
        }
    }

    public void saveProducts() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("products.txt");
            writer.write(getProductData());
        } catch (IOException e) {
            System.out.println("Couldn't instantiate the FileWriter in saveGameData()");
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (NullPointerException | IOException e) {
                System.out.println("Couldn't close the FileWriter in saveGameData()");
                e.printStackTrace();
            }
        }
    }

    // get the information from the product that have been tested
    public String getProductData() {
        StringBuilder gameData = new StringBuilder();
        String productData;

        for (Product p : products) {
            productData = String.format("%s:%d:%d\n", p.getName(), p.getAccepted(),p.getScore());
            gameData.append(productData);

        }
        return gameData.toString();
    }


    public static ArrayList<Product> loadProducts() {
        ArrayList<Product> loadedProducts = new ArrayList<>();

        File file = new File("products.txt");
        String[] productLine;
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // once for each group of words
        if (scan != null) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                // check for blank line
                if (line.isEmpty()) continue;
                productLine = line.split(":");

                loadedProducts.add(new Product(productLine[0],Integer.parseInt(productLine[1]), Integer.parseInt(productLine[2])));
            }
        }
        return loadedProducts;
    }


    public void adminAcceptOrDeny(){
        System.out.println("Tag venligst stilling til om følgende produkter skal accepteres eller afvises:\n");

        for (Product p : products) {
            if(p.getAccepted() != 0) continue;

            String input;
            input = UI.getUserInput("Vil du acceptere (Y) eller afvise (N):\n\n|Navn: " + p.getName() + " , " + "score: "+ p.getScore() + "|\n\n" + "Y/N: ");

            if (input.equalsIgnoreCase("y")){
                p.setAccepted(1);
            } else if(input.equalsIgnoreCase("n")){
                p.setAccepted(2);
            }

        }


    }

}
