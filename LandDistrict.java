/**
 * The abstract class LandDistrict is a part of the game Domination. It contains some common 
 * information that is required for all of the districts in the game. 
 *
 * @author (Aishwarya, Anurag, Caroline, Serena)
 * @version (June 5, 2018)
 */
public abstract class LandDistrict
{
    //variables to keep track of the district’s information/stats
    protected int dTroopCount, dPopulation, dTreasury, internal, masterNum;
    //name of the district
    protected String identifier;
    //an array of the directions in which the district can attack 
    protected int[] dDirection;

    /**
     * The constructor for objects of class LandDistrict sets (instantiates) all of the information 
     * passed as parameters to the class variables. 
     *
     * @param pop - the int population of the district
     * @param tr - the int troop count of the district
     * @param mn - the int amount of money the district has (for the treasury)
     * @param dir - the array of ints that represent where the district can attack
     * @param intern - the int that represents the index of the LandDistrict inside the Country 
     * ArrayList
     * @param ide - the String that contains the district’s name
     * 
     * Author - Aishwarya
     *
     */
    public LandDistrict(int pop, int tr, int mn, int[] dir, int intern, String ide, int mNum)
    {
        //instantiates the class variables 
        dPopulation = pop;
        dTreasury = mn;
        dTroopCount = tr; 
        dDirection = dir;
        internal = intern;
        identifier = ide;
        masterNum = mNum;
    }
    
    /**
    * The setPopilation method provides a way to update the population of the district (a setter 
    * method)
    *
    * @param pop - an int that represents the population of the district
    * 
    * Author - Anurag
    */   
    public void setPopulation( int pop)
    {
        //updates dPopulation (a private class variable) 
        dPopulation = pop;
    }
    
    /**
    * The setTroopCount method provides a way to update the troop count of the district (a setter 
    * method)
    *
    * @param tc - an int that represents the troop count of the district
    * 
    * Author - Anurag
    */
    public void setTroopCount( int tc )
    {
        //updates dTroopCount (a private class variable) 
        dTroopCount = tc;
    }
    
    /**
    * The setTreasury method provides a way to update the treasury (money count) of the district (a 
    * setter method)
    *
    * @param mn - an int that represents the money (treasury) of the district
    * 
    * Author - Anurag
    */
    public void setTreasury(int mn)
    {
        //updates dTreasury (a private class variable)
        dTreasury = mn;
    }
    
    /**
    * The setDIrection method provides a way to update the directions a district can use to attack 
    * (the locations of the districts surrounding this district)  (a setter method)
    *
    * @param direction - an array of ints that contains the numerical moves a district can make to 
    * attack. (aka the numbers that represent the surrounding districts so the district can only attack 
    * districts that are directly next to it)
    * 
    * Author - Serena
    */
    public void setDirection( int[] direction)
    {
        //updates dDirection (a private class varaible) 
        dDirection = direction;
    }
    
    /**
    * The setInternal method provides a way to update the index of the LandDistrict inside its 
    * country ArrayList (a setter method)
    *
    * @param intern - an int that represents the index of the district in the countryArrayList
    * 
    * Author - Serena
    */
    public void setInternal(int intern)
    {
        //updates internal (a private class variable) 
        internal= intern;
    }
    
    /**
    * The setIdentifier method provides a way to update the name of the district (a setter 
    * method)
    *
    * @param ide - a String of the district’s name
    * 
    * Author - Serena
    */
    public void setIdentifier (String ide)
    {
        //updates identifier (a private class variable)
        identifier = ide;
    }
    
    /**
    * The getdPopulation method provides a way to get the population of the district (a getter 
    * method)
    *
    * @return dPopulation - the int that represents the district’s population
    * Author - Aishwarya
    */
    public int getdPopulation()
    {
        return dPopulation;
    }
    
    /**
    * The getIdentifier method provides a way to get the district’s country's name (a getter 
    * method)
    *
    * @return dPopulation - the String that represents the district’s country's name
    * Author - Aishwarya
    */
    public String getIdentifier()
    {
        return identifier;
    }
    
    /**
    * The getdTreasury method provides a way to get the treasury of the district (a getter 
    * method)
    *
    * @return dTreasury - the int that represents the district’s money (treasury)
    * Author - Aishwarya
    */
    public int getdTreasury()
    {
        return dTreasury;
    }
    
    /**
    * The getdTroopCount method provides a way to get the troop count of the district (a getter 
    * method)
    *
    * @return dTroopCount - the int that represents the district’s troop count 
    * Author - Aishwarya
    */
    public int getdTroopCount()
    {
        return dTroopCount;
    }
    
    /**
    * The getdDirection method provides a way to get the array of direction that a district can attack 
    * with (a getter method)
    *
    * @return dDirection - the array of ints that represents the district’s possible locations to attack
    * Author - Caroline
    */
    public int[] getdDirection()
    {
        return dDirection;
    }
    
    /**
    * The getInternal method provides a way to get the index of the district in its country’s ArrayList (a getter method)
    *
    * @return internal - the int that represents the district’s index in its country’s ArrayList
    * Author - Caroline
    */
    public int getInternal()
    {
        return internal;
    }
    
    /**
    * The recruit method provides a way to increase a district’s troop count by reducing the district’s * treasury, and increasing the district’s troop count. (the districts treasury will be reduced by one, 
    * for each hundred troops recruited)
    *
    * @param i - an int that represents the number that the district’s treasury will be reduced by, as 
    * well as the multiplier for how many troops will be added to troop count)
    * Author - Anurag
    */
    public void recruit(int i)
    {
        
        //updates the treasury by subtracting how much it cost to recruit the troops 
        if(i < dTreasury)
        {   
            dTreasury -= i;
            //the number of troops recruited
            dTroopCount += i*100;
        }
        else
        {
            dTroopCount += dTreasury*100;
            dTreasury = 0;
        }
    }
    
    /**
    * The toString method prints out the information/stats for districts (like the treasury, troop 
    * count, and population
    *
    * @return - returns a string that contains the stats/information of a district
    * Author - Caroline
    */
    public String toString()
    {
        return "Country: " + identifier + "    " + "Treasury: " + dTreasury + "    " + "Population: " + dPopulation
                                        + "    " + "Troop Count: " + dTroopCount; 
                                                 
    }
    
    /**
	 * The getMasterNum method provides a way to get the index of the district (a getter method)
	 *
     * @return internal - the int that represents the district’s index
     * Author - Anurag
     */
    public int getMasterNum()
    {
    	return masterNum;
    }
    /**
     * <b>Summary: </b> a method that returns a boolean to the gui. It is true
     * if a fort can be added with the funds possessed in that district
     * It is false if the funds are not present to build a fort
     * (It needs 5 dollars from treasury to make a fort)
     * 
     * @param   none
     * @return  true    if the disstrict has enough money to build a fort
     * Author - Aishwarya
     */
    public boolean addFort()
    {
        if(dTreasury >= 5)// checks whether you have enough money to buy fort
        {
            addTFort();
            return true;
        }
        else
        {
            return false;
        }
        }
        
    /**
     * <b>Summary: </b> a helper method that adds a fort to the district. 
     * A fort costs $5, which will be removed from the district's treasury. 
     * A fort also contains 200 troops that are added to the district's 
     * troop count.
     * 
     * @param   none
     * @return  none
     * Author - Aishwarya
     */
    public void addTFort()
    {
            dTroopCount += 200;//increases troop count
            dTreasury -= 5;// decreases treasury
    }

}
