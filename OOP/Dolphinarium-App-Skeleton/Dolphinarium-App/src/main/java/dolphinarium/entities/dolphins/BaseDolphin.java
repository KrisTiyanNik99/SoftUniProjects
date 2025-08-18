package dolphinarium.entities.dolphins;

import dolphinarium.entities.foods.Food;

import static dolphinarium.common.ExceptionMessages.DOLPHIN_NAME_NULL_OR_EMPTY;

public abstract class BaseDolphin implements Dolphin {
    private static final int DECREASED_ENERGY_VALUE = 10;

    private String name;
    private int energy;

    public BaseDolphin(String name, int energy) {
        setName(name);
        setEnergy(energy);
    }

    @Override
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new NullPointerException(DOLPHIN_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    protected void setEnergy(int energy) {
        this.energy = Math.max(energy, 0);
    }

    @Override
    public void eat(Food food) {
        setEnergy(getEnergy() + food.getCalories());
    }

    @Override
    public void jump() {
        setEnergy(getEnergy() - DECREASED_ENERGY_VALUE);
    }
}
