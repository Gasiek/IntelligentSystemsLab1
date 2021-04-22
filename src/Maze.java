public class Maze {

    private static final int ROWS = 60;
    private static final int COLUMNS = 80;
    private static double fraction = 0.3;
    private final char OBSTACLE = '*';
    private final char INITIAL_STATE = 'I';
    private final char GOAL_STATE = 'G';
    private final char WALKABLE = ' ';
    Node[][] maze = new Node[ROWS][COLUMNS];
    private int row;
    private int column;
    private Node startingNode; //Where the initial state is
    private Node goalNode;   //where the goal state is

    public Maze() {
        this(fraction);
    }

    public Maze(double newFraction) {
        if (newFraction > 0.9 || newFraction < 0) {
            System.out.println("The fraction has to be a value between 0 and 0.9. "
                    + "It has been changed to default value: " + fraction);
            newFraction = fraction;
        }
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (Math.random() < newFraction) maze[i][j] = new Node(i, j, OBSTACLE);//maze[i][j] = OBSTACLE;
                else maze[i][j] = new Node(i, j, WALKABLE);
            }
        }
        setState(INITIAL_STATE);
        setState(GOAL_STATE);
        startingNode.setgCost(0);                                                                                 //set g cost of I to 0
    }

    public static int getROWS() {
        return ROWS;
    }

    public static int getCOLUMNS() {
        return COLUMNS;
    }

    public static double getFraction() {
        return fraction;
    }

    public Node[][] getMazeMatrix() {
        return maze;
    }

    public char getGoalState() {
        return GOAL_STATE;
    }

    public Node getStartingNode() {
        return startingNode;
    }

    public Node getGoalNode() {
        return goalNode;
    }

    public void printMaze() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(maze[i][j].getNodeState() + " ");
            }
            System.out.print("\n");
        }
    }

    //Manhattan distance
    public double calcHcost(Node n) {
        return Math.abs(goalNode.getX() - n.getX()) + Math.abs(goalNode.getY() - n.getY());
    }

    private void setState(char newState) {
        do {
            row = (int) (Math.random() * ROWS);
            column = (int) (Math.random() * COLUMNS);
        } while (maze[row][column].getNodeState() != WALKABLE);
        maze[row][column] = new Node(row, column, newState);
        if (newState == INITIAL_STATE) startingNode = maze[row][column]; //locating the starting point
        else goalNode = maze[row][column];  //locating the ending point
    }
}