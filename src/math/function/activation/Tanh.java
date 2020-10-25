package math.function.activation;

public class Tanh extends ActivationFunction {

    public Tanh() {
        super(
                (in) -> (Math.pow(Math.E, 2 * in) - 1) / (Math.pow(Math.E, 2 * in) - 1),
                (in) -> 1 - (in * in),
                "Tanh"
        );
    }

}
