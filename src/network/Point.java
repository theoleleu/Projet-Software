package network;

import java.lang.String;
import java.util.NoSuchElementException;

public class Point {
    public String id;
    public String nom;
    public String donnee;
    public String[] parcours;
    public float priority;
    public int position;
    public boolean onNode;

    public Point(String id, String nom, String donnees, String[] parcours, float priority, int position, boolean onNode) {
        this.id = id;
        this.nom = nom;
        this.donnee = donnees;
        this.parcours = parcours;
        this.priority = priority;
        
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

    public Point() {

    }

    public String toString() {
        String res = "[id: " + this.id + ", name: " + this.nom + "]\ndonnees: " + this.donnee;
        return res;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.nom;
    }

    public String getDonnees() {
        return this.donnee;
    }

    public float getPriority() {
        return this.priority;
    }

    public void setPriority(float p) {
        this.priority = p;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int p) {
        this.position = p;
    }

    public String getIdPosition() {
        return this.parcours[position];
    }

    public boolean isOnNode() {
        return this.onNode;
    }

    public int step() {
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