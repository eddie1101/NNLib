package function.activation;

import function.OneParameterFunction;

public class SigmoidDerivative implements OneParameterFunction {

    @Override
    public double compute(double in) {
        return ActivationFunctions.SIGMOID.compute(in) * (1 - ActivationFunctions.SIGMOID.compute(in));
    }

}
