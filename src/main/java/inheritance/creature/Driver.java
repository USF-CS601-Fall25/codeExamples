package inheritance.creature;

import java.util.ArrayList;

public class Driver {
        public static void main(String[] args) {
            ArrayList<Creature> creatures = new ArrayList<>();

            // 1) Add 3 aliens and 2 humans to the creatures ArrayList.
            // FILL IN CODE
            creatures.add(new Human("Hao", "Ni hao"));
            creatures.add(new Human("Kathy", "Hello"));
            creatures.add(new Alien("bmmmbbmb", "Mars"));
            creatures.add(new Alien("hohohoh", "Mars"));
            creatures.add(new Alien("yoyoyoy", "Mars"));

            // 2) Iterate over the ArrayList, call speak() method for
            // each creature. Call fight() for each Alien
            for (Creature creature: creatures) {
                creature.speak();
                if (creature instanceof Alien)
                    ((Alien)creature).fight();
            }
            // What if you add aliens as you iterate: would print messages change?


        }

}
