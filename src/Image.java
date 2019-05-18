public class Image {
    public Image(double[] aInput, double aOutput){
        int size = aInput.length;
        input = new double[1][size];
        // preprocessing
        for (int i = 0; i <size ; i++) {
            input[0][i] = aInput[i] /255;
        }
//        input[0] =aInput;
        output =aOutput;
    }

    public double getOutput() {
        return output;
    }

    public double[][] getInput() {
        return input;
    }

    private double [][] input;
    private double output;
}
