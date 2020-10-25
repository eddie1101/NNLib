package neural_network;

import math.function.TwoParameterFunction;
import math.function.activation.ActivationFunction;
import math.function.activation.ActivationFunctions;
import math.function.error.ErrorFunctions;
import math.matrix.Matrix;

import java.util.ArrayList;
import java.util.Arrays;

public class NeuralNetwork {

    double learningRate = 0.1;
    ActivationFunction activationFunction = ActivationFunctions.Sigmoid;
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
            if(i < biases.length - 1) {
                biases[i] = new Matrix(numNodesPerHiddenLayer, 1);
            }else {
                biases[i] = new Matrix(numOutputs, 1);
            }
            biases[i].randomInitialization();
        }
    }

    public NeuralNetwork setWeightInitBounds(double lower, double upper) {
        for(Matrix weight: weights) {
            weight.randomInitialization(lower, upper);
        }
        return this;
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
            result.map(ActivationFunctions.Sigmoid.extract());
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

        ArrayList<Matrix> tempO = new ArrayList<>();
        tempO.add(new Matrix(inputs));

        tempO.addAll(Arrays.asList(trainForwardPropagation(inputs)));

        Matrix[] outputs = new Matrix[tempO.size()];
        outputs = tempO.toArray(outputs);

        assert outputs.length == matricesLength + 1;

        Matrix gradients = new Matrix();
        Matrix error = new Matrix();
        Matrix lastError = new Matrix();
        Matrix targetMatrix = new Matrix(targets);

        Matrix outputError = Matrix.mappingOf(targetMatrix, outputs[matricesLength - 1], ErrorFunctions.DIFFERENCE_ERROR);

        for(int i = matricesLength - 1; i >= 0; i--) {
            if(i == matricesLength - 1) {
                error = outputError;
            }else{
                error = Matrix.multiplicationOf(Matrix.transpositionOf(weights[i + 1]), lastError);
            }
            lastError = error;

            gradients = Matrix.mappingOf(outputs[i + 1], this.activationFunction.dextract());
            gradients.hadamard(error);
            gradients.mult(learningRate);

            Matrix oTranspose = Matrix.transpositionOf(outputs[i]);
            Matrix dW = Matrix.multiplicationOf(gradients, oTranspose);

            System.out.println("Transpose weight deltas:\n" + dW);

            weights[i].add(dW);
            biases[i].add(gradients);

        }

    }

    private Matrix[] trainForwardPropagation(Double[] inputs) {

        Matrix result = new Matrix(inputs);
        Matrix[] outputs = new Matrix[matricesLength];
        for(int i = 0; i < weights.length; i++) {
            result = Matrix.multiplicationOf(weights[i], result);
            result.add(biases[i]);
            result.map(ActivationFunctions.Sigmoid.extract());

            outputs[i] = result;
        }

        return outputs;

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

