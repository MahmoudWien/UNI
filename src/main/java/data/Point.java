/**
 * 
 */
package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * @author Markus,Ibrahim
 *
 */
public class Point {
    private ArrayList<Double> coordinates;

    public Point() {
        coordinates.add(0.0);//x
        coordinates.add(0.0);//y
    }

    public Point (ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Double> coordinates) {
        this.coordinates = coordinates;
    }

    public void addDimension(Double newCoord) {
        this.coordinates.add(newCoord);
    }

    public double distance(Point p2) {
        assert this.coordinates.size() == p2.coordinates.size();

        Iterator<Double> coordinate2 = p2.coordinates.iterator();
        Double squaredSum = 0.0;
        for(double coordinate : this.coordinates) {
            squaredSum += Math.pow(coordinate - coordinate2.next(), 2);
        }

        return Math.sqrt(squaredSum);

    }


}
