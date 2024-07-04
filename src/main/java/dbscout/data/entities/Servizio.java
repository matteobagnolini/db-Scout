package dbscout.data.entities;

import java.util.Optional;

public class Servizio { 
    String nome;
    String dataInizio;
    String dataFine;
    String periodo;
    String descrizione;
    String tipologia;
    Associato capoReferente;
    Optional<String> branca;
    Optional<String> Luogo;
    Optional<String> nomeEnteEsterno;
    Optional<String> cognomeEsterno;
    Optional<String> resoconto;

    // Getter per 'nome'
    public String getNome() {
        return this.nome;
    }

    // Getter per 'dataInizio'
    public String getDataInizio() {
        return this.dataInizio;
    }

    // Getter per 'dataFine'
    public String getDataFine() {
        return this.dataFine;
    }

    // Getter per 'periodo'
    public String getPeriodo() {
        return this.periodo;
    }

    // Getter per 'descrizione'
    public String getDescrizione() {
        return this.descrizione;
    }

    // Getter per 'tipologia'
    public String getTipologia() {
        return this.tipologia;
    }

    // Getter per 'capoReferente'
    public Associato getCapoReferente() {
        return this.capoReferente;
    }

    // Getter per 'branca'
    public Optional<String> getBranca() {
        return this.branca;
    }

    // Getter per 'Luogo'
    public Optional<String> getLuogo() {
        return this.Luogo;
    }

    // Getter per 'nomeEnteEsterno'
    public Optional<String> getNomeEnteEsterno() {
        return this.nomeEnteEsterno;
    }

    // Getter per 'cognomeEsterno'
    public Optional<String> getCognomeEsterno() {
        return this.cognomeEsterno;
    }

    // Getter per 'resoconto'
    public Optional<String> getResoconto() {
        return this.resoconto;
    }
    public void setResoconto(String resoconto) {
        this.resoconto = Optional.of(resoconto);
    }

    // Metodo equals 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servizio servizio = (Servizio) o;
        return nome.equals(servizio.nome) &&
                dataInizio.equals(servizio.dataInizio) &&
                dataFine.equals(servizio.dataFine) &&
                periodo.equals(servizio.periodo) &&
                descrizione.equals(servizio.descrizione) &&
                tipologia.equals(servizio.tipologia) &&
                capoReferente.equals(servizio.capoReferente) &&
                branca.equals(servizio.branca) &&
                Luogo.equals(servizio.Luogo) &&
                nomeEnteEsterno.equals(servizio.nomeEnteEsterno) &&
                cognomeEsterno.equals(servizio.cognomeEsterno) &&
                resoconto.equals(servizio.resoconto);
    }

}

