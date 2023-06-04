package GraphPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
    // Variables

    public int verticesNo;
    int edgeNo;
    boolean isDigraph;
    public Vertex vertices[];
//--------------------------------------------------------------------------
    //Constructes

    public Graph(int veticesNo, int edgeNo, boolean isDigraph) {
        this.verticesNo = veticesNo;
        this.edgeNo = edgeNo;
        this.isDigraph = isDigraph;
        vertices = new Vertex[verticesNo];
    }
//--------------------------------------------------------------------------
    //Srtters

    public void setVeticesNo(int veticesNo) {
        this.verticesNo = veticesNo;
    }

    public void setEdgeNo(int edgeNo) {
        this.edgeNo = edgeNo;
    }

    public void setIsDigraph(boolean isDigraph) {
        this.isDigraph = isDigraph;
    }

    public void setVertices(Vertex[] vertices) {
        this.vertices = vertices;
    }
//--------------------------------------------------------------------------
    //Gettres

    public Vertex[] getVertices() {
        return vertices;
    }

    public Vertex getVertex(int i) {
        return vertices[i];
    }

    public int getVeticesNo() {
        return verticesNo;
    }

    public int getEdgeNo() {
        return edgeNo;
    }
//--------------------------------------------------------------------------
    // method to retrn the boolean value of the varible IsDigraph
    // if it's true  > Directed
    // if it's false > Undirected

    public boolean isIsDigraph() {
        return isDigraph;
    }
//--------------------------------------------------------------------------
    //method to generate random graphs based on the vertices number and edge number 

    public void makeGraph(int verticesNum, int edgeNum) {

        verticesNo = verticesNum;
        vertices = new Vertex[verticesNum];

        // loop to creat the label (concatenet the numbers with letter "O")
        for (int i = 0; i < verticesNum; i++) {
            String label = "O" + (i + 1);
            Vertex ver = createVertex(label);
        }
        // loop to creat random weights and add them to edges
        for (int i = 0; i < (verticesNum - 1); i++) {
            int randomWeight = (int) ((Math.random() * 50)+1);
            addEdge(vertices[i], vertices[i + 1], randomWeight);
        }

        //calculate the remainig edges to generate it randomly
        int remaining = edgeNum - (verticesNum - 1);

        //loop to choose random vertcies
        for (int i = 0; i < remaining; i++) {
            int ver1 = (int) (Math.random() * verticesNum);
            int ver2 = (int) (Math.random() * verticesNum);
            //check if its not duplicated
            if (ver1 != ver2 && !isDuplicated(ver1, ver2) && !isDuplicated(ver2, ver1)) {
                int weight = (int) ((Math.random() * 50)+1);
                addEdge(vertices[ver1], vertices[ver2], weight);
            } else {
                i--;
            }
        }

    }
//--------------------------------------------------------------------------
    public boolean isDuplicated(int SourceId, int DestinationId) {
        //loop check edge if it exist
        for (Edge edge : vertices[SourceId].getAdjlist()) {
            if (edge.getTarget().getLabel().equals(vertices[DestinationId].getLabel())) {
                return true;
            }
        }
        return false;
    }

//--------------------------------------------------------------------------
    // method to read the input file
    public void readGraphFromFile(File filename) throws FileNotFoundException {
        try (Scanner input = new Scanner(filename)) {
            //read the inputs
            input.next();
            this.isDigraph = (input.nextInt() == 1);
            this.verticesNo = input.nextInt();
            int edgeNum = input.nextInt();
            vertices = new Vertex[verticesNo];
            //loop to read each edge
            for (int i = edgeNum; i > 0; i--) {
                String sourceLabel = input.next();
                Vertex source = createVertex(sourceLabel);
                String targetLabel = input.next();
                Vertex target = createVertex(targetLabel);
                int weight = input.nextInt();
                addEdge(source, target, weight);
            }

        }
    }
//--------------------------------------------------------------------------
    //method to creat and add the edges into the adjlist
    public void addEdge(Vertex v, Vertex u, int w) {
        Edge ed = createEdge(v, u, w);
        v.getAdjlist().add(ed);
        if (this.isDigraph) {
            this.edgeNo++;
        } else {
            this.edgeNo = this.edgeNo + 2;
            ed = createEdge(u, v, w);
            u.getAdjlist().add(ed);
        }
    }
//--------------------------------------------------------------------------
    // super method to creat the vertex
    public Vertex createVertex(String label) {
        return null;
    }
//--------------------------------------------------------------------------
    // super method to creatthe edge
    public Edge createEdge(Vertex v, Vertex u, int w) {
        return null;
    }

}
