package se.lexicon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VendingMachineImplTest {
    VendingMachineImpl vendingMachineImpl = new VendingMachineImpl();

    @Test
    @DisplayName("Insert valid coin into the machine")
    void insertCoin() {
        int coinToInsert = 10;
        vendingMachineImpl.insertCoin(coinToInsert);
        Assertions.assertEquals(10 , vendingMachineImpl.getBalance());
    }

    @Test
    @DisplayName("Reject invalid coin")
    void rejectInvalidCoin() {
        int coinToInsert = 17;
        vendingMachineImpl.insertCoin(coinToInsert);
        Assertions.assertEquals(0 , vendingMachineImpl.getBalance());
    }

    @Test
    @DisplayName("Return change")
    void returnChange() {
        vendingMachineImpl.setBalance(50);
        vendingMachineImpl.returnRemaining();
        Assertions.assertEquals(0 , vendingMachineImpl.getBalance());
    }

    @Test
    @DisplayName("There is nothing to return")
    void noReturn() {
        vendingMachineImpl.setBalance(0);
        vendingMachineImpl.returnRemaining();
        Assertions.assertEquals(0 , vendingMachineImpl.getBalance());
    }
}
