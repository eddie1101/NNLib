import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import math.function.activation.ActivationFunction;
import math.function.activation.ActivationFunctions;
import math.function.error.ErrorFunction;
import math.function.error.ErrorFunctions;
import math.matrix.Matrix;
import neural_network.NeuralNetwork;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        neuralNetworkDebug();
    }

    public static void neuralNetworkDebug() {
        NeuralNetwork neuralNetwork = new NeuralNetwork(
                2,
                1,
                2,
                1)
                .setWeightInitBounds(0, 1)
                .setLearningRate(0.1)
                .setActivation(ActivationFunctions.ReLU)
                .setError(ErrorFunctions.Difference);

//        Double[] inputs = {1d, 0d};

        Double[] inputs = new Double[2];
        for(int i = 0; i < inputs.length; i++) {
            inputs[i] = ThreadLocalRandom.current().nextGaussian();
        }

//        Double[] targets = {1d};

        Double[] targets = new Double[1];
        for(int i = 0; i < targets.length; i++) {
            targets[i] = ThreadLocalRandom.current().nextGaussian();
        }

        System.out.println(neuralNetwork);
        neuralNetwork.train(inputs, targets);

//        Double[] results = neuralNetwork.forwardPropagation(inputs);
//
//        for(Double result: results) {
//            System.out.print(result + " ");
//        }
//        System.out.println();

        Gson gson = new Gson();

        File nn = new File("./data/models/sample.json");

        String jsonString = gson.toJson(neuralNetwork);

        try {
            FileWriter writer = new FileWriter(nn);
            writer.write(jsonString + "\n");
            writer.write(neuralNetwork.activationFunction.getName() + "\n");
            writer.write(neuralNetwork.errorFunction.getName());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NeuralNetwork newNet = loadFromFile("./data./models./sample.json");

        System.out.println(newNet);

    }

    public static NeuralNetwork loadFromFile(String path) {
        File nnf = new File(path);
        NeuralNetwork nn;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nnf));

            String jsonString = reader.readLine();

            System.out.println(jsonString);

            Gson gson = new Gson();

            nn = gson.fromJson(jsonString, NeuralNetwork.class);

            nn.setActivation(ActivationFunctions.get(reader.readLine()));
            nn.setError(ErrorFunctions.get(reader.readLine()));

            reader.close();

            return nn;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void matrixDebug() {
        Matrix m = new Matrix(2, 2);
        m.randomInitialization(-10, 10);

        Matrix o = new Matrix(2, 2);
        o.randomInitialization(0, 2);

        System.out.println("m:\n" + m);
        System.out.println("o:\n" + o);

        m.add(o);

        System.out.println("-------Addition-------\n" + m);


        Double[][] mData = {
                {5d, -3d, 4d},
                {-1d, 7d, 3d},
                {-6d, 3d, -2d},
                {6d, -2d, -3d}
        };

        Double[][] oData = {
                {-2d, 2d, 1d, 9d},
                {1d, 3d, 2d, -6d},
                {2d, -5d, -2d, 10d},
                {-4d, 4d, 2d, -5d}
        };

        m = new Matrix(3, 4, mData);
        o = new Matrix(4, 4, oData);

        System.out.println("\n\n\n");
        System.out.println("m:\n" + m);
        System.out.println("o:\n" + o);

        m.mult(o);

        System.out.println("----Multiplication----\n" + m);

        m.transpose();

        System.out.println("----Transposition----\n" + m);

        m.map(in -> in / 2);

        System.out.println("----Mapping----\n" + m);

        Double[] arr = m.toArray();

        System.out.println("----ToArray----\n");
        for (Double aDouble : arr) System.out.println(aDouble);
    }


}
