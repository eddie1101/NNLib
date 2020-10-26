package math.function.activation;

public class ReLU extends ActivationFunction {

    public ReLU() {
        super(
                (in) -> in < 0 ? 0 : Math.max(in, 1d),
                (in) -> in < 0 ? 0 : 1,
                "ReLU"
        );

    }

}
