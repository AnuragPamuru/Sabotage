import java.util.*;
/**
 * Class Country - is a class that is a part of the game domination
 * It contains common information about a country, such as it's name, 
 * population, the hexadecimal value that represents it's color, the number 
 * of districts, the amount of money in treasury, and an arraylist of the districts,
 * as well as an identifying numerical id
 *
 * @author Aishwarya, Anurag, Caroline, Serena
 * @version May 21, 2018
 */
public class Country 
{
    public ArrayList<LandDistrict> provinces;//an arraylist of land districts
    private String id, hColor;//the name of the country and the hexadecimal number for its color
    private int cPopulation;// the population of the country
    private int cTroop, districtNum;// the number of troops, and districts in the country
    private int treasury;//the money in the country
    private int numId;//the country's numerical id
    
    /**
     * The constructor for objects of class LandDistrict sets (instantiates) all of the information 
     * passed as parameters to the class variables. 
     *
     * @param name - the String that stores the country's name
     * @param population - the int that stores the country's population
     * @param colorHex - the String that stores the hexadecimal value of the country's color
     * @param dNum - the int that stores the number of districts in the country
     * @param money - the int that stores the money the country has
     * @param prov - the array list of LandDistrict that stores all of the districts in the country
     * @param ID - the int that stores the country's numerical ID
     * 
     * Author - Aishwarya
     */
    public Country(String name, int population, String colorHex, int dNum, int money, ArrayList<LandDistrict> prov, int ID)
    {
        //initializes values
        id = name;
        cPopulation = population;
        hColor = colorHex;
        cTroop = 100 * dNum;//sets troopcount to 100 per district
        districtNum = dNum;
        treasury = money;
        provinces = prov;
        numId = ID;
    }
    
    /**
     * <b>Summary: </b> a method that returns the population of the country
     * 
     * @param   none
     * @return  cPopulation     the country's population
     * 
     * Author - Caroline
     */
    public int getPopulation()
    {
        return cPopulation;
    }
    
    /**
     * <b>Summary: </b> a method that returns the (i + 1)th LandDistrict object
     * in the province arraylist
     * 
     * @param   none
     * @return  LandDistrict    the desired LandDistrict
     * 
     * Author - Caroline
     */
    public LandDistrict getProvince(int i)
    {
        return provinces.get(i);
    }
    
    /**
     * <b>Summary: </b> a method that returns the treasury of the country.
     * 
     * @param   none
     * @return  treasury    the amount of money the country has
     * 
     * Author - Caroline
     */
    public int getTreasury()
    {
        return treasury;
    }
    
    /**
     * <b>Summary: </b> a method that returns the number of troops in 
     * the country.
     * 
     * @param   none
     * @return  cTroop    number of troops in the country
     * 
     * Author - Caroline
     */
    public int getTroopCount()
    {
        return cTroop;
    }
    
    /**
     * <b>Summary: </b> a method that returns the number of disstricts
     * in a country.
     * 
     * @param   none
     * @return  districtNum    the number of districts in a country
     * 
     * Author - Caroline
     */
    public int getDistrictNum()
    {
        return districtNum;
    }
    
    /**
     * <b>Summary: </b> a method that returns the name of the country.
     * 
     * @param   none
     * @return  id          the name of the country
     * 
     * Author - Caroline
     */
    public String getName()
    {
        return id;
    }
    
    /**
     * <b>Summary: </b> a method that returns the hexadecimal for the color
     * that the country will be when playing the game.
     * 
     * @param   none
     * @return  hColor      hexadecimal String representing color that country will be
     *
     * Author - Caroline
     */
    public String getColor()
    {
        return hColor;
    }
    
    /**
     * <b>Summary: </b> a method that returns the numerical id of the country.
     * 
     * @param   none
     * @return  numId    the numerical id of the country 
     * 
     * Author - Caroline
     */
    public int getNumID()
    {
        return numId;
    }
    
    /**
     * <b>Summary: </b> a method that updates the population, troopcount,
     * district number, and internals of a country.
     * 
     * @param   none
     * @return  none
     * 
     * Author - Serena
     */
    public void updateAll()
    {
        updatePopulation();//updates population
        updateTroopCount();//updates troopcount
        updateDistrictNum();//updates district number   
        updateInternals();// updates internal
    }
    
    /**
     * <b>Summary: </b> a method that updates the population of a country.
     * 
     * @param   none
     * @return  none
     * 
     * Author - Serena
     */
    public void updatePopulation()
    {
        int sumPopulation = 0; // keeps track of population sum
        
        //adds population in every district in country to get country's population
        for (int i = 0; i < provinces.size(); i++)//till end of district arraylist
        {
            sumPopulation += provinces.get(i).getdPopulation();
        }
        cPopulation = sumPopulation;
    }
    
    /**
     * <b>Summary: </b> a method that updates the troopcount of a country.
     * 
     * @param   none
     * @return  none
     * 
     * Author - Serena
     */
    public void updateTroopCount()
    {
        int sumTroop = 0;// keeps track of troop count sum
        
        //adds together all district troop sums to get the country's troop number
        for (int i = 0; i < provinces.size(); i++)// till end of district array list
        {
            sumTroop += provinces.get(i).getdTroopCount(); 
        }
        cTroop = sumTroop;
    }
    
    /**
     * <b>Summary: </b> a method that updates district numbers of a country.
     * 
     * @param   none
     * @return  none
     * 
     * Author - Serena
     */
    public void updateDistrictNum()
    {
        districtNum = provinces.size();//sets it to size of district array list
    }
    
    /**
     * <b>Summary: </b> a method that updates the internals of a country.
     * 
     * @param   none
     * @return  none
     * Author - Serena
     */
    public void updateInternals()
    {
        for (int i = 0; i < provinces.size(); i++)//goes through the entire district arraylist
        {
            provinces.get(i).setInternal(i);//sets internal to i - so reorders them
        }
    }
    
    
}
