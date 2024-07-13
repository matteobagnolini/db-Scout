-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.2              
-- * Generator date: Sep 14 2021              
-- * Generation date: Mon Jun 24 18:19:58 2024 
-- * LUN file: C:\Users\feded\OneDrive\Desktop\Università\Secondo_Anno\Secondo semestre\Basi di Dati\Progetto\dbscout.lun 
-- * Schema: dbscout/1 
-- ********************************************* 


-- Database Section
-- ________________ 
DROP DATABASE IF EXISTS dbscout;

CREATE DATABASE IF NOT EXISTS dbscout;

use dbscout;


-- Tables Section
-- _____________ 

create table ASSOCIATO (
     CodAssociato integer not null default 0,
     NomeBranca VARCHAR(13) not null default "Lupetti",
     Recapito_tel VARCHAR(10) not null,
     Mail VARCHAR(25),
     Nome VARCHAR(20) not null,
     Cognome  VARCHAR(20) not null,
     Codice_fiscale VARCHAR(16) not null,
     Eta integer not null default 8,
     Sesso char(1) not null,
     constraint IDASSOCIATO primary key (CodAssociato),
     constraint IDASSOCIATO_1 unique (NomeBranca, CodAssociato));

create table ATTIVITA (
     NomeBranca VARCHAR(13) not null,
     `Data` datetime not null,
     Ora datetime not null,
     Descrizione mediumtext not null,
     DataFine datetime,
     Luogo VARCHAR(20),
     Materiale mediumtext,
     Quota decimal(7,2) default 0,
     constraint IDATTIVITA primary key (NomeBranca, Data));

create table AUTO_FINANZIAMENTO (
     NomeBranca VARCHAR(13) not null,
     `Data` datetime not null,
     Luogo VARCHAR(20) not null,
     Guadagno decimal(7,2) not null,
     Tipo VARCHAR(20) not null,
     constraint IDAUTO_FINANZIAMENTO primary key (NomeBranca, Data, Luogo));

create table BRANCA (
     NomeBranca VARCHAR(13) not null,
     Numero_membri smallint not null default 0,
     FondoCassa decimal(8,2) not null default 0,
     constraint IDBRANCA primary key (NomeBranca));

create table CAPO (
     Associato integer not null default 0,
     NomeBranca VARCHAR(13) not null,
     constraint FKAssociato_Capo_ID primary key (Associato));

create table ESTERNO (
     Ente VARCHAR(20) not null,
     Nome VARCHAR(20) not null,
     Cognome VARCHAR(20) not null,
     Recapito_Tel VARCHAR(20) not null,
     Sesso char(1) not null,
     constraint IDESTERNO primary key (Ente, Cognome));

create table LUPETTO (
     Associato integer not null default 0,
     constraint FKAssociato_Lupetto_ID primary key (Associato));

create table SVOLGIMENTO_SERVIZIO (
     NomeSquadriglia VARCHAR(20) not null,
     NomeServizio VARCHAR(20) not null,
     `Data` datetime not null,
     constraint IDSVOLGIMENTO_SERVIZIO primary key (NomeSquadriglia, NomeServizio, Data));

create table NOVIZIO (
     Associato integer not null default 0,
     constraint FKAssociato_Novizio_ID primary key (Associato));

create table PARTECIPAZIONE (
     Associato integer not null default 0,
     NomeBranca VARCHAR(13) not null,
     `Data` datetime not null,
     Descrizione  mediumtext,
     Numero_Stelle decimal(3,2),
     constraint IDPARTECIPAZIONE primary key (Associato, NomeBranca, Data),
	 CONSTRAINT CK_Numero_Stelle CHECK (Numero_Stelle BETWEEN 1 AND 5));
    
create table REPARTARO (
     Associato integer not null default 0,
     constraint FKAssociato_Repartaro_ID primary key (Associato));

create table ROVER_SCOLTA (
     Associato integer not null default 0,
     constraint FKAssociato_Clan_ID primary key (Associato));

create table SERVIZI_A_GIRO (
     Nome varchar(22) not null,
     Descrizione mediumtext not null,
     constraint IDSERVIZIO_REP primary key (Nome));

create table SERVIZIO (
     Nome varchar(20) not null,
     DataInizio datetime not null,
     Associato_Clan integer not null,
     DataFine datetime not null,
     Giorno varchar(9) not null,
     Ora time not null,
     Descrizione mediumtext not null,
     Branca varchar(7),
     Luogo varchar(20),
     Tipologia varchar(15) not null,
     NomeEnte varchar(20),
     Cognome varchar(20),
     Resoconto longtext,
     Capo_Referente integer not null,
     constraint IDSERVIZIO primary key (Nome, DataInizio),
     constraint FKOffre_ID unique (Associato_Clan));

create table SESTIGLIA (
     Nome varchar(20) not null,
     Capo_Ses integer not null,
     Vice_Ses integer not null,
     Motto varchar(40) not null,
     constraint IDSESTIGLIA primary key (Nome),
     constraint FKCapo_Sestiglia_ID unique (Capo_Ses),
     constraint FKVicecapo_Sestiglia_ID unique (Vice_Ses)
);

create table SQUADRIGLIA (
     Nome varchar(20) not null,
     Capo_Sq integer not null,
     Vice_Sq integer not null,
     Motto varchar(40) not null,
     Sesso char(1) not null,
     constraint IDSQUADRIGLIA primary key (Nome),
     constraint FKCapo_SQ_ID unique (Capo_Sq),
     constraint FKVicecapo_SQ_ID unique (Vice_Sq));

CREATE TABLE SQUADRIGLIA_MEMBRO (
    Nome_Squadriglia VARCHAR(20) NOT NULL,
    CodMembro integer not null,
    CONSTRAINT FKSquadriglia FOREIGN KEY (Nome_Squadriglia) REFERENCES SQUADRIGLIA(Nome),
    CONSTRAINT FKRepartaro FOREIGN KEY (CodMembro) REFERENCES REPARTARO(Associato),
    CONSTRAINT UNIQUE_SQ_MEMBRO UNIQUE (Nome_Squadriglia, CodMembro)
);
CREATE TABLE SESTIGLIA_MEMBRO (
    Nome_Sestiglia VARCHAR(20) NOT NULL,
    CodMembro integer not null,
    CONSTRAINT FKSestiglia FOREIGN KEY (Nome_Sestiglia) REFERENCES SESTIGLIA(Nome),
    CONSTRAINT FKLupetto FOREIGN KEY (CodMembro) REFERENCES LUPETTO(Associato),
    CONSTRAINT UNIQUE_SES_MEMBRO UNIQUE (Nome_Sestiglia, CodMembro)
);

#Inserimenti

insert into branca(NomeBranca, Numero_membri, FondoCassa)
values("Lupetti", 0, 100),
      ("Reparto", 0, 100),
      ("Noviziato", 0, 100),
      ("Clan", 0, 100),
      ("Coca", 0, 100);

