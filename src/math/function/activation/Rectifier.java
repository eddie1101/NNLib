package math.function.activation;

public class Rectifier extends ActivationFunction {

    public Rectifier() {
        super(
                (in) -> in < 0 ? 0 : in,
                (in) -> in < 0 ? 0 : 1,
                "ReLU"
        );

    }

}
