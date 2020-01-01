package network;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

public class Network {
    static private int id; //compteur de network crée
    private final List<Node> nodes;
    private final List<Arc> arcs;
    private final List<Point> points;


    public Network(List<Node> nodes, List<Arc> arcs,List<Point> points) {
        this.arcs = arcs;
        this.nodes = nodes;
        this.points = points;
    }

    public Network() {
        this.arcs = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.points = new ArrayList<>();
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node newNode){
        this.nodes.add(newNode);
    }

    public void delNode(Node node){
        this.nodes.remove(node);
    }

    public void addArc(ARC arc){
        this.arcs.add(arc);
    }

    public void delArc(Arc arc){
        this.arcs.remove(arc);
    }

    public void addPoint(Point point){
        this.points.add(point);
    }

    public void delPoint(Point point){
        this.points.remove(point);
    }

}



