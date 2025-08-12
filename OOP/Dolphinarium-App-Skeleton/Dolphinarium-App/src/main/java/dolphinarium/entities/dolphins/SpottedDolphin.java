package dolphinarium.entities.dolphins;

public class SpottedDolphin extends BaseDolphin {
    private static final int DECREASED_ENERGY_VALUE = 90;

    public SpottedDolphin(String name, int energy) {
        super(name, energy);
    }

    @Override
    public void jump() {
        super.jump();
        setEnergy(getEnergy() - DECREASED_ENERGY_VALUE);
    }
}
