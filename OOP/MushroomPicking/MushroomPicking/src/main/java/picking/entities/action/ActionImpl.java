package picking.entities.action;

import picking.entities.pickers.Picker;
import picking.entities.places.Place;

import java.util.Iterator;

public class ActionImpl implements Action {
    @Override
    public void startPicking(Place place) {
        Iterator<String> mushrooms = place.getMushrooms().iterator();

        for (Picker currentPicker : place.getPickers()) {
            if (currentPicker.getVitality() <= 0) {
                continue;
            }

            pickMushrooms(mushrooms, currentPicker);
        }
    }

    private void pickMushrooms(Iterator<String> mushrooms, Picker picker) {
        while (picker.getVitality() > 0 && mushrooms.hasNext()) {
            String currentMushroom = String.valueOf(mushrooms.next());
            picker.getBag().getMushrooms().add(currentMushroom);

            if (currentMushroom.startsWith("poisonous")) {
                picker.getBag().getMushrooms().clear();
            }

            picker.pick();
            mushrooms.remove();
        }
    }
}
