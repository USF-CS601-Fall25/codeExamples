package design.patterns.behavioral.observer.jobpostings;

public class UniversityProgramManager implements Observer {
    private String name;
    private String university;

    public UniversityProgramManager(String name, String university) {
        this.name = name;
        this.university = university;
    }

    @Override
    public void update(String jobDescr ) {
        if (jobDescr.contains("software development")) {
            // Send to the mailing list
            System.out.println("University Program Manager " + name + ": Post to the student mailing list");
        }
    }
}
