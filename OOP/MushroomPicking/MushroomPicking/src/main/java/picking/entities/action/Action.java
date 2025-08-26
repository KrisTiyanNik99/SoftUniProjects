package picking.entities.action;

import picking.entities.pickers.Picker;
import picking.entities.places.Place;

import java.util.Collection;

public interface Action {

    void startPicking(Place place);
}
