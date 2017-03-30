import algorithms.Kmeans;

/**
 * Created by mahmoud on 30.03.17.
 */
public class Test {

    public static void main(String[] args) {
        Kmeans kmeans = new Kmeans(5,100,3);
        kmeans.init();
        kmeans.calculate();
    }
}
