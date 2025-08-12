package dolphinarium.entities.dolphins;

public class BottleNoseDolphin extends BaseDolphin {
    private static final int DECREASED_ENERGY_VALUE = 190;
    
    public BottleNoseDolphin(String name, int energy) {
        super(name, energy);
    }

    @Override
    public void jump() {
        super.jump();
        setEnergy(getEnergy() - DECREASED_ENERGY_VALUE);
    }
}
