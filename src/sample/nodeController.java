package sample;

import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class nodeController {

    public javafx.scene.control.Button closeButton;
    public javafx.scene.control.Button validateButton;
    public javafx.scene.control.TextField nodeName;
    public javafx.scene.control.TextField nodeCapacity;
    public javafx.scene.control.Label capacityWarning;
    public javafx.scene.control.Label positionX;
    public javafx.scene.control.Label positionY;
    private double capacite;
    private String nom;
    private boolean valide;

    private ArrayList<VectorNode> TableNode = new ArrayList<>(13);

    public nodeController(){
        this.valide=false;
    }
    void setCoord(String x, String y){
        positionX.setText(x);
        positionY.setText(y);
    }
    void setTableNode(ArrayList<VectorNode> Table){
        this.TableNode=Table;
    }
    boolean isvalide(){
        return this.valide;
    }

    public void closeFen() {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
    }
    double getcapacite(){
            return this.capacite;
    }
    String getname(){
        return this.nom;
    }
    public void validateFen() {//ActionEvent actionEvent
        if (nodeCapacity.getText().equals("") | nodeName.getText().equals("") ){
            capacityWarning.setText("Attention : les champs Nom et Capacité sont incomplets");
        } else {
            String name = nodeName.getText();
            try {
                int capacity = Integer.parseInt(nodeCapacity.getText());
                this.nom=name;
                this.capacite=capacity;
                boolean NodeExist = false;
                int j = 0;
                while ((!NodeExist ) && j < this.TableNode.size()) {
                    if (this.TableNode.get(j).nom.equals(nom)) {
                        NodeExist = true;
                    }
                    ++j;
                }
                if (NodeExist){
                    MenuController C= new MenuController();
                    C.message("Le Noeud " + nom + " existe déjà.","Merci de choisir un autre Nom.","Nom Incorrect");
                }
                else {
                Stage stage = (Stage) validateButton.getScene().getWindow();
                stage.close();
                this.valide=true;}
            } catch (java.lang.RuntimeException e) {
                capacityWarning.setText("Attention : la capacité doit être un entier");
            }
        }
    }
}
