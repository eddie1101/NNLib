package math.function.activation;

public class Tanh extends ActivationFunction {

    public Tanh() {
        super(
                (in) -> Math.tanh(in),
                (in) -> 1 - (in * in),
                "Tanh"
        );
    }

}
