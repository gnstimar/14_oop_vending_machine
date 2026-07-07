package se.lexicon;

public class VendingMachineApp {
    static void main() {
        VendingMachineImpl vendingMachineImpl = new VendingMachineImpl();
        MenuManager menuManager = new MenuManager(vendingMachineImpl);

        menuManager.start();
    }
}
