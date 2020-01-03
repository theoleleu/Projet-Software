package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ObjetController {

    public javafx.scene.control.Button btnQuitter;
    public javafx.scene.control.Button btnValider;
    public javafx.scene.control.TextField tfNom;
    public javafx.scene.control.TextField tfDonnees;
    public javafx.scene.control.TextField tfDepart;
    public javafx.scene.control.TextField tfArrivee;
    public javafx.scene.control.TextField tfVitesse;
    public javafx.scene.control.Label positionX;
    public javafx.scene.control.Label positionY;
    private double Vitesse;
    private String nom;
    private String Depart;
    private String Arrivee;
    private String Donnees;


    public void setCoord(String x, String y){
        positionX.setText(x);
        positionY.setText(y);
    }

    public String getname(){
        return this.nom;
    }
    public void quitFen(ActionEvent actionEvent) {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }
    public String getDonnees(){
        return tfDonnees.getText();
    }
    public String getDepart(){
        return tfArrivee.getText();
    }
    public String getArrivee(){
        return tfDepart.getText();
    }
    public String getVitesse(){
        return this.tfVitesse.getText();
    }
    public void validerFen(ActionEvent actionEvent) {
        if(tfNom.getText().equals("") || tfDonnees.getText().equals("") || tfDepart.getText().equals("") || tfArrivee.getText().equals("") || tfVitesse.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Création Objet");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        } else {
            try {
                Integer.parseInt(tfVitesse.getText());

                System.out.println(tfNom.getText());
                this.nom=tfNom.getText();
                this.Donnees=tfDonnees.getText();
                this.Depart=tfDepart.getText();
                this.Arrivee=tfArrivee.getText();
                this.Vitesse=Double.parseDouble(tfVitesse.getText());
                System.out.println(tfDonnees.getText());
                System.out.println(tfDepart.getText());
                System.out.println(tfArrivee.getText());
                System.out.println(tfVitesse.getText());
                Stage stage = (Stage) btnValider.getScene().getWindow();
                stage.close();
            } catch (java.lang.RuntimeException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Création Objet");
                alert.setHeaderText(null);
                alert.setContentText("La vitesse doit être un entier !");
                alert.showAndWait();
            }
        }
    }


}
