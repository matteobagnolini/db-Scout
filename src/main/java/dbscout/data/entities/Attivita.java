package dbscout.data.entities;

import java.util.Optional;

public record Attivita(
    String branca, 
    String dataOra, 
    String descrizione, 
    Optional<String> dataFine, 
    Optional<String> luogo,
    Optional<String> materiale, 
    Optional<Integer> quota,
    Optional<Integer> Voto) { 

    // Getter personalizzato per 'branca'
    public String getBranca(){
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
    public Optional<String> getLuogo() {
        return this.luogo;
    }

    // Getter per 'quota'
    public Optional<Integer> getQuota() {
        return this.quota;
    }
    public Optional<Integer> getVoto() {
        return this.Voto;
    }
}


