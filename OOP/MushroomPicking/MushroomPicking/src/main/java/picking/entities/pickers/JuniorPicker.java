package picking.entities.pickers;

public class JuniorPicker extends BasePicker {
    private static final int VITALITY_UNITS = 30;

    public JuniorPicker(String name) {
        super(name, VITALITY_UNITS);
    }
}
