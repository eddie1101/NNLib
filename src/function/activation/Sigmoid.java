package function.activation;

import function.OneParameterFunction;

public class Sigmoid implements OneParameterFunction {

    @Override
    public double compute(double in) {
        return 1 / (1 + (Math.pow(Math.E, -in)));
    }

}
