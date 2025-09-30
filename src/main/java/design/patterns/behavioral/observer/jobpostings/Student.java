package design.patterns.behavioral.observer.jobpostings;

public class Student implements  Observer {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void update(String info) {
        System.out.println("Student " + name + ": I will go apply for this internship now!");
        System.out.println();
        // System.out.println(info);

        // apply for a job - not shown here
    }


}
