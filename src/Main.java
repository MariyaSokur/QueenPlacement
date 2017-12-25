import Abstract.IAgent;
import Abstract.IGenerator;
import Concrete.CostFunction;
import Concrete.ProportionalAgent;
import Concrete.SimpleGenerator;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.demo.charts.ExampleChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mariya on 23.12.2017.
 */
public class Main {//implements ExampleChart<XYChart> {
    static IGenerator gen;

    public static void main(String[] args){
        gen = new SimpleGenerator();
        int iters = 20;

        double[] X = new double[iters];
        for(int i = 0; i< X.length; i++)
            X[i] = i+1;

        double[][] resultWithWithoutMutations = analyzeWithWithoutMutations(iters);
        double[][] resultMoreLessLayers = analyzeMoreLessLayers(iters);

        // Create Chart
        XYChart chart = QuickChart.getChart("Графік з та без мутацій",
                "Спроба", "Ітерація, в якій було знайдено рішення",
                "Without mutation", X, resultWithWithoutMutations[0]);
        XYChart chart1 = QuickChart.getChart("Графік більше/менше даних/ітерацій",
                "Спроба", "Ітерація, в якій було знайдено рішення",
                "More layers less data", X, resultMoreLessLayers[0]);

        chart.addSeries("With mutation", X, resultWithWithoutMutations[1]);
        chart1.addSeries("Less layers more data", X, resultMoreLessLayers[1]);
        List<XYChart> charts = new ArrayList();
        charts.add(chart);
        charts.add(chart1);
        // Show it
        new SwingWrapper(charts).displayChartMatrix();
    }

    public static double[][] analyzeWithWithoutMutations(int iterations){
        double[][] results = new double[2][iterations];
        for(int iter = 0; iter < iterations; iter++) {
            int[][] array = gen.generate(10000, 8);

            IAgent agent = new ProportionalAgent(new CostFunction(), false);
            IAgent agent1 = new ProportionalAgent(new CostFunction(), true);
            int[] result = agent.solve(array, 50);

            if(result.length == 0)
                results[0][iter] = 50;
            else
                results[0][iter] = result[result.length-1];

            result = agent1.solve(array, 50);

            if(result.length == 0)
                results[1][iter] = 50;
            else
                results[1][iter] = result[result.length-1];
        }
        return results;
    }

    public static double[][] analyzeMoreLessLayers(int iterations){
        double[][] results = new double[2][iterations];
        for(int iter = 0; iter < iterations; iter++) {
            int[][] array = gen.generate(1000, 8);
            int[][] array1 = gen.generate(10000, 8);

            IAgent agent = new ProportionalAgent(new CostFunction(), false);
            int[] result = agent.solve(array, 500);

            if(result.length == 0)
                results[0][iter] = 500;
            else
                results[0][iter] = result[result.length-1];

            result = agent.solve(array1, 50);

            if(result.length == 0)
                results[1][iter] = 50;
            else
                results[1][iter] = result[result.length-1];
        }
        return results;
    }
}
