import algorithms.Kmeans;

/**
 * Created by mahmoud on 30.03.17.
 */
public class Test {

    public static void main(String[] args) {
        Kmeans kmeans = new Kmeans(3,10,2);
        kmeans.init();
        kmeans.calculate();
    }
}
