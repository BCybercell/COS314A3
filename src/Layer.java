public class Layer {
    public Layer(int aSize, double [][] inputs, double [][] aWeights){
        size =aSize;
        neurons = new Neuron[size];
        for (int i = 0; i < size; i++){
            neurons[i] = new Neuron(inputs[0][i]);
        }
        weights = aWeights;
        deltaWeights = new double[aWeights.length][aWeights[0].length];
    }

    public void assignInputsToNeurons(double [][] inputs){
        for (int i = 0; i < size; i++){
            neurons[i] = new Neuron(inputs[0][i]);
        }
    }

    public void processLayer(UtilsA utils, Layer followingLayer, int functionToApply /*0 =relu, 1 = softmax*/){
        double [][] tempInputs = new double[1][size];

        for (int i = 0; i < size; i++){
            tempInputs[0][i] = neurons[i].getInput();
        }
        double[][] tempWeights = followingLayer.getWeights();
        outputs = utils.multiplyMatrices(
                tempInputs,tempWeights,tempInputs.length,tempInputs[0].length,tempWeights[0].length);

        //apply reLU

        if (functionToApply==0){
            int tempSize = followingLayer.getSize();
            for (int i = 0; i <tempSize ; i++) {
                outputs[0][i] = utils.reLU(outputs[0][i]);
            }
        }
        else {
            //utils.displayProduct(outputs);
            outputs[0] = utils.softMax(outputs[0]);
        }


        followingLayer.assignInputsToNeurons(outputs);
//        utils.displayProduct(outputs);
    }

    public double[][] getWeights() {
        return weights;
    }

    public int getSize() {
        return size;
    }

    public double[][] getInputs(){
        double [][] tempInputs = new double[1][size];

        for (int i = 0; i < size; i++){
            tempInputs[0][i] = neurons[i].getInput();
        }
        return tempInputs;
    }

    public void updateWeights(Layer prev, double learningRate, double gradient){
        double [][] oldDeltaWeights = deltaWeights.clone();
        double [][] newDeltaWeights =deltaWeights.clone();
        double [][] prevOutputs = prev.getOutputs();
        for (int i = 0; i <newDeltaWeights.length ; i++) {
            for (int j = 0; j <newDeltaWeights[0].length ; j++) {
                newDeltaWeights[i][j] =
                        learningRate
                        * prevOutputs[0][j]
                        * gradient
                        + 0.2
                        * oldDeltaWeights[i][j];
                weights[i][j] += newDeltaWeights[i][j];
            }
        }
        deltaWeights = newDeltaWeights;
    }

    public void updateWeights(Layer prev, double learningRate, double [][] gradient){
        double [][] oldDeltaWeights = deltaWeights.clone();
        double [][] newDeltaWeights =deltaWeights.clone();
        double [][] prevOutputs = prev.getOutputs();
        for (int i = 0; i <newDeltaWeights.length ; i++) {
            for (int j = 0; j <newDeltaWeights[0].length ; j++) {
                newDeltaWeights[i][j] =
                        learningRate
                                * prevOutputs[0][j]
                                * gradient[0][j]
                                + 0.2
                                * oldDeltaWeights[i][j];
                weights[i][j] += newDeltaWeights[i][j];
            }
        }
        deltaWeights = newDeltaWeights;
    }

    public double[][] getOutputs() {
        return outputs;
    }

    private Neuron [] neurons;
    private int size;
    private double [][] weights;
    private double [][] deltaWeights;
    private double [][] outputs;
}
