public class BacktrackingAssigner {

    private SchoolRestrictions restrictions;
    private AssignmentState state;
    private boolean traceMode;
    private int n;
    private boolean success;

    public BacktrackingAssigner(SchoolRestrictions restrictions, AssignmentState state, boolean traceMode) {
        this.restrictions = restrictions;
        this.state = state;
        this.traceMode = traceMode;
        this.n = state.getSolClassroom().length - 1;
        this.success = false;
    }

    public boolean assign(int course) {
        // Check if all courses have been assigned
        if (course > n) {
            success = true;
            if (traceMode) {
                System.out.println("TRACE: Solution found! All " + n + " courses have been assigned.");
            }
        } else {
            int classroom = 1;
            
            // Loop through all classrooms
            while (classroom <= n && !success) {
                
                if (state.isClassroomFree(classroom) && restrictions.isValid(classroom, course)) {
                    
                    state.setSolClassroom(course, classroom);
                    state.setClassroomFree(classroom, false);

                    int professor = 1;
                    
                    // Loop through all professors
                    while (professor <= n && !success) {
                        
                        if (state.isProfessorFree(professor) && restrictions.hasSpecialty(professor, course)) {
                            
                            state.setSolProfessor(course, professor);
                            state.setProfessorFree(professor, false);

                            if (traceMode) {
                                System.out.println("TRACE: Attempting Course " + course + 
                                                   " -> Classroom " + classroom + 
                                                   " | Professor " + professor);
                            }

                            // Recursive call
                            assign(course + 1);

                            // Undo professor if no success
                            if (!success) {
                                if (traceMode) {
                                    System.out.println("TRACE: [BACKTRACK] Undoing Professor " + professor + 
                                                       " for Course " + course);
                                }
                                state.setProfessorFree(professor, true);
                            }
                        }
                        professor++;
                    }

                    // Undo classroom if no success
                    if (!success) {
                        if (traceMode) {
                            System.out.println("TRACE: [BACKTRACK] Undoing Classroom " + classroom + 
                                               " for Course " + course);
                        }
                        state.setClassroomFree(classroom, true);
                    }
                }
                classroom++;
            }
        }
        return success;
    }
}