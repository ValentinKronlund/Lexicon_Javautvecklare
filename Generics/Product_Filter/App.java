package Generics.Product_Filter;

import Generics.Product_Filter.Helpers.Helper;
import java.util.Scanner;

public class App {
    Scanner input = new Scanner(System.in);
    Helper helper = new Helper();

    public void run() {
        System.out.println("Running the app! ");
        Product products = new Product();

        Boolean looping = true;
        while (looping) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Get all products");
            System.out.println("2. Get a brand of product");
            System.out.println("3. Get a brand and flavour of product");
            System.out.println("4. Sell a product");
            System.out.println("x. Exit loop!");
            Boolean actionTaken = false;

            while (!actionTaken) {
                char choice = input.nextLine().trim().charAt(0);

                switch (choice) {
                    case '1': {
                        products.printAllProducts();
                        actionTaken = true;
                        break;
                    }
                    case '2': {
                        String brandName = helper.askLine(input, "What brand?");
                        products.printProductBrand(brandName);
                        actionTaken = true;
                        break;
                    }
                    case '3': {
                        String brandName = helper.askLine(input, "What brand? ");
                        String flavour = helper.askLine(input, "What flavour? ");
                        products.printProductBrandAndFlavour(brandName, flavour);
                        actionTaken = true;
                        break;
                    }
                    case '4': {
                        String brandName = helper.askLine(input, "What brand? ");
                        String flavour = helper.askLine(input, "What flavour? ");
                        int amount = helper.askInt(input, "How many? ");
                        products.sell(brandName, flavour, amount);
                        actionTaken = true;
                        break;
                    }
                    case 'x': {
                        System.out.println("Exiting loop -- See ya' later!");
                        looping = false;
                        return;
                    }
                    default:
                        System.out.println("No such command -- Try again!");
                        continue;
                }
            }

        }

    }
}
