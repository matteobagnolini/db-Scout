package dbscout.data.entities;

import java.util.Optional;

public record Partecipazione(
    String branca, 
    String dataOra, 
    String descrizione, 
    Optional<String> dataFine, 
    Optional<String> materiale, 
    Optional<Integer> quota,
    Optional<String> luogo,
    Optional<String> recensione, 
    Optional<Integer> voto 
) { 

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

    // Getter per 'quota'
    public Optional<Integer> getQuota() {
        return this.quota;
    }

    // Getter per 'recensione'
    public Optional<String> getRecensione() {
        return this.recensione;
    }

    // Getter per 'voto'
    public Optional<Integer> getVoto() {
        return this.voto;
    }
        // Getter per 'luogo'
    public Optional<String> getLuogo() {
        return this.luogo;
    }
}

