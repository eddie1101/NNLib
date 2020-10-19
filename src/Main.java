import function.activation.IActivationFunction;
import function.error.IErrorFunction;
import network.Network;
import network.Perceptron;

public class Main {
    public static void main(String[] args) {

        Double[] input = {0.6, 2.4};

        Network network = new Network(1, 1, 1, true, 1, input, (double in) -> in < 0 ? -1 : 1);

        Perceptron perceptron = new Perceptron(2,
                (IActivationFunction) ((double in) -> in < 0 ? -1 : 1),
                (IErrorFunction) ((double target, double mark) -> target - mark), 1d, 0.1);



    }

}
