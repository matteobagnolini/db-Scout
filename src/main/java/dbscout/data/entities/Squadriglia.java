package dbscout.data.entities;

import java.sql.Connection;
import java.util.Set;

import dbscout.data.DAOException;
import dbscout.data.DAOUtils;
import dbscout.data.Queries;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Squadriglia {

    private final String nome;
    private ServizioSq servizio;
    private List<Repartaro> membri;

    public Squadriglia(final String nome, final ServizioSq servizio) {
        this.nome = nome;
        this.servizio = servizio;
    }

    public Squadriglia(List<Repartaro> membri) {
        this.membri = membri;
        nome = null;
        servizio = null;
    }

    public String getNome() {
        return nome;
    }
    public ServizioSq getServizio() {
        return servizio;
    }

    public void setServizio(final ServizioSq servizio) {
        this.servizio = servizio;
    }

    public final class DAO {

        public static List<Repartaro> membri(Connection connection, String nomeSquadriglia) {
            var membri = new LinkedList<Repartaro>();
            try (
                var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.SQUADRIGLIA, nomeSquadriglia);
                var resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    var codAssociato = resultSet.getInt("A.codAssociato");
                    var nome = resultSet.getString("A.nome");
                    var cognome = resultSet.getString("A.cognome");
                    var eta = resultSet.getInt("A.età");
                    var sesso = resultSet.getString("A.sesso").charAt(0);

                    
                    // here create a new associato;
                    membri.add(new Repartaro(codAssociato, null, null, nome, cognome, "abc", eta, sesso));
                }
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
            return membri;
        }
    }

}

