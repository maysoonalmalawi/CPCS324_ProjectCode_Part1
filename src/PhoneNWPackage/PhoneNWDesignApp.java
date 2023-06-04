package PhoneNWPackage;

import GraphPackage.KruskalAlg;
import java.io.File;
import java.io.FileNotFoundException;
import GraphPackage.MHPrimAlg;

public class PhoneNWDesignApp {
//--------------------------------------------------------------------------
    // object graph from the inherted class BluePrintsGraph
    public static final String RED = "\033[0;31m";     // RED
    static public BluePrintsGraph PhLNetwork = new BluePrintsGraph(0, 0, false);
    //--------------------------------------------------------------------------

    public static void main(String[] args) throws FileNotFoundException {
        // creat the file  
        File inputFile = new File("inputfile.txt");
        // check if the file exist
        if (!inputFile.exists()) {
            System.out.println(inputFile.getName() + " file not found.");

            System.exit(0);
        }
        // print the graph from the input file ( Requirement 1)
        PhLNetwork.readGraphFromFile(inputFile);

        // print the graph that randomy generated(‏ Requirement 2)
        //PhLNetwork.makeGraph(10000, 25000);
        
        
        //The kruskal algotrthim
        KruskalAlg k = new KruskalAlg();
        System.out.println("------------------------------------------------------------");

        long startTimeK = System.currentTimeMillis();
        k.KruskalAlgorithm(PhLNetwork);
        long endTimeK = System.currentTimeMillis();
        
        
        long elapsedTimeK = endTimeK - startTimeK;
        System.out.println(RED+"Elapsed time in milliseconds: " + elapsedTimeK);
        

        //The prim algorthim
        MHPrimAlg prim = new MHPrimAlg();
        System.out.println("------------------------------------------------------------");

        long startTimeP = System.currentTimeMillis();
        prim.primMST(PhLNetwork, PhLNetwork.verticesNo, PhLNetwork.vertices);
        long endTimep = System.currentTimeMillis();

        long elapsedTimeP = endTimep - startTimeP;
        System.out.println(RED+"Elapsed time in milliseconds: " + elapsedTimeP);
    }
}
