package sample;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;

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
    private String Donnees;
    private boolean valide;
    private ArrayList<VectorObject> TableObject = new ArrayList<>(13);
    private ArrayList<VectorArc> TableArc = new ArrayList<>(13);
    private ArrayList<VectorNode> TableNode = new ArrayList<>(13);
    private ArrayList<VectorA> TableA = new ArrayList<>(13);
    private ArrayList<VectorN> TableN = new ArrayList<>(13);
    private VectorArc VectorArc;
    private VectorA VectorA;
    private double x;
    private double y;
    private VectorN departN;
    private VectorN arriveN;
    private VectorNode departNode;
    private VectorNode arriveNode;

    public ObjetController(){ this.valide=false; }
    VectorArc getArc(){ return this.VectorArc; }
    VectorA getA(){ return this.VectorA; }
    VectorNode getdepartNode(){
        return this.departNode;
    }
    VectorNode getarriveeNode(){
        return this.arriveNode;
    }
    VectorN getdepartN(){ return this.departN; }
    VectorN getarriveeN(){ return this.arriveN; }
    void setCoord(String x, String y){
        positionX.setText(x);
        positionY.setText(y);
    }
    Double getx(){
        return this.x;

    }
    Double gety(){
        return this.y;
    }
    void setTable(ArrayList<VectorNode> TableNode,ArrayList<VectorArc> TableArc,ArrayList<VectorA> TableA,ArrayList<VectorN> TableN,ArrayList<VectorObject> TableObject){
        this.TableNode=TableNode;
        this.TableN=TableN;
        this.TableArc=TableArc;
        this.TableA=TableA;
        this.TableObject=TableObject;
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
                System.out.println(tfNom.getText());
                this.nom=tfNom.getText();
                this.Donnees=tfDonnees.getText();
                String depart = tfDepart.getText();
                String arrivee = tfArrivee.getText();
                this.Vitesse=Double.parseDouble(tfVitesse.getText());


                boolean NodeDEPExist = false;
                boolean NodeARRExist = false;
                int j = 0;
                while ((!NodeDEPExist || !NodeARRExist) && j < TableNode.size()) {
                    if (!NodeDEPExist) {
                        if (TableNode.get(j).nom.equals(depart)) {
                            NodeDEPExist = true;
                            departN = TableN.get(j);
                            departNode = TableNode.get(j);
                            this.x = departNode.x;
                            this.y = departNode.y;
                        }
                    }
                    if (!NodeARRExist) {
                        if (TableNode.get(j).nom.equals(arrivee)) {
                            NodeARRExist = true;
                            arriveN = TableN.get(j);
                            arriveNode = TableNode.get(j);
                        }
                    }
                    ++j;
                }
                boolean ArcExist = false;
                int k = 0;
                while (!ArcExist && k < TableArc.size()) {
                    if (TableArc.get(k).depart.nom.equals(depart) && TableArc.get(k).arrivee.nom.equals(arrivee)) {
                        ArcExist = true;
                        this.VectorArc = TableArc.get(k);
                        this.VectorA = TableA.get(k);
                    }
                    if (TableArc.get(k).doublesens && TableArc.get(k).depart.nom.equals(arrivee) && TableArc.get(k).arrivee.nom.equals(depart)) {
                        ArcExist = true;
                        this.VectorArc = TableArc.get(k);
                        this.VectorA = TableA.get(k);
                    }
                    ++k;
                }
                boolean ObjectExist = false;
                j = 0;
                while ((!ObjectExist ) && j < TableObject.size()) {
                    if (TableObject.get(j).nom.equals(nom)) {
                        ObjectExist = true;
                    }
                    ++j;
                }
                MenuController C= new MenuController();
                if (this.VectorArc.capacite<=this.VectorArc.points.size())
                {
                    C.message("L'Arc "+ this.VectorArc.nom + " est déjà complet.","Vous ne pouvez créer l'objet.","Opération Impossible");
                }
                else if (ObjectExist) {
                    C.message("L'objet "+ nom + " existe déjà.","Merci de choisir un autre nom.","Nom Invalide");
                }
                else if (!NodeDEPExist) {
                    C.message("Le noeud de " + depart + " n'existe pas.","Merci de choisir un noeud de départ valide !","Noeud Invalide");
                }
                else if (!NodeARRExist) {
                    C.message("Le noeud d'arrivée " + arrivee + " n'existe pas.","Merci de choisir un noeud d'arrivée valide !","Noeud Invalide");
                } else if (!ArcExist) {
                    C.message("L'arc entre les Noeuds "+ depart +" et "+ arrivee + " n'existe pas.","Merci de choisir des Noeuds reliés par un arc !","Arc Invalide");
                }
                else{
                Stage stage = (Stage) btnValider.getScene().getWindow();
                stage.close();
                this.valide=true;}
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
