public class Layer {
    public Layer(int aSize, double [][] inputs){
        size =aSize;
        neurons = new Neuron[size];
        for (int i = 0; i < size; i++){
            neurons[i] = new Neuron(inputs[0][i]);
        }
    }

    public void assignInputsToNeurons(double [][] inputs){
        for (int i = 0; i < size; i++){
            neurons[i] = new Neuron(inputs[0][i]);
        }
    }

    private Neuron [] neurons;
    private int size;
}
