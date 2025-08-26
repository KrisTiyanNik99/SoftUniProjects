package picking.core;

import picking.common.ConstantMessages;
import picking.common.ExceptionMessages;
import picking.entities.action.Action;
import picking.entities.action.ActionImpl;
import picking.entities.pickers.ExperiencedPicker;
import picking.entities.pickers.JuniorPicker;
import picking.entities.pickers.Picker;
import picking.entities.places.Place;
import picking.entities.places.PlaceImpl;
import picking.repositories.PlaceRepository;
import picking.repositories.Repository;

import java.util.List;

//TODO - Implement all the methods
public class ControllerImpl implements Controller {
    private final Repository<Place> placesRepository;

    public ControllerImpl() {
        placesRepository = new PlaceRepository();
    }

    @Override
    public String addPlace(String placeName, String... mushrooms) {
        Place place = new PlaceImpl(placeName);
        place.getMushrooms().addAll(List.of(mushrooms));
        placesRepository.add(place);

        return String.format(ConstantMessages.PLACE_ADDED, placeName);
    }

    @Override
    public String addPicker(String placeName, String pickerType, String pickerName) {
        Place place = getPlaceByName(placeName);
        if (place == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NON_EXISTING_PLACE, placeName));
        }

        Picker picker = getPickerFromPlace(pickerName, place);
        if (picker != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PICKER);
        }

        if (pickerType.equals(JuniorPicker.class.getSimpleName())) {
            picker = new JuniorPicker(pickerName);
        } else if (pickerType.equals(ExperiencedPicker.class.getSimpleName())) {
            picker = new ExperiencedPicker(pickerName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PICKER);
        }

        place.getPickers().add(picker);
        return String.format(ConstantMessages.PICKER_ADDED, pickerType, pickerName);
    }

    @Override
    public String startPicking(String placeName) {
        Place place = getPlaceByName(placeName);
        if (place == null) {
            throw new NullPointerException(String.format(ExceptionMessages.NON_EXISTING_PLACE, placeName));
        }


        Action action = new ActionImpl();
        action.startPicking(place);

        return String.format(ConstantMessages.PLACE_VISITED, placeName);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        for (Place place : placesRepository.getCollection()) {
            List<Picker> pickers = place.getPickers().stream().toList();
            if (!pickers.isEmpty()) {
                sb.append(String.format("Pickers in the %s:", place.getName())).append(System.lineSeparator());
                sb.append(getPickerInfo(pickers)).append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    private String getPickerInfo(List<Picker> pickers) {
        StringBuilder nsb = new StringBuilder();
        for (Picker currentPicker : pickers) {
            nsb.append(String.format("Name: %s", currentPicker.getName())).append(System.lineSeparator());
            nsb.append(String.format("Vitality: %d", currentPicker.getVitality())).append(System.lineSeparator());

            List<String> mushrooms = currentPicker.getBag().getMushrooms().stream().toList();
            if (mushrooms.isEmpty()) {
                nsb.append("Bag mushrooms: none").append(System.lineSeparator());
            } else {
                nsb.append("Bag mushrooms: ").append(String.join(", ", mushrooms)).append(System.lineSeparator());
            }
        }

        return nsb.toString();
    }

    private Picker getPickerFromPlace(String pickerName, Place place) {
        return place.getPickers().stream()
                .filter(e -> pickerName.equals(e.getName()))
                .findFirst()
                .orElse(null);
    }

    private Place getPlaceByName(String placeName) {
        return placesRepository.getCollection().stream()
                .filter(e -> placeName.equals(e.getName()))
                .findFirst()
                .orElse(null);
    }
}
