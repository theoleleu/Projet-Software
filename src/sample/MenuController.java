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
import network.Node;

import java.io.IOException;
import java.util.*;

class VectorObject
    {


        private Integer id;

        public VectorObject(Circle cercle, Double x, Double y, String nom, String donnee, String depart, String arrivee, Double vitesse, Text text, Double t0, Integer id) {
            this.id=id;
            this.cercle = cercle;
            this.nom = nom;
            this.x = x;
            this.y = y;
            this.donnee = donnee;
            this.depart = depart;
            this.arrivee = arrivee;
            this.vitesse = vitesse;
            this.text = text;
            this.t0=t0;
        }
        Double t0;
        Circle cercle;
        String nom;
        String donnee;
        String depart;
        String arrivee;
        Double vitesse;
        Text text;
        Double x;
        Double y;
    }
class VectorArc
{
    private Integer id;
    Line line;
    String nom;
    Integer capacite;
    String depart;
    String arrivee;
    public VectorArc(Line line,String nom,Integer capacite,String depart,String arrivee,Integer id){
        this.id=id;
        this.line = line;
        this.nom = nom;
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
    Integer id;
    public VectorNode(Circle cercle, Text text, String nom, Double capacite, Double x,Double y,Integer id)
    {   this.id=id;
        this.cercle = cercle;
        this.nom = nom;
        this.text = text;
        this.x = x;
        this.y = y;
        this.capacite = capacite;

    }

}
public class MenuController<E> {

    private int idarc;
    private int idobjet;
    private int idnode;
    private ArrayList<VectorObject> TableObject = new ArrayList<VectorObject>(13);
    private ArrayList<VectorArc> TableArc = new ArrayList<VectorArc>(13);
    private ArrayList<VectorNode> TableNode = new ArrayList<VectorNode>(13);
    private double temps;
    public javafx.scene.control.RadioButton radioNoeud;
    public javafx.scene.control.RadioButton radioArc;
    public javafx.scene.control.RadioButton radioObjet;
    public javafx.scene.control.RadioButton radioSupprimer;
    public javafx.scene.control.RadioButton radioAjouter;


    public javafx.scene.control.Button btnQuitter;
    public javafx.scene.canvas.Canvas Canvas;

    private Group root;

    public MenuController(){
        this.temps=0.0;
        this.idarc=0;
        this.idnode=0;
        this.idobjet=0;
    }


    public void setRoot(Group root) {
        this.root = root;
    }

    public void createNode(Double x,Double y,Double capacite,String nom) {//Permet d'afficher un Noeud
        Circle cercle = new Circle();
        cercle.setCenterX(x+183);//réglage de la position, de la taille et de la couleur du cercle
        cercle.setCenterY(y);
        cercle.setRadius(capacite);
        Text text = new Text();
        text.setX(x+178);
        text.setY(y+2);
        text.setText(nom);
        Random rand = new Random();
        float r1 = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        cercle.setFill(new Color(r1,g,b,1));
        cercle.setStroke(Color.BLACK);//réglage de la couleur
        cercle.setStrokeWidth(1);
        this.root.getChildren().add(cercle);//on ajoute le cercle au groupe root
        this.root.getChildren().add(text);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage

        //Création de l'espace de stockage des Noeuds

        VectorNode element = new VectorNode(cercle,text,nom,capacite,x,y,this.idnode);
        this.idnode++;
        TableNode.add(element);
        System.out.println(Arrays.toString(TableNode.toArray()));
        for(int i = 0; i < TableNode.size(); i++) {
            System.out.print("nom = "+TableNode.get(i).nom+" - ");
            System.out.print("x = "+TableNode.get(i).x+" - ");
            System.out.print("y = "+TableNode.get(i).y+" - ");
            System.out.print("capacite = "+TableNode.get(i).capacite+"\n");
        }
    }


