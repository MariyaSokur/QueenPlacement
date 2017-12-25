package Concrete;

import Abstract.IAgent;
import Abstract.ICostFunction;
import Abstract.IGenerator;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by Mariya on 23.12.2017.
 */
public class ProportionalAgent implements IAgent {
    ICostFunction cf;
    boolean withMutation;

    public ProportionalAgent(ICostFunction cf, boolean withMutation){
        this.cf = cf;
        this.withMutation = withMutation;
    }

    @Override
    public int[] solve(int[][] data, int iterationsNumber) {
        int[] result = new int[data[0].length+1];
        for (int i = 0; i < iterationsNumber; i++) {
            int[] costs = cf.evaluate(data);
            int index = cf.validate(costs, data[0].length);
            if(index != -1){
                for(int k = 0; k < data[0].length; k++)
                    result[k] = data[index][k];
                result[data[0].length] = i;
                return result;
            }
            data = cross(data, costs);
            if(withMutation)
                mutation(data, 2);
        }
        return new int[0];
    }

    public int getLearningItem(int[] sums) {
        int total = IntStream.of(sums).sum();
        double random = ThreadLocalRandom.current().nextDouble() * total;
        for (int i = 0; i < sums.length; i++) {
            random -= sums[i];
            if (random < 0)
                return i;
        }
        return -1;
    }

    public int[][] cross(int[][] data, int[] sums){
        int[][] result = new int[data.length][data[0].length];
        for(int i=0; i<data.length; i+=2) {
            int[][] res = crossPair(data, getLearningItem(sums), getLearningItem(sums)); //возможная доработка
            result[i] = res[0];
            result[i+1] = res[1];
        }
        return result;
    }

    public int[][] crossPair(int[][] data, int first, int second){
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

    public void mutation(int data[][], int probability){
        for(int i=0; i<data.length; i++)
            for(int j=0; j<data[0].length; j++){
                int random  = (int)ThreadLocalRandom.current().nextDouble() * 100;
                if(random > probability)
                    data[i][j]+=1;
            }
    }
}
