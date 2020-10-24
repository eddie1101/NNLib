package function.activation;

public class Sigmoid extends ActivationFunction {
    public Sigmoid() {
        super(
                (in) -> 1 / (1 + (Math.pow(Math.E, -in))),
                (in) -> in * (1 - in)
        );

    }
}
