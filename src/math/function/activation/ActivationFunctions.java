package math.function.activation;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivationFunctions {

    public static final ActivationFunction Sigmoid = new Sigmoid();
    public static final ActivationFunction ReLU = new Rectifier();
    public static final ActivationFunction BoolCoerce = new BooleanCoercion();
    public static final ActivationFunction Tanh = new Tanh();

    private static final ArrayList<ActivationFunction> functions = new ArrayList<>(Arrays.asList(
            Sigmoid,
            ReLU,
            BoolCoerce,
            Tanh
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
