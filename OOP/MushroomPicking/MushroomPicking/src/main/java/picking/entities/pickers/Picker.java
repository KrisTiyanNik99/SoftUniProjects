package picking.entities.pickers;

import picking.entities.bag.Bag;

public interface Picker {

    String getName();

    int getVitality();

    Bag getBag();

    void pick();
}
