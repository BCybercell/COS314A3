import java.util.List;

public class main {
    public static void main(String[] args){
        //Initial setup
        unittest test = new unittest();
        test.testUtils();
        UtilsA utils = new UtilsA();
        //Get cvs data
        List<Image> imagesTrain= utils.readCVS("COS314A3/fashionmnist/fashion-mnist_train.csv");
        double [][] inputs = new double[1][785];
        double [][] outputs = new double[1][10];


        double [][] weights1 = new double[785][100];
        double [][] weights2 = new double[100][50];
        double [][] weights3 = new double[50][10];
        double [][] tempH1 = utils.multiplyMatrices(inputs,weights1,inputs.length,inputs[0].length,weights1[0].length);
//        utils.displayProduct(temp);
        double [][] tempH2 = utils.multiplyMatrices(tempH1,weights2,tempH1.length,tempH1[0].length,weights2[0].length);
        outputs = utils.multiplyMatrices(tempH2,weights3,tempH2.length,tempH2[0].length,weights3[0].length);
        utils.displayProduct(outputs);
        /*
         * NOTES:
         *   Input Neuron:
         *       Mini-batch size of 50 (50 of them)
         *
         *   Hidden Layers
         *       Have 2-hidden layers using the ReLU activation function
         *           Hidden layer 1 must have 100 units
         *           Hidden layer 2 must have 50 units
         *   Output Neuron:
         *       softmax activation function
         *       1?
         *   Cost function:
         *       negative log likelihood
         *
         *
         * */
        //PRE STEP
        //Scale values to 0-1

        }

        //STEP 1; Weighted sum of inputs
        // ans = x1w1 + x2w2 + ...
        // where x is input value and w is its weight

        //STEP 2
        //normalizing function
        //sigmoid, gets num between 0 and 1 based on ans

        //STEP 3
        //Calculate diff between expected output and actual output
        //



    /*
     * class net
     *
     *
     * class layer? don't think so?
     * Program 1 = train
     * Program 2 = test
     *
     * */
//    class neuron(){
//        public:
//        neuron(number of Outpus)
//        if output layer num outputs = 0?
//        private:
//        double output;
//        double [] outputWeights;
//        double [] deltaWeights;
//    }
}


/*
*
    Small values give a learning process that converges quickly at the cost of noise in the training process.
    Large values give a learning process that converges slowly with accurate estimates of the error gradient.

Tip 1: A good default for batch size might be 32.

    … [batch size] is typically chosen between 1 and a few hundreds, e.g. [batch size] = 32 is a good default
    *  value, with values above 10 taking advantage of the speedup of matrix-matrix products over matrix-vector products.*/