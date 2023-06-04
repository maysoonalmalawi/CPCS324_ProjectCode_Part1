package PhoneNWPackage;

import GraphPackage.Edge;
import GraphPackage.Vertex;

public class Line extends Edge {
//--------------------------------------------------------------------------
    //varibles
    
    int lLength;
//--------------------------------------------------------------------------
    //constructers
    public Line(int weight, Vertex source, Vertex target) {
        super(weight, source, target);
        this.lLength = 5 * weight;
    }

//--------------------------------------------------------------------------
    //setters
    public void setlLength(int lLength) {

        this.lLength = lLength;
    }
//--------------------------------------------------------------------------
    //gettres
    public int getlLength() {
        return lLength;
    }
//--------------------------------------------------------------------------
   //method to display the line info.
    @Override
    public void displayInfo() {
            System.out.println("Office No. " + getSource().getLabel() + " - Office No. " + getTarget().getLabel() + ":line length: " + getWeight());

    }
    
}
