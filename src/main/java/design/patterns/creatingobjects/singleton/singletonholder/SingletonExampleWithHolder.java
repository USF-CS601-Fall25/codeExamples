package design.patterns.creatingobjects.singleton.singletonholder;

public class SingletonExampleWithHolder {
    public static void main(String[] args) {
        // Not created until getInstance is invoked
        Singleton obj1 = Singleton.getInstance(); // creates in Holder and returns
        Singleton obj2 = Singleton.getInstance(); // returns the existing one
        System.out.println(obj1 == obj2);

    }
}
