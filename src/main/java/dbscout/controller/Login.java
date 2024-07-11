package dbscout.controller;

import dbscout.data.entities.Associato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login {

    private Controller controller;

    @FXML
    private Button button;

    @FXML
    private TextField userId;

    @FXML
    private Label errorID;

    @FXML
    private Label cod_errore;

    @FXML
    void logIn(ActionEvent event) {
        checkUser(Integer.parseInt(userId.getText()));
    }

    private void checkUser(int id) {

        if (Associato.DAO.checkAssociatoExists(controller.getConnection(), id)) {
            final Associato loggedAssociato = Associato.DAO.getAssociatoFromId(controller.getConnection(), id);
            controller.setAssociato(loggedAssociato);
            controller.changeScene(loggedAssociato.getBranca() + ".fxml");
        } else {
            cod_errore.setVisible(true);
        }
    }

    public void setController(final Controller controller) {
        this.controller = controller;
    }

}
