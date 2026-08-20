public class AssignmentState {
    
    //Arrays to store the solution
    private int[] solClassroom;
    private int[] solProfessor;
    
    // Arrays to keep track of available resources
    private boolean[] classroomFree;
    private boolean[] professorFree;

    public AssignmentState(int n) {
        // Init arrays with size n + 1
        solClassroom = new int[n + 1];
        solProfessor = new int[n + 1];
        classroomFree = new boolean[n + 1];
        professorFree = new boolean[n + 1];

        // Set all classrooms and professors as free initially
        for (int i = 1; i <= n; i++) {
            classroomFree[i] = true;
            professorFree[i] = true;
        }
    }

    public boolean isClassroomFree(int classroom) { 
        return classroomFree[classroom]; 
    }
    
    public void setClassroomFree(int classroom, boolean isFree) { 
        classroomFree[classroom] = isFree; 
    }

    public boolean isProfessorFree(int professor) { 
        return professorFree[professor]; 
    }
    
    public void setProfessorFree(int professor, boolean isFree) { 
        professorFree[professor] = isFree; 
    }

    public void setSolClassroom(int course, int classroom) { 
        solClassroom[course] = classroom; 
    }
    
    public int[] getSolClassroom() { 
        return solClassroom; 
    }

    public void setSolProfessor(int course, int professor) { 
        solProfessor[course] = professor; 
    }
    
    public int[] getSolProfessor() { 
        return solProfessor; 
    }
}