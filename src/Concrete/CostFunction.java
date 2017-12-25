package Concrete;

import Abstract.ICostFunction;

/**
 * Created by Mariya on 23.12.2017.
 */
public class CostFunction implements ICostFunction {
    @Override
    public int[] evaluate(int[][] array) {
        int[] result = new int[array.length];
        for(int i=0; i< array.length; i++) {
            int[][] passedVertex = new int[array[0].length][array[0].length];
            for (int j = 0; j < array[0].length; j++) {
                int x0 = j;
                int y0 = array[i][j];
                passedVertex[j][j] = 1;
                for (int k = 0; k < array[0].length; k++) {
                    if (k != x0 && passedVertex[j][k] == 0 && passedVertex[k][j] == 0) {
                        if (array[i][k] == y0) //если на одной линии
                            result[i] += 1;
                        if (Math.abs(x0 - k) == Math.abs(array[i][k] - array[i][j])) //если на одной диагонали
                            result[i] += 1;
                    }
                    passedVertex[j][k] = passedVertex[k][j] = 1; //устанавливаем, что просмотрели эту вершину
                }
            }
        }
        for(int i=0; i < result.length; i++)
            result[i] = 28 - result[i];
        return result;
    }
}
