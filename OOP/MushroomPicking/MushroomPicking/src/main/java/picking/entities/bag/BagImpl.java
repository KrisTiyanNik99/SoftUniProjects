package picking.entities.bag;

import java.util.ArrayList;
import java.util.Collection;

public class BagImpl implements Bag {
    private Collection<String> mushrooms;

    public BagImpl() {
        mushrooms = new ArrayList<>();
    }

    public void setMushrooms(Collection<String> mushrooms) {
        this.mushrooms = mushrooms;
    }

    @Override
    public Collection<String> getMushrooms() {
        return mushrooms;
    }
}
