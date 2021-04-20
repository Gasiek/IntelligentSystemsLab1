
public class Maze {
    public static int Rows = 60;
    public static int Columns = 80;
    char[][] A = new char[Rows][Columns];
    public final char OBSTACLE = '*';
    public final char INITIAL_STATE = 'I';
    public final char GOAL_STATE = 'G';
    public final char BELONG = '+';
    public final char WALKABLE = ' ';
    public static double fraction = 0.3;
    public int row;
    public int column;

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
                if (Math.random() < newFraction) A[i][j] = OBSTACLE;
                else A[i][j] = WALKABLE;
            }
        }
        setState(INITIAL_STATE);
        setState(GOAL_STATE);
    }

    private void setState(char newState) {
        do {
            row = (int) (Math.random() * Rows);
            column = (int) (Math.random() * Columns);
        } while (A[row][column] != WALKABLE);
        A[row][column] = newState;
    }

    public void printMaze() {
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
