package network;

import function.OneParameterFunction;
import function.TwoParameterFunction;

import java.util.Random;

public class Perceptron {

    double bias = 0;
    double learningRate = 0;

    Double[] weights;
    OneParameterFunction activation;
    TwoParameterFunction error;

    public Perceptron(Double[] weights, OneParameterFunction activation, TwoParameterFunction error, double bias, double learningRate) {
        setWeights(weights);
        setActivationFunction(activation);
        setErrorFunction(error);
        setBias(bias);
        setLearningRate(learningRate);
    }

    public Perceptron(int numWeights, OneParameterFunction activation, TwoParameterFunction error, double bias, double learningRate) {
        randomInitialization(numWeights);
        setActivationFunction(activation);
        setErrorFunction(error);
        setBias(bias);
        setLearningRate(learningRate);
    }

    public void setWeights(Double[] weights) {
        this.weights = weights;
    }

    public void setActivationFunction(OneParameterFunction activation) {
        this.activation = activation;
    }

    public void setErrorFunction(TwoParameterFunction error) {
        this.error = error;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public double evaluate(Double[] inputs) {
        assert weights.length == inputs.length;

        double sum = 0;
        for(int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }
        sum += bias;

        return activationFunction(sum);
    }

    public void train(Double[] inputs, double target) {
        double evaluation = evaluate(inputs);
        double error = errorFunction(target, evaluation);

        for(int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * learningRate;
        }
    }

    public void randomInitialization(int numWeights) {
        Random random = new Random();
        Double[] randomWeights = new Double[numWeights];

        for(int i = 0; i < numWeights; i++) {
            randomWeights[i] = random.nextGaussian();
        }

        setWeights(randomWeights);
    }

    private double activationFunction(double in) {
        return this.activation.compute(in);
    }

    private double errorFunction(double target, double mark) {
        return this.error.compute(target, mark);
    }

}
