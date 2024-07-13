package dbscout.data;

public class Queries {
    //Visualizzare tutti i membri di ogni rispettiva branca
    public static final String ALL_MEMBRI_BRANCA = 
    """
    select A.Nome, A.Cognome, A.Eta, A.Sesso, A.NomeBranca
    from associato A
    order by A.NomeBranca asc
    """;


    //query singola, visualizzare i membri di una rispettiva branca 
    public static final String MEMBRI_BRANCA =
    """
    select A.Nome, A.Cognome, A.Eta, A.Sesso, A.NomeBranca
    from associato A
    where A.NomeBranca = ?
    order by A.Eta desc      
    """;



    //Visualizzare i capi di ogni rispettiva branca
    public static final String  ALL_CAPI_BRANCA=
    """
    select A.Nome, A.Cognome, A.Eta, A.Sesso, A.Recapito_tel, A.Mail, C.NomeBranca
    from associato A, capo C
    where A.CodAssociato = C.Associato
    order by C.NomeBranca asc
    """;
    
    //Visualizzare i capi di ogni rispettiva branca
    public static final String  CAPI_BRANCA=
    """
    select A.*, C.NomeBranca
    from associato A, capo C
    where A.CodAssociato = C.Associato and C.NomeBranca = ?
    order by A.Eta
    """;


    //Visualizzare le attività di ogni rispettiva branca con numero di presenze
    public static final String ALL_ATTIVITA =
    """
    select Att.*, count(P.Associato) AS `Numero presenze`
    from attivita Att, partecipazione P
    where P.NomeBranca = Att.NomeBranca and Att.`Data` = P.`Data`
    group by P.`Data`
    order by Att.NomeBranca asc 
    """;


    //Query singola, Branca specifica 
    public static final String  ATTIVITA =
    """
    select Att.*, count(P.Associato) AS `Numero presenze`
    from attivita Att, partecipazione P
    where P.NomeBranca = Att.NomeBranca and Att.`Data` = P.`Data`and Att.NomeBranca = ?
    group by P.`Data`
    order by Att.NomeBranca asc 
    """;


    //Visualizzare le recensioni per ogni uscita
    public static final String ALL_RECENSIONI =
    """
    select A.Nome, A.Cognome, P.NomeBranca, P.`Data`, P.Descrizione, P.Numero_Stelle
    from partecipazione P, associato A
    where A.CodAssociato = P.Associato and Descrizione is not null
    order by P.NomeBranca asc,
	            P.`Data` desc 
    """;

		
    //query singola per una data uscita -> BrancaRichiesta, Data Richiesta
    public static final String RECENSIONI =
    """
    select A.Nome, A.Cognome, P.NomeBranca, P.`Data`, P.Descrizione, P.Numero_Stelle
    from partecipazione P, associato A
    where A.CodAssociato = P.Associato and Descrizione is not null
		and P.NomeBranca = ? and P.Data = ?
    order by P.Numero_Stelle asc
    """;

    //Visualizzare il saldo una data branca
    public static final String SALDO_BRANCA =
    """
    select NomeBranca, FondoCassa
    from branca
    where NomeBranca = ?
    """;

    //Visualizzare i soldi spesi totali da ogni branca
    public static final String SPESE_BRANCA =
    """
    select sum(Quota) AS `Spesa totale`, NomeBranca
    from attivita
    where Quota > 0
    group by NomeBranca
    order by NomeBranca asc 
    """;

    //Visualizzare squadriglie e sestiglie (reparto e lupetti)
    public static final String ALL_SESTIGLIE =
    """
    select distinct A.CodAssociato, A.Nome, A.Cognome, A.Eta, A.Sesso, S.Nome As NomeSestiglia
    from associato A, sestiglia S, sestiglia_membro Membro
    where S.Capo_Ses = A.CodAssociato OR S.Vice_Ses = A.CodAssociato OR (A.CodAssociato = Membro.CodMembro and Nome_Sestiglia = S.Nome)
    order by S.Nome,
		S.Capo_ses desc,
        S.Vice_Ses desc,
        A.Eta desc 
    """;

        
    //query singola 
    public static final String SESTIGLIA =
    """
    select distinct A.CodAssociato, A.Nome, A.Cognome, A.Eta, A.Sesso, S.Nome As NomeSestiglia
    from associato A, sestiglia S, sestiglia_membro Membro
    where (S.Capo_Ses = A.CodAssociato OR S.Vice_Ses = A.CodAssociato OR (A.CodAssociato = Membro.CodMembro and Nome_Sestiglia = S.Nome)) and S.Nome = ?
    order by S.Capo_ses desc,
         S.Vice_Ses desc,
         A.Eta desc
    """;

