package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessageController {

    @FXML
    private Group root;

    public javafx.scene.control.Button btnValider;
    /*public javafx.scene.control.Button btnQuitter;
    public void quitFen() {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }*/
    public void validerFen() {
                Stage stage = (Stage) btnValider.getScene().getWindow();
                stage.close();
    }

    void messsage(String M,String entete){
        Text nom = new Text();
        nom.setX(10);
        nom.setY(24);
        nom.setFont(new Font("Arial", 12));
        nom.setText(entete);
        this.root.getChildren().add(nom);
        Text text = new Text();
        text.setX(10);
        text.setY(45);
        text.setFont(new Font("Arial", 12));
        text.setText(M);
        this.root.getChildren().add(text);
    }

    void setRoot(Group root) {
        this.root=root;
    }
}
