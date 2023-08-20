package WordGuessingGame;
public class ChosenWord {

    private String word; // The chosen word
    private boolean[] charsGuessed;  // Array of the guessed chars in the word.
    // If charsGuessed[0] is True,
    // it means the first char was guessed by the user already
    // Initially, all the values are False

    
    public ChosenWord(String word){
        this.word = word.toLowerCase();
        charsGuessed = new boolean[word.length()];
    }

 // receives a char and checks if it appears in the word.
    public void charGuess(char guess) {
        int index = word.indexOf(guess); // Finding first occurrence
        // Iterating while there are more occurrences of the guess
        while (index >= 0) {
            charsGuessed[index] = true; // Marking the char appearance in the fitting index
            index = word.indexOf(guess, index + 1); // Finding next occurrence
        }
    }

    // Check if the entire word is already guessed
    public boolean isEntireWordGuessed() {

        // Iterating through the chars guessed array
        for (boolean b : charsGuessed) {
            // If a char was not guessed returning false
            if (!b)
                return false;
        }
        	// All the chars were guessed, return true.
        		return true;
    }

    
    // Building a string to represent the chosen word with it's revealed letters
    @Override
    public String toString(){
        StringBuilder formattedWord = new StringBuilder();

        // Iterating through the characters of the word.
        //if the character was guessed, adding it, otherwise add '_'
        for(int i = 0; i < word.length(); i++){
            if (charsGuessed[i]){
                formattedWord.append(word.charAt(i));
            } 
            else {
                formattedWord.append('_');
            }
            formattedWord.append(' ');
        }
        return formattedWord.toString();
    }
}