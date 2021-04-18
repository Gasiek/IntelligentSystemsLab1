/*import java.util.ArrayList;
import java.util.List;

public class Logic {
    public final static Node[][] Maze_Node = new Node[Maze.Rows][Maze.Columns] ;
    private final List<Node> openset = new ArrayList<>() ;
    private List<Node> path ;
    Maze basic_maze = new Maze() ; // setting up our first Maze
    private Node start; //where is the INITIAL_STATE
    private Node end;   //where is the GOAL_STATE

    public void setUp(){ //Here we are doing the basic initialization(converting our basic maze into the Node Maze)
        for(int i=0 ; i< basic_maze.row ; ++i){
            for(int j=0 ; j< basic_maze.column ; ++j){
                Maze_Node[i][j]  = new Node(i,j, basic_maze.getChar(i,j)) ;  //we are setting this up
                if(basic_maze.getChar(i,j) == basic_maze.INITIAL_STATE){
                    start = new Node(i,j, basic_maze.getChar(i,j)) ; //finding the INITIAL_STATE
                }
                if(basic_maze.getChar(i,j) == basic_maze.GOAL_STATE){
                    end = new Node(i,j, basic_maze.getChar(i,j)) ; //finding the GOAL_STATE
                }
            }
        }
        //----------------------
        for(int i=0 ; i< basic_maze.row ; ++i){
            for(int j=0 ; j< basic_maze.column ; ++j){
                Maze_Node[i][j].addNeighbours();
                Maze_Node[i][j].setgCost(calcGcost(Maze_Node[i][j]));
                Maze_Node[i][j].sethCost(calcHcost(Maze_Node[i][j]));
                Maze_Node[i][j].setfCost(calcFcost(Maze_Node[i][j]));
            }
        }
    }

    public double calcGcost(Node n){
        return Math.sqrt(Math.pow((n.getX() - start.getX()), 2) + Math.pow((n.getY() - start.getY()), 2));

    }
    public double calcHcost(Node n){
        return Math.sqrt(Math.pow((n.getX() - end.getX()), 2) + Math.pow((n.getY() - end.getY()), 2));
    }
    public double calcFcost(Node n){
        return calcGcost(n)+calcHcost(n) ;
    }


}
*/