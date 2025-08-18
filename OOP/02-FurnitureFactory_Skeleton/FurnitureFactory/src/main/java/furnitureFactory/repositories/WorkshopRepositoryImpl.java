package furnitureFactory.repositories;

import furnitureFactory.entities.workshops.Workshop;

import java.util.ArrayList;
import java.util.Collection;

import static furnitureFactory.common.ExceptionMessages.*;

public class WorkshopRepositoryImpl implements WorkshopRepository {
    private final Collection<Workshop> workshops;

    public WorkshopRepositoryImpl() {
        workshops = new ArrayList<>();
    }

    @Override
    public void add(Workshop workshop) {
        if (workshop.getWoodQuantity() <= 0) {
            throw new IllegalArgumentException(WORKSHOP_WOOD_QUANTITY_BELOW_OR_EQUAL_ZERO);
        }

        for (Workshop currentWorkshop : workshops) {
            if (workshop.getClass().getSimpleName().equals(currentWorkshop.getClass().getSimpleName())) {
                throw new IllegalArgumentException(WORKSHOP_EXISTS_IN_REPOSITORY);
            }
        }

        workshops.add(workshop);
    }

    @Override
    public boolean remove(Workshop workshop) {
        if (workshops.isEmpty()) {
            throw new NullPointerException(INVALID_WORKSHOP);
        }

        return workshops.remove(workshop);
    }

    @Override
    public Workshop findByType(String type) {
        return workshops.stream()
                .filter(e -> type.equals(e.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);
    }
}