-- Capi (10 record)
INSERT INTO ASSOCIATO (CodAssociato, NomeBranca, Recapito_tel, Mail, Nome, Cognome, Codice_fiscale, Eta, Sesso)
VALUES 
(1, 'CoCa', '1234567890', 'capo1@mail.com', 'CapoNome1', 'CapoCognome1', 'RSSMRA80A01H501U', 30, 'M'),
(2, 'CoCa', '1234567891', 'capo2@mail.com', 'CapoNome2', 'CapoCognome2', 'RSSMRA80A02H501U', 28, 'F'),
(3, 'CoCa', '1234567892', 'capo3@mail.com', 'CapoNome3', 'CapoCognome3', 'RSSMRA80A03H501U', 35, 'M'),
(4, 'CoCa', '1234567893', 'capo4@mail.com', 'CapoNome4', 'CapoCognome4', 'RSSMRA80A04H501U', 40, 'F'),
(5, 'CoCa', '1234567894', 'capo5@mail.com', 'CapoNome5', 'CapoCognome5', 'RSSMRA80A05H501U', 45, 'M'),
(6, 'CoCa', '1234567895', 'capo6@mail.com', 'CapoNome6', 'CapoCognome6', 'RSSMRA80A06H501U', 50, 'F'),
(7, 'CoCa', '1234567896', 'capo7@mail.com', 'CapoNome7', 'CapoCognome7', 'RSSMRA80A07H501U', 27, 'M'),
(8, 'CoCa', '1234567897', 'capo8@mail.com', 'CapoNome8', 'CapoCognome8', 'RSSMRA80A08H501U', 32, 'F'),
(9, 'CoCa', '1234567898', 'capo9@mail.com', 'CapoNome9', 'CapoCognome9', 'RSSMRA80A09H501U', 37, 'M'),
(10, 'CoCa', '1234567899', 'capo10@mail.com', 'CapoNome10', 'CapoCognome10', 'RSSMRA80A10H501U', 42, 'F');
insert into capo(Associato, NomeBranca)
Values (1, "Lupetti"),
(2, "Lupetti"),
(3, "Lupetti"),
(4, "Reparto"),
(5, "Reparto"),
(6, "Reparto"),
(7, "Noviziato"),
(8, "Clan"),
(9,"Clan"),
(10, "Clan");

-- Clan (30 record)
INSERT INTO ASSOCIATO (CodAssociato, NomeBranca, Recapito_tel, Mail, Nome, Cognome, Codice_fiscale, Eta, Sesso)
VALUES 
(11, 'Clan', '2234567890', 'clan1@mail.com', 'ClanNome1', 'ClanCognome1', 'RSSMRA01B01H501U', 18, 'M'),
(12, 'Clan', '2234567891', 'clan2@mail.com', 'ClanNome2', 'ClanCognome2', 'RSSMRA01B02H501U', 19, 'F'),
(13, 'Clan', '2234567892', 'clan3@mail.com', 'ClanNome3', 'ClanCognome3', 'RSSMRA01B03H501U', 20, 'M'),
(14, 'Clan', '2234567893', 'clan4@mail.com', 'ClanNome4', 'ClanCognome4', 'RSSMRA01B04H501U', 17, 'F'),
(15, 'Clan', '2234567894', 'clan5@mail.com', 'ClanNome5', 'ClanCognome5', 'RSSMRA01B05H501U', 18, 'M'),
(16, 'Clan', '2234567895', 'clan6@mail.com', 'ClanNome6', 'ClanCognome6', 'RSSMRA01B06H501U', 19, 'F'),
(17, 'Clan', '2234567896', 'clan7@mail.com', 'ClanNome7', 'ClanCognome7', 'RSSMRA01B07H501U', 20, 'M'),
(18, 'Clan', '2234567897', 'clan8@mail.com', 'ClanNome8', 'ClanCognome8', 'RSSMRA01B08H501U', 17, 'F'),
(19, 'Clan', '2234567898', 'clan9@mail.com', 'ClanNome9', 'ClanCognome9', 'RSSMRA01B09H501U', 18, 'M'),
(20, 'Clan', '2234567899', 'clan10@mail.com', 'ClanNome10', 'ClanCognome10', 'RSSMRA01B10H501U', 19, 'F'),
(21, 'Clan', '2234567900', 'clan11@mail.com', 'ClanNome11', 'ClanCognome11', 'RSSMRA01B11H501U', 20, 'M'),
(22, 'Clan', '2234567901', 'clan12@mail.com', 'ClanNome12', 'ClanCognome12', 'RSSMRA01B12H501U', 17, 'F'),
(23, 'Clan', '2234567902', 'clan13@mail.com', 'ClanNome13', 'ClanCognome13', 'RSSMRA01B13H501U', 18, 'M'),
(24, 'Clan', '2234567903', 'clan14@mail.com', 'ClanNome14', 'ClanCognome14', 'RSSMRA01B14H501U', 19, 'F'),
(25, 'Clan', '2234567904', 'clan15@mail.com', 'ClanNome15', 'ClanCognome15', 'RSSMRA01B15H501U', 20, 'M'),
(26, 'Clan', '2234567905', 'clan16@mail.com', 'ClanNome16', 'ClanCognome16', 'RSSMRA01B16H501U', 17, 'F'),
(27, 'Clan', '2234567906', 'clan17@mail.com', 'ClanNome17', 'ClanCognome17', 'RSSMRA01B17H501U', 18, 'M'),
(28, 'Clan', '2234567907', 'clan18@mail.com', 'ClanNome18', 'ClanCognome18', 'RSSMRA01B18H501U', 19, 'F'),
(29, 'Clan', '2234567908', 'clan19@mail.com', 'ClanNome19', 'ClanCognome19', 'RSSMRA01B19H501U', 20, 'M'),
(30, 'Clan', '2234567909', 'clan20@mail.com', 'ClanNome20', 'ClanCognome20', 'RSSMRA01B20H501U', 17, 'F'),
(31, 'Clan', '2234567910', 'clan21@mail.com', 'ClanNome21', 'ClanCognome21', 'RSSMRA01B21H501U', 18, 'M'),
(32, 'Clan', '2234567911', 'clan22@mail.com', 'ClanNome22', 'ClanCognome22', 'RSSMRA01B22H501U', 19, 'F'),
(33, 'Clan', '2234567912', 'clan23@mail.com', 'ClanNome23', 'ClanCognome23', 'RSSMRA01B23H501U', 20, 'M'),
(34, 'Clan', '2234567913', 'clan24@mail.com', 'ClanNome24', 'ClanCognome24', 'RSSMRA01B24H501U', 17, 'F'),
(35, 'Clan', '2234567914', 'clan25@mail.com', 'ClanNome25', 'ClanCognome25', 'RSSMRA01B25H501U', 18, 'M'),
(36, 'Clan', '2234567915', 'clan26@mail.com', 'ClanNome26', 'ClanCognome26', 'RSSMRA01B26H501U', 19, 'F'),
(37, 'Clan', '2234567916', 'clan27@mail.com', 'ClanNome27', 'ClanCognome27', 'RSSMRA01B27H501U', 20, 'M'),
(38, 'Clan', '2234567917', 'clan28@mail.com', 'ClanNome28', 'ClanCognome28', 'RSSMRA01B28H501U', 17, 'F'),
(39, 'Clan', '2234567918', 'clan29@mail.com', 'ClanNome29', 'ClanCognome29', 'RSSMRA01B29H501U', 18, 'M'),
(40, 'Clan', '2234567919', 'clan30@mail.com', 'ClanNome30', 'ClanCognome30', 'RSSMRA01B30H501U', 19, 'F');
insert into rover_scolta(Associato)
values (11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24),
(25),
(26),
(27),
(28),
(29),
(30),
(31),
(32),
(33),
(34),
(35),
(36),
(37),
(38),
(39),
(40);

