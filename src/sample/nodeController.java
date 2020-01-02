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
    double r;
    private String nom;

    public void setCoord(String x, String y){
        positionX.setText(x);
        positionY.setText(y);
    }


    public void closeFen(ActionEvent actionEvent) {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    public double getradius(){
            return this.r;
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
                this.r=capacity;
                Stage stage = (Stage) validateButton.getScene().getWindow();
                stage.close();
            } catch (java.lang.RuntimeException e) {
                capacityWarning.setText("Attention : la capacité doit être un entier");
            }
        }
    }
}
