public class unittest {
    public unittest(){}

    public void testUtils(){
        UtilsA utils = new UtilsA();

        System.out.println("==============================================");
        System.out.println("===========Running Safety Checks...===========");
        System.out.println("==============================================");
        System.out.println();
        int count;
        System.out.println("[*]Testing reLU");
        count = 1;
        if (5.5 == utils.reLU(5.5)){
            System.out.println(" [+]Pass reLU test " + count);
        }
        else {
            System.out.println(" [-]Fail reLU test " + count);
            System.out.println(" [*]Function returned " +utils.reLU(5.5));
            System.out.println("[*]Aborting");
            return;
        }
        count++;

        if (0.0 == utils.reLU(-1.0)){
            System.out.println(" [+]Pass reLU test " + count);
        }
        else {
            System.out.println(" [-]Fail reLU test " + count);
            System.out.println(" [*]Function returned " +utils.reLU(-1.0));
            System.out.println("[*]Aborting");
            return ;

        }
        //=============================
        System.out.println();
        System.out.println("[*]Testing sigmoid");
        count = 1;
        if (0.9945137011005495 == utils.sigmoid(5.2)){
            System.out.println(" [+]Pass sigmoid test " + count);
        }
        else {
            System.out.println(" [-]Fail sigmoid test " + count);
            System.out.println(" [*]Function returned " + utils.sigmoid(5.2));
            System.out.println("[*]Aborting");
            return;
        }
        count++;

        System.out.println();
        System.out.println("==============================================");
        System.out.println("===========Continuing With Execution...=======");
        System.out.println("==============================================");
        System.out.println();
    }
}
