package furnitureFactory.entities.workshops;

public class DeckingWorkshop extends BaseWorkshop {
    private static final int WOOD_QUANTITY_REDUCE_VALUE = 150;

    public DeckingWorkshop(int woodQuantity) {
        super(woodQuantity, WOOD_QUANTITY_REDUCE_VALUE);
    }

    @Override
    public void produce() {
        int woodQuantity = getWoodQuantity() - WOOD_QUANTITY_REDUCE_VALUE;

        setWoodQuantity(woodQuantity);
        setProducedFurnitureCount(getProducedFurnitureCount() + FURNITURE_COUNT_INCREASE);
    }
}