    public static final String ALL_SQUADRIGLIE =
    """
    select distinct A.CodAssociato, A.Nome, A.Cognome, A.Eta, A.Sesso, S.Nome AS NomeSQ
    from associato A, squadriglia S, squadriglia_membro Membro
    where S.Capo_Sq = A.CodAssociato OR S.Vice_Sq = A.CodAssociato OR (A.CodAssociato = Membro.CodMembro and Nome_Squadriglia = S.Nome)
    order by S.Nome,
		S.Capo_Sq desc,
        S.Vice_Sq desc,
        A.Eta desc
    """;
    
    //query singola
    public static final String SQUADRIGLIA =
    """
    select distinct A.CodAssociato, A.Nome, A.Cognome, A.Eta, A.Sesso, S.Nome AS NomeSQ
    from associato A, squadriglia S, squadriglia_membro Membro
    where (S.Capo_Sq = A.CodAssociato OR S.Vice_Sq = A.CodAssociato OR (A.CodAssociato = Membro.CodMembro and Nome_Squadriglia = S.Nome)) and S.Nome = ?
    order by S.Capo_Sq desc,
        S.Vice_Sq desc,
        A.Eta desc 
    """;




    //Visualizzare tutti i capi e vice squadriglia
    public static final String ALTA_SQ =
    """
    select S.Nome AS SQ, A.Nome AS CapoSq_Nome, A.Cognome AS CapoSq_Cognome, A2.Nome AS ViceSq_Nome, A2.Cognome AS ViceSq_Cognome
    from squadriglia S, Associato A, Associato A2
    where A.CodAssociato = S.Capo_Sq and A2.CodAssociato = S.Vice_Sq
    order by S.Sesso,
		S.Nome asc
    """;

        
    //Visualizzare le 3 migliori uscite per ogni branca in base alle recensioni
    public static final String ALL_BEST_3_USCITE =
    """
     WITH MiglioriUscite AS (
    SELECT RankUscite.*, 
    ROW_NUMBER() OVER (PARTITION BY NomeBranca ORDER BY Numero_Stelle DESC) as NumeroRighe
    FROM (select Att.*, round(avg(P.Numero_Stelle), 1)  AS Numero_Stelle
		from attivita Att, partecipazione P
		where P.NomeBranca = Att.NomeBranca and Att.`Data` = P.`Data` and P.Descrizione is not null
		group by P.`Data`) AS RankUscite 
    
    )
    SELECT M.NomeBranca, M.`Data`, M.Descrizione, M.DataFine, M.Luogo, M.Luogo, M.Materiale, M.Quota, M.Numero_Stelle
    FROM MiglioriUscite M
    WHERE NumeroRighe <= 3
    ORDER BY NomeBranca, Numero_Stelle DESC, Data
    """;


    //Per una Data Branca 
    public static final String BEST_3_USCITE_BRANCA =
    """
        select Att.*, round(avg(P.Numero_Stelle), 1)  AS Numero_Stelle
		from attivita Att, partecipazione P
		where P.NomeBranca = Att.NomeBranca and Att.`Data` = P.`Data` and P.NomeBranca = ? and P.Descrizione is not null 
		group by P.`Data`
        order by Numero_Stelle desc,
		P.`Data` desc
        Limit 3    
    """;    
  

