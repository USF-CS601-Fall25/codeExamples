package design.patterns.mvc.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// This is the "model" ("subject) in the MVC example
public class StudentDataModel implements Model {
    private Map<String, Student> studentMap = new TreeMap<>(); // name -> Student
    private List<Observer> observerList = new ArrayList<>();

    public void addStudent(Student st) {
        studentMap.put(st.getName(), st);
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observerList)
            o.update();

    }

    @Override
    public void changeName(String name1, String name2) {
        Student st = studentMap.get(name1);
        if (st == null)
             return;
        st.setName(name2);
        studentMap.remove(name1);
        studentMap.put(name2, st);
        notifyObservers(); // will notify the view if it registered as observer
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String stName: studentMap.keySet()) {
            sb.append(studentMap.get(stName));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
