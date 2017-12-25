package Concrete;

import Abstract.IGenerator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Mariya on 23.12.2017.
 */
public class SimpleGenerator implements IGenerator {
    @Override
    public int[][] generate(int n, int m) {
        int[][] dataSet = new int[n][8];
        for(int i=0; i< n;i++)
            for(int j=0; j<m; j++)
                dataSet[i][j] = ThreadLocalRandom.current().nextInt(1, m + 1);
        return dataSet;
    }

    public void print(int[][] map){
        for(int i=0; i< map.length; i++) {
            for (int j = 0; j < map[0].length; j++)
                System.out.print(map[i][j]);
            System.out.print('\n');
        }
    }
}
