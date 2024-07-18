package dbscout.data.entities;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Optional;

import java.text.ParseException;
import java.sql.Date;


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
            Associato ass = getAssociatoFromId(connection, id);
            switch (NomeBranca) {
                // qua si fanno le varie inizializzazioni in base al tipo di branca (es per lupetti carichiamo la squadriglia)
                case "Lupetti" -> {
                    try (
                var statement = DAOUtils.prepare(connection,  Queries.CHECK_LUPETTO, id);
                var resultSet = statement.executeQuery();
                    ) {
                if(ass.branca == NomeBranca && resultSet.next()){
                    return true; 
                    } else {
                    return false;
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
            if(!checkAssociatoExists(connection, codAssociato)) {
                System.out.println("PROBLEMI IN SESTIGLI");
                return null;
            }
            String NomeSestiglia = "Nulla";
            List<Associato> Sestiglia = new ArrayList<>();
            System.out.println("NOOO PROBLEMI IN SESTIGLIA");

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
                    try (
                        var statement2 = DAOUtils.prepare(connection, Queries.GET_SES, NomeSestiglia, NomeSestiglia);
                        var resultSet2 = statement2.executeQuery();
                        ) {
                            while (resultSet2.next()) 
                                    Sestiglia.add(getAssociatoFromId(connection,resultSet2.getInt("A.CodAssociato")));
                            }
                            

                    catch (Exception e) {
                        throw new DAOException(e.getMessage());
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
                        Optional<String> luogo = Optional.of(resultSet.getString("Att.Luogo"));
                        Attivita att = new Attivita(ass.branca, dataOra, descrizione, dataFine, luogo, materiale, quota, Optional.empty());
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
            var resultSet = statement.executeQuery();
                ) {
            while (resultSet.next() && NomeSq == "Nulla") {
                if(resultSet.getInt("A.CodAssociato") == ass.getCodAssociato()){
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
            if(!checkAssociatoExists(connection, codAssociato)) {
                return null;
            }
            String NomeSq = findSquadriglia(connection, codAssociato);
            List<ServizioSq> servizi = getServiziSq(connection, getAssociatoFromId(connection, codAssociato));
            return new Squadriglia(NomeSq, servizi);
        }
        public static List<ServizioSq> getServiziSq(Connection connection, Associato ass) {
            List<ServizioSq> ServiziSq = new ArrayList<>();
            String NomeSq = findSquadriglia(connection, ass.getCodAssociato());
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
                    return getAssociatoFromId(connection, resultSet.getInt("S.Capo_Referente"));
                }
                else
                    return null;
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
        }

        public static void putRecensione(Connection connection, Attivita attivita, Associato associato,
                String recensione, int voto) {
            try {
                var statement = DAOUtils.prepare(connection, Queries.ADD_RECENSIONE, recensione, voto, associato.getCodAssociato(), attivita.getBranca());
                statement.executeUpdate();
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
        }
        public static List<Associato> getMembriNoviziato(Connection connection, Associato associato) {
            List<Associato> Noviziato = new ArrayList<>();
            if(!checkAssociatoExists(connection, associato.codAssociato))
            return Noviziato;
            try (
                var statement = DAOUtils.prepare(connection, Queries.GET_Noviziato);
                var resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    Noviziato.add(getAssociatoFromId(connection, resultSet.getInt("A.CodAssociato")));
                }
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
            return Noviziato;
        }
        public static Servizio getServizio(Connection connection, int codAssociato) {
            Associato ass = getAssociatoFromId(connection, codAssociato);
            if(!checkRightBranca(connection, ass.codAssociato, "Clan"))
            return null;
            try (
                var statement = DAOUtils.prepare(connection, Queries.SERVIZIO_CLAN, ass.codAssociato);
                var resultSet = statement.executeQuery();
            ) {
                if (resultSet.first()) {                    
                    var Nome = resultSet.getString("S.Nome");
                    var DataInizio = resultSet.getString("S.DataInizio");
                    
                    var DataFine = resultSet.getString("S.DataFine");
                   // var Giorno = resultSet.getString("S.Giorno");
                   // var Ora = resultSet.getString("S.Ora");
                    var Descrizione = resultSet.getString("S.Descrizione");
                    var Branca = resultSet.getString("S.Branca");
                    var Luogo = resultSet.getString("S.Luogo");
                    var Tipologia = resultSet.getString("S.Tipologia");
                    var NomeEnte = resultSet.getString("S.NomeEnte");
                    var Cognome = resultSet.getString("S.Cognome");
                    var Resoconto = resultSet.getString("S.Resoconto");
                    var Capo_Referente =getAssociatoFromId(connection, resultSet.getInt("S.Capo_Referente")) ;
                    return new Servizio(Nome, DataInizio, DataFine, Descrizione, Tipologia, Capo_Referente, Optional.of(Branca), 
                    Optional.of(Luogo),  Optional.of(NomeEnte),  Optional.of(Cognome),  Optional.of(Resoconto));
                }
                else
                    return null;
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
            
                
        }
		public static List<Attivita> getTop3Attivita(Connection connection, String N) {
            List<Attivita> Top3 = new ArrayList<>();
            try (
                var statement = DAOUtils.prepare(connection, Queries.BEST_3_USCITE_BRANCA, N);
                var resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) { 
                    String branca = resultSet.getString("Att.NomeBranca");
                    String dataOra =  resultSet.getString(" Att.Data");
                    String descrizione = resultSet.getString("Att.Descrizione");
                    Optional<String> dataFine = Optional.of(resultSet.getString("Att.DataFine"));
                    Optional<String> materiale = Optional.of(resultSet.getString("Att.Materiale"));
                    Optional<Integer> quota = Optional.of(resultSet.getInt("Att.Quota"));
                    Optional<String> luogo = Optional.of(resultSet.getString("Att.Luogo"));
                    Optional<Integer> Numero_Stelle = Optional.of(resultSet.getInt("Numero_Stelle"));
                    
                    
                    Top3.add(new Attivita(branca, dataOra, descrizione, dataFine,luogo, materiale, quota, Numero_Stelle));
                }
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
            return Top3;
		}
        public static void addAssociato(Connection connection, Associato associato) {
            if(checkAssociatoExists(connection, associato.codAssociato)){
                return;
            }

            var codAssociato = associato.codAssociato;
            var nome = associato.nome;
            var cognome = associato.cognome;
            var eta = associato.eta;
            var sesso = associato.sesso;
            var tel = associato.tel;
            var mail = associato.mail;
            var CF = associato.cf;
            var branca = associato.branca;

            try (var addAssociato = DAOUtils.prepare(connection, Queries.ADD_ASSOCIATO, 
                    codAssociato, branca, tel, mail, nome, cognome, CF, eta, sesso)) {
                addAssociato.executeUpdate();
                switch (associato.branca) {
                    // qua si fanno le varie inizializzazioni in base al tipo di branca (es per lupetti carichiamo la squadriglia)
                    case "Lupetti" -> {
                        try (
                    var addLupetto = DAOUtils.prepare(connection,  Queries.ADD_LUPETTO, associato.codAssociato);
                    var addMembriLupetti = DAOUtils.prepare(connection,  Queries.UPDATE_BRANCA_MEMBRI, "Lupetti");
                        ) {
                            addLupetto.executeUpdate();
                            addMembriLupetti.executeUpdate();
                    }
                 catch (Exception e) {
                    throw new DAOException(e.getMessage());
                }
                        
    
                    }
                    case "Reparto" -> {
                        try (
                    var addRepartaro = DAOUtils.prepare(connection,  Queries.ADD_REPARTARO, associato.codAssociato);
                    var addMembriReparto = DAOUtils.prepare(connection,  Queries.UPDATE_BRANCA_MEMBRI, "Reparto");
                        ) {
                            addRepartaro.executeUpdate();
                            addMembriReparto.executeUpdate();
                    }
                 catch (Exception e) {
                    throw new DAOException(e.getMessage());
                }
                    }
                        case "Noviziato" -> {
                            try (
                                var addNovizio = DAOUtils.prepare(connection,  Queries.ADD_NOVIZIO, associato.codAssociato);
                                var addMembriNovizio  = DAOUtils.prepare(connection,  Queries.UPDATE_BRANCA_MEMBRI, "Noviziato");
                                    ) {
                                        addNovizio.executeUpdate();
                                        addMembriNovizio.executeQuery();
                                }
                             catch (Exception e) {
                                throw new DAOException(e.getMessage());
                            }
                    }
                        case "Clan" -> {
                            try (
                                var addClan = DAOUtils.prepare(connection,  Queries.ADD_ROVER_SCOLTA, associato.codAssociato);
                                var addMembriClan  = DAOUtils.prepare(connection,  Queries.UPDATE_BRANCA_MEMBRI, "Clan");
                                    ) {
                                        addClan.executeUpdate();
                                        addMembriClan.executeQuery();
                                }
                            catch (Exception e) {
                                throw new DAOException(e.getMessage());
                            }
                    }
                        case "Coca" -> {
                            try (
                                var add = DAOUtils.prepare(connection,  Queries.ADD_CAPO, associato.codAssociato, "Nessuna");
                                var addMembri  = DAOUtils.prepare(connection,  Queries.UPDATE_BRANCA_MEMBRI, "CoCa");
                                    ) {
                                        add.executeUpdate();
                                        addMembri.executeUpdate();
                                }
                            catch (Exception e) {
                                throw new DAOException(e.getMessage());
                            }
                    }
                }

            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }

        }
        public static Date stringToSqlDate(String dateString) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                // Convertire la stringa in oggetto java.util.Date
                java.util.Date utilDate = dateFormat.parse(dateString);
    
                // Convertire java.util.Date in java.sql.Date e restituirlo
                return new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                System.out.println("Errore di parsing della data: " + e.getMessage());
                return null;
            }
            }
        public static void addAttivita(Connection connection, Attivita attivita) {
            String branca = attivita.branca(); 
            String dataOra = attivita.dataOra();
            String descrizione = attivita.descrizione();
            Optional<String> dataFine = attivita.dataFine();
            Optional<String> luogo = attivita.luogo();
            Optional<String> materiale = attivita.materiale();
            Optional<Integer> quota = attivita.quota();
    
            try(
                //NomeBranca,Data,Ora,Descrizione,DataFine,Luogo,Materiale,Quota
                var addAttivita = DAOUtils.prepare(connection, Queries.ADD_ATTIVITA, 
                    branca, stringToSqlDate(dataOra) , stringToSqlDate(dataOra), descrizione, stringToSqlDate(dataFine.orElse(dataOra)), 
                    luogo.orElse("-"), materiale.orElse("-"), quota.orElse(0))) {
                addAttivita.executeUpdate();
                
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
            if(quota.isPresent()){
                try(
                    //NomeBranca,Data,Luogo,Guadagno,Tipo
                    var updateSaldo = DAOUtils.prepare(connection, Queries.UPDATE_NEGATIVE_BRANCA_FONDOCASSA, 
                        quota.get(), branca)
                        ) {
                    updateSaldo.executeUpdate();
                    
                } catch (Exception e) {
                    throw new DAOException(e.getMessage());
                }
            }
        }

        public static void addAutofinanziamento(Connection connection, Autofinanziamento Autofin) {
            String Branca = Autofin.getBranca();
            String data = Autofin.getData();
            String luogo = Autofin.getLuogo();
            float guadagno = Autofin.getGuadagno();
            String tipo = Autofin.getTipo();
            try(
                //NomeBranca,Data,Luogo,Guadagno,Tipo

                var addAutofinanziamento = DAOUtils.prepare(connection, Queries.ADD_AUTOFINANZIAMENTO, 
                    Branca, stringToSqlDate(data), luogo, guadagno, tipo);
                var updateSaldo = DAOUtils.prepare(connection, Queries.UPDATE_BRANCA_FONDOCASSA, 
                guadagno, Branca)
                    ) {
                addAutofinanziamento.executeUpdate();
                updateSaldo.executeQuery();
                
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
        }
        public static float getFinanza(Connection connection, String Branca) {
            try (
                var statement = DAOUtils.prepare(connection, Queries.SALDO_BRANCA, Branca);
                var resultSet = statement.executeQuery();
            ) {
                while (resultSet.next()) {
                    return resultSet.getFloat("FondoCassa");
                }
                return -1.f;
            } catch (Exception e) {
                throw new DAOException(e.getMessage());
            }
            
        }
    }

}

