public class main {
    public static void main(String[] args){
        UtilsA utils = new UtilsA();

//        System.out.println("Hello world");
//        double arr [] = {1, 2, 3, 4, 5, 6};
//        double e []= utils.softMax(arr);
//        for (double x : e){
//            System.out.println(x);
//        }


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

        //STEP 1; Weighted sum of inputs
        // ans = x1w1 + x2w2 + ...
        // where x is input value and w is its weight

        //STEP 2
        //normalizing function
        //sigmoid, gets num between 0 and 1 based on ans

        //STEP 3
        //Calculate diff between expected output and actual output
        //
        System.out.println(utils.sigmoid(5.0));
    }


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
