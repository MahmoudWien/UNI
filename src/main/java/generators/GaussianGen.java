/**
 * 
 */
package generators;

import data.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Markus
 *
 * (20 points): A data generator for gaussian clusters. 
 * The generator should take as input the count of clusters K, the count of data points N and the dimensionality D.
 */
public class GaussianGen{
    Random random = new Random();
    private Integer MIN;
    private Integer MAX;
    private Integer DIMENSION = 2; // Default DIMENSION ist 2

    public GaussianGen(int MIN, int MAX) {
        this.setMIN(MIN);
        this.setMAX(MAX);
    }

    public Integer getMIN() {
        return this.MIN;
    }

    public void setMIN(Integer MIN) {
        this.MIN = this.MIN == null ? MIN : this.MIN;
    }

    public Integer getMAX() {
        return this.MAX;
    }

    public void setMAX(Integer MAX) {
        this.MAX = this.MAX == null ? MAX : this.MAX;
    }

    public Integer getDIMENSION() {
        return DIMENSION;
    }

    public void setDIMENSION(Integer DIMENSION) {
        if(DIMENSION >0)
            this.DIMENSION = DIMENSION;
    }

    public Point generatePoint(int stdDeviation, int average) {
        assert (average > MIN && average < MAX);
        ArrayList<Double> coordinates = new ArrayList<Double>();
        int dimension = this.DIMENSION;

        while (dimension > 0) {
            Double coordinate = null;

                    while ( coordinate == null || coordinate < MIN || coordinate > MAX) {
                        coordinate = random.nextGaussian() * stdDeviation + average;
                    }

            coordinates.add(coordinate);
            dimension--;
        }

        return new Point(coordinates);
    }

    /**
     *  http://www.javamex.com/tutorials/random_numbers/gaussian_distribution_2.shtml
     *
     *  Used to generate one or more Distributions of Points to cluster later.
     *
     * @param n             Anzahl zu generierender Punkte
     * @param stdDeviation  Standardabweichung
     * @param average       Mittelwert (mean)
     * @return              Liste von Punkten
     */
    public ArrayList<Point> generatePointCluster(int n, int stdDeviation, int average) {
        ArrayList<Point> points = new ArrayList<Point>();

        while (n > 0) {
            points.add( generatePoint(stdDeviation,average) );
            n--;
        }

        return points;
    }


}
