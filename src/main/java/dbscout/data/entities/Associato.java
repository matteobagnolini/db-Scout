package dbscout.data.entities;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

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
            if(!checkAssociatoExists(connection, id)){
                return null;
            }
            try (
                var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.FIND_ASSOCIATO, id);
                var resultSet = statement.executeQuery();
            ) {
                resultSet.next();
                var codAssociato = resultSet.getInt("A.CodAssociato");
                var nome = resultSet.getString("A.Nome");
                var cognome = resultSet.getString("A.Cognome");
                var eta = resultSet.getInt("A.Eta");
                var sesso = resultSet.getString("A.Sesso").charAt(0);
                var tel = resultSet.getString("A.Recapito_tel");
                var mail = resultSet.getString("A.Mail");
                var CF = resultSet.getString("A.Codice_fiscale");
                var branca = resultSet.getString("A.NomeBranca");
                var ass = new Associato(codAssociato, tel, mail, nome, cognome, CF, eta, sesso);
                ass.setBranca(branca);
                return ass;
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }

        }
        public static boolean checkAssociatoExists(Connection connection, int id) {
            // bho bro uso la query sopra e se nello statemente non c'è nulla falso
            boolean Is_Present = false;
            try (
                var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.FIND_ASSOCIATO, id);
                var resultSet = statement.executeQuery();
            ) {
                if(resultSet.next()){
                    Is_Present = true;
                }
             
                }
             catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
            return Is_Present;
 
        }

        public static String getBrancaFromAssociato(Connection connection, int id) {
            if(!checkAssociatoExists(connection, id)){
                return null;
            }
            try (
                var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.BRANCA_ASSOCIATO, id);
                var resultSet = statement.executeQuery();
            ) {
                resultSet.next();
                return resultSet.getString("A.NomeBranca");
                }
             catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
            
        }
        public static List<Associato> getCapiBranca(Connection connection, Associato ass) {
        List<Associato> Capi = new ArrayList<>();
        try(
        var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.CAPI_BRANCA, ass.getBranca());
        var resultSet = statement.executeQuery();
        ) {

            while (resultSet.next()) {
                var codAssociato = resultSet.getInt("A.CodAssociato");
                var nome = resultSet.getString("A.Nome");
                var cognome = resultSet.getString("A.Cognome");
                var eta = resultSet.getInt("A.Eta");
                var sesso = resultSet.getString("A.Sesso").charAt(0);
                var tel = resultSet.getString("A.Recapito_tel");
                var mail = resultSet.getString("A.Mail");
                var CF = resultSet.getString("A.Codice_fiscale");
                Capi.add(new Associato(codAssociato, tel, mail, nome, cognome, CF, eta, sesso));
            }
        } catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        return Capi;
        }

        public static boolean checkRightBranca(Connection connection, int id, String NomeBranca) {

            // bho bro uso la query sopra e se nello statemente non c'è nulla falso
            if(!checkAssociatoExists(connection, id)){
                return false;
            }
            Associato ass = getAssociatoFromId(connection, id);
            switch (NomeBranca) {
                // qua si fanno le varie inizializzazioni in base al tipo di branca (es per lupetti carichiamo la squadriglia)
                case "Lupetti" -> {
                    try (
                var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.CHECK_LUPETTO, id);
                var resultSet = statement.executeQuery();
            ) {
                if(ass.branca == NomeBranca && resultSet.first()){
                    return true; 
                }
                }
             catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
                    

                }
                case "Reparto" -> {
                    try (
                var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.CHECK_REPARTARO, id);
                var resultSet = statement.executeQuery();
                    ) {
                if(ass.branca == NomeBranca && resultSet.first()){
                    return true; 
                }
                }
                    catch (Exception e) {
                        throw new DAOException(e.getMessage());
                    }
                }
                    case "Clan" -> {
                        try (
                    var statement = DAOUtils.prepare(connection, /*Query tutti di membri di una data sq*/ Queries.CHECK_CLAN, id);
                    var resultSet = statement.executeQuery();
                ) {
                    if(ass.branca == NomeBranca && resultSet.first()){
                        return true; 
                    }
                    }
                catch (Exception e) {
                    throw new DAOException(e.getMessage());
                }
                }
            }
            return false;
 
        }
        public static Sestiglia getSestiglia(Connection connection, int codAssociato) {
            if(!checkAssociatoExists(connection, codAssociato) || !checkRightBranca(connection, codAssociato, "Lupetti"))
                return null;
            String NomeSestiglia = "Nulla";
            List<Lupetto> Sestiglia = new ArrayList<>();
            Associato ass = getAssociatoFromId(connection, codAssociato);
                try (
                var statement = DAOUtils.prepare(connection, Queries.ALL_SESTIGLIE);
                var resultSet = statement.executeQuery();
                    ) {
                while (resultSet.next() && NomeSestiglia == "Nulla") {
                    if(resultSet.getInt("A.CodAssociato") == ass.codAssociato){
                        NomeSestiglia = resultSet.getString("S.NomeSestiglia");
                    }
                }
                resultSet.first();
                while (resultSet.next()) {
                    if(resultSet.getString("S.NomeSestiglia") == NomeSestiglia && 
                    resultSet.getInt("A.CodAssociato") != ass.codAssociato){
                        Sestiglia.add(new Lupetto(ass.codAssociato, ass.tel, ass.mail, ass.nome, ass.cognome, ass.getCf(), ass.eta, ass.sesso));
                    }
                }
               
                }
                    catch (Exception e) {
                        throw new DAOException(e.getMessage());
                    }
            return new Sestiglia(Sestiglia);
        }
        public static List<Attivita> getAttivita(Connection connection, Associato ass) {
            List<Attivita> Attivita = new ArrayList<>();
            if(!checkAssociatoExists(connection, ass.codAssociato))
                return Attivita;
                try (
                    var statement = DAOUtils.prepare(connection, Queries.ATTIVITA, ass.branca);
                    var resultSet = statement.executeQuery();
                        ) {
                    while (resultSet.next()) {
                        String dataOra = resultSet.getString("Att.data");
                        String descrizione = resultSet.getString("Att.Descrizione");
                        Optional<String> dataFine = Optional.of(resultSet.getString("Att.DataFine"));
                        Optional<String> materiale = Optional.of(resultSet.getString("Att.Materiale"));
                        Optional<Integer> quota = Optional.of(resultSet.getInt("Att.Quota"));
                        Attivita att = new Attivita(ass.branca, dataOra, descrizione, dataFine, materiale, quota);
                        Attivita.add(att);
                    }
                    }
                    catch (Exception e) {
                        throw new DAOException(e.getMessage());
                    }
            return Attivita;
        }
        public static String findSquadriglia(Connection connection, int codAssociato){
            String NomeSq = "Nulla";
            Associato ass = getAssociatoFromId(connection, codAssociato);
    
                try (
                var statement = DAOUtils.prepare(connection, Queries.ALL_SQUADRIGLIE);
                var statement2 = DAOUtils.prepare(connection, Queries.ALL_SQUADRIGLIE);
                var resultSet = statement.executeQuery();
                    ) {
                while (resultSet.next() && NomeSq == "Nulla") {
                    if(resultSet.getInt("A.CodAssociato") == ass.codAssociato){
                        NomeSq = resultSet.getString("S.NomeSQ");
                    }
                }
                }
                catch (Exception e) {
                    throw new DAOException(e.getMessage());
                }
                return NomeSq;
        }
        public static Squadriglia getSquadriglia(Connection connection, int codAssociato) {
            if(!checkAssociatoExists(connection, codAssociato) || !checkRightBranca(connection, codAssociato, "Reparto"))
            return null;
            String NomeSq = findSquadriglia(connection, codAssociato);
            return null;
        }
        public static List<ServizioSq> getServiziSq(Connection connection, Associato ass) {
            List<ServizioSq> ServiziSq = new ArrayList<>();
            if(!checkAssociatoExists(connection, ass.codAssociato) || !checkRightBranca(connection, ass.codAssociato, "Reparto"))
            return ServiziSq;
            String NomeSq = findSquadriglia(connection, ass.codAssociato);
            try (
                var statement = DAOUtils.prepare(connection, Queries.SERVIZIO_SETTIMANALE, NomeSq);
                var resultSet = statement.executeQuery();
                    ) {
                while (resultSet.next()) {
                    var nomeServizio = resultSet.getString("NomeServizio");
                    var data = resultSet.getString("Data");
                    ServiziSq.add(new ServizioSq(NomeSq, nomeServizio, data));
                }
                }
                catch (Exception e) {
                    throw new DAOException(e.getMessage());
                }
            return ServiziSq;
        }
        public static Associato getResponsabileServizio(Connection connection, Associato ass) {
            if(!checkAssociatoExists(connection, ass.codAssociato) || !checkRightBranca(connection, ass.codAssociato, "Clan"))
            return null;
            try (
                var statement = DAOUtils.prepare(connection, Queries.SERVIZIO_CLAN, ass.codAssociato);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.first()) {
                    var codAssociato = resultSet.getInt("Capo.codAssociato");
                    var nome = resultSet.getString("Capo.nome");
                    var cognome = resultSet.getString("Capo.cognome");
                    var eta = resultSet.getInt("Capo.età");
                    var sesso = resultSet.getString("Capo.sesso").charAt(0);
                    var tel = resultSet.getString("Capo.Recapito_tel");
                    var mail = resultSet.getString("Capo.Mail");
                    var CF = resultSet.getString("Capo.Codice_fiscale");
                    return new Associato(codAssociato, tel, mail, nome, cognome, CF, eta, sesso);
                }
                else
                    return null;
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
        }
        public static List<Associato> getMembriSest(Connection connection, Associato associato) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getMembriSest'");
        }
        public static void putRecensione(Connection connection, Attivita attivita, Associato associato,
                String recensione, int voto) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'putRecensione'");
        }
    }

}

