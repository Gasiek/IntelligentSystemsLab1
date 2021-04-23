import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Optional_task {
    private static double Max_Fraction = 0.9 ;
    private static int NumTests = 1000 ; //for each fraction
    private static String File_Location = "C:\\Users\\Parsa\\OneDrive\\Escritorio\\test.txt" ;
    private static double IncrementAmount = 0.1 ; //how much is added to the fractions in each step

    public static void main(String[] args) {
        if (Maze.Test_Mode) {  //running tests individually
            Maze m = new Maze(1);
            List<Node> path = new ArrayList<>();
            Astar astar = new Astar(m);

            if (m.calcHcost(m.getStartingNode()) != 0) {
                path = astar.aStarAlgorithm();
            }
            m.printMaze();
            if (path.isEmpty()) {
                System.out.println("There was no path connecting the starting point with the goal.");
            }
        }else { // runs NumTests for each fraction and save it
            double res =0 ; //this variable is for summing all the path size in case if it was successful (path.size !=0)
            int numDone =0 ;   //number of successful tests

            for (double i =0 ; i<Max_Fraction ; i+=IncrementAmount){
                for (int j =0 ; j<NumTests ; ++j){
                    Maze n = new Maze(i);         //creating a new maze with fraction i while j is lower than NumTests
                    List<Node> path_1 = new ArrayList<>();
                    Astar astar = new Astar(n);
                    if (n.calcHcost(n.getStartingNode()) != 0) {
                        path_1 = astar.aStarAlgorithm();
                    }
                    if(path_1.size()>0){
                        res += path_1.size() ;
                        ++numDone ;
                    }
                }
                //----------------------------------------------------------------------------- writing the File
                try {
                    File f = new File(File_Location) ;
                    String output =i + " " + (NumTests - numDone) +" " + res/NumTests + "\n"; //(fraction , number of failed tests , average)
                    write(output,f) ;
                }catch(IOException e){

                }
                res = 0 ; //set to zero for running again
                numDone = 0 ;
            }
        }
    }



    public static void write(String s , File f) throws IOException {
        FileWriter fw = new FileWriter(f,true) ; //append mode is on
        fw.write(s);
        fw.close();
    }
}
