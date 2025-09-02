package jsonprocessing;

import com.google.gson.annotations.SerializedName;

/** Class Fruit */
public class Fruit {
    private String name;
    private String color;
    private double price;

    @SerializedName(value = "organic")
    private boolean isOrganic; // if we want to have a name of the field that is
    // different from the ones in a json file

    /**
     * toString
     * @return string representation of this fruit
     */
    @Override
    public String toString() {
        return "Fruit: " + System.lineSeparator() +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", organic=" + isOrganic;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public boolean isOrganic() {
        return isOrganic;
    }

}
