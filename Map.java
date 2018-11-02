import java.awt.Font;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import apcslib.Format;
import chn.util.*;
/**
 * The Map class for the Domination game contains the information regarding all aspects relating to the map (countries, districts and players).
 *
 * @author (Aishwarya, Anurag, Caroline, Serena)
 * @version (June 5, 2018)
 */
public class Map
{
    private List<Country> europe;
    private int countryNum;
    private int currentPlayer;
    private List<Player> gamers = new ArrayList<Player>();
    private final static int NUM_PLAYERS = 4;

    /**
     * The constructor for the Map class loads all of the required files 
     * for the game, and instantiates them as well. It reads and loads the
     * information regarding the country, regarding the type of district 
     * and it intakes user input for each player to input their desired 
     * user name. 
     */ 
    //Aishwarya
    public Map()
    {
    	
        //ArrayList of Countries (for the whole map)
    	europe = new ArrayList<Country>();
        
        //file readers
        FileInput inFile1;
        FileInput inFile2;

        //files to read
        String fileName1 = "stats.txt";
        String fileName2 = "districts.txt";
        

        //initialisations
        inFile1 = new FileInput(fileName1);
        inFile2 = new FileInput(fileName2);
        
        //Caroline
        int countryNum = inFile1.readInt();        
        //reads data from files and initializes all of the information 
        //regarding the country  
        
        //overall number of the district
        int mNum = 0;
        for (int i = 0; i < countryNum; i++)
        {
            //name and initial population of the country
        	String cName = inFile1.readToken();
            int population = inFile1.readInt();

            //initial color of the country’s districts
            String cHex = inFile1.readToken();

            //total number of districts 
            int dNum = inFile1.readInt();
            //broken down into specific type of district
            int oceanBorder = inFile1.readInt();
            int landLocked = inFile1.readInt();

            // initial amount of money the country has
            int money = inFile1.readInt();
      
            //ArrayList of LandDistricts to keep track of each district
            //in the country 
            ArrayList<LandDistrict> prov = new ArrayList<LandDistrict>();

            //calculations to determine the population, troop count, 
            //and money a district has
            int dPop =  population / dNum;
            int dTroop = 100;
            int dMoney = money / dNum;
            int current = 0;

		    //continues for the oceanBorder districts to input which ways 
		    //(directions) the district can attack (by reading the strings 
		    //and converting them into ints) 
            for (int x = 0; x < oceanBorder; x++)
            {
                String input;
                int count;
                input = inFile2.readLine();
                String strarray[] = input.split(" ");
                int directions[] = new int[strarray.length];

                for (count = 0; count < directions.length ; count++)
                {
                    //adds the directions into the array (as an Integer)
                	directions[count] = Integer.parseInt(strarray[count]);
                }

                //creates a new OceanBorder object and adds it to the prov
                //array
                prov.add(new OceanBorder(dPop, dTroop, dMoney, directions, x, cName, mNum));
                mNum++;
                current = x;
            }

            //continues for the landLocked districts to input which ways 
            //(directions) the district can attack (by reading the strings 
            //and converting them into ints) 
            for (int x = 0; x < landLocked; x++)
            {
                String input;
                int count;
                input = inFile2.readLine();
                String strarray[] = input.split(" ");
                int directions[] = new int[strarray.length];

                for (count = 0; count < directions.length ; count++)
                {
                    ////adds the directions into the array (as an Integer)
                    directions[count] = Integer.parseInt(strarray[count]);
                }

            	//creates a new LandLocked objects and adds it to the 
                //prov array 
                prov.add(new LandLocked(dPop, dTroop, dMoney, directions,current + x, cName, mNum));
                mNum++;
            }
        
            //creates a new Country object and adds it to the europe array,
            //which is an array of Countries
            europe.add(new Country(cName, population, cHex, dNum, money, prov, i));
        }
        //Serena
        //takes user input (up to four players per game) for their 
        //chosen user name
        ConsoleIO kb = new ConsoleIO();
        String[] sarray = new String[europe.size()];
        String[] names = new String[NUM_PLAYERS];
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 26));
    	UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
        for(int n = 0; n < NUM_PLAYERS; n++)
        {
    		names[n] = JOptionPane.showInputDialog(null, "Player " + (n+1) + ", please enter your name: ");
        }
        for(int i = 0; i < europe.size(); i++)
    	{
    		sarray[i] = europe.get(i).getName();
    	}
        sarray = MapGUI.displaySelector(sarray,names,NUM_PLAYERS);
        for(int n = 0; n < NUM_PLAYERS; n++)
        {        	      
        	String name = names[n];
        	String cName = sarray[n];
        	int cIndex =  findId(sarray[n]);
            gamers.add(new Player(name,cName,cIndex));
        }
    }

    /**
    * The transferDistrict method is called when one districts attacks  
    * another. The method updates the information regarding both Countries 
    * involved in the attack. 
    *
    * (All params are ints so we can directly locate in the ArrayList)
    * @param wc - the winning Country of the attack 
    * @param lc - the losing Country of the attack
    * @param wd - the winning District of the attack
    * @param ld - the losing District of the attack 
    * 
    */
    //Aishwarya
    public void transferDistrict(int wc, int wd, int lc, int ld)
    {
        //takes the losing country’s province and adds it to the winning 
        // country and removes it from the losing country
        europe.get(lc).provinces.get(ld).setIdentifier(europe.get(wc).getName());
        
        europe.get(wc).provinces.add(europe.get(lc).provinces.remove(ld));
        
        //updating string identifier
        
        //updates both countries information/stats
        ((Country)europe.get(wc)).updateAll();
        ((Country)europe.get(lc)).updateAll();
    }
    
    /**
     * The getEurope method is a way to access the europe List that
     * contains all of the countries 
     * 
     * @return europe - returns the List<Country> that holds all of the 
     * countries on the map. 
     */
    //Anurag
	public List<Country> getEurope()
	{
		return europe;
	}
	
	/**
     * The getGamers method is a way to access the gamer List that
     * contains all of the players 
     * 
     * @return gamers - returns the List<Player> that holds all of the 
     * players on the map. 
     */
	//Anurag
	public List<Player> getGamers()
	{
		return gamers;
	}
    
	/**
	 * isMine(Player, DistrictButton) tells the caller if the DistrictButton mentioned belongs to its player
	 * @param p - the Player
	 * @param b - the District Button which is used to 
	 * @return rtn - tells you whether the Button is yours.
	 */
	//Anurag
	public boolean isMine(Player p, DistrictButton b)
	{
		boolean rtn = false;
		
		//returns true if the DistrictButton belongs to the player 
		if(europe.get(p.getCountryIndex()).getName().equals(b.getOwner().getName()))
		{
			rtn = true;
		}
		
		return rtn;
	}
	
    
	/**
	 * leaderBoard runs a StupidSort on the data inside the gamers ArrayList. After sorting the gamers based on their
	 * nation's dNum, the resulting ordered list of Strings with stats is returned
	 * 
	 * @return leader - a leader board of Strings containing stats in order by value.
	 */
	//Anurag
    public String[] leaderBoard()
    {
    	String[] stray = new String[NUM_PLAYERS];
    	int[] sort = new int[NUM_PLAYERS];
    	for(int i = 0 ; i < NUM_PLAYERS ; i++)
    	{
    		stray[i] = Format.left(gamers.get(i).getCountryName(), 18) + Format.right(europe.get(gamers.get(i).getCountryIndex()).getDistrictNum(), 4);
    	    sort[i] = europe.get(gamers.get(i).getCountryIndex()).getDistrictNum();
    	}
  	    int min, temp;
  	    String stemp;
  	    for (int outer = 0; outer < sort.length - 1; outer++)
  	    {
  	      min = outer;
  	      for (int inner = outer + 1; inner < sort.length; inner++)
  	      {
  	        if (sort[inner] > sort[min])
  	        {
  	          min = inner;
  	        }
  	      }
  	      //swap(list[outer], list[flag]);
  	      temp = sort[outer];
  	      stemp = stray[outer];
  	      
  	      sort[outer] = sort[min];
  	      stray[outer] = stray[min];
  	      
  	      sort[min] = temp;
  	      stray[min] = stemp;
  	    }
    	return stray;
    }
    
    /**
     * @param userCIndex - index in the europe ArrayList<Country> for the country that starts the invasion
     * @param userDIndex - index in the province ArrayList<LandDistrict> for the district that mounts the offense
     * @param invCIndex - the invaded country's index
     * @param invDIndex - the invading country's district's index
     * 
     */
    //Aishwarya
    public boolean invade(int userCIndex, int userDIndex, int invCIndex, int invDIndex)
    {
        
        if(europe.get(userCIndex).getProvince(userDIndex).getdTroopCount() >= 
            europe.get(invCIndex).getProvince(invDIndex).getdTroopCount())
        {
        	try {
				JOptionPane.showMessageDialog(null,new ImageIcon(ImageIO.read(new File("InvasionSuccess.jpg"))));
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	europe.get(invCIndex).getProvince(invDIndex).setTroopCount(
                    (europe.get(userCIndex).getProvince(userDIndex).getdTroopCount() - 
                    europe.get(invCIndex).getProvince(invDIndex).getdTroopCount()));
            europe.get(userCIndex).getProvince(userDIndex).setTroopCount(0);
            transferDistrict( userCIndex, userDIndex, invCIndex, invDIndex);
            return true;
        }
        else
        {
        	try {
				JOptionPane.showMessageDialog(null,new ImageIcon(ImageIO.read(new File("InvasionFail.jpg"))));
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	europe.get(userCIndex).getProvince(userDIndex).setTroopCount(
                    -1 * (europe.get(userCIndex).getProvince(userDIndex).getdTroopCount() - 
                    europe.get(invCIndex).getProvince(invDIndex).getdTroopCount()));
            europe.get(invCIndex).getProvince(invDIndex).setTroopCount(0);
            transferDistrict(invCIndex, invDIndex, userCIndex, userDIndex);
            return false;
        }
    }
    
    /**
     * findId(String) provides the user with the index in the europe ArrayList
     * <Country> for the Country with parameter s as its name
     * @param s - the Country Name
     * @return index of the Country in question.
     */
    //Anurag
    public int findId(String s)
    {
    	int rtn = 0;
    	for(int i = 0 ; i < europe.size() ; i++)
    	{
    		if(s.equals(europe.get(i).getName()))
    		{	
    			rtn = i;
    			i = europe.size();
    		}	
    	}
    	return rtn;
    }
    
    /**
     * nextTurn is a void method that moves the game on to the next player and skips over "dead" countries
     */
    //Anurag
    public void nextTurn()
    {
    	// makes sure that the numbers loop around
    	if(currentPlayer == gamers.size()-1)
    	{	
    		currentPlayer = 0;
    		gamers.get(currentPlayer).startTurn();
    	}
    	// makes sure that dead countries are skipped
    	else
    	{
    		currentPlayer++;
    		while(europe.get(gamers.get(currentPlayer).getCountryIndex()).getDistrictNum() == 0)
    		{	
    			currentPlayer++;
    		}
    		gamers.get(currentPlayer).startTurn();
    	}	
    }
    
    /**
     * currentPlayer is the getter method for the instance variable currentPlayer
     * @return the index of the currentPlayer in the gamer ArrayList<Player>
     */
    //Anurag
    public int currentPlayer()
    {
    	return currentPlayer; 
    }
}
