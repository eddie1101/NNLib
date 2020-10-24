package function.activation;

import function.OneParameterFunction;

public abstract class ActivationFunction {

    public OneParameterFunction func;
    public OneParameterFunction dfunc;

    public ActivationFunction(OneParameterFunction func, OneParameterFunction dfunc) {
        this.func = func;
        this.dfunc = func;
    }

    public double compute(double in) {
        return func.compute(in);
    }

    public double dcompute(double in) {
        return dfunc.compute(in);
    }

    public OneParameterFunction extract() {
        return this.func;
    }

    public OneParameterFunction dextract() {
        return this.dfunc;
    }

}
