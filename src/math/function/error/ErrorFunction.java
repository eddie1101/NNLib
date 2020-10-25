package math.function.error;

import math.function.TwoParameterFunction;

public abstract class  ErrorFunction {

    protected TwoParameterFunction func;

    protected String name;

    public ErrorFunction(TwoParameterFunction func) {
        this.func = func;
    }

    public double compute(double in1, double in2) {
        return this.func.compute(in1, in2);
    }

    public TwoParameterFunction extract() {
        return this.func;
    }

    public String getName() {
        return this.name;
    }

}
