package dbscout.data.entities;

import java.sql.Connection;
import java.util.List;

import dbscout.data.DAOException;
import dbscout.data.DAOUtils;
import dbscout.data.Queries;

public class Associato {
    private int codAssociato;
    private String tel;
    private String mail;
    private String nome;
    private String cognome;
    private String cf;
    private int eta;
    private char sesso;
    protected String branca;

    public Associato(int codAssociato, String tel, String mail, String nome, 
                     String cognome, String cf, int eta, char sesso) {
        this.codAssociato = codAssociato;
        this.tel = tel;
        this.mail = mail;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
        this.eta = eta;
        this.sesso = sesso;
    }

    public int getCodAssociato() {
        return codAssociato;
    }

    public void setCodAssociato(int codAssociato) {
        this.codAssociato = codAssociato;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public String getBranca() {
        return branca;
    }

    public void setBranca(String branca) {
        this.branca = branca;
    }

    public final class DAO {

        public static Associato getAssociatoFromId(Connection connection, int id) {
            // TODO: implements query to get an associato from database
            if(!checkAssociatoExists(connection, id)){
                return null;
            }
            try (
                var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.FIND_ASSOCIATO, id);
                var resultSet = statement.executeQuery();
            ) {
                var codAssociato = resultSet.getInt("A.codAssociato");
                var nome = resultSet.getString("A.nome");
                var cognome = resultSet.getString("A.cognome");
                var eta = resultSet.getInt("A.età");
                var sesso = resultSet.getString("A.sesso").charAt(0);
                var tel = resultSet.getString("A.Recapito_tel");
                var mail = resultSet.getString("A.Mail");
                var CF = resultSet.getString("A.Codice_fiscale");
                return new Associato(codAssociato, tel, mail, nome, cognome, CF, eta, sesso);
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }

        }
        public static boolean checkAssociatoExists(Connection connection, int id) {
            // TODO: implements query to check if it exists or not
            // bho bro uso la query sopra e se nello statemente non c'è nulla falso
            boolean Is_Present = false;
            try (
                var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.FIND_ASSOCIATO, id);
                var resultSet = statement.executeQuery();
            ) {
                if(resultSet.first()){
                    Is_Present = true;
                }
             
                }
             catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
            return Is_Present;
 
        }

        public static String getBrancaFromAssociato(Connection connection, int id) {
            //TODO: add query to get branca from user id
            if(!checkAssociatoExists(connection, id)){
                return null;
            }
            try (
                var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.BRANCA_ASSOCIATO, id);
                var resultSet = statement.executeQuery();
            ) {
                return resultSet.getString("A.NomeBranca");
                }
             catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
            
        }
        public static List<Associato> getCapiBranca(Connection connection, Associato ass) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCapiBranca'");
        }
        public static Sestiglia getSestiglia(Connection connection, int codAssociato) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getSestiglia'");
        }
        public static List<Attivita> getAttivita(Connection connection, Associato ass) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getAttivita'");
        }
        public static Squadriglia getSquadriglia(Connection connection, int codAssociato) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getSquadriglia'");
        }
        public static List<ServizioSq> getServiziSq(Connection connection, Associato ass) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getServiziSq'");
        }
        public static Associato getResponsabileServizio(Connection connection, Associato ass) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getResponsabileServizio'");
        }
    }

}

