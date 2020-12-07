package nl.bobbeldijk.day7;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private final String type;
    private List<BagRule> rules = new ArrayList<>();

    public Bag(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setRules(List<BagRule> rules) {
        this.rules = rules;
    }

    public List<BagRule> getRules() {
        return rules;
    }
}
