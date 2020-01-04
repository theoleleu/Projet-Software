package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.Serializable;
import java.io.*;
import java.util.*;

class VectorObject
    {
        Double t0;
        Circle cercle;
        String nom;
        String donnee;
        VectorNode depart;
        VectorNode arrivee;
        Double vitesse;
        Text text;
        Double x;
        Double y;
        VectorArc arc;
        public VectorObject(Circle cercle, Double x, Double y, String nom, String donnee, VectorNode depart, VectorNode arrivee, Double vitesse, Text text,VectorArc arc, Double t0) {
            this.t0=t0;
            this.cercle = cercle;
            this.nom = nom;
            this.x = x;
            this.y = y;
            this.donnee = donnee;
            this.depart = depart;
            this.arrivee = arrivee;
            this.vitesse = vitesse;
            this.text = text;
            this.arc = arc;
        }

    }
class VectorArc
{
    Double longueur;
    Line line;
    String nom;
    Integer capacite;
    VectorNode depart;
    VectorNode arrivee;
    public VectorArc(Line line,String nom,Integer capacite,VectorNode depart,VectorNode arrivee,Double longueur){
        this.line = line;
        this.nom = nom;
        this.longueur=longueur;
        this.depart = depart;
        this.arrivee = arrivee;
        this.capacite = capacite;
    }

}
class VectorNode
{   Circle cercle;
    Text text;
    String nom;
    Double capacite;
    Double x;
    Double y;
    public VectorNode(Circle cercle, Text text, String nom, Double capacite, Double x,Double y)
    {
        this.cercle = cercle;
        this.nom = nom;
        this.text = text;
        this.x = x;
        this.y = y;
        this.capacite = capacite;

    }

}
public class MenuController<E> {


    private ArrayList<VectorObject> TableObject = new ArrayList<VectorObject>(13);
    private ArrayList<VectorArc> TableArc = new ArrayList<VectorArc>(13);
    private ArrayList<VectorNode> TableNode = new ArrayList<VectorNode>(13);
    private ArrayList<VectorO> TableO = new ArrayList<VectorO>(13);
    private ArrayList<VectorA> TableA = new ArrayList<VectorA>(13);
    private ArrayList<VectorN> TableN = new ArrayList<VectorN>(13);
    private double temps;
    public javafx.scene.control.RadioButton radioNoeud;
    public javafx.scene.control.RadioButton radioArc;
    public javafx.scene.control.RadioButton radioObjet;
    public javafx.scene.control.RadioButton radioSupprimer;
    public javafx.scene.control.RadioButton radioAjouter;


    public javafx.scene.control.Button btnQuitter;
    public javafx.scene.canvas.Canvas Canvas;

    private Group root;

    public MenuController() {
        this.temps = 0.0;
    }


    void setRoot(Group root) {
        this.root = root;
    }

    //Evite le doublon de code
    private void affnoeud(Circle cercle, Text text, double x, double y, double capacite, String nom) {
        cercle.setCenterX(x + 183);//réglage de la position, de la taille et de la couleur du cercle
        cercle.setCenterY(y);
        cercle.setRadius(capacite);
        text.setX(x + 178);
        text.setY(y + 2);
        text.setText(nom);
        Random rand = new Random();
        float r1 = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        cercle.setFill(new Color(r1, g, b, 1));
        cercle.setStroke(Color.BLACK);//réglage de la couleur
        cercle.setStrokeWidth(1);
        this.root.getChildren().add(cercle);
        this.root.getChildren().add(text);
        VectorNode element = new VectorNode(cercle, text, nom, capacite, x, y);
        TableNode.add(element);
        System.out.println(Arrays.toString(TableNode.toArray()));
        for (VectorNode vectorNode : TableNode) {
            System.out.print("nom = " + vectorNode.nom + " - ");
            System.out.print("x = " + vectorNode.x + " - ");
            System.out.print("y = " + vectorNode.y + " - ");
            System.out.print("capacite = " + vectorNode.capacite + "\n");
        }
    }

