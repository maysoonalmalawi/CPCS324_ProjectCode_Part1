package GraphPackage;

import java.util.LinkedList;

public class Vertex {
//--------------------------------------------------------------------------
    // varibles

    String Label;
    boolean isVisited;
    private LinkedList<Edge> adjlist = new LinkedList<>();
//--------------------------------------------------------------------------
    // constructers

    public Vertex(String label) {
        this.Label = label;
    }
//--------------------------------------------------------------------------
    // Settres

    public void setAdjlist(LinkedList<Edge> adjlist) {
        this.adjlist = adjlist;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }
//--------------------------------------------------------------------------
    //Gettres

    public LinkedList<Edge> getAdjlist() {
        return adjlist;
    }

    public String getLabel() {
        return Label;
    }

    public boolean getIsVisited() {
        return isVisited;
    }
//--------------------------------------------------------------------------
    // method to return the boolean value 
    // true > maens the vertex ios visited
    // false > the vertex is not vidited yet

    public boolean isIsVisited() {
        return isVisited;
    }
//--------------------------------------------------------------------------
    // method to print the adjacency list of the vertices

    public void displayInfo() {
        for (int i = 0; i < adjlist.size(); i++) {
            adjlist.get(i).displayInfo();
        }

    }

}
