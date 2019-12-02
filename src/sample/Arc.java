package sample;

public class Arc {
    
    public String id;
    private String name;
    private Node start;
    private Node end;
    private int direction;
    private float length;
    private int capacity;
    private boolean permission;
    private float speedLimit;
    private boolean speedIsLimited;

    private int countPoint;
    private static int arcCounter;

    public Arc(){
        this.id="1"+Integer.toString(arcCounter++);
        this.nom="arc";
    }

    public Arc(String id, String name, int start, int end, int direction, float length, int capacity,
            boolean permission, float speedLimit, boolean speedIsLimited, int countPoint) {
        this.id = id;
        this.name = name;
        this.start = start;
        this.end = end;
        this.direction = direction;
        this.length = length;
        this.capacity = capacity;
        this.permission = permission;
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

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
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