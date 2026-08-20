import java.util.Random;

public class SchoolRestrictions {
    
    // Arrays to store the random compatibilities
    private boolean[][] validCapacity;
    private boolean[][] validSpecialty;

    public SchoolRestrictions(int n) {
        // Init arrays with size n + 1 for 1-based indexing
        validCapacity = new boolean[n + 1][n + 1];
        validSpecialty = new boolean[n + 1][n + 1];
        
        Random rand = new Random();

        // Loop to generate random boolean values
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                validCapacity[i][j] = rand.nextBoolean();
                validSpecialty[i][j] = rand.nextBoolean();
            }
        }
    }

    public boolean isValid(int classroom, int course) {
        return validCapacity[classroom][course];
    }

    public boolean hasSpecialty(int professor, int course) {
        return validSpecialty[professor][course];
    }
}