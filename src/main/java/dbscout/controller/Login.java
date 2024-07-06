package dbscout.controller;

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
    void logIn(ActionEvent event) {
        checkUser(userId.getText());
    }

    private void checkUser(String id) {

        if (controller.checkUserExists(id)) {
            switch (controller.getAssociato(id).getBranca()) {
                case "Lupetti" -> {}
            }
        } else {
            errorID.setText("User ID sbagliato!");
        }
    }

    public void setController(final Controller controller) {
        this.controller = controller;
    }

}
