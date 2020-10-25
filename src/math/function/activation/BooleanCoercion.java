package math.function.activation;

public class BooleanCoercion extends ActivationFunction {
    public BooleanCoercion() {
        super(
                (in) -> in >= 0 ? 1 : 0,
                (in) -> in >= 0 ? 1 : 0,
                "BoolCoerce"
        );
    }
}
