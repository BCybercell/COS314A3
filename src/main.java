import java.util.List;
public class main {
    public static void main(String[] args){
        //Initial setup
        long startTime = System.nanoTime();
        long tempTime = startTime;
        unittest test = new unittest();
        test.testUtils();
        UtilsA utils = new UtilsA();
//        double [][] outputs = new double[1][10];

        //Get cvs data
        List<Image> imagesTrain= utils.readCVS("COS314A3/fashionmnist/fashion-mnist_train.csv");
        System.out.println("[+]Cvs data read Successfully");
        long endTime = System.nanoTime();
        long timeElapsed = endTime - tempTime;
        System.out.println("[*]Time to read cvs data : " + (timeElapsed / 1000000)+ "ms");

        //Create Layer 1 (input Layer)
        double [][] weights0 = new double[1][785];
        for (int i = 0; i < 785; i++) {
            weights0[0][i]=1;
        }
        Layer inputLayer = new Layer(785, imagesTrain.get(1).getInput(), weights0);

        //Create Hidden Layer 1
        double [][] inputs = new double[1][100];
        double [][] weights1 = new double[785][100];
        utils.assignRandomWeights(weights1, 785, 100);
        Layer hiddenLayer1 = new Layer(100, inputs,weights1);

        //Create Hidden Layer 2
        double [][] weights2 = new double[100][50];
        inputs = new double[1][50];
        utils.assignRandomWeights(weights2,100,50);
        Layer hiddenLayer2 = new Layer(50, inputs, weights2);

        //Create Output Layer
        double [][] weights3 = new double[50][10];
        inputs = new double[1][10];
        utils.assignRandomWeights(weights3, 50,10);
        Layer outputLayer = new Layer(10, inputs, weights3);

        System.out.println("[+]Layers created successfully");
        tempTime = endTime;
        endTime = System.nanoTime();
        timeElapsed = endTime - tempTime;
        System.out.println("[*]Time to create layers : " + (timeElapsed / 1000000)+ "ms");

        inputLayer.processLayer(utils, hiddenLayer1,0);
        hiddenLayer1.processLayer(utils, hiddenLayer2, 0);
//        utils.displayProduct(hiddenLayer2.getInputs());
        hiddenLayer2.processLayer(utils, outputLayer, 1);

        utils.displayProduct(outputLayer.getInputs());
        System.out.println(imagesTrain.get(1).getOutput());

//        double [][] tempH1 = utils.multiplyMatrices(inputs,weights1,inputs.length,inputs[0].length,weights1[0].length);
////        utils.displayProduct(temp);
//        double [][] tempH2 = utils.multiplyMatrices(tempH1,weights2,tempH1.length,tempH1[0].length,weights2[0].length);
//        outputs = utils.multiplyMatrices(tempH2,weights3,tempH2.length,tempH2[0].length,weights3[0].length);
//        utils.displayProduct(outputs);
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

        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;

        System.out.println("[*]Execution time in milliseconds : " + (timeElapsed / 1000000));
        System.out.println("[*]Execution time in seconds : " + (timeElapsed / 1000000)/1000);
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