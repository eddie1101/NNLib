package network;

import function.OneParameterFunction;

import java.util.ArrayList;
import java.util.Arrays;

public class Network {

    ArrayList<Layer> layers = new ArrayList<>();
    Double[] input;

    double bias;

    public Network(int layers, int nodes, int outputs, boolean rawOutputs, double bias, Double[] input, OneParameterFunction func) {
        this.input = input;
        this.bias = bias;

        for(int i = 0; i < layers; i++) {
            Layer layer = new Layer();
            for(int n = 0; n < nodes; n++) {
                Node node;
                if(i == 0) {
                    node = new Node(func, input.length, bias);
                }else {
                    node = new Node(func, nodes, bias);
                }
                layer.addNode(node);
            }
            this.layers.add(layer);
        }

        Layer outputLayer = new Layer();
        for(int i = 0; i < outputs; i++) {
            if(rawOutputs)
                outputLayer.addNode(new Node((x) -> x, nodes, bias));
            else
                outputLayer.addNode(new Node(func, nodes, bias));
        }
        this.layers.add(outputLayer);
    }

    public Layer evaluate() {

        ArrayList<Double> outputs = new ArrayList<>(Arrays.asList(input));

        for (Layer layer : layers) {
            outputs = layer.evaluate(outputs);
        }

        return layers.get(layers.size() - 1);

    }


}
