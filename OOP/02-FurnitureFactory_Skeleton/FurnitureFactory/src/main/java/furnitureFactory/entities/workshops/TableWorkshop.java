package furnitureFactory.entities.workshops;

public class TableWorkshop extends BaseWorkshop {
    private static final int WOOD_QUANTITY_REDUCE_VALUE = 50;

    public TableWorkshop(int woodQuantity) {
        super(woodQuantity, WOOD_QUANTITY_REDUCE_VALUE);
    }

    @Override
    public void produce() {
        int woodQuantity = getWoodQuantity() - WOOD_QUANTITY_REDUCE_VALUE;

        setWoodQuantity(woodQuantity);
        setProducedFurnitureCount(getProducedFurnitureCount() + FURNITURE_COUNT_INCREASE);
    }
}
