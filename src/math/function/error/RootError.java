package math.function.error;

public class RootError extends ErrorFunction {
    public RootError() {
        super(
                (in1, in2) -> Math.sqrt(in1 - in2),
                "Root"
        );
    }
}
