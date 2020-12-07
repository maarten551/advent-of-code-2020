package nl.bobbeldijk.day7;

public class BagRule {
    private final int amountOfBags;
    private final Bag bag;

    public BagRule(int amountOfBags, Bag bag) {
        this.amountOfBags = amountOfBags;
        this.bag = bag;
    }

    public int getAmountOfBags() {
        return amountOfBags;
    }

    public Bag getBag() {
        return bag;
    }
}
