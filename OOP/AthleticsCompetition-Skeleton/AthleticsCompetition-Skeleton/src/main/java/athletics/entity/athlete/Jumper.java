package athletics.entity.athlete;

public class Jumper extends BaseAthlete {
    public Jumper(String name) {
        super(name, 70);
    }

    @Override
    public void compete() {
        setPerformance(getPerformance() + 1);
        setStamina(getStamina() - 20);
    }

    @Override
    public void train() {
        setStamina(getStamina() + 12);
    }
}
