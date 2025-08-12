package dolphinarium.entities.pools;

public class DeepWaterPool extends BasePool {
    private static final int POOL_CAPACITY = 5;
    public DeepWaterPool(String name) {
        super(name, POOL_CAPACITY);
    }
}
