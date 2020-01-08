package network;

public class Arc {
    public String id;
    public String name;
    public Node start;
    public Node end;
    public int direction;
    public Double longueur;
    public int capacite;
    public boolean doublesens;
    public float speedLimit;
    public boolean speedIsLimited;

    public int countPoint;

    public static int arcCounter;

    public Arc(){
        this.id="1"+Integer.toString(arcCounter++);
        this.name="arc";
    }

    public Arc(String id, String name, Node start, Node end, int direction, Double length, int capacity,
               boolean permission, float speedLimit, boolean speedIsLimited, int countPoint) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.direction = direction;
        this.longueur = length;
        this.capacite = capacity;
        this.doublesens = permission;
        this.speedLimit = speedLimit;
        this.speedIsLimited = speedIsLimited;
        this.countPoint = countPoint;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Double getLength() {
        return longueur;
    }

    public void setLength(Double length) {
        this.longueur = length;
    }

    public int getCapacity() {
        return capacite;
    }

    public void setCapacity(int capacity) {
        this.capacite = capacity;
    }

    public boolean isPermission() {
        return doublesens;
    }

    public void setPermission(boolean permission) {
        this.doublesens = permission;
    }

    public float getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(float speedLimit) {
        this.speedLimit = speedLimit;
    }

    public boolean isSpeedIsLimited() {
        return speedIsLimited;
    }

    public void setSpeedIsLimited(boolean speedIsLimited) {
        this.speedIsLimited = speedIsLimited;
    }

    public int getCountPoint() {
        return countPoint;
    }

    public void setCountPoint(int countPoint) {
        this.countPoint = countPoint;
    }

    public static int getArcCounter() {
        return arcCounter;
    }

    public static void setArcCounter(int arcCounter) {
        Arc.arcCounter = arcCounter;
    }
}
