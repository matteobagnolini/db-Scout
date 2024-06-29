package dbscout.data.entities;

import java.util.LinkedList;
import java.util.List;

public class Branca {
    
    private final String nome;
    private List<Associato> membri;
    private List<Capo> capi;
    private List<Attivita> attivita;

    public Branca(final String nome) {
        this.nome = nome;
        membri = new LinkedList<>();
        capi = new LinkedList<>();
    }

    public void addMembro(final Associato ass) {
        membri.add(ass);
    }

    public void addCapo(final Capo capo) {
        capi.add(capo);
    }

    public void addAttivita(final Attivita att) {
        if (att.branca().equals(this)) {
            attivita.add(att);
        } else {
            throw new IllegalArgumentException("Errore, attività non è della branca specificata.");
        }
    }

    public String getNome() {
        return nome;
    }

    public List<Capo> getCapi() {
        return capi;
    }

    public List<Associato> getMembri() {
        return membri;
    }

}