    private void affarc(Line line, String nom, Integer capacite, VectorNode departNode, VectorNode arriveNode, Double longueur) {
        Double DX = departNode.x;
        Double DY = departNode.y;
        Double FX = arriveNode.x;
        Double FY = arriveNode.y;
        line.setStartX(DX + 183);
        line.setStartY(DY);
        line.setEndX(FX + 183);
        line.setEndY(FY);
        Random rand = new Random();
        float r1 = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        line.setStroke(new Color(r1, g, b, 1));
        line.setStrokeWidth(6);
        this.root.getChildren().add(line);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage

        //Création de l'espace de stockage des Arcs
        VectorArc element = new VectorArc(line, nom, capacite, departNode, arriveNode, longueur);
        TableArc.add(element);
        System.out.println(Arrays.toString(TableArc.toArray()));
        for (VectorArc vectorArc : TableArc) {
            System.out.print("nom = " + vectorArc.nom + " - ");
            System.out.print("depart = " + vectorArc.depart + " - ");
            System.out.print("arrive = " + vectorArc.arrivee + " - ");
            System.out.print("longueur = " + vectorArc.longueur + " - ");
            System.out.print("capacite = " + vectorArc.capacite + "\n");
        }
    }

    private void affobjet(Circle cercle, Text text, Double x, Double y, String nom, String donnees, VectorNode departNode,
                          VectorNode arriveNode, Double vitesse, VectorArc vectorArc) {
        cercle.setCenterX(x + 183);
        cercle.setCenterY(y);
        cercle.setRadius(5);
        cercle.setFill(Color.BLACK);
        this.root.getChildren().add(cercle);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage
        text.setX(x + 178);
        text.setY(y);
        text.setText(nom);
        //Création de l'espace de stockage des Objets
        VectorObject element = new VectorObject(cercle, x, y, nom, donnees, departNode, arriveNode, vitesse, text, vectorArc, this.temps);
        TableObject.add(element);
        System.out.println(Arrays.toString(TableObject.toArray()));
        for (VectorObject vectorObject : TableObject) {
            System.out.print("nom = " + vectorObject.nom + " - ");
            System.out.print("x = " + vectorObject.x + " - ");
            System.out.print("y = " + vectorObject.y + " - ");
            System.out.print("depart = " + vectorObject.depart + " - ");
            System.out.print("arrive = " + vectorObject.arrivee + " - ");
            System.out.print("donnees = " + vectorObject.donnee + " - ");
            System.out.print("vitesse = " + vectorObject.vitesse + "\n");
        }
        this.root.getChildren().add(text);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage
    }


    private void createNode(Double x, Double y, Double capacite, String nom) {//Permet d'afficher un Noeud
        Circle cercle = new Circle();
        Text text = new Text();
        affnoeud(cercle, text, x, y, capacite, nom);
        //Création de l'espace de stockage des Noeuds
        VectorN elem = new VectorN(nom, capacite, x, y);
        TableN.add(elem);
    }
    private void createarc(String nom, Integer capacite, String depart, String arrivee, Double longueur) {//A modifier avec le corps de l'algo

        boolean NodeDEPExist = false;
        boolean NodeARRExist = false;

        VectorN departN = null;
        VectorN arriveN = null;
        VectorNode departNode = null;
        VectorNode arriveNode = null;
        int j = 0;
        while ((!NodeDEPExist || !NodeARRExist) && j < TableNode.size()) {
            if (!NodeDEPExist) {
                if (TableNode.get(j).nom.equals(depart)) {
                    NodeDEPExist = true;
                    departNode = TableNode.get(j);
                    departN = TableN.get(j);
                    System.out.println("le node de depart est : " + TableNode.get(j).nom);
                }
            }
            if (!NodeARRExist) {
                if (TableNode.get(j).nom.equals(arrivee)) {
                    NodeARRExist = true;
                    arriveNode = TableNode.get(j);
                    arriveN = TableN.get(j);
                    System.out.println("le node d'arrive est : " + TableNode.get(j).nom);
                }
            }
            j++;
        }
        if (!NodeARRExist) {
            System.out.println("le node d'arrive " + arrivee + " n'existe pas");
            System.exit(1);
        }
        if (!NodeDEPExist) {
            System.out.println("le node de depart " + depart + " n'existe pas");
            System.exit(1);
        }
        Line line = new Line();
        affarc(line, nom, capacite, departNode, arriveNode, longueur);
        //Création de l'espace de stockage des Arcs
        VectorA elem = new VectorA(nom, capacite, departN.nom, arriveN.nom, longueur);
        TableA.add(elem);
    }

