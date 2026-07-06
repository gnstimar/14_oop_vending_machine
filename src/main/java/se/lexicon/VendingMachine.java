package se.lexicon;

import java.util.List;

public interface VendingMachine {
    void insertCoin(int coinValue);
    void returnRemaining();
    Product purchaseProduct(int id);
    List<Product> getProducts();
}
