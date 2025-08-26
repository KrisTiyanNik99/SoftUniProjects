package picking.entities.places;

import picking.common.ExceptionMessages;
import picking.entities.pickers.Picker;

import java.util.ArrayList;
import java.util.Collection;

public class PlaceImpl implements Place {
    private String name;
    private Collection<String> mushrooms;
    private Collection<Picker> pickers;

    public PlaceImpl(String name) {
        setName(name);
        mushrooms = new ArrayList<>();
        pickers = new ArrayList<>();
    }

    public void setName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessages.PLACE_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<String> getMushrooms() {
        return mushrooms;
    }

    public void setMushrooms(Collection<String> mushrooms) {
        this.mushrooms = mushrooms;
    }

    @Override
    public Collection<Picker> getPickers() {
        return pickers;
    }

    public void setPickers(Collection<Picker> pickers) {
        this.pickers = pickers;
    }
}
