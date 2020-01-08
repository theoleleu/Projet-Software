package sample;

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
import network.Arc;
import network.Node;
import network.Point;

import java.io.Serializable;
import java.io.*;
import java.util.*;

/**
 * Classe permettant de stocker un objet
 */
class VectorObject extends Point
    {
        Double t0;
        Circle cercle;
        VectorNode depart;
        VectorNode arrivee;
        Double vitesse;
        Text text;
        Double x;
        Double y;
        VectorArc arc;

        /**
         * Permet de construire un objet
         * @param cercle Cercle permettant de représenter l'objet
         * @param x Position de l'objet selon x
         * @param y Position de l'objet selon y
         * @param nom Nom de l'objet
         * @param donnee Donnée à transmettre au noeud arrivée
         * @param depart Noeud de départ
         * @param arrivee Noeud d'arrivée
         * @param vitesse Vitesse de l'objet
         * @param text Label indiquant le nom de l'objet
         * @param arc Arc par lequel circule l'objet
         * @param t0 Temps de création de l'objet
         */
        VectorObject(Circle cercle, Double x, Double y, String nom, String donnee, VectorNode depart, VectorNode arrivee, Double vitesse, Text text, VectorArc arc, Double t0) {
            super();
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

/**
 * Classe permettant de stocker un arc
 */
class VectorArc extends Arc
{
    Line line;
    Text text;
    String nom;
    VectorNode depart;
    VectorNode arrivee;

    /**
     * Fonction permettant de construire l'arc
     * @param line Ligne permettant de répresenter l'arc
     * @param text Label montrant le nom de l'arc
     * @param nom Nom de l'arc
     * @param capacite Capacité de l'arc
     * @param depart Noeud de départ de l'arc
     * @param arrivee Noeud d'arrivé de l'arc
     * @param longueur Longueur de l'arc
     * @param doublesens L'arc se parcourt en doublesens ? True or False
     */
    VectorArc(Line line, Text text, String nom, Integer capacite, VectorNode depart, VectorNode arrivee, Double longueur, Boolean doublesens){
        this.text=text;
        this.line = line;
        this.nom = nom;
        this.longueur=longueur;
        this.depart = depart;
        this.arrivee = arrivee;
        this.capacite = capacite;
        this.doublesens=doublesens;
    }

}

/**
 * Classe permettant de stocker un Noeud
 */
class VectorNode extends Node
{   Circle cercle;
    Text text;


    /**
     * Fonction permettant de créer un noeud
     * @param cercle Cercle permettant de représenter un noeud
     * @param text Label affichant le nom du noeud
     * @param nom Nom du noeud
     * @param capacite Capacité du Noeud(non implémenté)
     * @param x Position du Noeud selon x
     * @param y Postion du Noeud selon y
     */
    VectorNode(Circle cercle, Text text, String nom, Double capacite, Double x, Double y)
    {
        super();
        this.cercle = cercle;
        this.nom = nom;
        this.text = text;
        this.x = x;
        this.y = y;
        this.capacite = capacite;

    }

}

/**
 * Permet de gérer l'application : Fonction Principale
 */
public class MenuController {


    private ArrayList<VectorObject> TableObject = new ArrayList<>(13);
    private ArrayList<VectorArc> TableArc = new ArrayList<>(13);
    private ArrayList<VectorNode> TableNode = new ArrayList<>(13);
    private ArrayList<VectorO> TableO = new ArrayList<>(13);
    private ArrayList<VectorA> TableA = new ArrayList<>(13);
    private ArrayList<VectorN> TableN = new ArrayList<>(13);
    private double temps;
    public javafx.scene.control.RadioButton radioNoeud;
    public javafx.scene.control.RadioButton radioArc;
    public javafx.scene.control.RadioButton radioObjet;
    public javafx.scene.control.RadioButton radioSupprimer;
    public javafx.scene.control.RadioButton radioAjouter;


    public javafx.scene.control.Button btnQuitter;
    public javafx.scene.canvas.Canvas Canvas;
    private boolean charge;
    private Group root;

    /**
     * Initialise le temps et le booléen qui empèche de charger 2 fois les mêmes information depuis la sauvegarde
     */
    public MenuController() {
        this.charge=false;
        this.temps = 0.0;
    }

    /**
     * @param root espace d'affichage de l'application
     * Permet d'écrire depuis le controller
     */
    void setRoot(Group root) {
        this.root = root;
    }

    /**
     * Permet de créer une interface pour afficher un message
     * @param entete 1ère ligne de l'interface
     * @param message 2ème ligne de l'interface
     * @param nomint Nom de l'interface
     */
    void message(String entete, String message,String nomint){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent p = loader.load();
            MessageController MessageController = loader.getController();
            Group root2 = new Group(p);
            MessageController.setRoot(root2);
            Stage fils = new Stage();
            fils.setTitle(nomint);
            fils.setScene(new Scene(root2, 300, 100));
            fils.show();
            MessageController.messsage(message,entete);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'afficher le noeud et de l'enregistrer avec les informations fournies
     * @param cercle Cercle associé au noeud représenté
     * @param text Label associé au noeud
     * @param x position du noeud selon x
     * @param y position du noeud selon y
     * @param capacite capacité du noeud (non implémenté)
     * @param nom Nom du noeud
     */
    //Evite le doublon de code
    private void affnoeud(Circle cercle, Text text, double x, double y, double capacite, String nom) {
        cercle.setCenterX(x + 183);//réglage de la position, de la taille et de la couleur du cercle
        cercle.setCenterY(y);
        cercle.setRadius(capacite);
        text.setX(x + 178);
        text.setY(y + 25);
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
        System.out.println(" Vecteur des Noeuds :");
        for (VectorNode vectorNode : TableNode) {
            System.out.println(" Noeud :" + vectorNode.nom + ": \n");
            System.out.print("X = " + vectorNode.x + " - ");
            System.out.print("Y = " + vectorNode.y + " - ");
            System.out.print("Capacite = " + vectorNode.capacite + "\n");
        }
    }

    /**
     * Permet d'afficher un arc et de l'enregistrer avec les informations fournies
     * @param line Ligne pour représenter l'arc
     * @param text Label associé au noeud
     * @param nom Nom de l'arc
     * @param capacite Capacité de l'arc (non implémenté)
     * @param departNode Noeud de départ de l'arc
     * @param arriveNode Noeud d'arrivé de l'arc
     * @param longueur Longueur de l'arc
     * @param doublesens Doublesens ou non
     */
    private void affarc(Line line,Text text, String nom, Integer capacite, VectorNode departNode, VectorNode arriveNode, Double longueur,Boolean doublesens) {
        Double DX = departNode.x;
        Double DY = departNode.y;
        Double FX = arriveNode.x;
        Double FY = arriveNode.y;
        line.setStartX(DX + 183);
        line.setStartY(DY);
        line.setEndX(FX + 183);
        line.setEndY(FY);
        text.setX(183+(DX+FX)/2);
        text.setY((DY+FY)/2-2);
        text.setText(nom);
        Random rand = new Random();
        float r1 = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        line.setStroke(new Color(r1, g, b, 1));
        line.setStrokeWidth(6);
        this.root.getChildren().add(line);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage
        this.root.getChildren().add(text);
        //Création de l'espace de stockage des Arcs
        VectorArc element = new VectorArc(line, text, nom, capacite, departNode, arriveNode, longueur, doublesens);
        TableArc.add(element);
        System.out.println("Vecteur des Arcs :");
        for (VectorArc vectorArc : TableArc) {
            System.out.print("Arc " + vectorArc.nom + " : \n");
            System.out.print("Noeud de départ = " + vectorArc.depart.nom + " - ");
            System.out.print("Noeud d'arrivée = " + vectorArc.arrivee.nom + " - ");
            System.out.print("Longueur = " + vectorArc.longueur + " - ");
            System.out.print("Capacite = " + vectorArc.capacite + " - ");
            System.out.print("doublesens = " + vectorArc.doublesens + "\n");
        }
    }

    /**
     * Permet d'afficher un objet et de l'enregistrer avec les informations fournies
     * @param cercle Cercle représentant l'objet
     * @param text Labal associé à l'objet
     * @param x Position de l'objet selon x
     * @param y Position de l'objet selon y
     * @param nom Nom de l'objet
     * @param donnees Données que l'objet doit transmettre au noeud
     * @param departNode Noeud de départ
     * @param arriveNode Noeud d'arrivée
     * @param vitesse Vitesse de l'objet
     * @param vectorArc Arc par lequel passe l'objet
     * @param t0 Temps de création de l'objet
     */
    private void affobjet(Circle cercle, Text text, Double x, Double y, String nom, String donnees, VectorNode departNode,
                          VectorNode arriveNode, Double vitesse, VectorArc vectorArc,Double t0) {
        cercle.setCenterX(x + 183);
        cercle.setCenterY(y);
        cercle.setRadius(5);
        cercle.setFill(Color.BLACK);
        this.root.getChildren().add(cercle);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage
        text.setX(x + 186);
        text.setY(y-3);
        text.setText(nom);
        //Création de l'espace de stockage des Objets
        VectorObject element = new VectorObject(cercle, x, y, nom, donnees, departNode, arriveNode, vitesse, text, vectorArc, t0);
        TableObject.add(element);
        System.out.println("Vecteur des Objets :");
        for (VectorObject vectorObject : TableObject) {
            System.out.print("Objet " + vectorObject.nom + " : \n ");
            System.out.print("x = " + vectorObject.x + " - ");
            System.out.print("Y = " + vectorObject.y + " - ");
            System.out.print("Noeud de départ = " + vectorObject.depart.nom + " - ");
            System.out.print("Noeud d'arrivée = " + vectorObject.arrivee.nom + " - ");
            System.out.print("Données = " + vectorObject.donnee + " - ");
            System.out.print("Vitesse = " + vectorObject.vitesse + "\n");
        }
        this.root.getChildren().add(text);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage
    }


    /**
     * Permet de créer un Noeud avec les informations en entrée
     * @param x Position du nom selon l'axe x
     * @param y Position du nom selon l'axe y
     * @param capacite Capacite du Noeud (non implémenté)
     * @param nom Nom du noeud
     */
    private void createNode(Double x, Double y, Double capacite, String nom) {//Permet d'afficher un Noeud
            Circle cercle = new Circle();
            Text text = new Text();
            affnoeud(cercle, text, x, y, capacite, nom);
            //Création de l'espace de stockage des Noeuds
            VectorN elem = new VectorN(nom, capacite, x, y);
            TableN.add(elem);

    }

    /**
     * Permet de créer un Arc avec les informations en entrée
     * @param nom Nom de l'arc
     * @param capacite Capacite du Noeud (non implémenté)
     * @param longueur
     * @param doublesens
     * @param departNode
     * @param arriveNode
     * @param departN
     * @param arriveN
     */
    private void createarc(String nom, Integer capacite, Double longueur,Boolean doublesens,
                           VectorNode departNode, VectorNode arriveNode,VectorN departN,VectorN arriveN) {//A modifier avec le corps de l'algo
        Line line = new Line();
        Text text = new Text();
        affarc(line, text, nom, capacite, departNode, arriveNode, longueur, doublesens);
        //Création de l'espace de stockage des Arcs
        VectorA elem = new VectorA(nom, capacite, departN.nom, arriveN.nom, longueur,doublesens);
        TableA.add(elem);
    }

    /**
     * Permetde créer un objet avec les informations en entrée
     * @param x Position de l'objet selon x
     * @param y Position de l'objet selon y
     * @param nom Nom de l'objet
     * @param donnees
     * @param vitesse Vitesse de l'objet
     * @param departN Noeud de départ de l'objet (pour la sauvegarde)
     * @param arriveN Noeud d'arrivée de l'objet (pour la sauvegarde)
     * @param departNode Noeud de départ de l'objet
     * @param arriveNode Noeud d'arrivée de l'objet
     * @param vectorA Arc par lequel passse l'objet (pour la sauvegarde)
     * @param vectorArc Arc par lequel passe l'objet
     */
    private void createobjet(Double x, Double y, String nom, String donnees, Double vitesse,
                             VectorN departN,VectorN arriveN, VectorNode departNode, VectorNode arriveNode,VectorA vectorA,VectorArc vectorArc) {//A modifier avec le corps de l'algo
        Circle cercle = new Circle();
        Text text = new Text();
        VectorO elem = new VectorO(x, y, nom, donnees, departN.nom, arriveN.nom, vitesse, vectorA.nom, this.temps);
        TableO.add(elem);
        affobjet(cercle, text, x, y, nom, donnees, departNode, arriveNode, vitesse, vectorArc,this.temps);}


    /**
     * Permet de quitter avec le bouton quitter
     */
    public void quitFen() {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }

    /**
     * Permet l'avancée du point dans le temps
     * Il transmet un message (donnée) lorsqu'il arrive à destination
     */
    public void avancer() {
        ++this.temps;
        for (int i=0;i<TableObject.size();i++){
            VectorObject objet=TableObject.get(i);
            VectorO o=TableO.get(i);
            Double lambda=objet.vitesse*(this.temps-objet.t0)/(objet.arc.longueur);
            double l=objet.vitesse*(this.temps-1-objet.t0)/(objet.arc.longueur);
            if (l>=1){
                this.root.getChildren().remove(objet.cercle);
                this.root.getChildren().remove(objet.text);
                TableObject.remove(objet);
                TableO.remove(o);
                i--;
            }
            else{
            if (lambda<1 & lambda>=0) {
                objet.x = (1 - lambda) * objet.depart.x + lambda * objet.arrivee.x;
                objet.y = (1 - lambda) * objet.depart.y + lambda * objet.arrivee.y;
            }
            else {
                objet.x = objet.arrivee.x;
                objet.y = objet.arrivee.y;
                message("Message du Noeud " + objet.depart.nom + " au Noeud "+ objet.arrivee.nom + " :",objet.donnee,"Message Arrivé");
            }
            o.x=objet.x;
            o.y=objet.y;
            this.root.getChildren().remove(objet.cercle);
            objet.cercle.setCenterX(objet.x + 183);//réglage de la position, de la taille et de la couleur du cercle
            objet.cercle.setCenterY(objet.y);
            this.root.getChildren().add(objet.cercle);
            this.root.getChildren().remove(objet.text);
            objet.text.setX(objet.x + 186);//réglage de la position, de la taille et de la couleur du text
            objet.text.setY(objet.y-3);
            this.root.getChildren().add(objet.text);
            }
        }

    }

    /**
     * Interface utilisateur pour ajout suppression
     * Permet de faire le lien avec les autres controllers afin d'inviter l'utilisateur à renseigner les différents champs
     * @param mouseEvent Clic sur l'écran pour obtenir la position (pour le Noeud)
     */
    public void CanvasFunction(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();//Positions du clic
        if(radioAjouter.isSelected()){
            if(radioArc.isSelected()){ //Faut 2 points peut etre indiquer dans les champs
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("arc.fxml"));
                    Parent p = loader.load();
                    ArcController arcController = loader.getController();
                    arcController.setCoord("X : "+ x,"Y : "+ y);
                    arcController.setTable(TableNode,TableArc,TableN);
                    Stage fils = new Stage();
                    fils.setTitle("Création Arc");
                    fils.setScene(new Scene(p, 400, 240));
                    fils.showAndWait();

                    if (arcController.isvalide()) {
                        // Utiliser les fonctions extraites de l'interface
                        arcController.getnom();
                        arcController.getcapacity();
                        this.createarc(arcController.getnom(), arcController.getcapacity(),arcController.getlongueur(),
                                arcController.isdoublesens(), arcController.getdepartNode(), arcController.getarriveeNode(),arcController.getdepartN(), arcController.getarriveeN());
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
                    nodeController.setTableNode(TableNode);
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
                    objetController.setTable(TableNode,TableArc,TableA,TableN,TableObject);
                    Stage fils = new Stage();
                    fils.setTitle("Création Objet");
                    fils.setScene(new Scene(p, 400, 200));
                    fils.showAndWait();
                    if (objetController.isvalide()) {
                        this.createobjet(objetController.getx(), objetController.gety(), objetController.getname(), objetController.getDonnees(),
                                objetController.getVitesse(),objetController.getdepartN(),objetController.getarriveeN(),objetController.getdepartNode()
                                ,objetController.getarriveeNode(),objetController.getA(),objetController.getArc());
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
                message("Fonction non Implémentée !","Désolé !","Erreur");
                //this.root.getChildren().remove(line);
            } else if (radioNoeud.isSelected()){
                message("Fonction non Implémentée !","Désolé !","Erreur");
                    //this.root.getChildren().remove(cercle);
                    //this.root.getChildren().remove(text);
            } else if (radioObjet.isSelected()){
                try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("objetsuppr.fxml"));
                Parent p = loader.load();
                ObjetControllerSuppr objetControllerSuppr = loader.getController();
                Stage fils = new Stage();
                fils.setTitle("Suppression Objet");
                fils.setScene(new Scene(p, 300, 100));
                fils.showAndWait();
                String nom = objetControllerSuppr.getname();
                if (objetControllerSuppr.isvalide()) {
                    boolean NodeExist = false;
                    VectorObject Object = null;
                    int j = 0;
                    while ((!NodeExist) && j < TableNode.size()) {
                        if (TableNode.get(j).nom.equals(nom)) {
                            NodeExist = true;
                            TableO.remove(TableO.get(j));
                            Object = TableObject.get(j);
                        }
                        ++j;
                    }
                    if (!NodeExist) {
                        message("Le noeud "+ nom +"  n'existe pas","Vous ne pouvez pas le supprimer","Nom Incorrect");

                    }
                    else {
                        this.root.getChildren().remove(Object.cercle);
                        this.root.getChildren().remove(Object.text);
                        TableObject.remove(Object);
                    }
                }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Suppression");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner le type d'élément à supprimer !");

                alert.showAndWait();
            }
        }
    }

    /**
     * Permet d'enregistrer les élements ajoutés
     */
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

    /**
     * Permet de charger les éléments précédemeent enregistrés
     */
    public void charger() {
        if (!this.charge){
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
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        this.charge=true;
        for (VectorN node : TableN) {
            Circle cercle = new Circle();
            Text text = new Text();
            affnoeud(cercle, text, node.x, node.y, node.capacite, node.nom);
        }
        for (VectorA arc : TableA) {
            Line line = new Line();
            Text text = new Text();
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
                ++j;
            }
            assert arriveNode != null;
            assert departNode != null;
            affarc(line,text, arc.nom, arc.capacite, departNode, arriveNode, arc.longueur, arc.doublesens);
        }
        for (VectorO objet : TableO) {
            Circle cercle=new Circle();
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
                ++j;
            }
            VectorArc arc = null;
            int i = 0;
            while (i < TableArc.size()) {
                if (TableArc.get(i).nom.equals(objet.arc)) {
                    arc = TableArc.get(i);
                }
                ++i;
            }
            affobjet(cercle, text, objet.x, objet.y, objet.nom, objet.donnee, depart, arrivee, objet.vitesse, arc,objet.t0);
        }}
    }

}

/**
 * Classe Serializable pour l'enregistrement de la classe VectorObjet
 */
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
    VectorO(Double x, Double y, String nom, String donnee, String depart, String arrivee, Double vitesse, String arc, Double t0) {
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

/**
 * Classe Serializable pour l'enregistrement de la classe VectorArc
 */
class VectorA implements Serializable
{
    Double longueur;
    String nom;
    Integer capacite;
    String depart;
    String arrivee;
    Boolean doublesens;
    VectorA(String nom, Integer capacite, String depart, String arrivee, Double longueur, Boolean doublesens){
        this.nom = nom;
        this.longueur=longueur;
        this.depart = depart;
        this.arrivee = arrivee;
        this.capacite = capacite;
        this.doublesens=doublesens;
    }

}

/**
 * Classe Serializable pour l'enregistrement de la classe VectorNode
 */
class VectorN implements Serializable
{
    String nom;
    Double capacite;
    Double x;
    Double y;
    VectorN(String nom, Double capacite, Double x, Double y)
    {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.capacite = capacite;

    }
}
