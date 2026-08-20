import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class asignacionCursos {
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

        int n = 0;

        // Read input using Scanner style
        try {
            File file = new File(inputFile);
            try (Scanner scanner = new Scanner(file)) {
                if (!scanner.hasNextInt()) {
                    System.err.println("Error reading the input file: expected number of courses/classrooms/professors");
                    return;
                }
                
                n = scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading the input file: file not found: " + inputFile);
            return;
        } catch (InputMismatchException e) {
            System.err.println("Error reading the input file: invalid number format");
            return;
        }

        // Initialize the Object-Oriented components
        SchoolRestrictions restrictions = new SchoolRestrictions(n);
        AssignmentState state = new AssignmentState(n);
        BacktrackingAssigner assigner = new BacktrackingAssigner(restrictions, state, traceMode);

        // Run the Backtracking algorithm starting from course 1
        boolean success = assigner.assign(1);

        //Output for display or file
        if (outputFile == null) {
            if (!success) {
                System.out.println("0");
            } else {
                int[] solClassroom = state.getSolClassroom();
                int[] solProfessor = state.getSolProfessor();
                
                for (int course = 1; course <= n; course++) {
                    System.out.println(solClassroom[course] + " " + course + " " + solProfessor[course]);
                }
            }
        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
                if (!success) {
                    writer.println("0");
                } else {
                    int[] solClassroom = state.getSolClassroom();
                    int[] solProfessor = state.getSolProfessor();
                    
                    for (int course = 1; course <= n; course++) {
                        writer.println(solClassroom[course] + " " + course + " " + solProfessor[course]);
                    }
                }
            } catch (IOException e){
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    public static void showHelp(){
        System.out.println("ASIGNACION CURSOS - Classroom and Professor Assignment");
        System.out.println("The program calculates a valid assignment using Backtracking algorithm\n");
        System.out.println("How to use: java asignacionCursos [options] <inputFile> [outputFile]\n");
        System.out.println("Options:");
        System.out.println("  -h           Show this message of help");
        System.out.println("  -t           Active the trace mode to see the progress of the algorithm");
        System.out.println("  <inputFile>  File containing the value of n (courses, classrooms, professors)");
        System.out.println("  [outputFile] File to save the list of assigned courses and professors");
    }
}