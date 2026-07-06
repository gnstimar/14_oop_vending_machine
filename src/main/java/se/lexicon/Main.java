package se.lexicon;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Product chips = new Snack(1, "Chips", 15, 5, 130);
        Product cola = new Beverage(2, "Cola", 20, 3, 330);
        Product apple = new Fruit(3, "Apple", 10, 8, "Sweden");

        chips.describe();
        cola.describe();
        apple.describe();
    }
}
