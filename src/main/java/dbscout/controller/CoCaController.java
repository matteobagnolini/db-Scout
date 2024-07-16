package dbscout.controller;

import java.util.List;
import java.util.Optional;

import dbscout.data.entities.Associato;
import dbscout.data.entities.Attivita;
import dbscout.data.entities.Autofinanziamento;
import dbscout.data.entities.Partecipazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CoCaController implements FXController {

    private Controller controller;

    private Associato newAssociato;

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
        nomeAssociato.setText(controller.getModel().getAssociato().getNome() + " " + controller.getModel().getAssociato().getCognome());
    }

        @FXML
    private Label attivita;

    @FXML
    private Label boxAtt1;

    @FXML
    private Label boxAtt2;

    @FXML
    private Label boxAtt3;

    @FXML
    private Label boxFinanza;

    @FXML
    private Button buttonAddAssociato;

    @FXML
    private Button buttonAddAttivita;

    @FXML
    private Button buttonAddAutofinanziamento;

    @FXML
    private Label finanza;

    @FXML
    private Label nomeAssociato;

    @FXML
    private Button buttonClan;

    @FXML
    private Button buttonLupetti;

    @FXML
    private Button buttonNoviziato;

    @FXML
    private Button buttonReparto;

    @FXML
    void addAssociato(ActionEvent event) {
        windowAddAssociato();
    }

    @FXML
    void addAttivita(ActionEvent event) {
        windowAddAttivita();
    }

    @FXML
    void addAutofinanziamento(ActionEvent event) {
        windowAddAutofinanziamento();
    }

    @FXML
    void showAttClan(ActionEvent event) {
        List<Partecipazione> attivitas = Associato.DAO.getTop3Attivita(controller.getConnection(), "Clan");
        boxAtt1.setText(attivitas.get(0).getDescrizione() + "\n" + attivitas.get(0).dataOra());
        boxAtt2.setText(attivitas.get(1).getDescrizione() + "\n" + attivitas.get(1).dataOra());
        boxAtt3.setText(attivitas.get(2).getDescrizione() + "\n" + attivitas.get(2).dataOra());
    }

    @FXML
    void showAttLupetti(ActionEvent event) {
        List<Partecipazione> attivitas = Associato.DAO.getTop3Attivita(controller.getConnection(), "Lupetti");
        boxAtt1.setText(attivitas.get(0).getDescrizione() + "\n" + attivitas.get(0).dataOra());
        boxAtt2.setText(attivitas.get(1).getDescrizione() + "\n" + attivitas.get(1).dataOra());
        boxAtt3.setText(attivitas.get(2).getDescrizione() + "\n" + attivitas.get(2).dataOra());
    }

    @FXML
    void showAttNoviziato(ActionEvent event) {
        List<Partecipazione> attivitas = Associato.DAO.getTop3Attivita(controller.getConnection(), "Noviziato");
        boxAtt1.setText(attivitas.get(0).getDescrizione() + "\n" + attivitas.get(0).dataOra());
        boxAtt2.setText(attivitas.get(1).getDescrizione() + "\n" + attivitas.get(1).dataOra());
        boxAtt3.setText(attivitas.get(2).getDescrizione() + "\n" + attivitas.get(2).dataOra());
    }

    @FXML
    void showAttReparto(ActionEvent event) {
        List<Partecipazione> attivitas = Associato.DAO.getTop3Attivita(controller.getConnection(), "Reparto");
        boxAtt1.setText(attivitas.get(0).getDescrizione() + "\n" + attivitas.get(0).dataOra());
        boxAtt2.setText(attivitas.get(1).getDescrizione() + "\n" + attivitas.get(1).dataOra());
        boxAtt3.setText(attivitas.get(2).getDescrizione() + "\n" + attivitas.get(2).dataOra());
    }

    @FXML
    void showFinanza(MouseEvent event) {
        float conto = Associato.DAO.getFinanza(controller.getConnection(), "Lupetti");
        boxFinanza.setText("Lupetti: " + conto + " euro");
        conto = Associato.DAO.getFinanza(controller.getConnection(), "Reparto");
        boxFinanza.setText(boxFinanza.getText() + "\nReparto: " + conto + " euro");
        conto = Associato.DAO.getFinanza(controller.getConnection(), "Noviziato");
        boxFinanza.setText(boxFinanza.getText() + "\nNoviziato: " + conto + " euro");
        conto = Associato.DAO.getFinanza(controller.getConnection(), "Clan");
        boxFinanza.setText(boxFinanza.getText() + "\nClan: " + conto + " euro");
    }

    private void windowAddAssociato() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Aggiungi Nuova Entità");
        window.setMinWidth(400);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Labels and TextFields
        Label codAssociatoLabel = new Label("CodAssociato:");
        GridPane.setConstraints(codAssociatoLabel, 0, 0);
        TextField codAssociatoInput = new TextField();
        GridPane.setConstraints(codAssociatoInput, 1, 0);

        Label nomeLabel = new Label("Nome:");
        GridPane.setConstraints(nomeLabel, 0, 1);
        TextField nomeInput = new TextField();
        GridPane.setConstraints(nomeInput, 1, 1);

        Label cognomeLabel = new Label("Cognome:");
        GridPane.setConstraints(cognomeLabel, 0, 2);
        TextField cognomeInput = new TextField();
        GridPane.setConstraints(cognomeInput, 1, 2);

        Label codiceFiscaleLabel = new Label("Codice Fiscale:");
        GridPane.setConstraints(codiceFiscaleLabel, 0, 3);
        TextField codiceFiscaleInput = new TextField();
        GridPane.setConstraints(codiceFiscaleInput, 1, 3);

        Label numeroTelefonoLabel = new Label("Numero Telefono:");
        GridPane.setConstraints(numeroTelefonoLabel, 0, 4);
        TextField numeroTelefonoInput = new TextField();
        GridPane.setConstraints(numeroTelefonoInput, 1, 4);

        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 5);
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 5);

        Label etaLabel = new Label("Età:");
        GridPane.setConstraints(etaLabel, 0, 6);
        TextField etaInput = new TextField();
        GridPane.setConstraints(etaInput, 1, 6);

        Label sessoLabel = new Label("Sesso:");
        GridPane.setConstraints(sessoLabel, 0, 7);
        TextField sessoInput = new TextField();
        GridPane.setConstraints(sessoInput, 1, 7);

        Label brancaLabel = new Label("Branca:");
        GridPane.setConstraints(brancaLabel, 0, 8);
        TextField brancaInput = new TextField();
        GridPane.setConstraints(brancaInput, 1, 8);

        // Button to submit the data
        Button addButton = new Button("Aggiungi");
        GridPane.setConstraints(addButton, 1, 9);

        // Add event handler to the button
        addButton.setOnAction(e -> {
            newAssociato =  new Associato(Integer.parseInt(codAssociatoInput.getText()), numeroTelefonoInput.getText(), emailInput.getText(),
            nomeInput.getText(), cognomeInput.getText(), codiceFiscaleInput.getText(), Integer.parseInt(etaInput.getText()), sessoInput.getText().charAt(0));
            newAssociato.setBranca(brancaInput.getText());
            Associato.DAO.addAssociato(controller.getConnection(), newAssociato);
            window.close();
        });

        grid.getChildren().addAll(
            codAssociatoLabel, codAssociatoInput, nomeLabel, nomeInput, cognomeLabel, cognomeInput,
            codiceFiscaleLabel, codiceFiscaleInput, numeroTelefonoLabel, numeroTelefonoInput,
            emailLabel, emailInput, etaLabel, etaInput, sessoLabel, sessoInput, brancaLabel, brancaInput,
            addButton
        );

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();

    }

    private void windowAddAttivita() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Aggiungi Nuova Entità");
        window.setMinWidth(400);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Labels and TextFields
        Label dataLabel = new Label("Data:");
        GridPane.setConstraints(dataLabel, 0, 0);
        TextField dataInput = new TextField();
        GridPane.setConstraints(dataInput, 1, 0);

        Label dataFineLabel = new Label("Data Fine:");
        GridPane.setConstraints(dataFineLabel, 0, 1);
        TextField dataFineInput = new TextField();
        GridPane.setConstraints(dataFineInput, 1, 1);

        Label oraLabel = new Label("Ora:");
        GridPane.setConstraints(oraLabel, 0, 2);
        TextField oraInput = new TextField();
        GridPane.setConstraints(oraInput, 1, 2);

        Label descrizioneLabel = new Label("Descrizione:");
        GridPane.setConstraints(descrizioneLabel, 0, 3);
        TextField descrizioneInput = new TextField();
        GridPane.setConstraints(descrizioneInput, 1, 3);

        Label materialeLabel = new Label("Materiale:");
        GridPane.setConstraints(materialeLabel, 0, 4);
        TextField materialeInput = new TextField();
        GridPane.setConstraints(materialeInput, 1, 4);

        Label quotaLabel = new Label("Quota:");
        GridPane.setConstraints(quotaLabel, 0, 5);
        TextField quotaInput = new TextField();
        GridPane.setConstraints(quotaInput, 1, 5);

        Label luogoLabel = new Label("Luogo:");
        GridPane.setConstraints(luogoLabel, 0, 6);
        TextField luogoInput = new TextField();
        GridPane.setConstraints(luogoInput, 1, 6);

        Label brancaLabel = new Label("Branca:");
        GridPane.setConstraints(brancaLabel, 0, 7);
        TextField brancaInput = new TextField();
        GridPane.setConstraints(brancaInput, 1, 7);

        // Button to submit the data
        Button addButton = new Button("Aggiungi");
        GridPane.setConstraints(addButton, 1, 8);

        // Add event handler to the button
        addButton.setOnAction(e -> {
            Attivita att = new Attivita(brancaInput.getText(), dataInput.getText(), descrizioneInput.getText(),
            Optional.of(dataFineInput.getText()),Optional.of(materialeInput.getText()), Optional.of(Integer.parseInt(quotaInput.getText())));
            Associato.DAO.addAttivita(controller.getConnection(), att);
            window.close();
        });

        grid.getChildren().addAll(
            dataLabel, dataInput, dataFineLabel, dataFineInput, oraLabel, oraInput,
            descrizioneLabel, descrizioneInput, materialeLabel, materialeInput,
            quotaLabel, quotaInput, luogoLabel, luogoInput, brancaLabel, brancaInput, addButton
        );

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();    }

    private void windowAddAutofinanziamento() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Aggiungi Nuova Entità");
        window.setMinWidth(400);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Labels and TextFields
        Label dataLabel = new Label("Data:");
        GridPane.setConstraints(dataLabel, 0, 0);
        TextField dataInput = new TextField();
        GridPane.setConstraints(dataInput, 1, 0);

        Label luogoLabel = new Label("Luogo:");
        GridPane.setConstraints(luogoLabel, 0, 1);
        TextField luogoInput = new TextField();
        GridPane.setConstraints(luogoInput, 1, 1);

        Label guadagnoStimatoLabel = new Label("Guadagno Stimato:");
        GridPane.setConstraints(guadagnoStimatoLabel, 0, 2);
        TextField guadagnoStimatoInput = new TextField();
        GridPane.setConstraints(guadagnoStimatoInput, 1, 2);

        Label tipoLabel = new Label("Tipo:");
        GridPane.setConstraints(tipoLabel, 0, 3);
        TextField tipoInput = new TextField();
        GridPane.setConstraints(tipoInput, 1, 3);

        // Button to submit the data
        Button addButton = new Button("Aggiungi");
        GridPane.setConstraints(addButton, 1, 4);

        // Add event handler to the button
        addButton.setOnAction(e -> {
            Autofinanziamento autofin = new Autofinanziamento(dataInput.getText(), luogoInput.getText(),
            tipoInput.getText(), Integer.parseInt(guadagnoStimatoInput.getText()));
            Associato.DAO.addAutofinanziamento(controller.getConnection(), autofin);
            window.close();
        });

        grid.getChildren().addAll(
            dataLabel, dataInput, luogoLabel, luogoInput,
            guadagnoStimatoLabel, guadagnoStimatoInput, tipoLabel, tipoInput, addButton
        );

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }

}
