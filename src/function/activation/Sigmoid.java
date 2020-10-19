package function.activation;

public class Sigmoid implements IActivationFunction {

    @Override
    public double compute(double in) {
        return 1d / (1 + (Math.pow(Math.E, -in)));
    }
}
