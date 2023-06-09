package GraphPackage;

import java.util.*;

public class KruskalAlg extends MSTAlgorthim {
    //Varibles     

    //to store the edges in ascending order
    List<Edge> edges = new ArrayList<>();
    //to store the disjoint set
    static HashMap<Vertex, Vertex> parents = new HashMap<>();

    //--------------------------------------------------------------------------
    public void KruskalAlgorithm(Graph graph) {
        // to store the final result
        MSTResultList = new ArrayList<>();

        int verticesNo = graph.getVeticesNo();

        // collect all the edges from the adjacency list       
        for (int i = 0; i < graph.getVeticesNo(); i++) {
            edges.addAll(graph.getVertex(i).getAdjlist());
        }

        // Sort edges in ascending order of weight
        edges.sort((Edge e1, Edge e2) -> Integer.compare(e1.getWeight(), e2.getWeight()));

        // initialize‏ the disjoint set
        for (int i = 0; i < verticesNo; i++) {
            parents.put(graph.vertices[i], graph.vertices[i]);
        }

        for (Edge edge : edges) {
            //to find if the two vertices is in the same set 
            if (find(edge.getSource()) != find(edge.getTarget())) {
                // if there not combine them
                union(edge.getTarget(), edge.getSource());
                MSTResultList.add(edge);
            }
        }
        displayResultingMST();
    }

//--------------------------------------------------------------------------
    // recurion to find the parent
    public Vertex find(Vertex vertex) {
        if (parents.get(vertex) == vertex) {
            return vertex;
        } else {
            return find(parents.get(vertex));
        }
    }
//--------------------------------------------------------------------------
    // union the sets
    public void union(Vertex target, Vertex source) {

        parents.put(find(target), find(source));

    }
//--------------------------------------------------------------------------
   //display
    @Override
    public void displayResultingMST() {
        int total = 0;
        System.out.println("The phone network (minimum spanning tree) generated by Kruskal algorithm is as follows:\n");
        for (Edge edge : MSTResultList) {
            edge.displayInfo();
            total += edge.getWeight();
        }

        System.out.println("\nThe cost of designed phone network: " + total);
    }

}
