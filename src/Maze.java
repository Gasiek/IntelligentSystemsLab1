public class Maze {
    public static int Rows = 60;
    public static int Columns = 80;
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
        if(newFraction>0.9 || newFraction<0) {
            System.out.println("The fraction has to be a value between 0 and 0.9. It has been changed to default value: " + fraction);
            newFraction=fraction;
        }
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                if (Math.random() < newFraction) A[i][j] = new Node(i,j,OBSTACLE) ;//A[i][j] = OBSTACLE;
                else A[i][j] = new Node (i,j,WALKABLE);
            }
        }
        setState(INITIAL_STATE);
        setState(GOAL_STATE);
    }

    private void setState(char newState) {
        do {
            row = (int) (Math.random() * Rows);
            column = (int) (Math.random() * Columns);
        } while (A[row][column].getNodeState() != WALKABLE);
        A[row][column] = new Node (row , column ,newState);
        if(newState==INITIAL_STATE) start = A[row][column] ; //locating the starting point
        else end = A[row][column] ;  //locating the ending point
    }


    public void printMaze() {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                System.out.print(A[i][j].getNodeState() + " ");
            }
            System.out.print("\n");
        }
    }
    public double calcGcost(Node n){
        return Math.sqrt(Math.pow((n.getX() - start.getX()), 2) + Math.pow((n.getY() - start.getY()), 2));
        // im not sure about this one
    }
    public double calcHcost(Node n){
        return Math.sqrt(Math.pow((n.getX() - end.getX()), 2) + Math.pow((n.getY() - end.getY()), 2));
    }
    public double calcFcost(Node n){
        return calcGcost(n)+calcHcost(n) ;
    }

    public static void main(String[] args) {
        //TODO here we write the A* algorithm
        Maze m = new Maze() ;
        m.printMaze();
        System.out.println(m.calcHcost(m.start));
    }

}