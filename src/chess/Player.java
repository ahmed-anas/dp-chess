package chess;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * This is the Player Class
 * It provides the functionality of keeping track of all the users
 * Objects of this class is updated and written in the Game's Data Files after every Game
 *
 */
public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer gamesplayed;
	private Integer gameswon;
	
	Player(String name)
	{
		this.name = name.trim();
		gamesplayed = new Integer(0);
		gameswon = new Integer(0);
	}
	
	//Name Getter
	String name()
	{
		return name;
	}
	
	//Returns the number of games played
	Integer gamesplayed()
	{
		return gamesplayed;
	}
	
	//Returns the number of games won
	Integer gameswon()
	{
		return gameswon;
	}
	

	
	//Increments the number of games played
	void updateGamesPlayed()
	{
		gamesplayed++;
	}
	
	//Increments the number of games won
	void updateGamesWon()
	{
		gameswon++;
	}
	
	private static String getFilePath(String fileName) {
		return System.getProperty("user.dir")+ File.separator + fileName;
	}

	private static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	

	static ArrayList<Player> fetchPlayers()         //Function to fetch the list of the players
	{
		ObjectInputStream input = null;
		ArrayList<Player> players = new ArrayList<Player>();
		try
		{
			File infile = new File(getFilePath("chessgamedata.dat"));
			input = new ObjectInputStream(new FileInputStream(infile));
			try
			{
				while(true)
					players.add((Player) input.readObject());
			}
			catch(EOFException e)
			{
				input.close();
			}
		}
		catch (FileNotFoundException e)
		{
			players.clear();
			return players;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			try {input.close();} catch (IOException e1) {}
			showMessage("Unable to read the required Game files !!");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			showMessage("Game Data File Corrupted !! Click Ok to Continue Builing New File");
		}
		catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return players;
	}
	
	void Update_Player()            //Function to update the statistics of a player
	{
		ObjectInputStream input = null;
		ObjectOutputStream output = null;
		Player temp_player;
		File inputfile=null;
		File outputfile=null;
		try
		{
			inputfile = new File(getFilePath("chessgamedata.dat"));
			outputfile = new File(getFilePath("tempfile.dat"));
		}
		catch (SecurityException e)
		{
			showMessage("Read-Write Permission Denied !! Program Cannot Start");
			System.exit(0);
		}
		
		boolean playerdonotexist;
		try
		{
			if(outputfile.exists()==false)
				outputfile.createNewFile();
			if(inputfile.exists()==false)
			{
				output = new ObjectOutputStream(new java.io.FileOutputStream(outputfile,true));
				output.writeObject(this);
			}
			else
			{
				input = new ObjectInputStream(new FileInputStream(inputfile));
				output = new ObjectOutputStream(new FileOutputStream(outputfile));
				playerdonotexist=true;
				try
				{
				while(true)
				{
					temp_player = (Player)input.readObject();
					if (temp_player.name().equals(name()))
					{
						output.writeObject(this);
						playerdonotexist = false;
					}
					else
						output.writeObject(temp_player);
				}
				}
				catch(EOFException e){
					input.close();
				}
				if(playerdonotexist)
					output.writeObject(this);
			}
			inputfile.delete();
			output.close();
			File newf = new File(getFilePath("chessgamedata.dat"));
			if(outputfile.renameTo(newf)==false)
				System.out.println("File Renameing Unsuccessful");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			showMessage("Unable to read/write the required Game files !! Press ok to continue");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			showMessage("Game Data File Corrupted !! Click Ok to Continue Builing New File");
		}
		catch (Exception e){}
	}
}
