package dbscout.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.util.List;

import dbscout.data.entities.Associato;
import dbscout.data.entities.Attivita;

public class LupettiController implements FXController {

    private Controller controller;

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
    private Label boxMembri;

    @FXML
    private Label capi;

    @FXML
    private Label membri;

    @FXML
    private Label nomeAssociato;

    public void setController(Controller controller) {
        this.controller = controller;
        nomeAssociato.setText(controller.getModel().getAssociato().getNome() + " " + controller.getModel().getAssociato().getCognome());
    }

    @FXML
    void showAttivita(MouseEvent event) {
        List<Attivita> attivitas = Associato.DAO.getAttivita(controller.getConnection(), controller.getModel().getAssociato());
        boxAtt1.setText(boxAtt1.getText() + "\n" + attivitas.get(0).getDescrizione());
        boxAtt2.setText(boxAtt2.getText() + "\n" + attivitas.get(1).getDescrizione());
        boxAtt3.setText(boxAtt3.getText() + "\n" + attivitas.get(2).getDescrizione());

    }

    @FXML
    void showCapiBranca(MouseEvent event) {
        List<Associato> capi = Associato.DAO.getCapiBranca(controller.getConnection(), controller.getModel().getAssociato());
        for (Associato capo : capi) {
            boxCapi.setText(boxCapi.getText() + "\n" + capo.getNome() + " " + capo.getCognome());
        }
    }

    @FXML
    void showMembriSestiglia(MouseEvent event) {
        List<Associato> membriSest = Associato.DAO.getMembriSest(controller.getConnection(), controller.getModel().getAssociato());
        for (Associato membro : membriSest) {
            boxMembri.setText(boxMembri.getText() + "\n" + membro.getNome() + " " + membro.getCognome());
        }
    }

    @FXML
    void addRecensioneAtt1(MouseEvent event) {
        // TODO: aggiungere pagina per recensioni qua dentro
    }

    @FXML
    void addRecensioneAtt2(MouseEvent event) {
        // TODO: aggiungere pagina per recensioni qua dentro
    }

    @FXML
    void addRecensioneAtt3(MouseEvent event) {
        // TODO: aggiungere pagina per recensioni qua dentro
    }

}