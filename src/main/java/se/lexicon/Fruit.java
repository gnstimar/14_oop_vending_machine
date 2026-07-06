package se.lexicon;

public class Fruit extends Product {
    private String origin;

    public Fruit(int id, String name, int price, int quantity, String origin) {
        super(id, name, price, quantity);
        this.origin = origin;
    }

    @Override
    public void describe() {
        IO.println(getName() + " (" + this.getClass().getSimpleName() + ", Origin: " + origin + ")");
    }
}
