package furnitureFactory.entities.wood;

public abstract class BaseWood implements Wood {
    private int woodQuantity;

    public BaseWood(int woodQuantity) {
        setWoodQuantity(woodQuantity);
    }

    @Override
    public int getWoodQuantity() {
        return woodQuantity;
    }

    protected void setWoodQuantity(int woodQuantity) {
        this.woodQuantity = Math.max(woodQuantity, 0);
    }
}
