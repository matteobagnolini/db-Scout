package dbscout.Data;

import java.util.LinkedList;
import java.util.List;

public class Branca {
    
    private final String nome;
    private List<Associato> membri;
    private List<Capo> capi;

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
