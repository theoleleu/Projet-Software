package NewProjet;
import java.util.ArrayList;
import java.util.List;
public class Node {
	private int id;
	private String descripteur;
    List<Arc> arc = new ArrayList<>();
    private int nbArc;
    List<Object> object = new ArrayList<>();
    List<Node> children = new ArrayList<>();//all the nodes come to my node 
    List<Node> parents = new ArrayList<>();// all the nodes that my node goes to 

    public Node(int Noeudid) {
        this.id = Noeudid;
    }

    public void addNodeChild(Node n1) {
    	children.add(n1);
    }

    public void addNodeParent(Node n2) {
        parents.add(n2);
    }

    public List<Node> getNodeChildren() {
        return children;
    }

    public List<Node> getNodeParents() {
        return parents;
    }

    public void removeNodeChild(Node n) {
        children.remove(n);
    }
    
    public void removeNodeParents(Node n) {
        parents.remove(n);
    }
    public void addObject(Object n) {
        object.add(n);
    }
    
    public void supprimerObject(Object n) {
        object.remove(n);
    }
    
    public void removeObject(Node n) {
        object.remove(n);
    }
    
    public void envoyerObject(Node n,Object o) {
        object.remove(o);
        n.addObject(o);
    }
    
    public void recevoirObject(Node n,Object o) {
        this.addObject(o);
    	n.supprimerObject(o);
    }
    
    public int getNodeId() {
        return id;
    }
    
    public String getNodeDetail() {
        return descripteur;
    }

}
