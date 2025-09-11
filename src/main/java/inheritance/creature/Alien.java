package inheritance.creature;

/**
 *  Represents an Alien, a subclass of Creature.
 *  Overrides speak() method to say: Alien from planet <nameOfPlanet> says <greeting>
 *  where the greeting is from the parent's class Creature.
 *  Has a fight() method that is not in the parent class.
 *  From the book : Java 1.4 Game programming.
 *  */
public class Alien extends Creature { // FILL IN CODE: Alien should be a subclass of Creature, use the keyword "extends"
    private String planet; // the name of the planet where the alien lives
    private static int numAliensFromMars = 0; // the number of aliens from Mars

    public Alien(String greeting, String planet) {
        // Initialize greeting and planet
        super(greeting);
        this.planet = planet;
        // if the planet is Mars, increment numAliensFromMars to keep track of all aliens from Mars
        if (planet.equals("Mars"))
            numAliensFromMars++;
    }

    // Override the speak method so that the alien says:
    // Alien from planet <planetName> says <greeting>
    @Override
    public void speak() {
        System.out.println("Alien from planet " + planet + " says " + getGreeting());
    }

    public void fight() {
        // If this alien is from Mars and the total number of aliens from Mars is >=  3, then
        // print "Let's fight!");
        if (planet.equals("Mars") && numAliensFromMars >= 3)
            System.out.println("Let's fight! ");
        else {
            System.out.println("Do nothing");
        }

    }

}
