package server.model.gamesModels;

import java.util.HashMap;

public enum Continent {

    ASIA("Asia") {
        @Override
        public void setCountries(HashMap<Integer, Country> countries) {

        }

        @Override
        public void setCountriesInBoard(HashMap<Integer, Country> countries) {

        }
    },
    EUROPE("Europe") {
        @Override
        public void setCountries(HashMap<Integer, Country> countries) {

        }

        @Override
        public void setCountriesInBoard(HashMap<Integer, Country> countries) {

        }
    },
    AFRICA("Africa") {
        @Override
        public void setCountries(HashMap<Integer, Country> countries) {

        }

        @Override
        public void setCountriesInBoard(HashMap<Integer, Country> countries) {

        }
    },
    SOUTH_AMERICA("South America") {
        @Override
        public void setCountries(HashMap<Integer, Country> countries) {

        }

        @Override
        public void setCountriesInBoard(HashMap<Integer, Country> countries) {

        }
    },
    AUSTRALIA("Australia") {
        @Override
        public void setCountries(HashMap<Integer, Country> countries) {

        }

        @Override
        public void setCountriesInBoard(HashMap<Integer, Country> countries) {

        }
    },
    NORTH_AMERICA("North America") {
        @Override
        public void setCountries(HashMap<Integer, Country> countries) {

        }

        @Override
        public void setCountriesInBoard(HashMap<Integer, Country> countries) {

        }
    };

    private String name;
    private HashMap<Integer, Country> countries;
    private HashMap<Integer, Country> countriesInBoard;

    private Continent(String name) {
        this.name = name;
        this.countries = new HashMap<>();
        this.countriesInBoard = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, Country> getCountries() {
        return countries;
    }

    public HashMap<Integer, Country> getCountriesInBoard() {
        return countriesInBoard;
    }

    public abstract void setCountries(HashMap<Integer, Country> countries);

    public abstract void setCountriesInBoard(HashMap<Integer, Country> countriesInBoard);
}
