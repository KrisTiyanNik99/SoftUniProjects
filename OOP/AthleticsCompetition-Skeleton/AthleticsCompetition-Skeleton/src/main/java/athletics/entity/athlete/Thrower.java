package athletics.entity.athlete;

public class Thrower extends BaseAthlete {
    private static final int STARTER_STAMINA_VALUE = 90;
    private static final int DECREASE_STAMINA_VALUE = 12;
    private static final int GET_STAMINA_PER_TRAINING = 8;

    public Thrower(String name) {
        super(name, STARTER_STAMINA_VALUE);
    }

    @Override
    public void compete() {
        setPerformance(getPerformance() + ADD_PERFORMANCE_VALUE);
        setStamina(getStamina() - DECREASE_STAMINA_VALUE);
    }

    @Override
    public void train() {
        setStamina(getStamina() + GET_STAMINA_PER_TRAINING);
    }
}
