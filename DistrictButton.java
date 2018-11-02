import java.awt.Color;
import javax.swing.JButton;

//Anurag
public class DistrictButton extends JButton
{
	private LandDistrict linkedDistrict; 
	private Country owner; 
	
	/**
	 * Basic Constructor
	 * @param d - linkedDistrict
	 * @param c - owner
	 */
	public DistrictButton(LandDistrict d, Country c)
	{
		linkedDistrict = d;
		owner = c;
	}
	/**
	 * The toString of the linkedDistrict
	 */
    public String toString()
    {
    	return linkedDistrict.toString();
    }
    /**
     * DistrictId() returns the id of the District
     * @return the index of the DistrictButton's District in its owners province ArrayList<LandDistrict>
     */
    public int DistrictId()
    {
    	return linkedDistrict.getInternal();
    }
    /**
     * getLinked returns the reference to the actual district
     * @return the actual district reference
     */
    public LandDistrict getLinked()
    {
    	return linkedDistrict;
    }
    /**
     * getOwner returns the reference variable to the owner
     * @return owner - the owner of the Button
     */
    public Country getOwner()
    {
    	return owner;
    }
    /**
     * setOwner allocates a new Owner to this
     * 
     * @param c - new owner
     */
    public void setOwner(Country c)
    {
    	owner = c;
    }
    /**
     * CountryId() gets the ID of the owner
     * 
     * @return
     */
    public int CountryId()
    {
    	return owner.getNumID();
    }
    /**
     * updateInternals() changes the color of the button to match its new owner
     */
    public void updateInternals()
    {
    	this.setBackground(Color.decode("#" + owner.getColor().toUpperCase()));
    }
    
    /**
     * isBordering() checks if the parameter DistrictButton d is bordering the linkedDistrict button.
     * 
     * @param d - the DistrictButton that will be checked for its border
     * 
     */
    public boolean isBordering(DistrictButton d)
    {
    	boolean rtn = false;
    	int[] dir = linkedDistrict.getdDirection();
    	for(int i = 0; i < dir.length ; i++)
    	{	
	    	if(dir[i] == d.getLinked().getMasterNum())
	    	{
	    		rtn = true;
	    	}
    	}
    	return rtn;
    }
}
