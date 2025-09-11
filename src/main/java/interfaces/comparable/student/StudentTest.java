package interfaces.comparable.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentTest {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Student st1 = new Student("Z", 1, 3.5671);
        Student st2 = new Student("A", 2, 3.5618);
        Student st3 = new Student("B", 3, 3.1);
       // System.out.println(st1.compareTo(st2));

        students.add(st1);
        students.add(st2);
        students.add(st3);
        Collections.sort(students); // this uses the compareTo method we provided
        System.out.println(students);


    }
}
