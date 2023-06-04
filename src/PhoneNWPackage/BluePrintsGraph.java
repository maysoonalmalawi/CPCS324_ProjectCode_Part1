package PhoneNWPackage;

import GraphPackage.Graph;
import GraphPackage.Edge;
import GraphPackage.Vertex;

//--------------------------------------------------------------------------
public class BluePrintsGraph extends Graph {

//--------------------------------------------------------------------------   
    // constructer
    public BluePrintsGraph(int veticesNo, int edgeNo, boolean isDigraph) {
        super(veticesNo, edgeNo, isDigraph);
    }
//--------------------------------------------------------------------------   
    // the overriden method create edge to creat the new line object

    @Override
    public Edge createEdge(Vertex v, Vertex u, int w) {
        return new Line(w, v, u);
    }
//--------------------------------------------------------------------------
    // the overriden method create vertex to creat the new office object

    @Override
    public Vertex createVertex(String label) {
        Vertex office = isFound(label);
        return office;

    }
    //-------------------------------------------------------------------------- 
    // method to return the office to be created

    public Vertex isFound(String label) {
        Vertex found = null;
        int k = 0;
        while (k < getVeticesNo()) {
            if (getVertex(k) != null) {
                if (getVertex(k).getLabel().equals(label)) {
                    found = getVertex(k);
                    break;
                }
            } else {
                found = new Office(label);
                vertices[k] = found;
                break;
            }
            k++;
        }

        return found;
    }

}
