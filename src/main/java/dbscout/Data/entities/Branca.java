package dbscout.data.entities;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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

    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Branca branca = (Branca) o;
        return this.nome == branca.nome && Objects.equals(this.attivita, branca.attivita) 
        && Objects.equals(this.capi, branca.capi) && Objects.equals(this.membri, branca.membri);
    }

    public int hashCode() {
        return Objects.hash(nome, membri, attivita, capi);
    }
}
