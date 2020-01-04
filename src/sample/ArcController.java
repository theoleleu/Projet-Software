package sample;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ArcController {

    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button validerButton;
    public javafx.scene.control.TextField NameArc;
    public javafx.scene.control.TextField CapacityArc;
    public javafx.scene.control.TextField LongueurArc;
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
    private boolean valide;
    private double longueur;
    private boolean doublesens;

    public ArcController(){
        this.valide=false;
    }
    void setCoord(String x, String y){
        positionX.setText(x);
        positionY.setText(y);
    }

    boolean isvalide(){
        return this.valide;
    }
    boolean isdoublesens(){
        return this.doublesens;
    }
    public void closeFen() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    int getcapacity(){
    return this.capacity;
    }
    String getnom(){
        return this.nom;
    }
    String getdebut(){
        return this.debut;
    }
    String getfin(){
        return this.fin;
    }
    Double getlongueur(){
        return this.longueur;
    }
    public void validerFen() {//ActionEvent actionEvent
        if(NameArc.getText().equals("") || CapacityArc.equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Test Création Arc");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
        } else {
            try {
                this.nom=NameArc.getText();
                Integer.parseInt(CapacityArc.getText());
                this.longueur=Double.parseDouble(LongueurArc.getText());
                this.debut=DebutArc.getText();
                this.fin=FinArc.getText();

                this.doublesens= DoubleSens.isSelected();
                Stage stage = (Stage) validerButton.getScene().getWindow();
                stage.close();
                this.valide=true;
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
