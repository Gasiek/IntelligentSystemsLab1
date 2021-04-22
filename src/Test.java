import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        Maze m = new Maze(0.6);
        List<Node> path = new ArrayList<>();
        Astar astar = new Astar(m);

        if (m.calcHcost(m.getStartingNode()) != 0) {
            path = astar.aStarAlgorithm();
        }
        m.printMaze();
        if (path.isEmpty()) {
            System.out.println("There was no path connecting the starting point with the goal.");
        }
    }
}
