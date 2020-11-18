package math.function.activation;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivationFunctions {

    public static final ActivationFunction Sigmoid = new Sigmoid();
    public static final ActivationFunction Tanh = new Tanh();
    public static final ActivationFunction ReLU = new ReLU();
    public static final ActivationFunction SiLU = new SiLU();

    public static final ActivationFunction BoolCoerce = new BooleanCoercion();


    private static final ArrayList<ActivationFunction> functions = new ArrayList<>(Arrays.asList(
            Sigmoid,
            Tanh,
            ReLU,
            SiLU,
            BoolCoerce
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
