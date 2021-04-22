import java.util.*;

public class Astar {
	private List<Node> closedSet;
	private List<Node> openSet;
	private HashMap<Node, Node> parent;
	private Node current;
	private double tentative_g;
	private List<Node> path = new ArrayList<>();
	private Maze maze;

	public Astar() {
		this(new Maze());
	}

	public Astar(Maze maze) {
		this.maze = maze;
		closedSet = new ArrayList<>();
		openSet = new ArrayList<>(List.of(maze.getStartingNode()));
		parent = new HashMap<Node, Node>();
		path = new ArrayList<>();
	}

	public Maze getMaze() {
		return maze;
	}

	public List<Node> aStarAlgorithm() {
		while (!openSet.isEmpty()) {
			current = Collections.min(openSet, Comparator.comparing(Node::getfCost));//chooses a node with minimal f_cost
			current.addNeighbors(maze.getMazeMatrix()); //Modified

			if (current.getNodeState() == maze.getGoalState()) { //Modified
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
					neighbor.sethCost(maze.calcHcost(neighbor)); //Modified
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