    private void createobjet(Double x, Double y, String nom, String donnees, String depart, String arrivee, Double vitesse) {//A modifier avec le corps de l'algo
        Circle cercle = new Circle();
        boolean NodeDEPExist = false;
        boolean NodeARRExist = false;

        VectorN departN = null;
        VectorN arriveN = null;
        VectorNode departNode = null;
        VectorNode arriveNode = null;
        int j = 0;
        while ((!NodeDEPExist || !NodeARRExist) && j < TableNode.size()) {
            if (!NodeDEPExist) {
                if (TableNode.get(j).nom.equals(depart)) {
                    NodeDEPExist = true;
                    departN = TableN.get(j);
                    departNode = TableNode.get(j);
                    x = departNode.x;
                    y = departNode.y;
                    System.out.println("le node de depart est : " + TableNode.get(j).nom);
                }
            }
            if (!NodeARRExist) {
                if (TableNode.get(j).nom.equals(arrivee)) {
                    NodeARRExist = true;
                    arriveN = TableN.get(j);
                    arriveNode = TableNode.get(j);
                    System.out.println("le node d'arrive est : " + TableNode.get(j).nom);
                }
            }
            j++;
        }
        if (!NodeARRExist) {
            System.out.println("le node d'arrive " + arrivee + " n'existe pas");
            System.exit(1);
        }
        if (!NodeDEPExist) {
            System.out.println("le node de depart " + depart + " n'existe pas");
            System.exit(1);
        }

        VectorA vectorA = null;
        VectorArc vectorArc = null;
        boolean ArcExist = false;
        int k = 0;
        while (!ArcExist && k < TableArc.size()) {
            if (TableArc.get(k).depart.nom.equals(depart)) {
                ArcExist = true;
                vectorArc = TableArc.get(k);
                vectorA = TableA.get(k);
            }
            k++;
        }
        if (!ArcExist) {
            System.out.println("l'arc partant du Node " + depart + " n'existe pas");
            System.exit(1);
        }
        Text text = new Text();
        VectorO elem = new VectorO(x, y, nom, donnees, departN.nom, arriveN.nom, vitesse, vectorA.nom, this.temps);
        TableO.add(elem);
        affobjet(cercle, text, x, y, nom, donnees, departNode, arriveNode, vitesse, vectorArc);
    }


