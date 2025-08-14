package athletics.entity.athlete;

public class Thrower extends BaseAthlete {
    public Thrower(String name) {
        super(name, 90);
    }

    @Override
    public void compete() {
        setPerformance(getPerformance() + 1);
        setStamina(getStamina() - 12);
    }

    @Override
    public void train() {
        setStamina(getStamina() + 8);
    }
}
