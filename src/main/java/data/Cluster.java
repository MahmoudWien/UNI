package data;

import java.util.ArrayList;

/**
 * @author Markus,Ibrahim
 *
 */
public class Cluster {
	private ArrayList<Point> points;
	private Point centroid;

    public Cluster(){
        this.points = new ArrayList<Point>();
        this.centroid = null;
    }

    /**
     *
     * @return
     */
    public ArrayList<Point> getPoints() {
		return this.points;
	}

    /**
     *
     * @param points
     */
    public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

    public void addPoint(Point point){
        this.points.add(point);
    }
    /**
     *
     * @return
     */
    public Point getCentroid() {
		return this.centroid;
	}

    /**
     *
     * @param centroid
     */
    public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}

    public void clear() {
        points.clear();
    }
    public void plotCluster() {
        System.out.println("[Centroid: " + centroid.getCoordinates().toString() + "]");
        System.out.println("[Points: \n");
        for(Point p : points) {
            System.out.println(p.getCoordinates().toString());
        }
        System.out.println("]");
    }

}
