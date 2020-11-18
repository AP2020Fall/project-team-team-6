package model;

public class Army {
   private final Color color;
   private Country country;

    public Army(final Color color, Country country) {
        this.color = color;
        this.country = country;
    }

    public Color getColor() {
        return color;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
