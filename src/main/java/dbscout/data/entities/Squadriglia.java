package dbscout.data.entities;

import java.sql.Connection;

import dbscout.data.DAOException;
import dbscout.data.DAOUtils;
import dbscout.data.Queries;

import java.util.LinkedList;
import java.util.List;

public class Squadriglia {

    private final String nome;
    private List<ServizioSq> servizi;

    public Squadriglia(final String nome, final List<ServizioSq> servizio) {
        this.nome = nome;
        this.servizi = servizio;
    }

    public String getNome() {
        return nome;
    }
    public List<ServizioSq> getServizi() {
        return servizi;
    }

    public void setServizio(final List<ServizioSq> servizio) {
        this.servizi = servizio;
    }

    public final class DAO {

        public static List<Repartaro> membri(Connection connection, String nomeSquadriglia) {
            var membri = new LinkedList<Repartaro>();
            try (
                var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.SQUADRIGLIA, nomeSquadriglia);
                var resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    var codAssociato = resultSet.getInt("A.CodAssociato");
                    var nome = resultSet.getString("A.Nome");
                    var cognome = resultSet.getString("A.Cognome");
                    var eta = resultSet.getInt("A.Eta");
                    var sesso = resultSet.getString("A.Sesso").charAt(0);

                    
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

