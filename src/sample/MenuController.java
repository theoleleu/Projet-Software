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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MenuController<E> {

    private double temps;
    public javafx.scene.control.RadioButton radioNoeud;
    public javafx.scene.control.RadioButton radioArc;
    public javafx.scene.control.RadioButton radioObjet;
    public javafx.scene.control.RadioButton radioSupprimer;
    public javafx.scene.control.RadioButton radioAjouter;


    public javafx.scene.control.Button btnQuitter;
    public javafx.scene.canvas.Canvas Canvas;
    private ArrayList<ArrayList<Double>> Arcs;
    private ArrayList<ArrayList<Double>> Noeuds;
    private ArrayList<ArrayList<Double>> Objets;
    private Group root;

    public MenuController(){
        this.Arcs=new ArrayList<>();
        this.Objets=new ArrayList<>();
        this.Noeuds=new ArrayList<>();
        this.temps=0.0;
    }


    public void setRoot(Group root) {
        this.root = root;
    }
    public void createNode(Double x,Double y,Double r,String nom) {//Permet d'afficher un Noeud
        Circle cercle = new Circle();
        cercle.setCenterX(x+183);//réglage de la position, de la taille et de la couleur du cercle
        cercle.setCenterY(y);
        cercle.setRadius(r);
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
    }
    public void createarc(Double DX,Double DY,Double FX,Double FY,Double r) {//A modifier avec le corps de l'algo
        Line line = new Line();
        line.setStartX(DX+183);
        line.setStartY(DY);
        line.setEndX(FX+183);
        line.setEndY(FY);
        line.setStroke(Color.GREEN);
        line.setStrokeWidth(r);
        this.root.getChildren().add(line);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage
    }
    public void createobjet(Double x,Double y,String nom,String donnees,String depart,String arrivee,Double vitesse) {//A modifier avec le corps de l'algo
        Circle cercle = new Circle();
        cercle.setCenterX(x+183);
        cercle.setCenterY(y);
        cercle.setRadius(5);
        cercle.setFill(Color.BLACK);
        this.root.getChildren().add(cercle);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage
        Text text = new Text();
        text.setX(x+178);
        text.setY(y);
        text.setText(nom);
        this.root.getChildren().add(text);//Attention : il faut garder ces infos dans le vecteur pour les modifier à l'affichage
    }

    public void quitFen(ActionEvent actionEvent) {
        Stage stage = (Stage) btnQuitter.getScene().getWindow();
        stage.close();
    }
    public void avancer(){
        this.temps++;
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

                    arcController.getInfo();
                    Stage fils = new Stage();
                    fils.setTitle("Création Arc");
                    fils.setScene(new Scene(p, 400, 200));
                    fils.showAndWait();
                    //this.createarc(arcController.DX(), arcController.DY(), arcController.FX(), arcController.FY(), arcController.getwidth());
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
                    this.createNode(x,y, (double) nodeController.getradius(),nodeController.getname());
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
                    //utiliser les fonctions
                    objetController.getname();
                    objetController.getDonnees();
                    objetController.getDepart();
                    objetController.getArrivee();
                    objetController.getVitesse();
                    this.createobjet(x,y,objetController.getname(),objetController.getDonnees(),objetController.getDepart(),objetController.getArrivee(),objetController.getVitesse());
                } catch (IOException e){
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
