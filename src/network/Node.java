package network;

import function.activation.IActivationFunction;
import function.activation.Sigmoid;

import java.util.*;

public class Node {

    IActivationFunction function;
    ArrayList<Double> weights = new ArrayList<>();
    double bias = 0;

    public Node(IActivationFunction function) {
        this.function = function;
    }

    public Node() {}

    public Node(int numWeights) {
        randomInitialization(numWeights + 1);
    }

    public Node(IActivationFunction function, int numWeights, double bias) {
        this.bias = bias;
        this.function = function;
        randomInitialization(numWeights + 1);
    }

    public void setActivationFunction(IActivationFunction function) {
        this.function = function;
    }

    public double output(ArrayList<Double> inputs) {

        double sum = 0;

        for(int i = 0; i < inputs.size(); i++) {
            sum += inputs.get(i) * weights.get(i);
        }

        sum += bias * weights.get(weights.size() - 1);

        if(function == null) {
            function = new Sigmoid();
        }
        return function.compute(sum);
    }

    public void addWeight(Double... weights) {
        this.weights.addAll(Arrays.asList(weights));
    }

    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

    public void randomInitialization(int numWeights) {
        Random random = new Random();
        for(int i = 0; i < numWeights; i++) {
            addWeight((random.nextGaussian()));
        }
    }


}
