package Concrete;

import Abstract.IAgent;
import Abstract.IGenerator;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by Mariya on 23.12.2017.
 */
public class ProportialAgent implements IAgent {
    IGenerator generator;

    public ProportialAgent(IGenerator generator){
        this.generator = generator;
    }

    @Override
    public int[] solve(int[][] data, int iterationsNumber) {
        for (int i = 0; i < iterationsNumber; i++) {
            CostFunction cf = new CostFunction();
            int[] costs = cf.evaluate(data);
            int index = validate(costs);
            if(index != -1)
                return data[index];
            data = cross(data, costs);
        }
        return new int[0];
    }

    private int validate(int[] costs) {
        for(int i=0; i< costs.length; i++)
            if(costs[i] == 28)
                return i;
        return -1;
    }

    private int getLearningItem(int[] sums) {
        int total = IntStream.of(sums).sum();
        double random = ThreadLocalRandom.current().nextDouble() * total;
        for (int i = 0; i < sums.length; i++) {
            random -= sums[i];
            if (random < 0)
                return i;
        }
        return -1;
    }

    private int[][] cross(int[][] data, int[] sums){
        int[][] result = new int[data.length][data[0].length];
        for(int i=0; i<data.length; i+=2) {
            int[][] res = crossPair(data, getLearningItem(sums), getLearningItem(sums)); //возможная доработка
            result[i] = res[0];
            result[i+1] = res[1];
        }
        return result;
    }

    private int[][] crossPair(int[][] data, int first, int second){
        int[][] result = new int[2][data[0].length];
        int length = data[0].length/2;
        for(int i=0; i < length; i++){
            result[0][i] = data[first][i];
            result[1][i] = data[second][i];
            result[0][i+length] = data[second][i+length];
            result[1][i+length] = data[first][i+length];
        }
        return result;
    }
}
