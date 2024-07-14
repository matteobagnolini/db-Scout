package dbscout.data.entities;

import java.util.List;

public class Sestiglia {

    private final List<Associato> membri;

    public Sestiglia(final List<Associato> membri) {
        this.membri = membri;
    }

    public List<Associato> getMembri() {
        return membri;
    }

}
