/**
 * The OceanBorder class a part of the Domination game. This class extends the LandDistrict  
 * class, which is an abstract class. This class deals specifically with the districts that are 
 * surrounded by or are near/touching water, which makes them OceanBorder as opposed to
 * the  LandLocked class.
 *
 * @author Aishwarya, Anurag, Caroline, Serena
 * @version June 5, 2018
 */
public class OceanBorder extends LandDistrict
{  
    /**
     * The constructor for objects of class OceanBorder instantiates the information/stats the (the 
     * class variables) for the district by calling super to invoke the LandDistrict class’s constructor
     *
     * @param pop - an int that represents the district’s population
     * @param tr - an int that represents the district’s troop count
     * @param mn - an int that represents the district’s money count (treasury)
     * @param iden - an int that represents the district’s numerical identification
     * @param dir - an array of ints that are the possible directions a district can move to attack
     * @param intern - an int that represents the district’s index in the country ArrayList
     * @param ide - a String that contains the district’s identifier (the name)
     * 
     * author = Serena\\\\\\\\\\\\\\\\\\
     */
    public OceanBorder(int pop, int tr, int mn, int[] dir,int intern, String ide, int mNum)
    {
           //calls super to invoke the LandDistrict constructor
           super(pop, tr, mn, dir, intern, ide, mNum);      
    }
}