    //Visualizzare tutti i tipi di servizio (clan) con rispettivi associati che lo svolgono e capi di riferimento
    public static final String ALL_SERVIZI_CLAN = 
    """
    select S.Nome AS Nome_Servizio, S.Tipologia As Tipologia_servizio, Clan.Nome AS Nome, Clan.Cognome AS Cognome, Capo.Nome AS Nome_Capo_Rif, Capo.Cognome AS Cognome_Capo_Rif
    from servizio S, Associato Clan, Associato Capo
    where Clan.CodAssociato = S.AssociatoClan and Capo.CodAssociato = S.Capo_Referente
    order by day(S.DataInizio)
    """;
    //query singola
    public static final String SERVIZIO_CLAN = 
    """
    select S.Nome AS Nome_Servizio, S.Tipologia As Tipologia_servizio, Clan.Nome AS Nome, Clan.Cognome AS Cognome, Capo.*
    from servizio S, Associato Clan, Associato Capo
    where Clan.CodAssociato = S.AssociatoClan and Capo.CodAssociato = S.Capo_Referente and Clan.CodAssociato = ?
    """;


    //Visualizzare servizio settimanale (reparto)
    public static final String ALL_SERVIZI_SETTIMANALI =
    """
    select *
    from svolgimento_servizio
    where datediff(CURDATE(), `Data`) <=7
    order by NomeSquadriglia,
		date(`Data`) desc
    """;
    //query singola
    public static final String SERVIZIO_SETTIMANALE =
    """
    select *
    from svolgimento_servizio
    where datediff(CURDATE(), `Data`) <=7 and NomeSquadriglia = ?
    order by date(`Data`) desc
    """;

    //Aggiungere un nuovo associato + branca
    public static final String ADD_BRANCA =
    """
    insert into branca(NomeBranca, Numero_membri, FondoCassa)
    values(?, 0, ?) 
    """;

