package dolphinarium.entities.dolphins;

public class SpinnerDolphin extends BaseDolphin {
    private static final int DECREASED_ENERGY_VALUE = 40;

    public SpinnerDolphin(String name, int energy) {
        super(name, energy);
    }

    @Override
    public void jump() {
        super.jump();
        setEnergy(getEnergy() - DECREASED_ENERGY_VALUE);
    }
}
