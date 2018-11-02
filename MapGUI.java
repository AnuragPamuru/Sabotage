import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

import chn.util.FileInput;

/**
 * MapGUI is the Graphical User Interface of the program "Domination". It uses multiple ActionListeners, JButtons, JOptionPanes,
 * and JLabels on a JPanel/Frame to allow for user interaction with the program
 * 
 * 
 * @author (Aishwarya, Anurag, Caroline, Serena)
 * @version (June 5, 2018)
 */

public class MapGUI 
{
	//Aishwarya
    private JFrame frame; //our frame where all graphics except JOptionPanes appear
    private final static int NUM_PLAYERS = 4;
    
    //Anurag
    private ArrayList<DistrictButton> buttons = new ArrayList<DistrictButton>(); //stores all the districts
    private JLabel leaderboard[] = new JLabel[NUM_PLAYERS]; //Leaderboard of the map's users
    
    //Caroline
    private static final Color MAP_COLOR = new Color(235, 213, 179); //Color of the map
    private Map game; //map
    private DistrictButton selected;
    private DistrictButton target;   
   
    //Serena
    private JButton go;
    private java.util.List<Player> gamers;
    
    
    /**
     * Launch the application.
     */
    //Aishwarya & Caroline
    public static void main(String[] args)
    {
        
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                	//intializing Map and MapGUI
                    MapGUI window = new MapGUI(new Map());
                    window.frame.setVisible(true);
                } 
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Creates the application.
     */
    //Anurag
    public MapGUI(Map m) {
        initialize();//helper method
        game = m;
    }

