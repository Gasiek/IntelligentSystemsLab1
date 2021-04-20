import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.MAX_VALUE;

public class Node {
    private int x, y;
    private double hCost, fCost;
    private double gCost = MAX_VALUE;
    private char nodeState; //here we define which state are we
    private List<Node> neighbors = new ArrayList<>();
    private Node parent;
    private Boolean path = false;

    public Node(int girdX, int girdY, char state) {
        this.x = girdX;
        this.y = girdY;
        this.nodeState = state;
    }

    public void addNeighbors(Node[][] A) {
        if (x < Maze.Rows - 1) {
            this.neighbors.add(A[x + 1][y]);
        }
        if (x > 0) {
            this.neighbors.add(A[x - 1][y]);
        }
        if (y < Maze.Columns - 1) {
            this.neighbors.add(A[x][y + 1]);
        }
        if (y > 0) {
            this.neighbors.add(A[x][y - 1]);
        }
    }

    public double getgCost() {
        return gCost;
    }

    public void setgCost(double gCost) {
        this.gCost = gCost;
    }

    public double gethCost() {
        return hCost;
    }

    public void sethCost(double hCost) {
        this.hCost = hCost;
    }

    public void setfCost(double fCost) {
        this.fCost = fCost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getNodeState() {
        return nodeState;
    }

    public void setNodeState(char nodeState) {
        this.nodeState = nodeState;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }
}
