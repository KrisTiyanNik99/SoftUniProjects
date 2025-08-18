package furnitureFactory.entities.workshops;

import furnitureFactory.entities.wood.Wood;

import static furnitureFactory.common.ExceptionMessages.INVALID_WOOD;

public abstract class BaseWorkshop implements Workshop {
    protected static final int FURNITURE_COUNT_INCREASE = 1;

    private int woodQuantity;
    private int producedFurnitureCount;
    private final int woodQuantityReduceFactor;

    public BaseWorkshop(int woodQuantity, int woodQuantityReduceFactor) {
        setWoodQuantity(woodQuantity);
        this.woodQuantityReduceFactor = woodQuantityReduceFactor;
    }

    @Override
    public void addWood(Wood wood) {
        if (wood == null) {
            throw new IllegalArgumentException(INVALID_WOOD);
        }

        woodQuantity += wood.getWoodQuantity();
    }

    @Override
    public int getWoodQuantity() {
        return woodQuantity;
    }

    protected void setWoodQuantity(int woodQuantity) {
        this.woodQuantity = Math.max(woodQuantity, 0);
    }

    @Override
    public int getProducedFurnitureCount() {
        return producedFurnitureCount;
    }

    protected void setProducedFurnitureCount(int producedFurnitureCount) {
        this.producedFurnitureCount = Math.max(producedFurnitureCount, 0);
    }

    @Override
    public int getWoodQuantityReduceFactor() {
        return woodQuantityReduceFactor;
    }
}
