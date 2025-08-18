package furnitureFactory.core;

import furnitureFactory.common.ConstantMessages;
import furnitureFactory.common.ExceptionMessages;
import furnitureFactory.entities.factories.AdvancedFactory;
import furnitureFactory.entities.factories.Factory;
import furnitureFactory.entities.factories.OrdinaryFactory;
import furnitureFactory.entities.wood.OakWood;
import furnitureFactory.entities.wood.Wood;
import furnitureFactory.entities.workshops.DeckingWorkshop;
import furnitureFactory.entities.workshops.TableWorkshop;
import furnitureFactory.entities.workshops.Workshop;
import furnitureFactory.repositories.WoodRepository;
import furnitureFactory.repositories.WoodRepositoryImpl;
import furnitureFactory.repositories.WorkshopRepository;
import furnitureFactory.repositories.WorkshopRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static furnitureFactory.common.ConstantMessages.*;
import static furnitureFactory.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private final WoodRepository woodRepository;
    private final WorkshopRepository workshopRepository;
    private final Collection<Factory> factories;

    public ControllerImpl() {
        woodRepository = new WoodRepositoryImpl();
        workshopRepository = new WorkshopRepositoryImpl();
        factories = new ArrayList<>();
    }

    //TODO Implement all the methods
    @Override
    public String buildFactory(String factoryType, String factoryName) {
        Factory factory;
        if (factoryType.equals(OrdinaryFactory.class.getSimpleName())) {
            factory = new OrdinaryFactory(factoryName);
        } else if (factoryType.equals(AdvancedFactory.class.getSimpleName())) {
            factory = new AdvancedFactory(factoryName);
        } else {
            throw new IllegalArgumentException(INVALID_FACTORY_TYPE);
        }

        List<Factory> identityFactories = factories.stream()
                .filter(e -> factoryName.equals(e.getName()))
                .toList();

        if (!identityFactories.isEmpty()) {
            throw new NullPointerException(FACTORY_EXISTS);
        }

        factories.add(factory);
        return String.format(SUCCESSFULLY_BUILD_FACTORY_TYPE, factoryType, factoryName);
    }

    @Override
    public Factory getFactoryByName(String factoryName) {
        return factories.stream()
                .filter(e -> factoryName.equals(e.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String buildWorkshop(String workshopType, int woodCapacity) {
        Workshop workshop;
        if (workshopType.equals(TableWorkshop.class.getSimpleName())) {
            workshop = new TableWorkshop(woodCapacity);
        } else if (workshopType.equals(DeckingWorkshop.class.getSimpleName())) {
            workshop = new DeckingWorkshop(woodCapacity);
        } else {
            throw new IllegalArgumentException(INVALID_WORKSHOP_TYPE);
        }

        workshopRepository.add(workshop);
        return String.format(SUCCESSFULLY_BUILD_WORKSHOP_TYPE, workshopType);
    }

    @Override
    public String addWorkshopToFactory(String factoryName, String workshopType) {
        Workshop workshop = workshopRepository.findByType(workshopType);
        if (workshop == null) {
            throw new NullPointerException(String.format(NO_WORKSHOP_FOUND, workshopType));
        }

        Factory factory = getFactoryByName(factoryName);
        List<String> workshops = factory.getWorkshops()
                .stream()
                .map(e -> e.getClass().getSimpleName())
                .filter(e -> e.equals(workshopType))
                .toList();

        if (!workshops.isEmpty()) {
            throw new IllegalArgumentException(WORKSHOP_EXISTS);
        }

        if (factory instanceof OrdinaryFactory && !(workshop instanceof TableWorkshop)) {
            return NON_SUPPORTED_WORKSHOP;
        }

        if (factory instanceof AdvancedFactory && !(workshop instanceof DeckingWorkshop)) {
            return NON_SUPPORTED_WORKSHOP;
        }

        factory.addWorkshop(workshop);
        return String.format(SUCCESSFULLY_ADDED_WORKSHOP_IN_FACTORY, workshopType, factoryName);
    }


    @Override
    public String buyWoodForFactory(String woodType) {
        if (!woodType.equals(OakWood.class.getSimpleName())) {
            throw new IllegalArgumentException(INVALID_WOOD_TYPE);
        }

        woodRepository.add(new OakWood());
        return String.format(SUCCESSFULLY_BOUGHT_WOOD_FOR_FACTORY, woodType);
    }

    @Override
    public String addWoodToWorkshop(String factoryName, String workshopType, String woodType) {
        Factory factory = getFactoryByName(factoryName);

        // Тук може да се очаква един workshop
        Workshop workshop = factory.getWorkshops()
                .stream()
                .filter(e -> workshopType.equals(e.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);

        if (workshop == null) {
            throw new NullPointerException(NO_WORKSHOP_ADDED);
        }

        Wood wood = woodRepository.findByType(woodType);
        if (wood == null) {
            throw new NullPointerException(String.format(NO_WOOD_FOUND, woodType));
        }

        workshop.addWood(wood);
        woodRepository.remove(wood);

        return String.format(SUCCESSFULLY_ADDED_WOOD_IN_WORKSHOP, woodType, workshopType);
    }

    @Override
    public String produceFurniture(String factoryName) {
        Factory factory = getFactoryByName(factoryName);
        List<Workshop> workshops = factory.getWorkshops().stream().toList();

        if (workshops.isEmpty()) {
            throw new NullPointerException(String.format(THERE_ARE_NO_WORKSHOPS, factoryName));
        }

        int producedFurnitureByFactory = 0;
        for (Workshop workshop : workshops) {
            while (workshop.getWoodQuantity() > workshop.getWoodQuantityReduceFactor()
                    && workshop.getWoodQuantity() > 0) {
                workshop.produce();
                producedFurnitureByFactory++;
            }

            String workshopMessage;
            if (workshop.getWoodQuantity() <= 0) {
                workshopMessage = String.format(WORKSHOP_STOPPED_WORKING, workshop.getClass().getSimpleName());
                factory.getWorkshops().remove(workshop);
                factory.getRemovedWorkshops().add(workshop);
                workshopRepository.remove(workshop);

                System.out.println(workshopMessage);
            } else {
                System.out.println(INSUFFICIENT_WOOD);
            }
        }

        return getStringByProducedFurniture(factoryName, producedFurnitureByFactory);
    }

    @Override
    public String getReport() {

        StringBuilder report = new StringBuilder();

        for (Factory factory : factories) {
            report.append("Production by ").append(factory.getName()).append(" factory:\n");
            if (factory.getWorkshops().isEmpty() && factory.getRemovedWorkshops().isEmpty()) {
                report.append("  No workshops were added to produce furniture.\n");
            }

            getAllWorkshopsInfo(factory.getWorkshops(), report);
            getAllWorkshopsInfo(factory.getRemovedWorkshops(), report);
        }
        return report.toString().trim();
    }

    private static void getAllWorkshopsInfo(Collection<Workshop> factory, StringBuilder report) {
        for (Workshop workshop : factory) {
            report.append(String.format("  %s: %d furniture produced\n",
                    workshop.getClass().getSimpleName(),
                    workshop.getProducedFurnitureCount()));
        }
    }

    private String getStringByProducedFurniture(String factoryName, int producedFurnitureByFactory) {
        if (producedFurnitureByFactory <= 0) {
            return String.format(FACTORY_DO_NOT_PRODUCED, factoryName);
        } else {
            return String.format(SUCCESSFUL_PRODUCTION, producedFurnitureByFactory, factoryName);
        }
    }
}