-- Noviziato (10 record)
INSERT INTO ASSOCIATO (CodAssociato, NomeBranca, Recapito_tel, Mail, Nome, Cognome, Codice_fiscale, Eta, Sesso)
VALUES 
(41, 'Noviziato', '3234567890', 'novizio1@mail.com', 'NovizioNome1', 'NovizioCognome1', 'RSSMRA02C01H501U', 16, 'M'),
(42, 'Noviziato', '3234567891', 'novizio2@mail.com', 'NovizioNome2', 'NovizioCognome2', 'RSSMRA02C02H501U', 16, 'F'),
(43, 'Noviziato', '3234567892', 'novizio3@mail.com', 'NovizioNome3', 'NovizioCognome3', 'RSSMRA02C03H501U', 16, 'M'),
(44, 'Noviziato', '3234567893', 'novizio4@mail.com', 'NovizioNome4', 'NovizioCognome4', 'RSSMRA02C04H501U', 16, 'F'),
(45, 'Noviziato', '3234567894', 'novizio5@mail.com', 'NovizioNome5', 'NovizioCognome5', 'RSSMRA02C05H501U', 16, 'M'),
(46, 'Noviziato', '3234567895', 'novizio6@mail.com', 'NovizioNome6', 'NovizioCognome6', 'RSSMRA02C06H501U', 16, 'F'),
(47, 'Noviziato', '3234567896', 'novizio7@mail.com', 'NovizioNome7', 'NovizioCognome7', 'RSSMRA02C07H501U', 16, 'M'),
(48, 'Noviziato', '3234567897', 'novizio8@mail.com', 'NovizioNome8', 'NovizioCognome8', 'RSSMRA02C08H501U', 16, 'F'),
(49, 'Noviziato', '3234567898', 'novizio9@mail.com', 'NovizioNome9', 'NovizioCognome9', 'RSSMRA02C09H501U', 16, 'M'),
(50, 'Noviziato', '3234567899', 'novizio10@mail.com', 'NovizioNome10', 'NovizioCognome10', 'RSSMRA02C10H501U', 16, 'F');
insert into novizio(Associato)
values (41),
(42),
(43),
(44),
(45),
(46),
(47),
(48),
(49),
(50);

