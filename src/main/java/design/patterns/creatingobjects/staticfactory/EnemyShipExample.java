package design.patterns.creatingobjects.staticfactory;

import java.util.Scanner;
import java.util.ServiceLoader;
public class EnemyShipExample {
    public static void main(String[] args) {
        EnemyShipExample ex = new EnemyShipExample();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the type of the ship: ");
        String type = sc.nextLine();
        String fullName = ex.getClass().getPackageName()+ "." +  type;
        //String fullName = "java.lang." + type;
        System.out.println(fullName);
        EnemyShip myShip = EnemyShip.getInstance(fullName);
        if (myShip != null)
         myShip.attack();
    }
}