    /**
     * Initializes the contents of the frame.
     */
    //Aishwarya, Anurag, and Serena
    private void initialize()
    {
    	//new window with fonts specified
        frame = new JFrame();
        frame.setFont(new Font("Courier New", Font.PLAIN, 30));
        frame.setBounds(100, 100, 1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.getContentPane().setLayout(null);
        try 
        {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("TitleScreen.jpg"))))); 
        } 
        catch (IOException e) 
        { 
            e.printStackTrace(); 
        }
        frame.pack(); 
        frame.setVisible(true);
        
        //start button
        JButton start = new JButton();
        start.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        start.setForeground(Color.WHITE);
        Color redColor = new Color(66, 13, 9);
        //setting up the start button to initialize map
        start.setBackground(redColor);
        start.setText("Start");
        frame.add(start);
        start.setBounds(1520, 400, 250, 100);
        start.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                initializeMap();
            }
        });  
        
        //instructions button
        JButton information = new JButton();
        information.setFont(new Font("Times New Roman", Font.PLAIN, 45));
        information.setForeground(Color.WHITE);
        
        information.setBackground(redColor);
        information.setText("Instructions");
        frame.add(information);
        information.setBounds(1520, 560, 250, 100);
                
        //if clicked then...
        information.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	//show instructions to the game
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 26));
                UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
                try
                {
                    JOptionPane.showMessageDialog(frame, new ImageIcon(ImageIO.read(new File("Instructions.jpg"))));
                } 
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }          
            }
        }
        );        
    }
    
    /**
     *  
     * <p> 
     * Changes the start screen to a map with buttons placed on coordinate points from "coordinates.txt".
     * This method also creates many auxiliary buttons and labels which are described as such:
     * </p>
     * 
     * <b>
     * leaderboard (JLabel) - displays the leaderboard of all players indefinitely
     * stats (JLabel) - displays the stats of the selectedButton
     * move (JButton) - displays the words "Move/Invade" and when clicked prompts the user to select provinces in a
     * JOptionPane Message Dialog
     * addFort(JButton) - displays the words "Build Fort" and when clicked returns whether or not a fort was added.
     * </b>
     * @param  N/A
     * @return N/A
     */
    //Caroline
    private void initializeMap()
    {    
    	gamers = game.getGamers();
        //loading background...
        try 
        {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Map.png")))));
            frame.pack(); 
            frame.setVisible(true);
        } 
        catch (IOException e) 
        { 
            e.printStackTrace(); 
        }
        
        //leaderboard labels - Anurag
        String[] lead = game.leaderBoard();
        for(int i = 0 ; i < NUM_PLAYERS ; i++)
        {
        	leaderboard[i] = new JLabel();
            frame.add(leaderboard[i]);
            leaderboard[i].setFont(new Font("Arial", Font.PLAIN, 26));
            leaderboard[i].setBounds(384,378 + (i * 100),327,58);
            leaderboard[i].setBorder(null);
            leaderboard[i].setOpaque(false);
            leaderboard[i].setText(lead[i]);
            leaderboard[i].setVisible(true);     
        }

        //statistics label - Anurag
        JLabel stats = new JLabel();
        frame.add(stats);
        stats.setFont(new Font("Arial", Font.PLAIN, 26));
        stats.setBounds(18,18,1904,61);
        stats.setBorder(null);
        stats.setOpaque(false);
        stats.setVisible(true);
        
        //currentTurn label - Aishwarya
        JLabel currentTurn = new JLabel();
        frame.add(currentTurn);
        currentTurn.setFont(new Font("Arial", Font.PLAIN, 26));
        currentTurn.setBounds(1055,13,837,67);
        currentTurn.setBorder(null);
        currentTurn.setOpaque(false);
        currentTurn.setVisible(true);
        currentTurn.setText("Player: " + gamers.get(game.currentPlayer()).getName() + ", Country Name: " 
                + gamers.get(game.currentPlayer()).getCountryName());
        
        //initializing go button - Aishwarya
        go = new JButton();
        frame.add(go);
        go.setFont(new Font("Arial", Font.PLAIN, 26));  
        go.setBounds(976,980,288,100);
        go.setBorder(null);
        go.setOpaque(false);
        go.setBackground(MAP_COLOR);
        go.setText("Go!");
        go.setVisible(false);
        
        //initializing endTurn button - Serena
        JButton endTurn = new JButton();
        frame.add(endTurn);
        endTurn.setFont(new Font("Arial", Font.PLAIN, 26));  
        endTurn.setBounds(1296,980,288,100);
        endTurn.setBorder(null);
        endTurn.setOpaque(false);
        endTurn.setBackground(MAP_COLOR);
        endTurn.setText("End Turn!");
        endTurn.setVisible(false);
        
        //if clicked then...
        endTurn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	//end the turn and update the currentTurn label on the HDD
        		game.nextTurn();
        		
        		currentTurn.setText("Player: " + gamers.get(game.currentPlayer()).getName() + ", Country Name: " 
                        + gamers.get(game.currentPlayer()).getCountryName());
            }
        });
        
        //move button - Caroline
        JButton move = new JButton();
        frame.add(move);
        move.setFont(new Font("Arial", Font.PLAIN, 26));    
        move.setBounds(16,980,288,100);
        move.setBorder(null);
        move.setOpaque(false);
        move.setBackground(MAP_COLOR);
        move.setText("Move/Invade");
        move.setVisible(false);
        //if clicked then...
        move.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	//prompt user for the district to move to
                JOptionPane.showMessageDialog(frame, "Pick a neighboring district to invade");
                // int wC = selected.CountryId();
                // int wD = selected.DistrictId();                
                go.setVisible(true);
                go.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        int wC = selected.CountryId();
                        int iC = target.CountryId();
                        int wD = selected.DistrictId();
                        int iD = target.DistrictId(); 
                        
                        //error testing - > System.out.print(wC + " " + wD + " " + iC + " " + iD);
                        //CHECK IF THE SELECTED PROVINCE BELONGS TO THE CURRENT PLAYER
                        if(selected.isBordering(target))
                        {
                        	if(game.isMine(gamers.get(game.currentPlayer()), target))
                        	{
                        		target.getLinked().setTroopCount(selected.getLinked().getdTroopCount());
                        		selected.getLinked().setTroopCount(0);
                        		JOptionPane.showMessageDialog(frame, "Troop Transfer success!");
                        		go.setVisible(false);
                        	}
                        	else
                        	{
                        		game.invade(wC, wD , iC , iD);
    	                        selected.setOwner(game.getEurope().get(game.findId(selected.getLinked().getIdentifier())));
    	                        selected.updateInternals();
    	                        target.setOwner(game.getEurope().get(game.findId(selected.getLinked().getIdentifier())));
    	                        target.updateInternals();
    	                        go.setVisible(false);
    	                        selected = null;
    	                        gamers.get(game.currentPlayer()).makeMove();
    	                        String[] lead = game.leaderBoard();
    	                        
    	                        //update leaderboard
    	                        for(int i = 0 ; i < NUM_PLAYERS ; i++)
    	                        {
    	                        	leaderboard[i].setText(lead[i]);
    	                        }
                        	}
                        }
                        else
                        {
                        	JOptionPane.showMessageDialog(frame, "Pick a NEIGHBORING district to invade. Retry.");
                        	go.setVisible(false);
                        	
                        }
                    }
                });    
            }
        });    
        
        //addFort button - Anurag
        JButton addFort = new JButton();
        frame.add(addFort);
        addFort.setFont(new Font("Arial", Font.PLAIN, 26)); 
        addFort.setBounds(336,980,288,100);
        addFort.setBorder(null);
        addFort.setOpaque(false);
        addFort.setBackground(MAP_COLOR);
        addFort.setText("Build a Fort");
        addFort.setVisible(false);
        //if clicked then...
        addFort.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
        		//add a Fort
        		if(selected.getLinked().addFort())
        			JOptionPane.showMessageDialog(frame, "Added!");
        		else
        			JOptionPane.showMessageDialog(frame, "We're sorry, you're short on money!");            		
            }
        });
        
        //Recruit button - Caroline
        JButton recruit = new JButton();
        frame.add(recruit);
        recruit.setFont(new Font("Arial", Font.PLAIN, 26)); 
        recruit.setBounds(656,980,288,100);
        recruit.setBorder(null);
        recruit.setOpaque(false);
        recruit.setBackground(MAP_COLOR);
        recruit.setText("Recruit");
        recruit.setVisible(false);
        //if clicked then...
        recruit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
        		selected.getLinked().recruit(Integer.parseInt(JOptionPane.showInputDialog("How many sets of 100 would you like to recruit?")));
            }
        });
        
        //file reading - Aishwarya
        FileInput inFile3;
        String fileName3 = "coordinates.txt";
        inFile3 = new FileInput(fileName3);
        
        for (int x = 0; x < game.getEurope().size(); x++)
        {
        	//initialize country, color, and districtNum - Anurag
            Country c = game.getEurope().get(x); 
            int dNum = c.getDistrictNum();
            String col = c.getColor();
            Color cColor = Color.decode("#" + col.toUpperCase());
            
            for(int y = 0 ; y < dNum ; y++)
            {
                //parsing data from file - Caroline
                String input;
                int count;
                input = inFile3.readLine();
                String strarray[] = input.split(" ");
                int coord[] = new int[strarray.length];
    
                for (count = 0; count < coord.length ; count++)
                {
                    coord[count] = Integer.parseInt(strarray[count]);
                }
                //DistrictButton set up for all buttons on the array
                DistrictButton a;
                a = new DistrictButton(game.getEurope().get(x).getProvince(y),c);      
                frame.add(a);
                buttons.add(a);
                a.setForeground(Color.WHITE);
                a.setBackground(cColor);
                a.setBounds(coord[0], coord[1], coord[2], coord[3]);
                a.setVisible(true);
                a.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        String s = e.toString();
                        s = s.substring(s.indexOf("Country"));
                        stats.setText(s);
                        currentTurn.setVisible(true);
                        endTurn.setVisible(true);
                        if(go.isVisible())
                        {
                            target = a;
                            move.setVisible(false);
                            addFort.setVisible(false);
                            recruit.setVisible(false);
                        }
                        else
                        {
                        	selected = a;
                        	selected.updateInternals();
                        	if(game.isMine(gamers.get(game.currentPlayer()), selected))
                        	{
                        		move.setVisible(true);
                                addFort.setVisible(true);
                                recruit.setVisible(true);
                        	}
                        	else
                        	{
                        		move.setVisible(false);
                                addFort.setVisible(false);
                                recruit.setVisible(false);
                        	}
                        }
                        currentTurn.setText("Player: " + gamers.get(game.currentPlayer()).getName() + ", Country Name: " 
                                + gamers.get(game.currentPlayer()).getCountryName());
                    }
                }
                );
            }   
        }
    }
    /**
     * displaySelector(String[], String[], int) returns a multi-option JOptionPane with choices from the arrays given in the parameters
     * @param sarray - choice names
     * @param names - player names
     * @param num - number of repetitions
     * @return rtn - String array that gives the caller a JOPtionPane that presents them with options from a primitive array that they created
     */
    //Anurag
    public static String[] displaySelector(String[] sarray, String[] names, int num)
    {   
    	//stores the responses
        String[] rtn = new String[num];
        //repeat until all 4 have chosen
        for(int i = 0 ; i < num ; i++)
        {   
        	//prompting
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 26));
            UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
            int response = JOptionPane.showOptionDialog(null, names[i] + ", where do you wish to rule?", "Atlas",
                       JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                       null, sarray, sarray[0]);
            String[] temp = new String[sarray.length -1];
            int x = 0;
            int y = 0;
            //removing an answer choice after it has already been selected
            while(x < sarray.length)
            {
                if(x == response)
                {   
                    x++;
                    rtn[i] = sarray[response];
                }
                else
                {
                    temp[y] = sarray[x];
                    x++;
                    y++;
                }
            }
            sarray = temp;
        }
        //done
        return rtn;
    }
}
