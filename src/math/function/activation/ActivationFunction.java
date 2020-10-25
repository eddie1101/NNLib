package math.function.activation;

import math.function.OneParameterFunction;

public abstract class ActivationFunction {

    protected OneParameterFunction func;
    protected OneParameterFunction dfunc;

    protected String name;

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

    public String getName() {
        return this.name;
    }


}
