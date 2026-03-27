package Generics.Product_Filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Generics.Product_Filter.Errors.InvalidAmountException;
import Generics.Product_Filter.Errors.OutOfStockException;

public class Product {
    private Random rng = new Random();

    private Map<String, Map<String, Map<String, Integer>>> energyDrinkInventory = new HashMap<>();
    private String[] availableBrands = { "nocco", "celsius", "monster", "redbull" };
    private String[] availableFlavours = { "orange", "lemon", "apple", "rhubarb" };

    public Product() {
        for (int i = 0; i < availableBrands.length; i++) {
            Map<String, Map<String, Integer>> flavour = new HashMap<>();

            for (int j = 0; j < availableFlavours.length; j++) {
                Map<String, Integer> itemDetails = new HashMap<>();
                itemDetails.put("quantity", rng.nextInt(0, 11));
                itemDetails.put("cost", rng.nextInt(1, 6) * 10);

                flavour.put(availableFlavours[j], itemDetails);
            }

            energyDrinkInventory.put(availableBrands[i], flavour);
        }
    }

    public void sell(String brandName, String flavour, int amount) {
        if (checkBrand(brandName) && checkFlavour(flavour)) {
            Map<String, Integer> itemDetails = energyDrinkInventory.get(brandName).get(flavour);
            try {
                if (amount <= 0) {
                    throw new InvalidAmountException();
                }
                if (checkQuantity(itemDetails, amount)) {
                    int currentQuantity = itemDetails.get("quantity");
                    itemDetails.put("quantity", currentQuantity - amount);
                    System.out.println("Sold " + amount + " of " + brandName + " " + flavour + ", for a total of "
                            + (itemDetails.get("cost") * amount));
                } else {
                    int currentQuantity = itemDetails.get("quantity");
                    throw new OutOfStockException(currentQuantity);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printAllProducts() {
        for (String brand : energyDrinkInventory.keySet()) {
            System.out.println(capitalizeFirst(brand) + ": " + energyDrinkInventory.get(brand) + "\n");
        }
    }

    public void printProductBrand(String bName) {
        String brandName = bName.toLowerCase();
        if (checkBrand(brandName)) {
            for (String flavour : energyDrinkInventory.get(brandName).keySet()) {
                System.out.println(capitalizeFirst(flavour) + ": " + energyDrinkInventory.get(brandName).get(flavour));
            }
        }
    }

    public void printProductBrandAndFlavour(String bName, String flName) {
        String brandName = bName.toLowerCase();
        String flavour = flName.toLowerCase();
        if (checkBrand(brandName) && checkFlavour(flavour)) {
            System.out.println(energyDrinkInventory.get(brandName).get(flavour));
        }
    }

    private Boolean checkBrand(String bName) {
        String brandName = bName.toLowerCase();
        List<String> lowCaseAvailableBrands = new ArrayList<>();

        for (String name : availableBrands) {
            lowCaseAvailableBrands.add(name.toLowerCase());
        }

        try {
            if (lowCaseAvailableBrands.contains(brandName)) {
                return true;

            } else {
                throw new IllegalArgumentException("There is no brand with that name");
            }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private Boolean checkFlavour(String fName) {
        String flavourName = fName.toLowerCase();
        List<String> lowCaseAvailableFlavours = new ArrayList<>();

        for (String flavour : availableFlavours) {
            lowCaseAvailableFlavours.add(flavour.toLowerCase());
        }

        try {
            if (lowCaseAvailableFlavours.contains(flavourName)) {
                return true;
            } else {
                throw new IllegalArgumentException("There are no such flavours!");
            }

        } catch (Exception e) {
            return false;
        }
    }

    private Boolean checkQuantity(Map<String, Integer> inventoryDetails, int amount) {
        return inventoryDetails.get("quantity") >= amount;
    }

    private String capitalizeFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

}