    public void quitFen() {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }
    public void avancer(){
        this.temps+=1;
        for (VectorObject objet : TableObject){
            Double lambda=objet.vitesse*(this.temps-objet.t0)/(objet.arc.longueur);
            if (lambda<1 & lambda>0) {
                objet.x = (1 - lambda) * objet.depart.x + lambda * objet.arrivee.x;
                objet.y = (1 - lambda) * objet.depart.y + lambda * objet.arrivee.y;

            }
            else {
                objet.x = objet.arrivee.x;
                objet.y = objet.arrivee.y;
            }
            this.root.getChildren().remove(objet.cercle);
            objet.cercle.setCenterX(objet.x + 183);//réglage de la position, de la taille et de la couleur du cercle
            objet.cercle.setCenterY(objet.y);
            this.root.getChildren().add(objet.cercle);
            this.root.getChildren().remove(objet.text);
            objet.text.setX(objet.x + 183);//réglage de la position, de la taille et de la couleur du text
            objet.text.setY(objet.y);
            this.root.getChildren().add(objet.text);
        }

    }
    public void CanvasFunction(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        System.out.println("X : "+x+" // Y : "+y);
        if(radioAjouter.isSelected()){
            if(radioArc.isSelected()){ //Faut 2 points peut etre indiquer dans les champs
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("arc.fxml"));
                    Parent p = loader.load();
                    ArcController arcController = loader.getController();
                    arcController.setCoord("X : "+ x,"Y : "+ y);
                    Stage fils = new Stage();
                    fils.setTitle("Création Arc");
                    fils.setScene(new Scene(p, 400, 240));
                    fils.showAndWait();

                    if (arcController.isvalide()) {
                        // Utiliser les fonctions extraites de l'interface
                        arcController.getnom();
                        arcController.getcapacity();
                        arcController.getdebut();
                        arcController.getfin();
                        this.createarc(arcController.getnom(), arcController.getcapacity(), arcController.getdebut(), arcController.getfin(),arcController.getlongueur());
                    }
                } catch (IOException | NullPointerException e){
                    e.printStackTrace();
                }
            } else if (radioNoeud.isSelected()){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("node.fxml"));
                    Parent p = loader.load();
                    nodeController nodeController = loader.getController();
                    nodeController.setCoord("X : "+ x,"Y : "+ y);
                    Stage fils = new Stage();
                    fils.setTitle("Création Noeud");
                    fils.setScene(new Scene(p, 400, 200));
                    fils.showAndWait();
                    if (nodeController.isvalide()) {
                        //Utiliser les fonctions extraites de l'interface
                        nodeController.getname();
                        nodeController.getcapacite();

                        this.createNode(x, y, nodeController.getcapacite(), nodeController.getname());
                    }
                } catch (IOException | NullPointerException e){
                    e.printStackTrace();
                }
            } else if (radioObjet.isSelected()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("objet.fxml"));
                    Parent p = loader.load();
                    ObjetController objetController = loader.getController();
                    objetController.setCoord("X : "+ x,"Y : "+ y);
                    Stage fils = new Stage();
                    fils.setTitle("Création Objet");
                    fils.setScene(new Scene(p, 400, 200));
                    fils.showAndWait();
                    if (objetController.isvalide()) {
                        //utiliser les fonctions
                        objetController.getname();
                        objetController.getDonnees();
                        objetController.getDepart();
                        objetController.getArrivee();
                        objetController.getVitesse();

                        this.createobjet(x, y, objetController.getname(), objetController.getDonnees(), objetController.getDepart(), objetController.getArrivee(), objetController.getVitesse());
                    }} catch (IOException | NullPointerException e){
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Création");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner le type d'élément à ajouter !");

                alert.showAndWait();
            }
        } else if (radioSupprimer.isSelected()){
            if(radioArc.isSelected()){
                //this.root.getChildren().remove(line);
            } else if (radioNoeud.isSelected()){
                    //this.root.getChildren().remove(cercle);
                    //this.root.getChildren().remove(text);
            } else if (radioObjet.isSelected()){
                //this.root.getChildren().remove(cercle);
                //this.root.getChildren().remove(text;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Suppression");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner le type d'élément à supprimer !");

                alert.showAndWait();
            }
        }
    }
    public void enregistrer() {
        //Enregistrement nécessite d'implémenter l'interface sérialisable
        try {
            FileOutputStream fileNoeuds = new FileOutputStream("Noeuds");
            ObjectOutputStream OutNoeuds = new ObjectOutputStream(fileNoeuds);
            OutNoeuds.writeObject(TableN);
            OutNoeuds.close();
            fileNoeuds.close();
            FileOutputStream fileArcs = new FileOutputStream("Arcs");
            ObjectOutputStream OutArcs = new ObjectOutputStream(fileArcs);
            OutArcs.writeObject(TableA);
            OutArcs.close();
            fileArcs.close();
            fileArcs.close();
            FileOutputStream fileObjet = new FileOutputStream("Objets");
            ObjectOutputStream OutObjet = new ObjectOutputStream(fileObjet);
            OutObjet.writeObject(TableO);
            OutObjet.close();
            fileObjet.close();
            System.out.println("\nEnregistrement terminé avec succès...\n");//Sérialisation

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void charger() {
        try {
            FileInputStream fileInN = new FileInputStream("Noeuds");
            ObjectInputStream oisN = new ObjectInputStream(fileInN);
            TableN = (ArrayList<VectorN>) oisN.readObject();
            oisN.close();
            fileInN.close();
            FileInputStream fileInA = new FileInputStream("Arcs");
            ObjectInputStream oisA = new ObjectInputStream(fileInA);
            TableA = (ArrayList<VectorA>) oisA.readObject();
            oisA.close();
            fileInA.close();
            FileInputStream fileInO = new FileInputStream("Objets");
            ObjectInputStream oisO = new ObjectInputStream(fileInO);
            TableO = (ArrayList<VectorO>) oisO.readObject();
            oisO.close();
            fileInO.close();
        } catch (
                ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        for (VectorN node : TableN) {
            Circle cercle = new Circle();
            Text text = new Text();
            affnoeud(cercle, text, node.x, node.y, node.capacite, node.nom);
        }
        for (VectorA arc : TableA) {
            Line line = new Line();
            VectorNode departNode = null;
            VectorNode arriveNode = null;
            int j = 0;
            while (j < TableNode.size()) {
                if (TableNode.get(j).nom.equals(arc.depart)) {
                    departNode = TableNode.get(j);
                }
                if (TableNode.get(j).nom.equals(arc.arrivee)) {
                    arriveNode = TableNode.get(j);
                }
                j++;
            }
            assert arriveNode != null;
            assert departNode != null;
            affarc(line, arc.nom, arc.capacite, departNode, arriveNode, arc.longueur);
        }
        for (VectorO objet : TableO) {
            Circle cercle = new Circle();
            Text text = new Text();
            VectorNode depart = null;
            VectorNode arrivee = null;
            int j = 0;
            while (j < TableNode.size()) {
                if (TableNode.get(j).nom.equals(objet.depart)) {
                    depart = TableNode.get(j);
                }
                if (TableNode.get(j).nom.equals(objet.arrivee)) {
                    arrivee = TableNode.get(j);
                }
                j++;
            }
            VectorArc arc = null;
            int i = 0;
            while (i < TableArc.size()) {
                if (TableArc.get(i).nom.equals(objet.arc)) {
                    arc = TableArc.get(i);
                }
                affobjet(cercle, text, objet.x, objet.y, objet.nom, objet.donnee, depart, arrivee, objet.vitesse, arc);
            }
        }
    }

}

class VectorO implements Serializable
{
    Double t0;
    String nom;
    String donnee;
    String depart;
    String arrivee;
    Double vitesse;
    Double x;
    Double y;
    String arc;
    public VectorO(Double x, Double y, String nom, String donnee, String depart, String arrivee, Double vitesse, String arc, Double t0) {
        this.t0=t0;
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.donnee = donnee;
        this.depart = depart;
        this.arrivee = arrivee;
        this.vitesse = vitesse;
        this.arc = arc;
    }

}
class VectorA implements Serializable
{
    Double longueur;
    String nom;
    Integer capacite;
    String depart;
    String arrivee;
    public VectorA(String nom,Integer capacite,String depart,String arrivee,Double longueur){
        this.nom = nom;
        this.longueur=longueur;
        this.depart = depart;
        this.arrivee = arrivee;
        this.capacite = capacite;
    }

}
class VectorN implements Serializable
{
    String nom;
    Double capacite;
    Double x;
    Double y;
    public VectorN(String nom, Double capacite, Double x,Double y)
    {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.capacite = capacite;

    }

}
