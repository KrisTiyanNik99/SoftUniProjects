package dolphinarium.entities.foods;

public abstract class BaseFood implements Food {
    private int calories;

    public BaseFood(int calories) {
        this.calories = calories;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