    public void createarc(String nom,Integer capacite,String depart,String arrivee) {//A modifier avec le corps de l'algo
        Line line = new Line();
        /*line.setStartX(DX+183);
        line.setStartY(DY);
        line.setEndX(FX+183);
        line.setEndY(FY);
        line.setStroke(Color.GREEN);
        line.setStrokeWidth(r);
        this.root.getChildren().add(line);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage
        */


        //Création de l'espace de stockage des Arcs
        VectorArc element = new VectorArc(line,nom,capacite,depart,arrivee,this.idarc);
        this.idarc++;
        TableArc.add(element);
        System.out.println(Arrays.toString(TableArc.toArray()));
        for(int i = 0; i < TableArc.size(); i++) {
            System.out.print("nom = "+TableArc.get(i).nom+" - ");
            System.out.print("depart = "+TableArc.get(i).depart+" - ");
            System.out.print("arrive = "+TableArc.get(i).arrivee+" - ");
            System.out.print("capacite = "+TableArc.get(i).depart+"\n");
        }

    }
    public void createobjet(Double x,Double y,String nom,String donnees,String depart,String arrivee,Double vitesse) {//A modifier avec le corps de l'algo
        Circle cercle = new Circle();
        boolean NodeDEPExist=false;
        boolean NodeARRExist =false;
        VectorNode departNode;
        VectorNode arriveNode;
        int j=0;
        while ((!NodeDEPExist || !NodeARRExist )  && j<TableNode.size()){
            if(!NodeDEPExist){
                if(TableNode.get(j).nom.equals(depart)){
                    NodeDEPExist = true;
                    departNode = TableNode.get(j);
                    x = departNode.x;
                    y = departNode.y;
                    System.out.println("le node de depart est : "+TableNode.get(j).nom);
                }
            }
            if(!NodeARRExist){
                if(TableNode.get(j).nom.equals(arrivee)){
                    NodeARRExist = true;
                    arriveNode = TableNode.get(j);
                    System.out.println("le node d'arrive est : "+TableNode.get(j).nom);
                }
            }
            j++;
        }
        if(!NodeARRExist){
            System.out.println("le node d'arrive "+arrivee +" n'existe pas");

        }
        if(!NodeDEPExist){
            System.out.println("le node de depart "+depart +" n'existe pas");
        }
        cercle.setCenterX(x+183);
        cercle.setCenterY(y);
        cercle.setRadius(5);
        cercle.setFill(Color.BLACK);
        this.root.getChildren().add(cercle);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage
        Text text = new Text();
        text.setX(x+178);
        text.setY(y);
        text.setText(nom);


        //Création de l'espace de stockage des Objets
        VectorObject element = new VectorObject(cercle,x,y,nom,donnees,depart,arrivee,vitesse,text,this.temps,this.idobjet);
        this.idobjet++;
        TableObject.add(element);
        System.out.println(Arrays.toString(TableObject.toArray()));
        for(int i = 0; i < TableObject.size(); i++) {
            System.out.print("nom = "+TableObject.get(i).nom+" - ");
            System.out.print("x = "+TableObject.get(i).x+" - ");
            System.out.print("y = "+TableObject.get(i).y+" - ");
            System.out.print("depart = "+TableObject.get(i).depart+" - ");
            System.out.print("arrive = "+TableObject.get(i).arrivee+" - ");
            System.out.print("donnees = "+TableObject.get(i).donnee+" - ");
            System.out.print("vitesse = "+TableObject.get(i).vitesse+" - ");
            System.out.print("tempscrea = "+TableObject.get(i).t0+"\n");
        }

        this.root.getChildren().add(text);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage

    }



    public void quitFen(ActionEvent actionEvent) {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }
    public void avancer(){
        this.temps++;
        for (VectorObject objet : TableObject){
            //Double lambda=objet.vitesse*(this.temps-objet.t0)/objet.distance;
            //objet.x=lambda*objet.depart.y+(1-lambda)*objet.arrivee.x;
            //objet.y=lambda*objet.depart.x+(1-lambda)*objet.arrivee.y;
        }
    }
    public void CanvasFunction(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        System.out.println("X : "+x+" // Y : "+y);
        if(radioAjouter.isSelected()){
            Parent root = null;
            if(radioArc.isSelected()){ //Faut 2 points peut etre indiquer dans les champs
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("arc.fxml"));
                    Parent p = loader.load();
                    ArcController arcController = loader.getController();
                    arcController.setCoord("X : "+Double.toString(x),"Y : "+Double.toString(y));
                    Stage fils = new Stage();
                    fils.setTitle("Création Arc");
                    fils.setScene(new Scene(p, 400, 200));
                    fils.showAndWait();

                    if (arcController.isvalide()) {
                        // Utiliser les fonctions extraites de l'interface
                        arcController.getnom();
                        arcController.getcapacity();
                        arcController.getdebut();
                        arcController.getfin();
                        this.createarc(arcController.getnom(), arcController.getcapacity(), arcController.getdebut(), arcController.getfin());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
            } else if (radioNoeud.isSelected()){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("node.fxml"));
                    Parent p = loader.load();
                    nodeController nodeController = loader.getController();
                    nodeController.setCoord("X : "+Double.toString(x),"Y : "+Double.toString(y));
                    Stage fils = new Stage();
                    fils.setTitle("Création Noeud");
                    fils.setScene(new Scene(p, 400, 200));
                    fils.showAndWait();
                    if (nodeController.isvalide()) {
                        //Utiliser les fonctions extraites de l'interface
                        nodeController.getname();
                        nodeController.getcapacite();

                        this.createNode(x, y, (double) nodeController.getcapacite(), nodeController.getname());
                    }
                } catch (IOException e){
                    e.printStackTrace();
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
            } else if (radioObjet.isSelected()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("objet.fxml"));
                    Parent p = loader.load();
                    ObjetController objetController = loader.getController();
                    objetController.setCoord("X : "+Double.toString(x),"Y : "+Double.toString(y));
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
                    }} catch (IOException e){
                    e.printStackTrace();
                } catch (NullPointerException e){
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

            } else if (radioNoeud.isSelected()){

            } else if (radioObjet.isSelected()){

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Test Suppression");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez sélectionner le type d'élément à supprimer !");

                alert.showAndWait();
            }
        }
    }
}
