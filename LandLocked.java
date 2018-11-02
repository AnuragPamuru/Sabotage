/**
* The LandLocked class is a part of the Domination game. This class extends the LandDistrict 
* class, which is an abstract class. This class deals specifically with the districts that are 
* completely surrounded by other districts, which makes them LandLocked, as opposed to the 
* OceanBorder class. 
*
* @author (Aishwarya, Anurag, Caroline, Serena)
* @version (June 5, 2018)
*/
public class LandLocked extends LandDistrict
{
    /**
    * The constructor for objects of class LandLocked instantiates the information/stats (the      
    * class variables) for the district by calling super to invoke the LandDistrict class’s 
    * constructor
    *
    * @param pop - the int that represents the district’s population
    * @param tr - the int that represents the district’s troop count
    * @param mn - the int that represents the district’s money (treasury)
    * @param iden - the int that represents the district’s numerical identifier
    * @param dir - the array of int that contains the int locations of districts that this district 
    * can attack (because they are directly next to the district)
    * @param int intern - the int that represents the district’s index in the country’s ArrayList
    * @param ide - the String that contains the district’s name
    * 
    * Author - Serena
    */
    public LandLocked(int pop, int tr, int mn, int[] dir,int intern, String ide, int mNum)
    {
               //calls super to invoke the LandDistric’s constructor to instantiate the class variables
           super(pop, tr, mn, dir, intern, ide, mNum);    
    }
}
