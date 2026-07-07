package se.lexicon;

public class Snack extends Product {
    private int weightGrams;

    public Snack(int id, String name, int price, int quantity, int weightGrams) {
        super(id, name, price, quantity);
        this.weightGrams = weightGrams;
    }

    @Override
    public String describe() {
        return "(" + this.getClass().getSimpleName() + ", " + weightGrams + " g)";
    }
}
