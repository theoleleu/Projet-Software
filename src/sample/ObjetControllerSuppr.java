package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ObjetControllerSuppr {

    public javafx.scene.control.Button btnQuitter;
    public javafx.scene.control.Button btnValider;
    public TextField tfNom;
    private String nom;
    private boolean valide;
    public ObjetControllerSuppr(){
        this.valide=false;
    }


    public boolean isvalide(){
        return this.valide;
    }

    public String getname(){
        return this.nom;
    }
    public void quitFen(ActionEvent actionEvent) {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }

    public void validerFen(ActionEvent actionEvent) {
        if(tfNom.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Suppression Objet");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        } else {
                System.out.println(tfNom.getText());
                this.nom=tfNom.getText();

                System.out.println(tfNom.getText());
                Stage stage = (Stage) btnValider.getScene().getWindow();
                stage.close();
                this.valide=true;

        }
    }


}
