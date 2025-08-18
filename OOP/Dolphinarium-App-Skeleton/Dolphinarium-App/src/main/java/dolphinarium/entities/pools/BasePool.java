package dolphinarium.entities.pools;

import dolphinarium.entities.dolphins.Dolphin;
import dolphinarium.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;

import static dolphinarium.common.ExceptionMessages.*;

public abstract class BasePool implements Pool {
    private String name;
    private int capacity;
    private final Collection<Food> foods;
    private final Collection<Dolphin> dolphins;

    public BasePool(String name, int capacity) {
        setName(name);
        setCapacity(capacity);
        foods = new ArrayList<>();
        dolphins = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new NullPointerException(POOL_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    protected void setCapacity(int capacity) {
        this.capacity = Math.max(capacity, 0);
    }

    @Override
    public Collection<Dolphin> getDolphins() {
        return dolphins;
    }

    @Override
    public Collection<Food> getFoods() {
        return foods;
    }

    @Override
    public void addDolphin(Dolphin dolphin) {
        if (dolphins.size() >= capacity) {
            throw new IllegalArgumentException(NOT_ENOUGH_CAPACITY);
        }

        if (dolphin.getEnergy() <= 0) {
            throw new IllegalArgumentException(DOLPHIN_ENERGY_BELOW_OR_EQUAL_ZERO);
        }

        dolphins.add(dolphin);
    }

    @Override
    public void removeDolphin(Dolphin dolphin) {
        dolphins.remove(dolphin);
    }

    @Override
    public void addFood(Food food) {
        foods.add(food);
    }
}