-- Reparto (50 record)
INSERT INTO ASSOCIATO (CodAssociato, NomeBranca, Recapito_tel, Mail, Nome, Cognome, Codice_fiscale, Eta, Sesso)
VALUES 
(51, 'Reparto', '4234567890', 'reparto1@mail.com', 'RepartoNome1', 'RepartoCognome1', 'RSSMRA03D01H501U', 12, 'M'),
(52, 'Reparto', '4234567891', 'reparto2@mail.com', 'RepartoNome2', 'RepartoCognome2', 'RSSMRA03D02H501U', 13, 'F'),
(53, 'Reparto', '4234567892', 'reparto3@mail.com', 'RepartoNome3', 'RepartoCognome3', 'RSSMRA03D03H501U', 14, 'M'),
(54, 'Reparto', '4234567893', 'reparto4@mail.com', 'RepartoNome4', 'RepartoCognome4', 'RSSMRA03D04H501U', 15, 'F'),
(55, 'Reparto', '4234567894', 'reparto5@mail.com', 'RepartoNome5', 'RepartoCognome5', 'RSSMRA03D05H501U', 11, 'M'),
(56, 'Reparto', '4234567895', 'reparto6@mail.com', 'RepartoNome6', 'RepartoCognome6', 'RSSMRA03D06H501U', 12, 'F'),
(57, 'Reparto', '4234567896', 'reparto7@mail.com', 'RepartoNome7', 'RepartoCognome7', 'RSSMRA03D07H501U', 13, 'M'),
(58, 'Reparto', '4234567897', 'reparto8@mail.com', 'RepartoNome8', 'RepartoCognome8', 'RSSMRA03D08H501U', 14, 'F'),
(59, 'Reparto', '4234567898', 'reparto9@mail.com', 'RepartoNome9', 'RepartoCognome9', 'RSSMRA03D09H501U', 15, 'M'),
(60, 'Reparto', '4234567899', 'reparto10@mail.com', 'RepartoNome10', 'RepartoCognome10', 'RSSMRA03D10H501U', 11, 'F'),
(61, 'Reparto', '4234567900', 'reparto11@mail.com', 'RepartoNome11', 'RepartoCognome11', 'RSSMRA03D11H501U', 12, 'M'),
(62, 'Reparto', '4234567901', 'reparto12@mail.com', 'RepartoNome12', 'RepartoCognome12', 'RSSMRA03D12H501U', 13, 'F'),
(63, 'Reparto', '4234567902', 'reparto13@mail.com', 'RepartoNome13', 'RepartoCognome13', 'RSSMRA03D13H501U', 14, 'M'),
(64, 'Reparto', '4234567903', 'reparto14@mail.com', 'RepartoNome14', 'RepartoCognome14', 'RSSMRA03D14H501U', 15, 'F'),
(65, 'Reparto', '4234567904', 'reparto15@mail.com', 'RepartoNome15', 'RepartoCognome15', 'RSSMRA03D15H501U', 11, 'M'),
(66, 'Reparto', '4234567905', 'reparto16@mail.com', 'RepartoNome16', 'RepartoCognome16', 'RSSMRA03D16H501U', 12, 'F'),
(67, 'Reparto', '4234567906', 'reparto17@mail.com', 'RepartoNome17', 'RepartoCognome17', 'RSSMRA03D17H501U', 13, 'M'),
(68, 'Reparto', '4234567907', 'reparto18@mail.com', 'RepartoNome18', 'RepartoCognome18', 'RSSMRA03D18H501U', 14, 'F'),
(69, 'Reparto', '4234567908', 'reparto19@mail.com', 'RepartoNome19', 'RepartoCognome19', 'RSSMRA03D19H501U', 15, 'M'),
(70, 'Reparto', '4234567909', 'reparto20@mail.com', 'RepartoNome20', 'RepartoCognome20', 'RSSMRA03D20H501U', 11, 'F'),
(71, 'Reparto', '4234567910', 'reparto21@mail.com', 'RepartoNome21', 'RepartoCognome21', 'RSSMRA03D21H501U', 12, 'M'),
(72, 'Reparto', '4234567911', 'reparto22@mail.com', 'RepartoNome22', 'RepartoCognome22', 'RSSMRA03D22H501U', 13, 'F'),
(73, 'Reparto', '4234567912', 'reparto23@mail.com', 'RepartoNome23', 'RepartoCognome23', 'RSSMRA03D23H501U', 14, 'M'),
(74, 'Reparto', '4234567913', 'reparto24@mail.com', 'RepartoNome24', 'RepartoCognome24', 'RSSMRA03D24H501U', 15, 'F'),
(75, 'Reparto', '4234567914', 'reparto25@mail.com', 'RepartoNome25', 'RepartoCognome25', 'RSSMRA03D25H501U', 11, 'M'),
(76, 'Reparto', '4234567915', 'reparto26@mail.com', 'RepartoNome26', 'RepartoCognome26', 'RSSMRA03D26H501U', 12, 'F'),
(77, 'Reparto', '4234567916', 'reparto27@mail.com', 'RepartoNome27', 'RepartoCognome27', 'RSSMRA03D27H501U', 13, 'M'),
(78, 'Reparto', '4234567917', 'reparto28@mail.com', 'RepartoNome28', 'RepartoCognome28', 'RSSMRA03D28H501U', 14, 'F'),
(79, 'Reparto', '4234567918', 'reparto29@mail.com', 'RepartoNome29', 'RepartoCognome29', 'RSSMRA03D29H501U', 15, 'M'),
(80, 'Reparto', '4234567919', 'reparto30@mail.com', 'RepartoNome30', 'RepartoCognome30', 'RSSMRA03D30H501U', 11, 'F'),
(81, 'Reparto', '4234567920', 'reparto31@mail.com', 'RepartoNome31', 'RepartoCognome31', 'RSSMRA03D31H501U', 12, 'M'),
(82, 'Reparto', '4234567921', 'reparto32@mail.com', 'RepartoNome32', 'RepartoCognome32', 'RSSMRA03D32H501U', 13, 'F'),
(83, 'Reparto', '4234567922', 'reparto33@mail.com', 'RepartoNome33', 'RepartoCognome33', 'RSSMRA03D33H501U', 14, 'M'),
(84, 'Reparto', '4234567923', 'reparto34@mail.com', 'RepartoNome34', 'RepartoCognome34', 'RSSMRA03D34H501U', 15, 'F'),
(85, 'Reparto', '4234567924', 'reparto35@mail.com', 'RepartoNome35', 'RepartoCognome35', 'RSSMRA03D35H501U', 11, 'M'),
(86, 'Reparto', '4234567925', 'reparto36@mail.com', 'RepartoNome36', 'RepartoCognome36', 'RSSMRA03D36H501U', 12, 'F'),
(87, 'Reparto', '4234567926', 'reparto37@mail.com', 'RepartoNome37', 'RepartoCognome37', 'RSSMRA03D37H501U', 13, 'M'),
(88, 'Reparto', '4234567927', 'reparto38@mail.com', 'RepartoNome38', 'RepartoCognome38', 'RSSMRA03D38H501U', 14, 'F'),
(89, 'Reparto', '4234567928', 'reparto39@mail.com', 'RepartoNome39', 'RepartoCognome39', 'RSSMRA03D39H501U', 15, 'M'),
(90, 'Reparto', '4234567929', 'reparto40@mail.com', 'RepartoNome40', 'RepartoCognome40', 'RSSMRA03D40H501U', 11, 'F'),
(91, 'Reparto', '4234567930', 'reparto41@mail.com', 'RepartoNome41', 'RepartoCognome41', 'RSSMRA03D41H501U', 12, 'M'),
(92, 'Reparto', '4234567931', 'reparto42@mail.com', 'RepartoNome42', 'RepartoCognome42', 'RSSMRA03D42H501U', 13, 'F'),
(93, 'Reparto', '4234567932', 'reparto43@mail.com', 'RepartoNome43', 'RepartoCognome43', 'RSSMRA03D43H501U', 14, 'M'),
(94, 'Reparto', '4234567933', 'reparto44@mail.com', 'RepartoNome44', 'RepartoCognome44', 'RSSMRA03D44H501U', 15, 'F'),
(95, 'Reparto', '4234567934', 'reparto45@mail.com', 'RepartoNome45', 'RepartoCognome45', 'RSSMRA03D45H501U', 11, 'M'),
(96, 'Reparto', '4234567935', 'reparto46@mail.com', 'RepartoNome46', 'RepartoCognome46', 'RSSMRA03D46H501U', 12, 'F'),
(97, 'Reparto', '4234567936', 'reparto47@mail.com', 'RepartoNome47', 'RepartoCognome47', 'RSSMRA03D47H501U', 13, 'M'),
(98, 'Reparto', '4234567937', 'reparto48@mail.com', 'RepartoNome48', 'RepartoCognome48', 'RSSMRA03D48H501U', 14, 'F'),
(99, 'Reparto', '4234567938', 'reparto49@mail.com', 'RepartoNome49', 'RepartoCognome49', 'RSSMRA03D49H501U', 15, 'M'),
(100, 'Reparto', '4234567939', 'reparto50@mail.com', 'RepartoNome50', 'RepartoCognome50', 'RSSMRA03D50H501U', 11, 'F');
INSERT INTO REPARTARO (Associato)
VALUES 
(51),
(52),
(53),
(54),
(55),
(56),
(57),
(58),
(59),
(60),
(61),
(62),
(63),
(64),
(65),
(66),
(67),
(68),
(69),
(70),
(71),
(72),
(73),
(74),
(75),
(76),
(77),
(78),
(79),
(80),
(81),
(82),
(83),
(84),
(85),
(86),
(87),
(88),
(89),
(90),
(91),
(92),
(93),
(94),
(95),
(96),
(97),
(98),
(99),
(100);
-- Lupetti (50 record)
INSERT INTO ASSOCIATO (CodAssociato, NomeBranca, Recapito_tel, Mail, Nome, Cognome, Codice_fiscale, Eta, Sesso)
VALUES 
(101, 'Lupetti', '5234567890', 'lupetto1@mail.com', 'LupettoNome1', 'LupettoCognome1', 'RSSMRA04E01H501U', 8, 'M'),
(102, 'Lupetti', '5234567891', 'lupetto2@mail.com', 'LupettoNome2', 'LupettoCognome2', 'RSSMRA04E02H501U', 9, 'F'),
(103, 'Lupetti', '5234567892', 'lupetto3@mail.com', 'LupettoNome3', 'LupettoCognome3', 'RSSMRA04E03H501U', 10, 'M'),
(104, 'Lupetti', '5234567893', 'lupetto4@mail.com', 'LupettoNome4', 'LupettoCognome4', 'RSSMRA04E04H501U', 7, 'F'),
(105, 'Lupetti', '5234567894', 'lupetto5@mail.com', 'LupettoNome5', 'LupettoCognome5', 'RSSMRA04E05H501U', 8, 'M'),
(106, 'Lupetti', '5234567895', 'lupetto6@mail.com', 'LupettoNome6', 'LupettoCognome6', 'RSSMRA04E06H501U', 9, 'F'),
(107, 'Lupetti', '5234567896', 'lupetto7@mail.com', 'LupettoNome7', 'LupettoCognome7', 'RSSMRA04E07H501U', 10, 'M'),
(108, 'Lupetti', '5234567897', 'lupetto8@mail.com', 'LupettoNome8', 'LupettoCognome8', 'RSSMRA04E08H501U', 7, 'F'),
(109, 'Lupetti', '5234567898', 'lupetto9@mail.com', 'LupettoNome9', 'LupettoCognome9', 'RSSMRA04E09H501U', 8, 'M'),
(110, 'Lupetti', '5234567899', 'lupetto10@mail.com', 'LupettoNome10', 'LupettoCognome10', 'RSSMRA04E10H501U', 9, 'F'),
(111, 'Lupetti', '5234567900', 'lupetto11@mail.com', 'LupettoNome11', 'LupettoCognome11', 'RSSMRA04E11H501U', 10, 'M'),
(112, 'Lupetti', '5234567901', 'lupetto12@mail.com', 'LupettoNome12', 'LupettoCognome12', 'RSSMRA04E12H501U', 7, 'F'),
(113, 'Lupetti', '5234567902', 'lupetto13@mail.com', 'LupettoNome13', 'LupettoCognome13', 'RSSMRA04E13H501U', 8, 'M'),
(114, 'Lupetti', '5234567903', 'lupetto14@mail.com', 'LupettoNome14', 'LupettoCognome14', 'RSSMRA04E14H501U', 9, 'F'),
(115, 'Lupetti', '5234567904', 'lupetto15@mail.com', 'LupettoNome15', 'LupettoCognome15', 'RSSMRA04E15H501U', 10, 'M'),
(116, 'Lupetti', '5234567905', 'lupetto16@mail.com', 'LupettoNome16', 'LupettoCognome16', 'RSSMRA04E16H501U', 7, 'F'),
(117, 'Lupetti', '5234567906', 'lupetto17@mail.com', 'LupettoNome17', 'LupettoCognome17', 'RSSMRA04E17H501U', 8, 'M'),
(118, 'Lupetti', '5234567907', 'lupetto18@mail.com', 'LupettoNome18', 'LupettoCognome18', 'RSSMRA04E18H501U', 9, 'F'),
(119, 'Lupetti', '5234567908', 'lupetto19@mail.com', 'LupettoNome19', 'LupettoCognome19', 'RSSMRA04E19H501U', 10, 'M'),
(120, 'Lupetti', '5234567909', 'lupetto20@mail.com', 'LupettoNome20', 'LupettoCognome20', 'RSSMRA04E20H501U', 7, 'F'),
(121, 'Lupetti', '5234567910', 'lupetto21@mail.com', 'LupettoNome21', 'LupettoCognome21', 'RSSMRA04E21H501U', 8, 'M'),
(122, 'Lupetti', '5234567911', 'lupetto22@mail.com', 'LupettoNome22', 'LupettoCognome22', 'RSSMRA04E22H501U', 9, 'F'),
(123, 'Lupetti', '5234567912', 'lupetto23@mail.com', 'LupettoNome23', 'LupettoCognome23', 'RSSMRA04E23H501U', 10, 'M'),
(124, 'Lupetti', '5234567913', 'lupetto24@mail.com', 'LupettoNome24', 'LupettoCognome24', 'RSSMRA04E24H501U', 7, 'F'),
(125, 'Lupetti', '5234567914', 'lupetto25@mail.com', 'LupettoNome25', 'LupettoCognome25', 'RSSMRA04E25H501U', 8, 'M'),
(126, 'Lupetti', '5234567915', 'lupetto26@mail.com', 'LupettoNome26', 'LupettoCognome26', 'RSSMRA04E26H501U', 9, 'F'),
(127, 'Lupetti', '5234567916', 'lupetto27@mail.com', 'LupettoNome27', 'LupettoCognome27', 'RSSMRA04E27H501U', 10, 'M'),
(128, 'Lupetti', '5234567917', 'lupetto28@mail.com', 'LupettoNome28', 'LupettoCognome28', 'RSSMRA04E28H501U', 7, 'F'),
(129, 'Lupetti', '5234567918', 'lupetto29@mail.com', 'LupettoNome29', 'LupettoCognome29', 'RSSMRA04E29H501U', 8, 'M'),
(130, 'Lupetti', '5234567919', 'lupetto30@mail.com', 'LupettoNome30', 'LupettoCognome30', 'RSSMRA04E30H501U', 9, 'F'),
(131, 'Lupetti', '5234567920', 'lupetto31@mail.com', 'LupettoNome31', 'LupettoCognome31', 'RSSMRA04E31H501U', 10, 'M'),
(132, 'Lupetti', '5234567921', 'lupetto32@mail.com', 'LupettoNome32', 'LupettoCognome32', 'RSSMRA04E32H501U', 7, 'F'),
(133, 'Lupetti', '5234567922', 'lupetto33@mail.com', 'LupettoNome33', 'LupettoCognome33', 'RSSMRA04E33H501U', 8, 'M'),
(134, 'Lupetti', '5234567923', 'lupetto34@mail.com', 'LupettoNome34', 'LupettoCognome34', 'RSSMRA04E34H501U', 9, 'F'),
(135, 'Lupetti', '5234567924', 'lupetto35@mail.com', 'LupettoNome35', 'LupettoCognome35', 'RSSMRA04E35H501U', 10, 'M'),
(136, 'Lupetti', '5234567925', 'lupetto36@mail.com', 'LupettoNome36', 'LupettoCognome36', 'RSSMRA04E36H501U', 7, 'F'),
(137, 'Lupetti', '5234567926', 'lupetto37@mail.com', 'LupettoNome37', 'LupettoCognome37', 'RSSMRA04E37H501U', 8, 'M'),
(138, 'Lupetti', '5234567927', 'lupetto38@mail.com', 'LupettoNome38', 'LupettoCognome38', 'RSSMRA04E38H501U', 9, 'F'),
(139, 'Lupetti', '5234567928', 'lupetto39@mail.com', 'LupettoNome39', 'LupettoCognome39', 'RSSMRA04E39H501U', 10, 'M'),
(140, 'Lupetti', '5234567929', 'lupetto40@mail.com', 'LupettoNome40', 'LupettoCognome40', 'RSSMRA04E40H501U', 7, 'F'),
(141, 'Lupetti', '5234567930', 'lupetto41@mail.com', 'LupettoNome41', 'LupettoCognome41', 'RSSMRA04E41H501U', 8, 'M'),
(142, 'Lupetti', '5234567931', 'lupetto42@mail.com', 'LupettoNome42', 'LupettoCognome42', 'RSSMRA04E42H501U', 9, 'F'),
(143, 'Lupetti', '5234567932', 'lupetto43@mail.com', 'LupettoNome43', 'LupettoCognome43', 'RSSMRA04E43H501U', 10, 'M'),
(144, 'Lupetti', '5234567933', 'lupetto44@mail.com', 'LupettoNome44', 'LupettoCognome44', 'RSSMRA04E44H501U', 7, 'F'),
(145, 'Lupetti', '5234567934', 'lupetto45@mail.com', 'LupettoNome45', 'LupettoCognome45', 'RSSMRA04E45H501U', 8, 'M'),
(146, 'Lupetti', '5234567935', 'lupetto46@mail.com', 'LupettoNome46', 'LupettoCognome46', 'RSSMRA04E46H501U', 9, 'F'),
(147, 'Lupetti', '5234567936', 'lupetto47@mail.com', 'LupettoNome47', 'LupettoCognome47', 'RSSMRA04E47H501U', 10, 'M'),
(148, 'Lupetti', '5234567937', 'lupetto48@mail.com', 'LupettoNome48', 'LupettoCognome48', 'RSSMRA04E48H501U', 7, 'F'),
(149, 'Lupetti', '5234567938', 'lupetto49@mail.com', 'LupettoNome49', 'LupettoCognome49', 'RSSMRA04E49H501U', 8, 'M');
INSERT INTO Lupetto (Associato)
VALUES (101),
(102),
(103),
(104),
(105),
(106),
(107),
(108),
(109),
(110),
(111),
(112),
(113),
(114),
(115),
(116),
(117),
(118),
(119),
(120),
(121),
(122),
(123),
(124),
(125),
(126),
(127),
(128),
(129),
(130),
(131),
(132),
(133),
(134),
(135),
(136),
(137),
(138),
(139),
(140),
(141),
(142),
(143),
(144),
(145),
(146),
(147),
(148),
(149);
insert into sestiglia(Nome,Capo_Ses,Vice_Ses,Motto)
	values("Neri", 102, 107, "hooooog riiiider");
