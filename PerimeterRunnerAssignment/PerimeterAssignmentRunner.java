package PerimeterRunnerAssignment;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int sideNumbers=0;
        for(Point currPt : s.getPoints()){
            sideNumbers++;
            //delete that below  **************************************
            System.out.println(currPt);
        }
        return sideNumbers;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double averagei;
        averagei = getPerimeter(s)/getNumPoints(s);
        return averagei;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double maxi = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()){
            double currDist = prevPt.distance(currPt);
            if(Math.abs(currDist) > maxi){
                maxi = Math.abs(currDist);
            }
        }
        return maxi;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double max_x ;
        Point prevPt = s.getLastPoint();
        max_x = prevPt.getX();
        for(Point currPt : s.getPoints()){
            if(currPt.getX() > max_x){
                max_x = currPt.getX();
            }
        }
        return max_x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double biggestPeri = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(biggestPeri < getPerimeter(s)){
                biggestPeri = getPerimeter(s);
            }
            //System.out.println(getPerimeter(s));
        }
        return biggestPeri;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double biggestPeri = 0.0;
        String temp = " "; // replace this code
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if(biggestPeri < getPerimeter(s)){
                biggestPeri = getPerimeter(s);
                temp = f.getName();
            }
        }
        return temp;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of sides = " + getNumPoints(s));
        System.out.println("Average side's length is = " + getAverageLength(s));
        System.out.println("Largest side's length is = " + getLargestSide(s));
        System.out.println("Largest X is = " + getLargestX(s));
        System.out.println(s.getPoints());
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
         double biggestPerimeter = getLargestPerimeterMultipleFiles();
         System.out.println("The biggest perimeter is: " + biggestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String myFile = getFileWithLargestPerimeter();
        System.out.println(myFile);
        
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        //pr.printFileNames();
        //pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
