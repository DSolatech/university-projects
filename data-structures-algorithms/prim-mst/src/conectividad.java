import java.util.Scanner;
import java.io.File;

public class conectividad{
    public static void main(String[] args) {
        //Configuration variables
        boolean traceMode = false;
        String inputFile = null;
        String outputFile = null;

        //Loop through arguments
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-h")) {
                showHelp();
                return;
            } else if (args[i].equals("-t")) {
                traceMode = true;
            } else if (inputFile == null) {
                inputFile = args[i];
            } else if (inputFile != null && outputFile == null) {
                outputFile = args[i];
            } else {
                System.err.println("Error: Too many arguments.");
                showHelp();
                return;
            }
        }

        //Check if input file is not null
        if (inputFile == null) {
            System.err.println("Error: Input file is required.");
        }

        //Check if output file already exits
        if (outputFile != null) {
            File fileOut = new File(outputFile);
            if (fileOut.exists()){
                System.err.println("Error: The output file already exits");
                return;
            }
        }
    }

    public static void showHelp(){
        System.out.println("CONECTIVIDAD - Minimum Spanning Tree (MST) Calculator");
        System.out.println("The program calculates a MST using Prim algorithm\n");
        System.out.println("How to use: java conectividad [options] <inputFile> [outputFile]\n");
        System.out.println("Options:");
        System.out.println("  -h         Show this message of help");
        System.out.println("  -t         Active the trace mode to see the progress of the algorithm");
    }
}