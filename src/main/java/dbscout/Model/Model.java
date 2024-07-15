package dbscout.Model;

import java.sql.Connection;

import java.util.List;

import dbscout.data.entities.Associato;
import dbscout.data.entities.Attivita;
import dbscout.data.entities.ServizioSq;
import dbscout.data.entities.Sestiglia;
import dbscout.data.entities.Squadriglia;



public class Model {
    
    private Connection connection;

    private Associato ass;

    private List<Attivita> attivita;
    private List<Associato> capiBranca;

    private Squadriglia squadriglia;
    private List<ServizioSq> serviziSq;

    private Sestiglia sestiglia;

    private Associato responsabileServizioClan;

    public Model(Connection connection) {
        this.connection = connection;
    }

    public void setAssociato(Associato associato) {
        ass = associato;
        switch (ass.getBranca()) {
            // qua si fanno le varie inizializzazioni in base al tipo di branca (es per lupetti carichiamo la squadriglia)
            case "Lupetti" -> {
                capiBranca = Associato.DAO.getCapiBranca(connection, ass);
                sestiglia = Associato.DAO.getSestiglia(connection, ass.getCodAssociato());
                attivita = Associato.DAO.getAttivita(connection, ass);
            }
            case "Reparto" -> {
                squadriglia = Associato.DAO.getSquadriglia(connection, ass.getCodAssociato());
                capiBranca = Associato.DAO.getCapiBranca(connection, ass);
                attivita = Associato.DAO.getAttivita(connection, ass);
                serviziSq = Associato.DAO.getServiziSq(connection, ass);
            }
            case "Noviziato" -> {
                attivita = Associato.DAO.getAttivita(connection, ass);
                capiBranca = Associato.DAO.getCapiBranca(connection, ass);
            }
            case "Clan" -> {
                capiBranca = Associato.DAO.getCapiBranca(connection, ass);
                attivita = Associato.DAO.getAttivita(connection, ass);
                responsabileServizioClan = Associato.DAO.getResponsabileServizio(connection, ass);
            }
            case "CoCa" -> {
                //attivita = Associato.DAO.getTop3Attivita(connection);
            }
        }
    }

    public Associato getAssociato() {
        return ass;
    }

    public List<Attivita> getAttivita() {
        return attivita;
    }

    public List<Associato> getCapiBranca() {
        return capiBranca;
    }

    public Squadriglia getSquadriglia() {
        return squadriglia;
    }

    public List<ServizioSq> getServiziSq() {
        return serviziSq;
    }

    public Sestiglia getSestiglia() {
        return sestiglia;
    }

    public Associato getResponsabileServizio() {
        return this.responsabileServizioClan;
    }

}
