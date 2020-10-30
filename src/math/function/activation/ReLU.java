package math.function.activation;

public class ReLU extends ActivationFunction {

    public ReLU() {
        super(
                (in) -> Math.max(0, in),
                (in) -> in <= 0 ? 0 : 1,
                "ReLU"
        );

    }

}
