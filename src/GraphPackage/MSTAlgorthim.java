package GraphPackage;

import java.util.ArrayList;

public abstract class MSTAlgorthim {
//--------------------------------------------------------------------------
    //Varibels    

    Graph graph;
    ArrayList<Edge> MSTResultList;
//--------------------------------------------------------------------------
    //Setters

    public void setGraph(Graph graph) {
        this.graph = graph;

    }

//--------------------------------------------------------------------------
    //Getters
    public Graph getGraph() {
        return graph;
    }

//--------------------------------------------------------------------------
    //the display method to print the results of the algorthim,s
    public void displayResultingMST() {
    }

}