insert into sestiglia_membro(Nome_Sestiglia,CodMembro)
	values("Neri", 101),
		  ("Neri", 102),
          ("Neri", 103),
          ("Neri", 104);
          
insert into sestiglia(Nome,Capo_Ses,Vice_Ses,Motto)
	values("Bianchi", 111, 114, "300 black man, for only two pounds, yes sir");
insert into sestiglia_membro(Nome_Sestiglia,CodMembro)
	values("Bianchi", 105),
			("Bianchi", 106),
            ("Bianchi", 108),
            ("Bianchi", 109),
            ("Bianchi", 110),
            ("Bianchi", 112);
insert into squadriglia(Nome, Capo_Sq, Vice_Sq, Motto, Sesso)
values ("Cervi", 69, 93, "Cervi! Senza paura! Nei Boschi dell'avventura!!", "M"),
		("Aquile", 64, 68, "Aquile! Sulle ali della; Libertà!", "F");
insert into squadriglia_membro(Nome_Squadriglia, CodMembro)
values ("Cervi", 51),
		("Cervi", 53),
        ("Cervi", 55),
        ("Cervi", 57),
        ("Cervi", 59),
        ("Cervi", 61),
        ("Aquile", 52),
        ("Aquile", 54),
        ("Aquile", 56),
        ("Aquile", 58),
        ("Aquile", 60);



