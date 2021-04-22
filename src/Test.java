import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        Maze m = new Maze(0.3);
        System.out.println(m.calcHcost(m.getStartingNode()));
        List<Node> path = new ArrayList<>();
        Astar astar = new Astar(m);

        if (m.calcHcost(m.getStartingNode()) != 0) {
            path = astar.aStarAlgorithm();
        }
//        for (Node node : path) {
//            System.out.println(node.getX() + ", " + node.getY());
//        }
        m.printMaze();
        if (path.isEmpty()) {
            System.out.println("There was no path connecting the starting point with the goal.");
        }
    }
}
