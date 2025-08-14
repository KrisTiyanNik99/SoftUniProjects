package athletics.entity.athlete;

public class Runner extends BaseAthlete {
    public Runner(String name) {
        super(name, 80);
    }

    @Override
    public void compete() {
        setPerformance(getPerformance() + 1);
        setStamina(getStamina() - 15);
    }

    @Override
    public void train() {
        setStamina(getStamina() + 10);
    }
}
