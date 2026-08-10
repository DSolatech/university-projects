import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

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
            return;
        }

        //Check if output file already exits
        if (outputFile != null) {
            File fileOut = new File(outputFile);
            if (fileOut.exists()){
                System.err.println("Error: The output file already exits");
                return;
            }
        }
        
        Graph graph = null;

        try {
            File file = new File(inputFile);
            try (Scanner scanner = new Scanner(file)) {
                if (!scanner.hasNextInt()) {
                    System.err.println("Error reading the input file: expected number of nodes");
                    return;
                }

                int n = scanner.nextInt();
                if (!scanner.hasNextInt()) {
                    System.err.println("Error reading the input file: expected second number after node count");
                    return;
                }
                int y = scanner.nextInt();

                graph = new Graph(n, y);

                while (scanner.hasNextInt()) {
                    int node1 = scanner.nextInt();
                    int node2 = scanner.nextInt();
                    int cost = scanner.nextInt();

                    graph.addEdge(node1, node2, cost);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading the input file: file not found: " + inputFile);
            return;
        } catch (InputMismatchException e) {
            System.err.println("Error reading the input file: invalid number format");
            return;
        }

        Prim newMst = new Prim(graph, traceMode);
        List<Edge> mst = newMst.getMST();

        //Output for display or file
        int totalCost = 0;
        for (Edge edges : mst){
            totalCost += edges.getCost();
        }

        if (outputFile == null){
            System.out.println(totalCost);

            for (Edge edges : mst){
                System.out.println(edges.getNode1() + " " + edges.getNode2() + " " + edges.getCost());     
            }

        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))){
                writer.println(totalCost);

                for (Edge edges : mst){
                    writer.println(edges.getNode1() + " " + edges.getNode2() + " " + edges.getCost());     
                }
            } catch (IOException e){
                System.err.println("Error: " + e.getMessage());
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