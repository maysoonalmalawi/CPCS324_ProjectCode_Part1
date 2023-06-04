package GraphPackage;

import PhoneNWPackage.BluePrintsGraph;
import java.util.*;

public class MHPrimAlg extends MSTAlgorthim {
    
   // Create a LinkedHashMap to store the shortest path in order of insertion 
    LinkedHashMap<Vertex, Edge> shortestPath = new LinkedHashMap<>();
    
//--------------------------------------------------------------------------
    public void primMST(BluePrintsGraph g, int verticesNo, Vertex[] vertices) {
      // Create a HashSet to keep track of vertices inside the heap
        HashSet<Vertex> inHeap = new HashSet<>();
        
      // Create a HashMap to keep track of each vertecies's key
        HashMap<Vertex, Integer> key = new HashMap<>();
        
      // Create an array to store heap nodes in
        HeapNode[] heapNodes = new HeapNode[g.verticesNo];

      // Loop in size of graph vertices number
        for (int i = 0; i < g.verticesNo; i++) {
           // Create new node and store it in nodes array
            heapNodes[i] = new HeapNode();
            
          // Store graph vertex in heap node vertex
            heapNodes[i].vertex = g.vertices[i];
            
          // Initialize heap node to maximum value
            heapNodes[i].key = Integer.MAX_VALUE;
            
          // Add node to nodes array
            inHeap.add(vertices[i]);
            
          // Add graph vertex to keys HashMap with an initialized maximum value
            key.put(g.vertices[i], Integer.MAX_VALUE);
        }
        
      // Change source node's key to zero
        heapNodes[0].key = 0;

     // Create min heap in the size of graph vertices number
        MinHeap minHeap = new MinHeap(g.verticesNo);
        
       // Insert heap nodes to min heap
        for (int i = 0; i < g.verticesNo; i++) {
            minHeap.insert(heapNodes[i]);
        }

      // Loop through min heap nodes
        while (!minHeap.isEmpty()) {
           // Store minimum key node
            HeapNode extractedNode = minHeap.extractMin();

          // Get minimum key node' vertex
            Vertex extractedVertex = extractedNode.vertex;
            
          // Remove minimum key vertex from inHeap HashSet
            inHeap.remove(extractedVertex);

          // Store minimum key vertex's adjacency list to list
            LinkedList<Edge> list = extractedVertex.getAdjlist();
            
          // Loop through list
            for (int i = 0; i < list.size(); i++) {
              // Get adjacency list edge
                Edge edge = list.get(i);
                
               // Check if edge's destination is not in heap
                if (inHeap.contains(edge.target)) {
                   // Store edge's destination 
                    Vertex destination = edge.target;
                   
                  // Store edge's weight
                    int newKey = edge.weight;
                    
                   // Check if destination key is larger than edge's weight
                    if (key.get(edge.target) > newKey) {
                      // If true, update key
                        decreaseKey(minHeap, newKey, destination);
                        key.put(edge.target, newKey);
                        
                      // Then add edge to shortest path
                        shortestPath.put(edge.target, edge);
                    }
                }
            }
        }
        
      // Print shortest path HashMap
        displayResultingMST();
    }
//--------------------------------------------------------------------------
  // Metod to update key of heap node
    public void decreaseKey(MinHeap minHeap, int NewKey, Vertex vertex) {
      // Get vertex's index
        int index = minHeap.indexes.get(vertex);
        
      // Get heap node from minheap's nodes array
        HeapNode node = minHeap.minH[index];
        
       // Update node's key 
        node.key = NewKey;
        
       // Check parent key
        minHeap.bubbleUp(index);
    }    
//--------------------------------------------------------------------------
 // HeapNode Class
    static class HeapNode {
        Vertex vertex;
        int key;
    }
//--------------------------------------------------------------------------
  // MinHeap Class
    static class MinHeap {
        int capacity;
        int currentSize;
        HeapNode[] minH;
        HashMap<Vertex, Integer> indexes;

      // MinHeap Constructer
        public MinHeap(int capacity) {
          // Create a HashMap to keep track of heap nodes indexes in minHeap's nodes array
            this.indexes = new HashMap<Vertex, Integer>();
            this.capacity = capacity;
           // Create an array to store min heap nodes
            minH = new HeapNode[capacity + 1];
           
           // Add first heap node randomly to to start adding nodes from index 1
            minH[0] = new HeapNode();
            minH[0].key = Integer.MIN_VALUE;
            minH[0].vertex = null;
            currentSize = 0;
        }