INSERT INTO ESTERNO (Ente, Nome, Cognome, Recapito_Tel, Sesso) VALUES
('Croce Rossa', 'Mario', 'Cappelleti', '1234567890', 'M'),
('Medici Senza Frontiere', 'Luigi', 'Verdi', '1234567891', 'M'),
('Caritas', 'Giovanni', 'Belletti', '1234567892', 'M'),
('Save the Children', 'Paolo', 'Neri', '1234567893', 'M'),
('Emergency', 'Francesca', 'Spertoli', '1234567894', 'F'),
('Greenpeace', 'Laura', 'Leoni', '1234567895', 'F'),
('Amnesty International', 'Anna', 'Ferrani', '1234567896', 'F'),
('UNICEF', 'Elena', 'Belli', '1234567897', 'F'),
('Oxfam', 'Giulia', 'Natal', '1234567898', 'F'),
('WWF', 'Federica', 'Galli', '1234567899', 'F'),
('ActionAid', 'Roberto', 'Osvaldi', '1234567900', 'M'),
('Fondazione Telethon', 'Marco', 'Vannati', '1234567901', 'M');

Insert into servizi_a_giro(Nome, Descrizione)
values ("Quaderno di bordo", "Descrizione a tema o in modo particolare della precedente attività"),
		("Pulizia sede", "Tenere puliti gli spazi comuni"),
        ("Pulizia bagni", "Dare una pulita ai bagni"),
        ("Vangelo", "Preparare un momento di preghiera comunitaria"),
        ("Preparazione giochi", "Preparare un gioco a cui parteciperà tutto il gruppo"),
        ("Riposo", "Prendetevi una settimana libera"); 
INSERT INTO svolgimento_servizio(NomeSquadriglia, NomeServizio, `Data`)
	values ("Cervi", "Quaderno di bordo", "2024-06-23"),
			("Cervi", "Pulizia sede", "2024-06-10"),
            ("Aquile", "Pulizia sede", "2024-06-23"),
            ("Aquile", "Pulizia Bagni", "2024-06-10");
INSERT INTO ATTIVITA (NomeBranca, `Data`, Ora, Descrizione, DataFine, Luogo, Materiale, Quota) VALUES
		('Lupetti', '2024-05-15 10:00:00', '2024-05-15 11:00:00', 'Caccia al tesoro nel parco', '2024-05-15 12:00:00', 'Parco Comunale', 'Mappa del tesoro, Borse', 0.00),
		('Lupetti', '2024-04-10 14:00:00', '2024-04-10 16:00:00', 'Laboratorio di disegno', '2024-04-10 17:00:00', 'Sala Ricreativa', 'Colori, Carta', 0.00),
		('Lupetti', '2024-03-05 09:00:00', '2024-03-05 10:00:00', 'Giochi all\'aperto', '2024-03-05 11:00:00', 'Cortile della scuola', 'Palloni, Coni', 5.00),
		('Lupetti', '2024-02-20 11:00:00', '2024-02-20 13:00:00', 'Attività di lettura', '2024-02-20 14:00:00', 'Biblioteca', 'Libri illustrati', 0.00),
		('Lupetti', '2024-01-15 15:00:00', '2024-01-15 17:00:00', 'Laboratorio di scienze', '2024-01-15 18:00:00', 'Sala Laboratorio', 'Microscopi, Campioni', 0.00),
		('Lupetti', '2023-12-10 12:00:00', '2023-12-10 13:00:00', 'Attività di giardinaggio', '2023-12-10 14:00:00', 'Giardino', 'Semi, Attrezzi da giardinaggio', 0.00),
		('Lupetti', '2023-11-05 10:00:00', '2023-11-05 11:00:00', 'Escursione nel bosco', '2023-11-05 12:00:00', 'Bosco Comunale', 'Zaini, Bussole', 0.00),
		('Reparto', '2024-06-15 09:00:00', '2024-06-15 11:00:00', 'Gara di orientamento', '2024-06-15 12:00:00', 'Montagna', 'Mappe, Bussole', 10.00),
		('Reparto', '2024-05-20 14:00:00', '2024-05-20 16:00:00', 'Costruzione di un ponte di corda', '2024-05-20 17:00:00', 'Parco Avventura', 'Corde, Moschettoni', 15.00),
		('Reparto', '2024-04-25 08:00:00', '2024-04-25 10:00:00', 'Escursione in bicicletta', '2024-04-25 12:00:00', 'Pista Ciclabile', 'Biciclette, Caschi', 5.00),
		('Reparto', '2024-03-30 11:00:00', '2024-03-30 13:00:00', 'Campionato di cucina', '2024-03-30 14:00:00', 'Cucina Scout', 'Ingredienti, Attrezzi da cucina', 0.00),
		('Reparto', '2024-02-14 13:00:00', '2024-02-14 15:00:00', 'Giornata di arrampicata', '2024-02-14 16:00:00', 'Palestra di arrampicata', 'Imbragature, Scarpette', 0.00),
		('Reparto', '2024-01-10 15:00:00', '2024-01-10 17:00:00', 'Workshop di fotografia', '2024-01-10 18:00:00', 'Centro Culturale', 'Fotocamere, Laptop', 0.00),
		('Reparto', '2023-12-05 09:00:00', '2023-12-05 11:00:00', 'Torneo di calcio', '2023-12-05 12:00:00', 'Campo Sportivo', 'Palloni, Divise', 0.00),
		('Reparto', '2023-11-01 16:00:00', '2023-11-01 18:00:00', 'Attività di soccorso', '2023-11-01 19:00:00', 'Sala Conferenze', 'Manichini, Kit di primo soccorso', 0.00),
		('Noviziato', '2024-06-01 08:00:00', '2024-06-01 10:00:00', 'Progetto di volontariato', '2024-06-01 11:00:00', 'Centro Anziani', 'Volantini, Materiale informativo', 0.00),
		('Noviziato', '2024-05-10 11:00:00', '2024-05-10 13:00:00', 'Seminario sulla sostenibilità', '2024-05-10 14:00:00', 'Auditorium', 'Presentazioni, Brochure', 4.00),
		('Noviziato', '2024-04-05 09:00:00', '2024-04-05 11:00:00', 'Incontro con gli esperti', '2024-04-05 12:00:00', 'Sala Riunioni', 'Tavola rotonda, Discussioni', 0.00),
		('Noviziato', '2024-03-10 14:00:00', '2024-03-10 16:00:00', 'Laboratorio di teatro', '2024-03-10 17:00:00', 'Teatro Comunale', 'Costumi, Scenografie', 0.00),
		('Noviziato', '2024-02-01 12:00:00', '2024-02-01 14:00:00', 'Corso di leadership', '2024-02-01 15:00:00', 'Sala Conferenze', 'Materiali didattici, Slide', 0.00),
		('Clan', '2024-06-20 09:00:00', '2024-06-20 11:00:00', 'Progetto di costruzione', '2024-06-20 12:00:00', 'Cantiere', 'Attrezzi, Materiali da costruzione', 20.00),
		('Clan', '2024-05-25 13:00:00', '2024-05-25 15:00:00', 'Giornata di orientamento', '2024-05-25 16:00:00', 'Parco Nazionale', 'Mappe, GPS', 20.00),
		('Clan', '2024-04-15 10:00:00', '2024-04-15 12:00:00', 'Laboratorio di robotica', '2024-04-15 13:00:00', 'Laboratorio Tecnologico', 'Kit di robotica, Computer', 0.00),
		('Clan', '2024-03-25 14:00:00', '2024-03-25 16:00:00', 'Incontro sulla cittadinanza attiva', '2024-03-25 17:00:00', 'Sala Comunale', 'Volantini, Presentazioni', 0.00),
		('Clan', '2024-02-18 11:00:00', '2024-02-18 13:00:00', 'Progetto di agricoltura', '2024-02-18 14:00:00', 'Fattoria', 'Attrezzi agricoli, Semi', 0.00),
		('Clan', '2024-01-20 09:00:00', '2024-01-20 11:00:00', 'Corso di cucina internazionale', '2024-01-20 12:00:00', 'Cucina Scout', 'Ingredienti, Ricette', 10.00),
		('Clan', '2023-12-15 15:00:00', '2023-12-15 17:00:00', 'Giornata di pulizia del parco', '2023-12-15 18:00:00', 'Parco Comunale', 'Sacchi della spazzatura, Guanti', 0.00),
		('Clan', '2023-11-10 08:00:00', '2023-11-10 10:00:00', 'Escursione in montagna', '2023-11-10 12:00:00', 'Montagna', 'Zaini, Equipaggiamento da escursione', 6.00);
