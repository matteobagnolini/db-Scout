package dbscout.controller;

import java.util.List;

import dbscout.data.entities.Associato;
import dbscout.data.entities.Attivita;
import dbscout.data.entities.Repartaro;
import dbscout.data.entities.Squadriglia;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class RepartoController implements FXController {

    private Controller controller;

    private Squadriglia sq;

    private String recensione;
    private int voto;

    @FXML
    private Label Servizi;

    @FXML
    private Label attivita;

    @FXML
    private Label boxAtt1;

    @FXML
    private Label boxAtt2;

    @FXML
    private Label boxAtt3;

    @FXML
    private Label boxCapi;

    @FXML
    private Label boxMembriSq;

    @FXML
    private Label boxServizi;

    @FXML
    private Label capiBranca;

    @FXML
    private Label membriSq;

    @FXML
    private Label nomeAssociato;
    @FXML
    private Button BackButton;
    @FXML
    void backToLogin(MouseEvent event) {
        controller.changeScene("Login.fxml");
    }
    @FXML
    void addRecensioneAtt1(MouseEvent event) {
        showRecensioneWindow(1);
    }

    @FXML
    void addRecensioneAtt2(MouseEvent event) {
        showRecensioneWindow(2);
    }

    @FXML
    void addRecensioneAtt3(MouseEvent event) {
        showRecensioneWindow(3);
    }

    @FXML
    void showAttivita(MouseEvent event) {
        List<Attivita> attivitas = controller.getModel().getAttivita();
        System.out.println(attivitas);
        boxAtt1.setText(attivitas.get(0).getDescrizione() + "\n" + attivitas.get(0).dataOra());
        boxAtt2.setText(attivitas.get(1).getDescrizione() + "\n" + attivitas.get(1).dataOra());
        boxAtt3.setText(attivitas.get(2).getDescrizione() + "\n" + attivitas.get(2).dataOra());

    }

    @FXML
    void showCapi(MouseEvent event) {
        List<Associato> capi = controller.getModel().getCapiBranca();
        for (Associato capo : capi) {
            boxCapi.setText(boxCapi.getText() + "\n" + capo.getNome() + " " + capo.getCognome());
        }
    }

    @FXML
    void showMembriSq(MouseEvent event) {
        var membri = Squadriglia.DAO.membri(controller.getConnection(), sq.getNome());
        if (!membri.isEmpty()) {
            for (Repartaro membro : membri) {
                boxMembriSq.setText(boxMembriSq.getText() + "\n" + membro.getNome() + " " + membro.getCognome());
    
            }
        } else {
            boxMembriSq.setText("Nessuna squadriglia trovata..\nChiedi ai tuoi capi di farti aggiungere..");
        }
    
    }

    @FXML
    void showServizi(MouseEvent event) {
        if (!sq.getServizi().isEmpty()) {
            boxServizi.setText(sq.getServizi().get(0).data() + " " + sq.getServizi().get(0).nome() + "\n" + sq.getServizi().get(0).descrizione());

        } else {
            boxServizi.setText("Nessun servizio trovato...");
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
        nomeAssociato.setText(controller.getModel().getAssociato().getNome() + " " + controller.getModel().getAssociato().getCognome());
        this.sq = Associato.DAO.getSquadriglia(controller.getConnection(), controller.getModel().getAssociato().getCodAssociato());
    }

    private void showRecensioneWindow(int num) {
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 400, 500);
        Stage stage = new Stage();
        
        VBox vbox = new VBox(10); // Spaziatura di 10 pixel tra i nodi
        vbox.setStyle("-fx-padding: 10;"); // Padding intorno al VBox

        Label reviewLabel = new Label("Scrivi la tua recensione:");
        TextArea reviewTextArea = new TextArea();
        reviewTextArea.setPromptText("Inserisci la tua recensione qui...");

        Label ratingLabel = new Label("Valutazione (1-5 stelle):");
        ComboBox<Integer> ratingComboBox = new ComboBox<>();
        ratingComboBox.getItems().addAll(1, 2, 3, 4, 5);
        ratingComboBox.setPromptText("Seleziona il numero di stelle");

        Button submitButton = new Button("Invia Recensione");
        submitButton.setOnAction(e -> {
            recensione = reviewTextArea.getText();
            voto = ratingComboBox.getValue();
            
            System.out.println("Recensione: " + recensione);
            System.out.println("Voto: " + voto);

            Associato.DAO.putRecensione(controller.getConnection(), controller.getModel().getAttivita().get(num-1), controller.getModel().getAssociato(), recensione, voto);
            System.out.println("Recensione inviata correttamente.");
        });

        vbox.getChildren().addAll(reviewLabel, reviewTextArea, ratingLabel, ratingComboBox, submitButton);

        borderPane.setCenter(vbox);
        stage.setScene(scene);
        stage.setTitle("Aggiungi recensione");
        stage.show();
    }

}
