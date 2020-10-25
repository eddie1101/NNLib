package math.function.activation;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivationFunctions {

    public static final ActivationFunction Sigmoid = new Sigmoid();
    public static final ActivationFunction ReLU = new Rectifier();

    private static final ArrayList<ActivationFunction> functions = new ArrayList<>(Arrays.asList(
            Sigmoid,
            ReLU
    ));

    public static ActivationFunction get(String name) {
        for(ActivationFunction func: functions) {
            if(func.getName().equals(name)) {
                return func;
            }
        }
        return null;
    }

}
