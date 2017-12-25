package Abstract;

/**
 * Created by Mariya on 23.12.2017.
 */
public interface ICostFunction {
    int[] evaluate(int[][] array);
    int validate(int[] costs, int n);
    int getMaximumValue(int n);
}
