package math.function.error;

public class DifferenceError extends ErrorFunction {

    public DifferenceError() {
        super((in1, in2) -> in1 - in2);
        this.name = "Difference";
    }
}
