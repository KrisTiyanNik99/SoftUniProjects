package picking.core;

public interface Controller {

    String addPlace(String placeName, String... mushrooms);

    String addPicker(String placeName, String pickerType, String pickerName);

    String startPicking(String placeName);

    String getStatistics();

}
