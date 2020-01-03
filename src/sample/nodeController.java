package sample;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class nodeController {

    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button validateButton;
    public javafx.scene.control.TextField nodeName;
    public javafx.scene.control.TextField nodeCapacity;
    public javafx.scene.control.Label capacityWarning;
    public javafx.scene.control.Label positionX;
    public javafx.scene.control.Label positionY;
    double capacite;
    private String nom;
    private boolean valide;

    public nodeController(){
        this.valide=false;
    }
    public void setCoord(String x, String y){
        positionX.setText(x);
        positionY.setText(y);
    }
    public boolean isvalide(){
        return this.valide;
    }

    public void closeFen(ActionEvent actionEvent) {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    public double getcapacite(){
            return this.capacite;
    }
    public String getname(){
        return this.nom;
    }
    public void validateFen(ActionEvent actionEvent) {
        if (nodeCapacity.getText().equals("") | nodeName.getText().equals("") ){
            capacityWarning.setText("Attention : les champs Nom et Capacité sont incomplets");
        } else {
            String name = nodeName.getText();
            try {
                int capacity = Integer.parseInt(nodeCapacity.getText());
                System.out.println(name);
                System.out.println(capacity);
                this.nom=name;
                this.capacite=capacity;
                Stage stage = (Stage) validateButton.getScene().getWindow();
                stage.close();
                this.valide=true;
            } catch (java.lang.RuntimeException e) {
                capacityWarning.setText("Attention : la capacité doit être un entier");
            }
        }
    }
}
