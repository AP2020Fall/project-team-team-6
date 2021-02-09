package server.model.gamesModels;

import java.util.ArrayList;

public class Country {

    private final int countryCoordinate;
    private Color color;
    private int numberOfSoldiers;
    private ArrayList<Country> neighboringCountries;
    private final Continent continent;
    private final String name;


    public Country(final int countryCoordinate, final Continent continent, final String name) {
        this.countryCoordinate = countryCoordinate;
        this.name = name;
        this.color = null;
        this.numberOfSoldiers = 0;
        this.neighboringCountries = new ArrayList<>();
        this.continent = continent;
    }

    public int getCountryCoordinate() {
        return countryCoordinate;
    }

    public Color getColor() {
        return color;
    }

    public int getNumberOfSoldiers() {
        return numberOfSoldiers;
    }

    public ArrayList<Country> getNeighboringCountries() {
        return neighboringCountries;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setNumberOfSoldiers(int numberOfSoldiers) {
        this.numberOfSoldiers = numberOfSoldiers;
    }

    public Continent getContinent() {
        return continent;
    }

    public String getName() {
        return name;
    }
}