    // MinHeap inserton method
        public void insert(HeapNode x) {
           // Increase min heap size
            currentSize++;
            
           // Add heap node 
            int index = currentSize;
            minH[index] = x;
            indexes.put(x.vertex, index);
            
           // Check node parent
            bubbleUp(index);

        }
//--------------------------------------------------------------------------
    // Method to check if min heap parent nodes are still smaller than their children (move node up)
        public void bubbleUp(int k) {
          // Calculate parent index
            int parentIndex = k / 2;
           // Get node index
            int currentIndex = k;
            
          // Check if parent key is larger than child key
            while (currentIndex > 0 && minH[parentIndex].key > minH[currentIndex].key) {
               // If true, get heap nodes of both parent and child
                HeapNode currentNode = minH[currentIndex];
                HeapNode parentNode = minH[parentIndex];
                
               // Update their indexes in indexes HashMap
                indexes.put(currentNode.vertex, parentIndex);
                indexes.put(parentNode.vertex, currentIndex);
                
              // Swap their postions in minheap array
                swap(currentIndex, parentIndex);
                
              // Update indexes to check the parent's parent
                currentIndex = parentIndex;
                parentIndex = parentIndex / 2;
            }
        }
//--------------------------------------------------------------------------
    // Method to find the minimum key heap node
        public HeapNode extractMin() {
           // Get node in position 1
            HeapNode min = minH[1];
            
           // Get last node in min heap
            HeapNode lastNode = minH[currentSize];
            
           // Change last node index to 1 in indexes HashMap
            indexes.put(lastNode.vertex, 1);
            
           // Change node in index 1 to last node in minheap' array
            minH[1] = lastNode;
           
           // Remove last node from its previous index
            minH[currentSize] = null;
            
           // Check if node in postion 1 is smaller than its children 
            sinkDown(1);
            
           // Decrease minheap size 
            currentSize--;
            
           // Return minimum key node
            return min;
        }
//--------------------------------------------------------------------------
    // Method to check if parent node is smaller than its children (move parent down)
        public void sinkDown(int k) {
          // Get parent index and store it in smallest
            int smallest = k;
            
          // Get left child index 
            int leftChildIndex = 2 * k;
            
          // Get right child index 
            int rightChildIndex = 2 * k + 1;
            
           // If left child is smaller than parent
            if (leftChildIndex < heapSize() && minH[smallest].key > minH[leftChildIndex].key) {
               // Change smallest rto left child index
                smallest = leftChildIndex;
            }
            
           // If right child is smaller than parent
            if (rightChildIndex < heapSize() && minH[smallest].key > minH[rightChildIndex].key) {
               // Change smallest to right child index
                smallest = rightChildIndex;
            }
            
           // Check if the parent is still the smallest 
            if (smallest != k) {
              // If it is not get both parent and smallest heap nodes
                HeapNode smallestNode = minH[smallest];
                HeapNode kNode = minH[k];

              // Update their indexes in indexes HashMap
                indexes.put(smallestNode.vertex, k);
                indexes.put(kNode.vertex, smallest);
                
              // Swap their postions in minheap array
                swap(k, smallest);
                
               // Check the new parent 
                sinkDown(smallest);
            }
        }
//--------------------------------------------------------------------------
    // Method to swap two nodes in minheap's node array
        public void swap(int a, int b) {
            HeapNode temp = minH[a];
            minH[a] = minH[b];
            minH[b] = temp;
        }
//--------------------------------------------------------------------------
        public boolean isEmpty() {
            return currentSize == 0;
        }
//--------------------------------------------------------------------------
        public int heapSize() {
            return currentSize;
        }
    }
//--------------------------------------------------------------------------
  
 // Method to print the minimum spanning tree path 
    @Override
    public void displayResultingMST() {
        int total = 0;
        System.out.println("The phone network (minimum spanning tree) generated by min-heap based Prim algorithm  is as follows:\n");
        for (Edge i : shortestPath.values()) {
            i.displayInfo();
            total += i.getWeight();
        }
        System.out.println("\nThe cost of designed phone network: " + total);
    }

}
