package Abstract;

/**
 * Created by Mariya on 23.12.2017.
 */
public interface IAgent {
    int[] solve(int[][] data, int iterationsNumber);
    int getLearningItem(int[] sums);
    int[][] cross(int[][] data, int[] sums);
    int[][] crossPair(int[][] data, int first, int second);
    void mutation(int[][] data, int probability);
}
