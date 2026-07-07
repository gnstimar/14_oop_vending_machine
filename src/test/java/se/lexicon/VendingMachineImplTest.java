package se.lexicon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class VendingMachineImplTest {
    VendingMachineImpl vendingMachineImpl = new VendingMachineImpl();

    @Test
    @DisplayName("Insert valid coin into the machine")
    @Order(1)
    void insertCoin() {
        int coinToInsert = 10;
        vendingMachineImpl.insertCoin(coinToInsert);
        Assertions.assertEquals(10, vendingMachineImpl.getBalance());
    }

    @Test
    @DisplayName("Reject invalid coin")
    @Order(2)
    void rejectInvalidCoin() {
        int coinToInsert = 17;
        vendingMachineImpl.insertCoin(coinToInsert);
        Assertions.assertEquals(0, vendingMachineImpl.getBalance());
    }

    @Test
    @DisplayName("Return change")
    @Order(6)
    void returnChange() {
        vendingMachineImpl.setBalance(50);
        vendingMachineImpl.returnRemaining();
        Assertions.assertEquals(0, vendingMachineImpl.getBalance());
    }

    @Test
    @DisplayName("There is nothing to return")
    @Order(7)
    void noReturn() {
        vendingMachineImpl.setBalance(0);
        vendingMachineImpl.returnRemaining();
        Assertions.assertEquals(0, vendingMachineImpl.getBalance());
    }

    @Test
    @DisplayName("getAllProducts()")
    @Order(8)
    void getAllProducts() {
        Assertions.assertEquals(3, vendingMachineImpl.getProducts().size());
    }

    @Test
    @DisplayName("Successful purchase")
    @Order(3)
    void purchaseProduct() {
        vendingMachineImpl.insertCoin(20);
        Product productToPurchase = vendingMachineImpl.purchaseProduct(2);
        Assertions.assertEquals(0, vendingMachineImpl.getBalance());
        Assertions.assertEquals(2, productToPurchase.getQuantity());
        Assertions.assertEquals("Cola", productToPurchase.getName());
    }

    @Test
    @DisplayName("Purchase Fails — Out of Stock")
    @Order(5)
    void purchaseOutOfStock() {
        vendingMachineImpl.getProducts().get(1).setQuantity(0);
        vendingMachineImpl.insertCoin(20);
        Product productToPurchase = vendingMachineImpl.purchaseProduct(2);
        Assertions.assertNull(productToPurchase);
    }

    @Test
    @DisplayName("Purchase Fails — Insufficient Balance")
    @Order(4)
    void purchaseInsufficientBalance() {
        vendingMachineImpl.setBalance(5);
        Product productToPurchase = vendingMachineImpl.purchaseProduct(2);
        Assertions.assertNull(productToPurchase);
    }
}