    public static final String ADD_ASSOCIATO =
    """
    insert into associato(CodAssociato,NomeBranca,Recapito_tel,Mail,Nome,Cognome,Codice_fiscale,Eta,Sesso)
    values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;
    public static final String UPDATE_BRANCA_MEMBRI =
    """
    update Branca
    Set Numero_Membri = Numero_Membri + 1 
    where Branca.NomeBranca = ?
    """;
    public static final String ADD_CAPO =
    """
    insert into capo(Associato,NomeBranca)
    values(?, ?)
    """;
    public static final String ADD_LUPETTO =
    """
    insert into lupetto(Associato)
    values(?)
    """;
    public static final String ADD_REPARTARO =
    """
    insert into repartaro(Associato)
    values(?)
    """;
    public static final String ADD_NOVIZIO =
    """
    insert into novizio(Associato)
    values(?)
    """;
    public static final String ADD_ROVER_SCOLTA =
    """
    insert into rover_scolta(Associato)
    values(?)
    """;

//Aggiungere una nuova attività o uscita
//attività
public static final String ADD_ATTIVITA =
    """
    insert into attività(NomeBranca,Data,Ora,Descrizione,DataFine,Luogo,Materiale,Quota)
    value(?, ?, ?, ?, ?)
    """;


//uscita 
public static final String ADD_USCITA =
    """
    insert into attività(NomeBranca,Data,Ora,Descrizione,DataFine,Luogo,Materiale,Quota)
    value(?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;
public static final String UPDATE_BRANCA_SPESE =
    """
    update Branca
    Set FondoCassa = FondoCassa - ?
    where Branca.NomeBranca = ?
    """;


//Aggiungere un nuovo autofinanziamento
public static final String ADD_AUTOFINANZIAMENTO =
"""
insert into auto_finanziamento(NomeBranca,Data,Luogo,Guadagno,Tipo)
value(?, ?, ?, ?, ?) 
""";

public static final String UPDATE_BRANCA_FONDOCASSA =
"""
update Branca
Set FondoCassa = FondoCassa + ?
where Branca.NomeBranca = ?
""";


//Aggiungere una nuova recensione per una uscita controllando che il socio sia stato presente
public static final String ADD_RECENSIONE =
"""
update partecipazione
set Descrizione = ? and Numero_Stelle = ?
where Associato = ? and NomeBranca = ? and `Data` = ?
""";

//Aggiungere un servizio
//Associativo: aggiungere un controllo della branca in cui svolge il servizio 
public static final String ADD_SERVIZIO_ASSOCIATIVO = 
"""
insert into servizio(Nome,DataInizio, Associato_Clan,DataFine,Giorno,Ora,Descrizione,Branca,Tipologia,Resoconto,Capo_Referente)
values (?,?,?,?,?,?,?,?,?,?)
""";

//ExtraAssociativo
public static final String ADD_SERVIZIO_EXTRA_ASSOCIATIVO =
"""
 insert into servizio(Nome,DataInizio, Associato_Clan,DataFine,Giorno,Ora,Descrizione, Luogo, Tipologia, NomeEnte, Cognome,Resoconto,Capo_Referente)
values (?,?,?,?,?,?,?,?,?,?,?,?,?)
""";

//Aggiungere ed eliminare membri da squadriglie e sestiglie
//CodAssociato da Inserire/Eliminare
//membro squadriglia
public static final String DELETE_MEMBRO_SQ =
"""
Delete From squadriglia_membro
where CodAssociato = ?;
""";
//controlla che il membro sia delle stesso sesso della sq
public static final String ADD_MEBRO_SQ =
"""
insert into squadriglia_membro(Nome_Squadriglia, CodMembro)
values (?, ?);
""";


//membro sestiglia
public static final String DELETE_MEMBRO_SES =
"""
Delete From sestiglia_membro
where CodAssociato = ? 
""";

public static final String ADD_MEBRO_SES =
"""
insert into sestiglia_membro(Nome_Squadriglia, CodMembro)
values (?, ?); 
""";

//Aggiungere ed eliminare capi e vice squadriglie e sestiglie
//per Capi e Vice squadriglia si deve per forza aggiornare la tabella in quanto dobbiamo avere sempre questi due dati, che quindi verrano sostituiti
//update squadriglia
public static final String UPDATE_CAPO_SQ =
"""
    update squadriglia
	set Capo_Sq = ?
    where Nome = ?
""";


    public static final String UPDATE_VICE_SQ =
    """
    update squadriglia    
	set Vice_Sq = ?
    where Nome = ?   
    """;

    //Stessa cosa per le sestiglie
    public static final String UPDATE_CAPO_SES  =
    """
    update sestiglia
	set Capo_Ses = ?
    where Nome = ?
    """;


    public static final String UPDATE_VICE_SES =
    """
    update sestiglia
	set Vice_Ses = ?
    where Nome = ?
    """;



//Creare una sestiglia e squadriglia
public static final String ADD_SQUADRIGLIA =
"""
insert into squadriglia(Nome, Capo_Sq, Vice_Sq, Motto, Sesso)
values (?, ?, ?, ?);
""";

    public static final String ADD_SESTIGLIA =
    """
    insert into sestiglia(Nome,Capo_Ses,Vice_Ses,Motto)
	values(?, ?, ?, ?); 
    """;

//Assegnare un capo branca ad una branca Successivamente
public static final String UPDATE_RUOLO_CAPO =
"""
update Capo
	set NomeBranca = ?
    where Associato = ? 
""";

//Assegnare un capo branca ad un servizio
            
public static final String UPDATE_REFERENZA_SERVIZIO =
"""
update Servizio
	set Capo_Referente = ?
    where Associato = ? and
			"Clan" = (select C.Branca
				from Capo C
                where C.Associato = Capo_Referente)
""";    

//Inserire un esterno per servizio extra-associativo
public static final String ADD_ESTERNO =
"""
INSERT INTO ESTERNO (Ente, Nome, Cognome, Recapito_Tel, Sesso) 
VALUES (?,?,?,?,?)
""";

//Ottenere un'associato da un'id
public static final String FIND_ASSOCIATO = 
"""
select *
from associato A
where CodAssociato = ?
""";

//Ottenere la branca di un'associato
public static final String BRANCA_ASSOCIATO = 
"""
select B.*
from associato A, branca B
where CodAssociato = ? and B.NomeBranca = A.NomeBranca
""";


//controllare se un associato è un repartaro
public static final String CHECK_REPARTARO = 
"""
select A.*
from associato A, repartaro R
where R.Associato = A.CodAssociato and A.CodAssociato = ?
""";
//controllare se un associato è un lupetto
public static final String CHECK_LUPETTO = 
"""
select A.*
from associato A, lupetto L
where L.Associato = A.CodAssociato and A.CodAssociato = ?
""";
//controllare se un associato è in clan
public static final String CHECK_CLAN = 
"""
select A.*
from associato A, rover_scolta C
where C.Associato = A.CodAssociato and A.CodAssociato = ?
""";
}


