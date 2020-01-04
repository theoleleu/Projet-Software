package sample;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;

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
    private boolean valide;
    private double longueur;
    private boolean doublesens;
    private ArrayList<VectorArc> TableArc = new ArrayList<>(13);
    private ArrayList<VectorNode> TableNode = new ArrayList<>(13);
    private ArrayList<VectorN> TableN = new ArrayList<>(13);
    private VectorN departN;
    private VectorN arriveN;
    private VectorNode departNode;
    private VectorNode arriveNode;

    public ArcController(){
        this.valide=false;
    }
    void setTable(ArrayList<VectorNode> TableNode,ArrayList<VectorArc> TableArc,ArrayList<VectorN> TableN){
        this.TableNode=TableNode;
        this.TableN=TableN;
        this.TableArc=TableArc;
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
    VectorNode getdepartNode(){
        return this.departNode;
    }
    VectorNode getarriveeNode(){
        return this.arriveNode;
    }
    VectorN getdepartN(){ return this.departN; }
    VectorN getarriveeN(){ return this.arriveN; }


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

                this.longueur=Double.parseDouble(LongueurArc.getText());
                String depart=DebutArc.getText();
                String arrivee=FinArc.getText();
                this.capacity=Integer.parseInt(CapacityArc.getText());
                this.doublesens= DoubleSens.isSelected();
                boolean NodeDEPExist = false;
                boolean NodeARRExist = false;


                int j = 0;
                while ((!NodeDEPExist || !NodeARRExist) && j < TableNode.size()) {
                    if (!NodeDEPExist) {
                        if (TableNode.get(j).nom.equals(depart)) {
                            NodeDEPExist = true;
                            this.departNode = TableNode.get(j);
                            this.departN = TableN.get(j);
                        }
                    }
                    if (!NodeARRExist) {
                        if (TableNode.get(j).nom.equals(arrivee)) {
                            NodeARRExist = true;
                            this.arriveNode = TableNode.get(j);
                            this.arriveN = TableN.get(j);
                        }
                    }
                    ++j;
                }
                boolean ArcExist = false;
                j = 0;
                while ((!ArcExist ) && j < TableArc.size()) {
                    if (TableArc.get(j).nom.equals(nom)) {
                        ArcExist = true;
                    }
                    ++j;
                }
                MenuController C= new MenuController();
                if (depart.equals(arrivee)){
                    C.message("Les extrémités de l'Arc sont identiques.","Merci de choisir deux points distincts !","Arc Nul");
                }
                else if (ArcExist){
                    C.message("L'arc " + nom + " existe déjà.","Merci de choisir un autre Nom.","Nom Incorrect");
                }
                else if (!NodeDEPExist) {
                    C.message("Le noeud de " + depart + " n'existe pas.","Merci de choisir un noeud de départ valide !","Noeud Invalide");
                }
                else if (!NodeARRExist) {
                    C.message("Le noeud d'arrivée " + arrivee + " n'existe pas.","Merci de choisir un noeud d'arrivée valide !","Noeud Invalide");
                }
                else {

                Stage stage = (Stage) validerButton.getScene().getWindow();
                stage.close();
                this.valide=true;}
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
