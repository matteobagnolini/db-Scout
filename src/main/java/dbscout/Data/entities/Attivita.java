package dbscout.data.entities;

import java.util.Optional;

public record Attivita(
    Branca branca, 
    String dataOra, 
    String descrizione, 
    Optional<String> dataFine, 
    Optional<String> materiale, 
    Optional<Integer> quota) { 

    // Getter personalizzato per 'branca'
    public Branca getBranca(){
        return this.branca;
    }

    // Getter personalizzato per 'dataOra'
    public String getData(){
        return this.dataOra;
    }

    // Getter per 'descrizione'
    public String getDescrizione() {
        return this.descrizione;
    }

    // Getter per 'dataFine'
    public Optional<String> getDataFine() {
        return this.dataFine;
    }

    // Getter per 'materiale'
    public Optional<String> getMateriale() {
        return this.materiale;
    }

    // Getter per 'quota'
    public Optional<Integer> getQuota() {
        return this.quota;
    }
}


