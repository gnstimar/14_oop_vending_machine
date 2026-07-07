package se.lexicon;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {
    private int balance = 0;
    private List<Product> products = new ArrayList<>();

    public VendingMachineImpl() {
        Product chips = new Snack(1, "Chips", 15, 5, 130);
        Product cola = new Beverage(2, "Cola", 20, 3, 330);
        Product apple = new Fruit(3, "Apple", 10, 8, "Sweden");
        products.add(chips);
        products.add(cola);
        products.add(apple);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public void insertCoin(int coinValue) {
        if (EnumSet.allOf(Coin.class).stream().anyMatch(value -> value.value() == coinValue)) {
            this.balance += coinValue;
        } else {
            IO.println("ERROR: Invalid coin. Only 1, 2, 5, 10, 20, 50 kr accepted.");
        }
    }

    @Override
    public void returnRemaining() {
        if (balance == 0) {
            IO.println("No balance to return.");
        } else {
            IO.println("Change returned: " + balance + " kr");
            setBalance(0);
        }
    }

    @Override
    public Product purchaseProduct(int id) {
        Product productToPurchase = products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
        if (productToPurchase == null) {
            IO.println("ERROR: Invalid id, there is no such product.");
            return null;
        } else if (productToPurchase.getQuantity() == 0) {
            IO.println("ERROR: Stock is empty from this product. Try an other product.");
            return null;
        } else if (productToPurchase.getPrice() > balance) {
            IO.println("ERROR: Insufficient balance. " + productToPurchase.getName() + " costs " + productToPurchase.getPrice() + " kr. You miss " + (productToPurchase.getPrice() - balance) + " kr.");
            return null;
        } else {
            balance -= productToPurchase.getPrice();
            productToPurchase.lowerQuantity();
            if (balance > 0) {
                returnRemaining();
            }
            return productToPurchase;
        }
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

}
