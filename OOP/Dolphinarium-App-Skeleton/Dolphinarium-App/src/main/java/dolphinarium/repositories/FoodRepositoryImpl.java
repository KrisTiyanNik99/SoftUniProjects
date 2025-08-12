package dolphinarium.repositories;

import dolphinarium.entities.foods.Food;

import java.util.ArrayList;
import java.util.Collection;

public class FoodRepositoryImpl implements FoodRepository {
    private final Collection<Food> foods;

    public FoodRepositoryImpl() {
        foods = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public boolean remove(Food food) {
        return foods.remove(food);
    }

    @Override
    public Food findByType(String type) {
        return foods.stream()
                .filter(e -> type.equals(e.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);
    }
}
