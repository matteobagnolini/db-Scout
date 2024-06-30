package dbscout.data.entities;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import dbscout.data.DAOException;
import dbscout.data.DAOUtils;
import dbscout.data.Queries;

import java.util.HashSet;

public class Squadriglia {

    private final String nome;
    private final List<Repartaro> membri;
    private ServizioSq servizio;

    public Squadriglia(final String nome, final List<Repartaro> membri, final ServizioSq servizio) {
        this.nome = nome;
        this.membri = membri;
        this.servizio = servizio;
    }

    public List<Repartaro> getMembri() {
        return membri;
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

        public static Set<Repartaro> membri(Connection connection, String nomeSquadriglia) {
            var membri = new HashSet<Repartaro>();
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

                    var nomeSq = resultSet.getString("nomeSQ");
                    
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

}
