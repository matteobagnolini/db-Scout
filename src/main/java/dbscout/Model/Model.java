package dbscout.Model;

import java.sql.Connection;

import java.util.List;

import dbscout.data.entities.Associato;
import dbscout.data.entities.Attivita;
import dbscout.data.entities.Sestiglia;
import dbscout.data.entities.Squadriglia;
import java.util.Optional;



public class Model {
    
    private Connection connection;

    private Associato ass;

    private List<Attivita> attivita;

    private List<Associato> capiBranca;

    private Optional<Squadriglia> squadriglia;
    private Optional<Sestiglia> sestiglia;

    public Model(Connection connection) {
        this.connection = connection;
    }

    public void setAssociato(Associato associato) {
        ass = associato;
        switch (Associato.DAO.getBrancaFromAssociato(connection, ass.getCodAssociato())) {
            // qua si fanno le varie inizializzazioni in base al tipo di branca (es per lupetti carichiamo la squadriglia)
            case "Lupetti" -> {
                //set capibranca
                //set sestiglia
                // set attività
            }
            case "Reparto" -> {
                //set squadriglia
                // set capi
                // set attività
                // set servizi
            }
            case "Noviziato" -> {
                //set attivita
                // set capi
            }
            case "Clan" -> {
                // set capi
                // set servizio + referente servizio
            }
            case "CoCa" -> {
                
            }
        }
    }

    public Associato getAssociato() {
        return ass;
    }

}
