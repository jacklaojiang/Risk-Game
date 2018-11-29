package view.common;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import model.utilities.StringAnalyzer;

/**
 * This class is the view of map selection function for user to see. 
 * @author Yann Kerichard, Yueshuai Jiang, Che-Shao Chen
 */
public class MapSelectionView extends View 
{
	/**
	 * the map name
	 */
	private String mapName;
	
	/**
	 * The method is to let the user to select 2 ~ 6 players for the game.
	 * @return Returning the player number that was inputed if the value not right.
	 */
	public int print() 
	{
		System.out.println("Choose the number of players (2-6): ");
		int player_num;
		boolean correctValue;
		
		do 
		{
			player_num = getInteger();
			correctValue = isValueCorrect(player_num, 2, 6);
		}
		while(!correctValue);
		
		return player_num;
	}

	/**
	 * The method is for view of showing the map selection function and also showing the information of the selected .map file.
	 * @return Returning the selected file path string if selected a file, otherwise returning null.
	 */
	public String selectMap() 
	{
		System.out.println("Select the map file (.map)...");
		JFileChooser jf = new JFileChooser() 
		{
			
		protected JDialog createDialog(Component parent) throws HeadlessException 
		{
			JDialog jDialog = super.createDialog(parent);
			jDialog.setAlwaysOnTop(true);
			return jDialog;		
		}
		};
		
		/*To open in current directory*/
		File workingDirectory = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "maps");
		jf.setCurrentDirectory(workingDirectory);
		
		jf.showOpenDialog(null);
		File path = jf.getSelectedFile();

		if(StringAnalyzer.checkMapType(path)) 
		{
			mapName = path.getName().substring(0, path.getName().length() - 4);
			return path.getAbsolutePath();
		}
			
		else
			selectMap();
		
		return null;
	}

	/**
	 * to get the map name
	 * @return the map name
	 */
	public String getMapName() 
	{
		return mapName;
	}
	
	/**
	 * Ask player strategies
	 */
	public int askStrategy(int nb) 
	{
		System.out.println("Choose a strategy for player " + nb + ": ");
		System.out.println("1 - Human");
		System.out.println("2 - Aggressive computer");
		System.out.println("3 - Benevolent computer");
		System.out.println("4 - Random computer");
		System.out.println("5 - Cheater computer");
		int strat;
		boolean correctValue;
		
		do 
		{
			strat = getInteger();
			correctValue = isValueCorrect(strat, 1, 5);
		}
		while(!correctValue);
		
		return strat;
	}
	
	/**
	 * Ask the number of maps
	 */
	public int askMapNumber()
	{
		System.out.println("Choose the number of maps (1 to 5):");

		int nb;
		boolean correctValue;
		
		do 
		{
			nb = getInteger();
			correctValue = isValueCorrect(nb, 1, 5);
		}
		while(!correctValue);
		
		return nb;
	}
}
