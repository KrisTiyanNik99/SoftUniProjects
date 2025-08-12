package furnitureFactory.core;

import furnitureFactory.entities.factories.Factory;

public class ControllerImpl implements Controller {
    //TODO Implement all the methods
    @Override
    public String buildFactory(String factoryType, String factoryName) {
        return null;
    }

    @Override
    public Factory getFactoryByName(String factoryName) {
        return null;
    }

    @Override
    public String buildWorkshop(String workshopType, int woodCapacity) {
        return null;
    }

    @Override
    public String addWorkshopToFactory(String factoryName, String workshopType) {
        return null;
    }


    @Override
    public String buyWoodForFactory(String woodType) {
        return null;
    }

    @Override
    public String addWoodToWorkshop(String factoryName, String workshopType, String woodType) {
        return null;
    }

    @Override
    public String produceFurniture(String factoryName) {
        return null;
    }

    @Override
    public String getReport() {
        return null;
    }
}
