package se.lexicon;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuManager {
    private final VendingMachineImpl VENDINGMACHINEIMPL;
    private final Scanner SCANNER;
    private final String NAME = "Lexicon Vending Machine";
    private final String[] FUNCTIONALITIES = {"Insert coin", "Select product", "Return change", "Exit"};

    public MenuManager(VendingMachineImpl vendingMachineImpl) {
        this.VENDINGMACHINEIMPL = vendingMachineImpl;
        this.SCANNER = new Scanner(System.in);
    }

    public void start() {
        displayMenu();
        printBalance();
        boolean isRunning = true;

        while (isRunning) {
            int choice = readMenuItemNumber("Choose an option: ");
            switch (choice) {
                case 1 -> insertCoin();
                case 2 -> selectProduct();
                case 3 -> returnChange();
                case 4 -> {
                    returnChange();
                    IO.println("Goodbye!");
                    isRunning = false;
                    return;
                }
                default -> IO.println("Invalid option! Please try again");
            }
            printBalance();
        }
    }

    public void displayMenu() {
        IO.println("Welcome to " + NAME);
        lineSeparator();
        for (Product product : VENDINGMACHINEIMPL.getProducts()) {
            System.out.printf("[%-1s] %-10s - %-3d kr %-25s  Stock: %-2d%n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.describe(),
                    product.getQuantity());
        }
        lineSeparator();
        IO.println("Actions: ");
        for (int i = 0; i < FUNCTIONALITIES.length; i++) {
            System.out.printf("%-1d. %-15s%n", i + 1, FUNCTIONALITIES[i]);
        }
    }

    private static void lineSeparator() {
        for (int i = 0; i < 59; i++) {
            IO.print("-");
        }
        IO.println("-");
    }

    private int readMenuItemNumber(String message) {
        while (true) {
            try {
                IO.print(message);
                int number = SCANNER.nextInt();
                SCANNER.nextLine();
                if (number >= 1 && number <= FUNCTIONALITIES.length) {
                    return number;
                } else {
                    IO.println("ERROR: The number is out of range! It must be between 1 and " + FUNCTIONALITIES.length + ". ");
                }
            } catch (InputMismatchException e) {
                IO.println("ERROR: Invalid input! Please enter number only.");
                SCANNER.nextLine();
            }
        }
    }

    public void printBalance() {
        System.out.printf("Balance: %2d kr%n", VENDINGMACHINEIMPL.getBalance());
    }

    private void insertCoin() {
        int coinValue = readInt("Insert coin: ", Integer.MAX_VALUE);
        VENDINGMACHINEIMPL.insertCoin(coinValue);
    }

    private int readInt(String message, int max) {
        while (true) {
            try {
                IO.print(message);
                int number = SCANNER.nextInt();
                SCANNER.nextLine();
                if (number >= 1 && number <= max) {
                    return number;
                } else {
                    IO.println("Error: The number is out of range! It must be between 1 and " + max + ".");
                }
            } catch (InputMismatchException e) {
                IO.println("ERROR: Invalid input! Please enter number only.");
                SCANNER.nextLine();
            }
        }
    }

    private void selectProduct() {
        int productId = readInt("Select product: ", VENDINGMACHINEIMPL.getProducts().size());
        Product productToPurchase = VENDINGMACHINEIMPL.purchaseProduct(productId);
        if (productToPurchase == null) {
            IO.println("Purchase failed! (Insufficient balance or out of stock.)");
        } else {
            IO.println("Dispensing: " + productToPurchase.getName() + " " + productToPurchase.describe());
        }

    }

    private void returnChange() {
        VENDINGMACHINEIMPL.returnRemaining();
    }

}
