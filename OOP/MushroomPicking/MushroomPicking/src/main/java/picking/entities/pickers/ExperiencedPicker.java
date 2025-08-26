package picking.entities.pickers;

public class ExperiencedPicker extends BasePicker {
    private static final int VITALITY_UNITS = 60;

    public ExperiencedPicker(String name) {
        super(name, VITALITY_UNITS);
    }
}