INSERT INTO PARTECIPAZIONE (Associato, NomeBranca, `Data`, Descrizione, Numero_Stelle) VALUES
(101, 'Lupetti', '2024-05-15 10:00:00', 'Partecipazione alla caccia al tesoro nel parco', 4),
(102, 'Lupetti', '2024-04-10 14:00:00', 'Partecipazione al laboratorio di disegno', 5),
(103, 'Lupetti', '2024-03-05 09:00:00', 'Partecipazione ai giochi all\'aperto', 3),
(104, 'Lupetti', '2024-02-20 11:00:00', 'Partecipazione all\'attività di lettura', 2),
(105, 'Lupetti', '2024-01-15 15:00:00', 'Partecipazione al laboratorio di scienze', 4),
(106, 'Lupetti', '2023-12-10 12:00:00', 'Partecipazione all\'attività di giardinaggio', 5),
(107, 'Lupetti', '2023-11-05 10:00:00', 'Partecipazione all\'escursione nel bosco', 3),
(108, 'Lupetti', '2024-05-15 10:00:00', 'Partecipazione alla caccia al tesoro nel parco', 4),
(109, 'Lupetti', '2024-04-10 14:00:00', 'Partecipazione al laboratorio di disegno', 4),
(110, 'Lupetti', '2024-03-05 09:00:00', 'Partecipazione ai giochi all\'aperto', 3),
(111, 'Lupetti', '2024-02-20 11:00:00', 'Partecipazione all\'attività di lettura', 2),
(112, 'Lupetti', '2024-01-15 15:00:00', 'Partecipazione al laboratorio di scienze', 3),
(113, 'Lupetti', '2023-12-10 12:00:00', 'Partecipazione all\'attività di giardinaggio', 2),
(114, 'Lupetti', '2023-11-05 10:00:00', 'Partecipazione all\'escursione nel bosco', 5),
(115, 'Lupetti', '2024-05-15 10:00:00', 'Partecipazione alla caccia al tesoro nel parco', 4),
(116, 'Lupetti', '2024-04-10 14:00:00', 'Partecipazione al laboratorio di disegno', 5),
(117, 'Lupetti', '2024-03-05 09:00:00', 'Partecipazione ai giochi all\'aperto', 3),
(118, 'Lupetti', '2024-02-20 11:00:00', 'Partecipazione all\'attività di lettura', 2),
(119, 'Lupetti', '2024-01-15 15:00:00', 'Partecipazione al laboratorio di scienze', 4),
(120, 'Lupetti', '2023-12-10 12:00:00', 'Partecipazione all\'attività di giardinaggio', 5),

(51, 'Reparto', '2024-06-15 09:00:00', 'Partecipazione alla gara di orientamento', 3),
(52, 'Reparto', '2024-05-20 14:00:00', 'Partecipazione alla costruzione di un ponte di corda', 4),
(53, 'Reparto', '2024-04-25 08:00:00', 'Partecipazione all\'escursione in bicicletta', 5),
(54, 'Reparto', '2024-03-30 11:00:00', 'Partecipazione al campionato di cucina', 2),
(55, 'Reparto', '2024-02-14 13:00:00', 'Partecipazione alla giornata di arrampicata', 3),
(56, 'Reparto', '2024-01-10 15:00:00', 'Partecipazione al workshop di fotografia', 4),
(57, 'Reparto', '2023-12-05 09:00:00', 'Partecipazione al torneo di calcio', 2),
(58, 'Reparto', '2023-11-01 16:00:00', 'Partecipazione all\'attività di soccorso', 5),
(59, 'Reparto', '2024-06-15 09:00:00', 'Partecipazione alla gara di orientamento', 3),
(60, 'Reparto', '2024-05-20 14:00:00', 'Partecipazione alla costruzione di un ponte di corda', 4),
(61, 'Reparto', '2024-04-25 08:00:00', 'Partecipazione all\'escursione in bicicletta', 3),
(62, 'Reparto', '2024-03-30 11:00:00', 'Partecipazione al campionato di cucina', 5),
(63, 'Reparto', '2024-02-14 13:00:00', 'Partecipazione alla giornata di arrampicata', 2),
(64, 'Reparto', '2024-01-10 15:00:00', 'Partecipazione al workshop di fotografia', 4),
(65, 'Reparto', '2023-12-05 09:00:00', 'Partecipazione al torneo di calcio', 2),
(66, 'Reparto', '2023-11-01 16:00:00', 'Partecipazione all\'attività di soccorso', 4),

