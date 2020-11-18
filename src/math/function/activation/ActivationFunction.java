package math.function.activation;

import math.function.OneParameterFunction;

public abstract class ActivationFunction {

    private OneParameterFunction func;
    private OneParameterFunction dfunc;

    private String name;

    public ActivationFunction(OneParameterFunction func, OneParameterFunction dfunc, String name) {
        this.func = func;
        this.dfunc = dfunc;
        this.name = name;
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
