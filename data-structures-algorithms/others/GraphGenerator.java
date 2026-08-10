import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class GraphGenerator{

    public static void main(String[] args){
        
        if(args.length != 3){
            System.err.println("Use: java GraphGenerator <n> <y> <text_file>");
            return;
        }

        int n = 0;
        int y = 0;
        String fileName = args[2];

        try {
            n = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);

        } catch (NumberFormatException e){
            System.err.println("Error: Invalid integers");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(n + " " + y);

            for (int i = 1; i <= n; i++){
                for(int j = i + 1; j <= n; j++){
                    int cost = (i*j) % y;
                    writer.println(i + " " + j + " " + cost);
                }
            }
            System.out.println("File: " + fileName + " was created!");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }


}