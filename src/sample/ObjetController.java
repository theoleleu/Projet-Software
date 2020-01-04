package sample;

import javafx.scene.control.Alert;
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
    private boolean valide;
    public ObjetController(){
        this.valide=false;
    }

    void setCoord(String x, String y){
        positionX.setText(x);
        positionY.setText(y);
    }
    boolean isvalide(){
        return this.valide;
    }

    String getname(){
        return this.nom;
    }
    public void quitFen() {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }
    String getDonnees(){
        return this.Donnees;
    }
    String getDepart(){
        return this.Depart;
    }
    String getArrivee(){
        return this.Arrivee;
    }
    Double getVitesse(){
        return this.Vitesse;
    }
    public void validerFen() {//ActionEvent actionEvent
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
                Stage stage = (Stage) btnValider.getScene().getWindow();
                stage.close();
                this.valide=true;
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
