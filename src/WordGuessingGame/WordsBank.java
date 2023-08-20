package WordGuessingGame;
import java.util.Random;
import java.util.Arrays;

public class WordsBank {

  private final String[] hiddenWords;
  private final String[] clues;
  private int getRandomWordIndex;
  public static String generatedRandomWord;
  public static int noOfCharacters;
  public static String matchedClue;
  
 //Player have to guess one of these hidden words which he got randomly.
  public WordsBank(){
     hiddenWords = new String[]{ "Skeleton", "Custard","Etna","Carat",
      		 "Draughts","Camel", "Habitat", "Extinct","Biped",
      		 "Invisible","Lagoon","Herbivorous","Parasite","Sot","Flock"};
   
 //Hints for the above hidden words respectively
     clues=new String[] {"Hidden body framework","It is a delicious dessert made with only egg and milk","Europe's highest volcano ","Unit of gold measure by","It isa traditional board game",
      		 "Ship of desert called _____","Where an animal live", "Population zero", "Animal with two-feet","Incapable of being seen",
      		 "Salt water lake separated from the sea \nby sand banks","Grass eating animals are called _____","One who lives on others","Person who is a habitual drunkard called _____","A number of sheep called ____ "};
 	
  	}

  // Returns a random word from the existing words
  public String getRandomWord() {
	 
      Random rand = new Random();
      generatedRandomWord=hiddenWords[rand.nextInt(hiddenWords.length)];
      return generatedRandomWord;
  
  	}
  
  public void setClues() {
	//Match above hidden words with relevant clue
	    getRandomWordIndex=Arrays.asList(hiddenWords).indexOf(generatedRandomWord);
		WordsBank.matchedClue=clues[getRandomWordIndex];
		
	//Get the number of letters in the word which player got randomly
		WordsBank.noOfCharacters=generatedRandomWord.length();
  }  
  
}

 