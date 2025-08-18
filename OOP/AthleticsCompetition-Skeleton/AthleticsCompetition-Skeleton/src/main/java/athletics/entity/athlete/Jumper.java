package athletics.entity.athlete;

public class Jumper extends BaseAthlete {
    private static final int STARTER_STAMINA_VALUE = 70;
    private static final int DECREASE_STAMINA_VALUE = 20;
    private static final int GET_STAMINA_PER_TRAINING = 12;

    public Jumper(String name) {
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
