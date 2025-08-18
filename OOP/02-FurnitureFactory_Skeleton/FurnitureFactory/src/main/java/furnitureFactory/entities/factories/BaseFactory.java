package furnitureFactory.entities.factories;

import furnitureFactory.entities.workshops.Workshop;

import java.util.ArrayList;
import java.util.Collection;

import static furnitureFactory.common.ExceptionMessages.FACTORY_NAME_NULL_OR_EMPTY;
import static furnitureFactory.common.ExceptionMessages.INVALID_WORKSHOP;

public abstract class BaseFactory implements Factory {
    private String name;
    private final Collection<Workshop> workshops;
    private final Collection<Workshop> removedWorkshops;

    public BaseFactory(String name) {
        setName(name);
        workshops = new ArrayList<>();
        removedWorkshops = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new NullPointerException(FACTORY_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public void addWorkshop(Workshop workshop) {
        if (workshop == null) {
            throw new IllegalArgumentException(INVALID_WORKSHOP);
        }

        workshops.add(workshop);
    }

    @Override
    public Collection<Workshop> getWorkshops() {
        return workshops;
    }

    @Override
    public Collection<Workshop> getRemovedWorkshops() {
        return removedWorkshops;
    }
}
