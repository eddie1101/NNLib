package math.function.activation;

public class SiLU extends ActivationFunction {
    public SiLU() {
        super(
                (in) -> in * ActivationFunctions.Sigmoid.compute(in),
                (in) -> ((Math.pow(Math.E, in)) * (in + 1 + Math.pow(Math.E, in))) / (Math.pow(Math.pow(Math.E, in) + 1, 2)),
                "SiLU"
        );
    }
}
