package dolphinarium.entities.pools;

public class ShallowWaterPool extends BasePool {
    private static final int POOL_CAPACITY = 2;

    public ShallowWaterPool(String name) {
        super(name, POOL_CAPACITY);
    }
}