(41, 'Noviziato', '2024-06-01 08:00:00', 'Partecipazione al progetto di volontariato', 3),
(42, 'Noviziato', '2024-05-10 11:00:00', 'Partecipazione al seminario sulla sostenibilità', 4),
(43, 'Noviziato', '2024-04-05 09:00:00', 'Partecipazione all\'incontro con gli esperti', 5),
(44, 'Noviziato', '2024-03-10 14:00:00', 'Partecipazione al laboratorio di teatro', 3),
(45, 'Noviziato', '2024-02-01 12:00:00', 'Partecipazione al corso di leadership', 4),

(11, 'Clan', '2024-06-20 09:00:00', 'Partecipazione al progetto di costruzione', 5),
(12, 'Clan', '2024-05-25 13:00:00', 'Partecipazione alla giornata di orientamento', 4),
(13, 'Clan', '2024-04-15 10:00:00', 'Partecipazione al laboratorio di robotica', 5),
(14, 'Clan', '2024-03-25 14:00:00', 'Partecipazione all\'incontro sulla cittadinanza attiva', 3),
(15, 'Clan', '2024-02-18 11:00:00', 'Partecipazione al progetto di agricoltura', 4),
(16, 'Clan', '2024-01-20 09:00:00', 'Partecipazione al corso di cucina internazionale', 5),
(17, 'Clan', '2023-12-15 15:00:00', 'Partecipazione alla giornata di pulizia del parco', 3),
(18, 'Clan', '2023-11-10 08:00:00', 'Partecipazione all\'escursione in montagna', 4),
(19, 'Clan', '2024-06-20 09:00:00', 'Partecipazione al progetto di costruzione', 5),
(20, 'Clan', '2024-05-25 13:00:00', 'Partecipazione alla giornata di orientamento', 4),
(21, 'Clan', '2024-04-15 10:00:00', 'Partecipazione al laboratorio di robotica', 5),
(22, 'Clan', '2024-03-25 14:00:00', 'Partecipazione all\'incontro sulla cittadinanza attiva', 3),
(23, 'Clan', '2024-02-18 11:00:00', 'Partecipazione al progetto di agricoltura', 4),
(24, 'Clan', '2024-01-20 09:00:00', 'Partecipazione al corso di cucina internazionale', 5),
(25, 'Clan', '2023-12-15 15:00:00', 'Partecipazione alla giornata di pulizia del parco', 3),
(26, 'Clan', '2023-11-10 08:00:00', 'Partecipazione all\'escursione in montagna', 4);

/*
INSERT INTO ASSOCIATO (CodAssociato, NomeBranca, Recapito_tel, Mail, Nome, Cognome, Codice_fiscale, Età, Sesso)
VALUES 
(1, 'Lupetti', '1234567890', 'example1@mail.com', 'Mario', 'Rossi', 'RSSMRA80A01H501U', 10, 'M');


INSERT INTO CAPO (Associato, NomeBranca)
VALUES 
(1, 'Lupetti'),
(2, 'Esploratori');

INSERT INTO ATTIVITA (NomeBranca, `Data`, Ora, Descrizione, DataFine, Luogo, Materiale, Quota)
VALUES 
('Lupetti', '2024-06-24 09:00:00', '2024-06-24 09:00:00', 'Giochi all’aperto', '2024-06-24 12:00:00', 'Parco Comunale', 'Palloni, Coni', 0),
('Reparto', '2024-07-01 10:00:00', '2024-07-01 10:00:00', 'Escursione in montagna', '2024-07-01 18:00:00', 'Monte Bianco', 'Scarponi, Zaini', 20.50);

INSERT INTO AUTO_FINANZIAMENTO (NomeBranca, `Data`, Luogo, Guadagno, Tipo)
VALUES 
('Lupetti', '2024-05-15 14:00:00', 'Piazza Centrale', 150.00, 'Vendita dolci'),
('Esploratori', '2024-05-20 10:00:00', 'Centro Commerciale', 200.00, 'Lavaggio auto');

INSERT INTO BRANCA (NomeBranca, Numero_membri, FondoCassa)
VALUES 
('Lupetti', 25, 500.00),
('Esploratori', 20, 800.00),
('Rover', 15, 300.00);

*/



-- Constraints Section
-- ___________________ 

alter table ASSOCIATO add constraint FKFa_Parte
     foreign key (NomeBranca)
     references BRANCA (NomeBranca);

alter table ATTIVITA add constraint FKOrganizza
     foreign key (NomeBranca)
     references BRANCA (NomeBranca);

alter table AUTO_FINANZIAMENTO add constraint FKFinanzia
     foreign key (NomeBranca)
     references BRANCA (NomeBranca);

alter table CAPO add constraint FKAssociato_Capo_FK
     foreign key (Associato)
     references ASSOCIATO (CodAssociato);

alter table CAPO add constraint FKE_capo_di
     foreign key (NomeBranca)
     references BRANCA (NomeBranca);

alter table LUPETTO add constraint FKAssociato_Lupetto_FK
     foreign key (Associato)
     references ASSOCIATO (CodAssociato);

alter table SVOLGIMENTO_SERVIZIO add constraint FKR_6
     foreign key (NomeServizio)
     references SERVIZI_A_GIRO (Nome);

alter table SVOLGIMENTO_SERVIZIO add constraint FKR_5
     foreign key (NomeSquadriglia)
     references SQUADRIGLIA (Nome);

alter table NOVIZIO add constraint FKAssociato_Novizio_FK
     foreign key (Associato)
     references ASSOCIATO (CodAssociato);

alter table PARTECIPAZIONE add constraint COEXPARTECIPAZIONE
     check((Descrizione  is not null and Numero_Stelle is not null)
           or (Descrizione  is null and Numero_Stelle is null)); 

alter table PARTECIPAZIONE add constraint FKPartecipa
     foreign key (NomeBranca, Data)
     references ATTIVITA (NomeBranca, Data);

alter table PARTECIPAZIONE add constraint FKAdesione
     foreign key (Associato)
     references ASSOCIATO (CodAssociato);

alter table REPARTARO add constraint FKAssociato_Repartaro_FK
     foreign key (Associato)
     references ASSOCIATO (CodAssociato);

alter table ROVER_SCOLTA add constraint FKAssociato_Clan_FK
     foreign key (Associato)
     references ASSOCIATO (CodAssociato);

alter table SERVIZIO add constraint FKGestisce_FK
     foreign key (NomeEnte, Cognome)
     references ESTERNO (Ente, Cognome);

alter table SERVIZIO add constraint FKGestisce_CHK
     check((NomeEnte is not null and Cognome is not null)
           or (NomeEnte is null and Cognome is null)); 

alter table SERVIZIO add constraint FKResponsabile
     foreign key (Capo_Referente)
     references CAPO (Associato);

alter table SERVIZIO add constraint FKOffre_FK
     foreign key (Associato_Clan)
     references ROVER_SCOLTA (Associato);

alter table SESTIGLIA add constraint FKCapo_Sestiglia_FK
     foreign key (Capo_Ses)
     references LUPETTO (Associato);

alter table SESTIGLIA add constraint FKVicecapo_Sestiglia_FK
     foreign key (Vice_Ses)
     references LUPETTO (Associato);

alter table SQUADRIGLIA add constraint FKCapo_SQ_FK
     foreign key (Capo_Sq)
     references REPARTARO (Associato);

alter table SQUADRIGLIA add constraint FKVicecapo_SQ_FK
     foreign key (Vice_Sq)
     references REPARTARO (Associato);




	
-- Index Section
-- _____________ 

