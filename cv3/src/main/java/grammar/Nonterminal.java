package grammar;

import java.util.*;

public class Nonterminal extends Symbol {

    public Nonterminal(String name) {
        super(name);
    }

    public List<Rule> getRules() {
        return rules;
    }

    void addRule(Rule rule) {
        // assert rule.getLHS() == this;
        rules.add(rule);
    }

    private final List rules = new ArrayList();
}