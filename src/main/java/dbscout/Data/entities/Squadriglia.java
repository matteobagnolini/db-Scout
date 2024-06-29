package dbscout.data.entities;

import java.util.List;

public class Squadriglia {
    private final List<Repartaro> membri;
    private ServizioSq servizio;

    public Squadriglia(final List<Repartaro> membri, final ServizioSq servizio) {
        this.membri = membri;
        this.servizio = servizio;
    }

    public List<Repartaro> getMembri() {
        return membri;
    }

    public ServizioSq getServizio() {
        return servizio;
    }

    public void setServizio(final ServizioSq servizio) {
        this.servizio = servizio;
    }

}
