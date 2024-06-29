package dbscout.data.entities;

import java.util.List;

public class Sestiglia {

    private final List<Lupetto> membri;

    public Sestiglia(final List<Lupetto> membri) {
        this.membri = membri;
    }

    public List<Lupetto> getMembri() {
        return membri;
    }

}
