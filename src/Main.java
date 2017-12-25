import Abstract.IAgent;
import Abstract.IGenerator;
import Concrete.CostFunction;
import Concrete.ProportialAgent;
import Concrete.SimpleGenerator;

import java.util.Arrays;

/**
 * Created by Mariya on 23.12.2017.
 */
public class Main {

    public static void main(String[] args){
        IGenerator gen = new SimpleGenerator();
        int[][] array = gen.generate(1000, 8);
        IAgent agent = new ProportialAgent(gen);
        int[] result = agent.solve(array, 500);
        for(int i=0; i< result.length; i++) {
                System.out.print(result[i]);
            System.out.print('\n');
        }
    }
}
