package WordGuessingGame;
import java.awt.*; 
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

public class Game {
 
    private int numberOfGuesses;
    private String unguessedCharacters;
    private ChosenWord chosenRandomWord;
    private WordsBank wordsBank = new WordsBank();
    private JFrame frame = new JFrame("Guess the hidden word");
     
     
    public void startNewGame(){
        // The alphabet letters to guess
        this.unguessedCharacters = "abcdefghijklmnopqrstuvwxyz";

        numberOfGuesses = 0;

        // Generate a new random word to guess
        this.chosenRandomWord = new ChosenWord(wordsBank.getRandomWord());
        
        //Matching relevant clue
        wordsBank.setClues();
        
        UIManager.put("OptionPane.background",new Color(0, 0, 0));
        UIManager.put("OptionPane.messageForeground", Color.cyan);
        UIManager.put("Panel.background",new ColorUIResource(0, 0, 0)); 
    	UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 20)));
    	// Hint message
    	JOptionPane.showMessageDialog(frame,
    			"   ~  "+WordsBank.matchedClue+"\n\n"+"   ~  It is a "+WordsBank.noOfCharacters +" letter word   ",
    			"Hints",JOptionPane.PLAIN_MESSAGE);
        
    	inputUserLetterGuess();
    }

    private void inputUserLetterGuess() {
    	Character[] charactersArray = new Character[unguessedCharacters.length()];
        
        // Converting to Characters array to be able to present in the dialog
        for (int i = 0; i < charactersArray.length; i++) {
            charactersArray[i] = unguessedCharacters.charAt(i);
        }
        
        // Letters list dialog 
        UIManager.put("OptionPane.background",new ColorUIResource(128,128,128));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(0, 0, 0));
        UIManager.put("Panel.background", Color.cyan); 
    	UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Rockwell Condensed", Font.BOLD, 35)));
    	 
       Character  guessedLetter = (Character) JOptionPane.showInputDialog(frame,
                "\n\nGuess a letter.... \n\n\n",
                "Letters list",
                JOptionPane.QUESTION_MESSAGE,
                null,
                charactersArray,
                charactersArray[0]);

        try {
        // Handling the user guessed
        handleUserLetterGuess(guessedLetter);
        }
        catch(NullPointerException e) {
        	
        	UIManager.put("OptionPane.background",new Color(0, 0, 0));
	        UIManager.put("OptionPane.messageForeground", Color.orange);;
	        UIManager.put("Panel.background",new ColorUIResource(0, 0, 0)); 
	    	UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
        	
	    	JOptionPane.showMessageDialog(frame, "Please Continue guessing",
	    			"Something Went Wrong",JOptionPane.ERROR_MESSAGE);
        	
        }
        
        // Display results of the user guessed.
        displayUserGuessResults(frame);
    }
    
    // Handling a guess from the user, guessedChar is the guessed char
    private void handleUserLetterGuess(char guessedChar){

    // Increasing and count number of attempts player try
   	 if (numberOfGuesses<12) {
   		  numberOfGuesses++;
   	 }
   	 else {
   		 
   		Object[] options1 = {"Yes","Exit"};
        UIManager.put("OptionPane.background",new ColorUIResource(128,128,128));
        UIManager.put("OptionPane.messageForeground", new ColorUIResource(0, 0, 0));
        UIManager.put("Panel.background", new ColorUIResource(128,128,128)); 
     	UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Rockwell Condensed", Font.BOLD, 30)));
     	 
     //Display game over message and display the correct word 
   		 int result = JOptionPane.showOptionDialog(frame,
   				 "The Game is Over!\n\n"+"The correct word is:    "+ WordsBank.generatedRandomWord+"\n\nSure? Do you try another word?",
   				 "Given turns are over!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options1, null);
            if(result== JOptionPane.YES_OPTION) {
            	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           	startNewGame();
            }
            else {
            	  System.exit(1);
            } 
   	 }
        // Removing the guessed letter, so that the user can't guess it again
        removeGuessedChar(guessedChar);

        // Running the guessing logic
        chosenRandomWord.charGuess(guessedChar);    
   }

    private void removeGuessedChar(char guessedChar){
        // Replacing the guessed char with empty char, so it can no longer be guessed
        unguessedCharacters = unguessedCharacters.replace(Character.toString(guessedChar), "");
    }

    private void displayUserGuessResults(JFrame frame){
        // Displaying result
    	
       JLabel StrLabel = new JLabel(" After your guess:",JLabel.CENTER);
        StrLabel.setFont(new Font("Showcard Gothic", Font.BOLD,30));
        StrLabel.setForeground(new Color(255, 255, 0));
        StrLabel.setBounds(35,50, 350,60);
       
       JLabel wordStartLabel = new JLabel(chosenRandomWord.toString(),JLabel.CENTER);
        wordStartLabel.setForeground(Color.black);
        wordStartLabel.setFont(new Font("Calibri", Font.BOLD,35));
        wordStartLabel.setBounds(20,150,380,50);
        
       JButton button = new JButton();
        button.setFont(new Font("Berlin Sans FB Demi", Font.BOLD,25));
        button .setBackground(new Color(255,150, 110));
        button.setForeground(new Color(0, 0,0));
        button.setBounds(90,250, 250,50);
     
       JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(102, 205, 170));
        panel.add(StrLabel); 
        panel.add(wordStartLabel);
        panel.add(button);
       
        frame.add(panel);
        frame.setSize(430, 430);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBackground(Color.red);
       
        
        // Checking if the word is completely exposed
        if (!chosenRandomWord.isEntireWordGuessed()){
            // If it isn't continue guessing
            button.addActionListener(e -> {
                frame.remove(panel);
                inputUserLetterGuess();
            });
            button.setText("Continue Guessing");
        } 
        else {
        	//If it is, winning message will be pop up
        	UIManager.put("OptionPane.background",new Color(0, 255, 255));
	        UIManager.put("OptionPane.messageForeground",new ColorUIResource(0, 0, 255));
        	UIManager.put("Panel.background",new ColorUIResource(0, 255, 255)); 
        	UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
      	          "Berlin Sans FB Demi", Font.BOLD, 20)));
        	JOptionPane.showMessageDialog(frame,
        			"\n\n   You Win the Game.   \n   Congradulations!   \n   Number of guesses you tried:   " + numberOfGuesses+"   \n\n\n",
        			"Game Result",JOptionPane.PLAIN_MESSAGE);
        	
        	
        	//Pop up a message to ask from the player to start a new game or stop the game
        	UIManager.put("OptionPane.background",new Color(0, 0, 0));
	        UIManager.put("OptionPane.messageForeground",new ColorUIResource(192, 192, 192));
        	UIManager.put("Panel.background",new ColorUIResource(0, 0, 0));
        	UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
            	          "Arial", Font.BOLD, 15)));
        	int a = JOptionPane.showConfirmDialog(frame,"Do you need to start a new Game?",
        			"Start a new Game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
             if(a== JOptionPane.YES_OPTION) {
            	 frame.remove(panel);
            	startNewGame();
            	}
             else {
             	frame.dispose();
             	}
         }  
     }
}