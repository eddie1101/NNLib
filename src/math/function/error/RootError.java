package math.function.error;

public class RootError extends ErrorFunction {
    public RootError() {
        super(
                (in1, in2) -> {
                    boolean sign = in1 - in2 < 0; //t - f +
                    double res = Math.sqrt(Math.abs(in1 - in2));
                    return sign ? -res : res;
                },
                "Root"
        );
    }
}
