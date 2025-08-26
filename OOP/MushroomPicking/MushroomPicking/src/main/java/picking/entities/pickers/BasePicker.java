package picking.entities.pickers;

import picking.common.ExceptionMessages;
import picking.entities.bag.Bag;
import picking.entities.bag.BagImpl;

public abstract class BasePicker implements Picker {
    private static final int DECREASE_VITALITY_VALUE = 10;

    private String name;
    private int vitality;
    private Bag bag;

    public BasePicker(String name, int vitality) {
        setName(name);
        setVitality(vitality);
        bag = new BagImpl();
    }

    public void setName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessages.PICKER_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setVitality(int vitality) {
        if (vitality < 0) {
            throw new IllegalArgumentException(ExceptionMessages.PICKER_VITALITY_LESS_THAN_ZERO);
        }

        this.vitality = vitality;
    }

    @Override
    public int getVitality() {
        return vitality;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    @Override
    public Bag getBag() {
        return bag;
    }

    @Override
    public void pick() {
        int newVitality = getVitality() - DECREASE_VITALITY_VALUE;
        decreaseVitality(newVitality);
    }

    private void decreaseVitality(int vitality) {
        this.vitality = Math.max(vitality, 0);
    }
}
