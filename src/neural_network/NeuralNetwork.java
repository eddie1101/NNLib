package neural_network;

import function.OneParameterFunction;
import function.TwoParameterFunction;
import function.activation.ActivationFunction;
import function.activation.ActivationFunctions;
import function.error.ErrorFunctions;
import math.Matrix;

public class NeuralNetwork {

    double learningRate = 0.1;
    ActivationFunction activationFunction = ActivationFunctions.SIGMOID;
    TwoParameterFunction errorFunction = ErrorFunctions.DIFFERENCE_ERROR;

    Matrix[] weights;
    Matrix[] biases;

    int numInputs;
    int numHiddenLayers;
    int numNodesPerHiddenLayer;
    int numOutputs;

    int matricesLength;

    public NeuralNetwork(int numInputs, int numHiddenLayers, int numNodesPerHiddenLayer, int numOutputs) {

        this.numInputs = numInputs;
        this.numHiddenLayers = numHiddenLayers;
        this.numNodesPerHiddenLayer = numNodesPerHiddenLayer;
        this.numOutputs = numOutputs;

        this.matricesLength = numHiddenLayers + 1;

        this.weights = new Matrix[matricesLength];

        for (int i = 0; i < weights.length; i++) {
            if(i == 0) {
                weights[i] = new Matrix(numNodesPerHiddenLayer, numInputs);
            }else if(i < weights.length - 1) {
                weights[i] = new Matrix(numNodesPerHiddenLayer, numNodesPerHiddenLayer);
            }else if(i == weights.length - 1) {
                weights[i] = new Matrix(numOutputs, numNodesPerHiddenLayer);
            }else{
                weights[i] = new Matrix(); //????
            }
            weights[i].randomInitialization();
        }

        this.biases = new Matrix[matricesLength];

        for(int i = 0; i < biases.length; i++) {
            biases[i] = new Matrix(numNodesPerHiddenLayer, 1);
            biases[i].randomInitialization();
        }
    }

    public NeuralNetwork setLearningRate(double learningRate) {
        this.learningRate = learningRate;
        return this;
    }

    public NeuralNetwork setActivation(ActivationFunction func) {
        this.activationFunction = func;
        return this;
    }

    public NeuralNetwork setError(TwoParameterFunction func) {
        this.errorFunction = func;
        return this;
    }

    public Double[] forwardPropagation(Double[] inputs) {

        Matrix result = new Matrix(inputs);
        for(int i = 0; i < weights.length; i++) {
            result = Matrix.multiplicationOf(weights[i], result);
            result.add(biases[i]);
            result.map(ActivationFunctions.SIGMOID.extract());
        }

        return result.toArray();

    }

//    public Double[] forwardPropagationDebug(Double[] inputs) {
//
//        int count = 0;
//        Matrix result = new Matrix(inputs);
//        for(int i = 0; i < weights.length; i++, count++) {
//            System.out.println(count);
//            result = Matrix.multiplcationOf(weights[i], result);
//            result.add(biases[i]);
//            result.map(ActivationFunctions.SIGMOID);
//        }
//
//        return result.toArray();
//
//    }

    public void train(Double[] inputs, Double[] targets) {

        Matrix outputMatrix = new Matrix(forwardPropagation(inputs));
        Matrix targetMatrix = new Matrix(targets);

        Matrix outputError = Matrix.mappingOf(targetMatrix, outputMatrix, ErrorFunctions.DIFFERENCE_ERROR);

        Matrix[] errors = new Matrix[matricesLength];

        for(int i = matricesLength - 1; i >= 0; i--) {
            if(i == matricesLength - 1) {
                errors[i] = Matrix.multiplicationOf(Matrix.transpositionOf(weights[i]), outputError);
            }else{
                errors[i] = Matrix.multiplicationOf(Matrix.transpositionOf(weights[i]), errors[i + 1]);
            }
        }

        System.out.println(targetMatrix);
        System.out.println(outputMatrix);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("----------------\n");
        for (int i = 0; i < this.weights.length; i++) {
            Matrix m = this.weights[i];
            if(i == 0) {
                builder.append("Weights from input to hidden layer 1:\n");
            }else if(i > 0 && i < this.weights.length - 1) {
                builder.append("Weights from hidden layer ").append(i).append(" to hidden layer ").append(i + 1).append(":\n");
            }else if(i == this.weights.length - 1) {
                builder.append("Weights from hidden layer ").append(i).append(" to output layer:\n");
            }
            builder.append(m.toString());
            builder.append("\n").append(biases[i].toString());
            builder.append("----------------\n");
        }

        return builder.toString();
    }

}

