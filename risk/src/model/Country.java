package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Country {

   private final int countryCoordinate;
   private Color color;
   private int numberOfSoldiers;
   private ArrayList<Country> neighboringCountries;
   private final Continent continent;
   private final String name;



    public Country(final int countryCoordinate , final Continent continent, final String name) {
        this.countryCoordinate = countryCoordinate;
        this.name = name;
        this.color = null;
        this.numberOfSoldiers =0;
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

    public static void makeAllCountries(){
        //TODO hanooz marz haro va rah ertebati ghate va kamel nashode
        makeAllNorthAmericasCountries();
        makeAllSouthAmericasCountries();
        makeAllAfricaCountries();
        makeAllEuropeCountries();
        makeAllAsiaCountries();
        makeAllAustraliaCountries();
    }
    private static void makeAllNorthAmericasCountries(){
        DataBase dataBase = DataBase.getDataBase();
        String[] countriesName = {"Alaska","Albert","Central America","Eastern United States",
                                  "Greenland","Northwest Territory","Ontario","Quebec","Western United States"};
        HashMap<Integer , Country> allCountries = new HashMap<>();
        for(int i =1 ; i <= 9 ; i++){
            allCountries.put(i,new Country(i,Continent.NORTH_AMERICA,countriesName[i-1]));
        }
        dataBase.getAllCountriesWithNumber().putAll(allCountries);
        dataBase.getAllNorthAmericasCountries().putAll(allCountries);
    }
    private static void makeAllSouthAmericasCountries(){
        DataBase dataBase = DataBase.getDataBase();
        String[] countriesName ={"Argentina","Brazil","Peru","Venezuela"};
        HashMap<Integer , Country> allCountries = new HashMap<>();
        int counter =0 ;
        for(int i=10 ; i <=13 ; i++){
            allCountries.put(i,new Country(i,Continent.SOUTH_AMERICA , countriesName[counter]));
            counter++;
        }
        dataBase.getAllCountriesWithNumber().putAll(allCountries);
        dataBase.getAllSouthAmericasCountries().putAll(allCountries);
    }
    private static void makeAllAfricaCountries(){
        DataBase dataBase = DataBase.getDataBase();
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName= {"Congo","East Africa","Egypt","Madagascar","North Africa" ,"South Africa"};
        int counter = 0;
        for(int i =14 ; i <= 19 ; i++){
            allCountries.put(i,new Country(i,Continent.AFRICA,countriesName[counter]));
            counter++;
        }
        dataBase.getAllCountriesWithNumber().putAll(allCountries);
        dataBase.getAllAfricaCountries().putAll(allCountries);
    }
    private static void makeAllEuropeCountries(){
        DataBase dataBase = DataBase.getDataBase();
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName = {"Great Britain","Iceland","Northern Europe","Scandinavia",
                                  "Southern Europe" , "Ukraine", "Western Europe"};
        int counter = 0;
        for(int i =19 ; i <= 27 ; i++){
            allCountries.put(i,new Country(i,Continent.EUROPE , countriesName[counter]));
            counter++;
        }
        dataBase.getAllCountriesWithNumber().putAll(allCountries);
        dataBase.getAllEuropeCountries().putAll(allCountries);
    }
    private static void makeAllAsiaCountries(){
        DataBase dataBase = DataBase.getDataBase();
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName = {"Afghanistan", "China", "India", "Irkutsk", "Japan",
        "Kamchatka", "Middle East", "Mongolia", "Siam (Southeast Asia)", "Siberia", "Ural", "Yakutsk"};
        int counter = 0;
        for(int i =27 ; i <= 39 ; i++){
            allCountries.put(i,new Country(i,Continent.NORTH_AMERICA , countriesName[counter]));
            counter++;
        }
        dataBase.getAllCountriesWithNumber().putAll(allCountries);
        dataBase.getAllAsiaCountries().putAll(allCountries);
    }
    private static void makeAllAustraliaCountries(){
        DataBase dataBase = DataBase.getDataBase();
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName ={"Eastern Australia","Indonesia","New Guinea","Western Australia"};
        int counter  =0;
        for(int i =38 ; i <= 42 ; i++){
            allCountries.put(i,new Country(i,Continent.AUSTRALIA , countriesName[counter]));
            counter++;
        }
        dataBase.getAllCountriesWithNumber().putAll(allCountries);
        dataBase.getAllAsiaCountries().putAll(allCountries);
    }
}
