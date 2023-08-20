
package WordGuessingGame;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

public class Main {
	 public static void main(String[] args){
		 
		 	UIManager.put("OptionPane.background",new Color(0, 0, 0));
	        UIManager.put("OptionPane.messageForeground", Color.yellow);
	        UIManager.put("Panel.background",new ColorUIResource(0, 0, 0)); 
	    	UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
	    	 
		 JOptionPane.showMessageDialog(null, "INSTRUCTIONS TO PLAY THE GAME :\n\n "
		 		+ "~ Guess the correct letters of the word that you randomly got one at a time.\n\n "
		 		+ "~ You are given 12 attempts to guess the entire word correctly.\n\n "
		 		+ "~ User guessed letter will be removed from the list of letters after each attempt.\n  So you can't guess the same letter again.\n\n"
		 		+ "~ Try to guess the entire word with less tries.\n ","LETTER GUESSING GAME",JOptionPane.INFORMATION_MESSAGE);
	       
	  
	        // Starts a new game
	        Game game = new Game();
	        game.startNewGame(); 
	 	 
	 }
}

