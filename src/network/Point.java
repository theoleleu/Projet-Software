package network;

import java.lang.String;

public class Point {
    private String id;
    private String name;
    private String donnees;
    private String[] parcours;
    private float vitesse; //TODO

    private int position;
    private boolean onNode;

    public Point(String id, String name, String donnees, String[] parcours, int position, boolean onNode) {
        this.id = id;
        this.name = name;
        this.donnees = donnees;
        this.parcours = parcours;
        this.position = position;
        if (this.parcours[position].charAt(0) == 1) {
            this.onNode = false;
        }
        else if (this.parcours[position].charAt(0) == 2) {
            this.onNode = true;
        }
        else {
            throw new NoSuchElementException("unknown position");
        }
    }

    public String toString() {
        String res = "[id: " + this.id + ", name: " + this.name + "]\ndonnees: " + this.donnees;
        return res;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDonnees() {
        return this.donnees;
    }

    public float getVitesse() {
        return this.vitesse;
    }

    public void setVitesse(int v) {
        this.vitesse = v;
    }

    public String getPosition() {
        return this.position;
    }

    public String getIdPosition() {
        return this.parcours[position];
    }

    public boolean isOnNode() {
        return this.onNode;
    }

    public int stepParcours() {
        if (onNode && this.parcours[position+1].charAt(0) == 1) {
            this.onNode = false;
            position += 1;
        }
        else if (!onNode && this.parcours[position+1].charAt(0) == 2) {
            this.onNode = true;
            position += 1;
        }
        else {
            throw new NoSuchElementException("bad construction of parcours [Node, Arc, Node, Arc,..., Node]");
        }
        return this.position;
    }

    

}