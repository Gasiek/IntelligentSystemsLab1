import java.util.*;

public class Maze {
    public static int Rows = 30;
    public static int Columns = 40;
    Node[][] A = new Node[Rows][Columns];

    public final char OBSTACLE = '*';
    public final char INITIAL_STATE = 'I';
    public final char GOAL_STATE = 'G';
    public final char BELONG = '+';
    public final char WALKABLE = ' ';
    public static double fraction = 0.3;
    public int row;
    public int column;
    private Node start; //where is the INITIAL_STATE
    private Node end;   //where is the GOAL_STATE


    public Maze() {
        this(fraction);
    }

    public Maze(double newFraction) {
        if (newFraction > 0.9 || newFraction < 0) {
            System.out.println("The fraction has to be a value between 0 and 0.9. It has been changed to default value: " + fraction);
            newFraction = fraction;
        }
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                if (Math.random() < newFraction) A[i][j] = new Node(i, j, OBSTACLE);//A[i][j] = OBSTACLE;
                else A[i][j] = new Node(i, j, WALKABLE);
            }
        }
        setState(INITIAL_STATE);
        setState(GOAL_STATE);
    }

    public static void main(String[] args) {
        //TODO here we write the A* algorithm
        Maze m = new Maze(0.3);
        System.out.println(m.calcHcost(m.start));
        List<Node> path = new ArrayList<>();

        if (m.calcHcost(m.start) != 0) {
            path = m.aStarAlgorithm();
        }
        for (Node node : path) {
            System.out.println(node.getX() + ", " + node.getY());
        }
        m.printMaze();
    }

    public void printMaze() {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                System.out.print(A[i][j].getNodeState() + " ");
            }
            System.out.print("\n");
        }
    }

    public double calcHcost(Node n) {
        return Math.sqrt(Math.pow((n.getX() - end.getX()), 2) + Math.pow((n.getY() - end.getY()), 2));
    }

    private void setState(char newState) {
        do {
            row = (int) (Math.random() * Rows);
            column = (int) (Math.random() * Columns);
        } while (A[row][column].getNodeState() != WALKABLE);
        A[row][column] = new Node(row, column, newState);
        System.out.println(row + ", " + column);
        if (newState == INITIAL_STATE) start = A[row][column]; //locating the starting point
        else end = A[row][column];  //locating the ending point
    }

    public List<Node> aStarAlgorithm() {
        List<Node> closedSet = new ArrayList<>();
        List<Node> openSet = new ArrayList<>(List.of(start));
        HashMap<Node, Node> parent = new HashMap<Node, Node>();
        Node current;
        double tentative_g;
        List<Node> path = new ArrayList<>();

        start.setgCost(0);

        while (!openSet.isEmpty()) {
            current = Collections.min(openSet, Comparator.comparing(Node::gethCost));//chooses a node with minimal f_cost
            current.addNeighbors(A);
            if (current.getNodeState() == GOAL_STATE) {
                path.add(current);
                while (parent.get(current) != null) {
                    current = parent.get(current);
                    if (current.getNodeState() != 'G' && current.getNodeState() != 'I') current.setNodeState('+');
                    path.add(current);

                }
                return path;
            }
            openSet.remove(current);
            closedSet.add(current);
            for (Node neighbor : current.getNeighbors()) {
                if (closedSet.contains(neighbor)) continue;
                tentative_g = current.getgCost() + 1;//tentative_g := g[current] + dist_between(current,neighbor)
                if (!closedSet.contains(neighbor) || tentative_g < neighbor.getgCost()) {
                    parent.put(neighbor, current);
                    neighbor.setgCost(tentative_g);
                    neighbor.sethCost(calcHcost(neighbor));
                    neighbor.setfCost(neighbor.getgCost() + neighbor.gethCost());
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return path;
    }
}