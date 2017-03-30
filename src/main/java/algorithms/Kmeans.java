/**
 * Source @http://www.dataonfocus.com/k-means-clustering-java-code/
 */
package algorithms;

import data.Cluster;
import data.Point;
import generators.GaussianGen;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Axinya,Ibrahim
 *
 */
public class Kmeans {

    private int NUM_CLUSTERS;
    private int NUM_POINTS;
    private int MIN_COORDINATE = 1;
    private int MAX_COORDINATE = 20;
    private ArrayList<Cluster> clusterListe ;
    private int DIMENSION;
    private GaussianGen gaussiangenerator;
    private int stdDeviation=6;
    private int average = 5;
    private ArrayList<Point> points ;

    public Kmeans (int k, int n , int d){
        this.clusterListe = new ArrayList<Cluster>();
        this.points = new ArrayList<Point>();
        this.NUM_CLUSTERS = k;
        this.NUM_POINTS = n;
        this.DIMENSION = d;
        this.gaussiangenerator = new GaussianGen(MIN_COORDINATE,MAX_COORDINATE);
        this.gaussiangenerator.setDIMENSION(d);
    }

    public int getNUM_CLUSTERS() {
        return NUM_CLUSTERS;
    }

    public void setNUM_CLUSTERS(int NUM_CLUSTERS) {
        this.NUM_CLUSTERS = NUM_CLUSTERS;
    }

    public int getNUM_POINTS() {
        return NUM_POINTS;
    }

    public void setNUM_POINTS(int NUM_POINTS) {
        this.NUM_POINTS = NUM_POINTS;
    }

    public int getMIN_COORDINATE() {
        return MIN_COORDINATE;
    }

    public void setMIN_COORDINATE(int MIN_COORDINATE) {
        this.MIN_COORDINATE = MIN_COORDINATE;
    }

    public int getMAX_COORDINATE() {
        return MAX_COORDINATE;
    }

    public void setMAX_COORDINATE(int MAX_COORDINATE) {
        this.MAX_COORDINATE = MAX_COORDINATE;
    }

    public int getDIMENSION() {
        return DIMENSION;
    }

    public void setDIMENSION(int DIMENSION) {
        this.DIMENSION = DIMENSION;
    }

    public int getStdDeviation() {
        return stdDeviation;
    }

    public void setStdDeviation(int stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }


    protected  void createClusters(){
        for (int i = 0; i < this.NUM_CLUSTERS; i++){
            this.clusterListe.add(new Cluster());
        }
    }

    protected void iniClustersCenter(){
        for(int i=0; i<this.clusterListe.size(); i++){
            this.clusterListe.get(i).setCentroid(gaussiangenerator.generatePoint(stdDeviation,average));
        }
    }

    public void init() {
        //Create Points
        this.createClusters();
        this.iniClustersCenter();
        this.points = gaussiangenerator.generatePointCluster(NUM_POINTS,stdDeviation,average);
        System.out.println("###########################");
        System.out.println("FIRST VALUES");
        System.out.println("###########################");
        this.printResults();
    }

    //The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        // Add in new data, one at a time, recalculating centroids with each new one.
        while(!finish) {
            //Clear cluster state
            clearClusters();

            ArrayList<Point> lastCentroids = getCentroids();

            //Assign points to the closer cluster
            this.assignCluster();

            //Calculate new centroids.
            this.calculateCentroids();

            iteration++;

            ArrayList<Point> currentCentroids = getCentroids();

            //Calculates total distance between new and old Centroids
            double distance = 0;
            for(int i = 0; i < lastCentroids.size(); i++) {
                distance += this.altNewDistance(lastCentroids.get(i),currentCentroids.get(i));
            }
            System.out.println("#################");
            System.out.println("Iteration: " + iteration);
            System.out.println("Centroid distances: " + distance);
            this.printResults();
            if(distance == 0) {
                finish = true;
            }
        }
    }

    private void clearClusters() {
        for(Cluster cluster : clusterListe) {
            cluster.clear();
        }
    }

    private ArrayList<Point> getCentroids() {
        ArrayList<Point> centroids = new ArrayList(NUM_CLUSTERS);
        for(Cluster cluster : clusterListe) {
            Point aux = cluster.getCentroid();
            centroids.add(aux);
        }
        return centroids;
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;

        for(Point point : points) {
            min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
                Cluster c = clusterListe.get(i);
                distance = this.altNewDistance(point, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            clusterListe.get(cluster).addPoint(point);
        }
    }

    private void calculateCentroids() {
        ArrayList<Double> summe ;
        for(Cluster cluster : clusterListe) {
            summe = new ArrayList<Double>();
            for(int i =0; i<cluster.getCentroid().getCoordinates().size();i++){
                summe.add(0.0);
            }

            ArrayList<Point> list = cluster.getPoints();
            int n_points = list.size();

            for(Point point : list) {
                for(int i=0; i<summe.size(); i++){
                    double x = summe.get(i)+point.getCoordinates().get(i);
                    summe.set(i,x);
                }

            }

            Point centroid = cluster.getCentroid();
            if(n_points > 0) {
                for(int i=0; i<summe.size();i++){
                    double x = summe.get(i) /n_points;
                    summe.set(i,x);
                }
                centroid.setCoordinates(summe);
            }
        }
    }

    private void printResults(){
        for(int i = 0; i < clusterListe.size(); i++) {
            clusterListe.get(i).plotCluster();
        }
    }

    public double altNewDistance(Point p1, Point p2){
        Double squaredSum = 0.0;
        int coordinateSize = p1.getCoordinates().size();
        for(int i =0; i < coordinateSize; i++) {
            squaredSum += Math.pow(p1.getCoordinates().get(i) - p2.getCoordinates().get(i) , 2);
        }
        return Math.sqrt(squaredSum);
    }
}
