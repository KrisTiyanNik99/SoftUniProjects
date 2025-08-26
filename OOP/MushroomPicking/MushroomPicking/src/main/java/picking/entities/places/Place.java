package picking.entities.places;

import picking.entities.pickers.Picker;

import java.util.Collection;

public interface Place {

    String getName();
    Collection<String> getMushrooms();
    Collection<Picker> getPickers();
}
