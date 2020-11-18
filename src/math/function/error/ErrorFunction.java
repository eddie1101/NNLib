package math.function.error;

import math.function.TwoParameterFunction;

public abstract class  ErrorFunction {

    private TwoParameterFunction func;

    private String name;

    public ErrorFunction(TwoParameterFunction func, String name) {
        this.func = func;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if(! (o instanceof ErrorFunction)) return false;
        return ((ErrorFunction) o).getName().equals(this.name);
    }

}
