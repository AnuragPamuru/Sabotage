/**
 * Class Player - is the class that keeps track of the users that are 
 * playing the game, their name, the country they chose to play as,
 * the index of said country, and the number of moves they can make.
 *
 * @author Anurag, Aishwarya, Caroline, Serena
 * @version June 5, 2018
 */
public class Player
{
    private String myName;//stores the name of the player
    private String countryName;// name of the country that the user plays as
    private int countryIndex;// the index of their country in Map class's europe array
    private int movementPoints;// tracks their moves, and in cohesion with gui allows turns to be taken
    
    /**
     * Constructor for objects of class Person - initializes the name of the player,
     * the name of the country, and the index of the country
     * 
     * @param name - a String that represents the user's name
     * @param cName - a String that represents the user's country's name
     * @param cIndex - an int that represents the index of user's country on Map class's europe arraylist
     * 
     * author - Aishwarya
     */
    public Player(String name, String cName, int cIndex)
    {
        myName = name;
        countryName = cName;
        countryIndex = cIndex;
        movementPoints = 2;
    }
    
    /**
     * <b>Summary: </b> a method that returns the user's name
     * 
     * @param   none
     * @return  myName  user's name
     * 
     * Author - Aishwarya
     */
    public String getName()
    {
        return myName;
    }
    
    /**
     * <b>Summary: </b> a method that returns the user's country's name
     * 
     * @param   none
     * @return  countryName  user's country's name
     * 
     * Author - Aishwarya
     */
    public String getCountryName()
    {
        return countryName; 
    }
    
    /**
     * <b>Summary: </b> a method that returns the user's country's index
     * on Map class's europe arraylist
     * 
     * @param   none
     * @return  countryIndex    the user's country's index on arraylist europe
     *
     * Author - Aishwarya
     */
    public int getCountryIndex()
    {
        return countryIndex;
    }
    
    /**
     * <b>Summary: </b> a method that sets the number of movementpoints or
     * turns the user has
     * 
     * @param   none
     * @return  none
     * 
     * Author - Anurag
     */
    public void startTurn()
    {
        movementPoints = 2;
    }
    
    /**
     * <b>Summary: </b> a method that returns a boolean of
     * whether the user has turns or not
     * 
     * @param   none
     * @return  true    if movementPoints is greater than or equal to 0
     * 
     * Author - Anurag
     */
    public boolean hasMoreTurns()
    {
        if(movementPoints >= 0)
        {   
            return true;
        }   
        else
        {   
            return false;
        }   
    }
    
    /**
     * <b>Summary: </b> a method that removes movementPoints after
     * user makes a move
     * 
     * @param   none
     * @return  none
     * 
     * Author - Anurag
     */
    public void makeMove()
    {
        movementPoints--;
    }
    
    /**
     * <b>Summary: </b> a method that returns the user's number of movement
     * points.
     * 
     * @param   none
     * @return  movementPoints      the user's movementpoints
     * 
     * Author - Anurag
     */
    public int getMovementPoints()
    {
        return movementPoints;
    }
}
