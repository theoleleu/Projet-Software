package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ArcController {

    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button validerButton;
    public javafx.scene.control.TextField NameArc;
    public javafx.scene.control.TextField CapacityArc;
    public javafx.scene.control.CheckBox DoubleSens;
    public javafx.scene.control.Label Erreur;
    public javafx.scene.control.Label positionX;
    public javafx.scene.control.Label positionY;
    public javafx.scene.control.TextField DebutArc;
    public javafx.scene.control.TextField FinArc;
    private int capacity;
    private String nom;
    private String debut;
    private String fin;


    public void setCoord(String x, String y){
        positionX.setText(x);
        positionY.setText(y);
    }


    public void closeFen(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    public int getcapacity(){
    return this.capacity;
    }
    public String getnom(){
        return this.nom;
    }
    public String getdebut(){
        return this.debut;
    }
    public String getfin(){
        return this.fin;
    }
    public void validerFen(ActionEvent actionEvent) {
        if(NameArc.getText().equals("") || CapacityArc.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Création Arc");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        } else {
            try {
                System.out.println(NameArc.getText());
                this.nom=NameArc.getText();
                Integer.parseInt(CapacityArc.getText());
                System.out.println(CapacityArc.getText());
                this.capacity=Integer.parseInt(CapacityArc.getText());
                System.out.println(DebutArc.getText());
                this.debut=DebutArc.getText();
                System.out.println(FinArc.getText());
                this.fin=FinArc.getText();
                if(DoubleSens.isSelected()){
                    System.out.println("Double sens : Activé");
                } else {
                    System.out.println("Double sens : Désactivé");
                }
                Stage stage = (Stage) validerButton.getScene().getWindow();
                stage.close();
            } catch (java.lang.RuntimeException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Création Arc");
                alert.setHeaderText(null);
                alert.setContentText("La capacité doit être un entier !");
                alert.showAndWait();
            }
        }
    }
}
