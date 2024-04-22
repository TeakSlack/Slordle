package teak.events;

import teak.*;
import java.util.ArrayList;

public class SubmitEvent implements EventListener<char[]> {
    public void onEvent(char[] word)
    {
        ArrayList<String> answers = App.getInstance().getAnswers();
        String answerStr = new String();
        for(int i = 0; i < word.length; i++)
        {
            answerStr += word[i];
        }

        if(!answers.contains(answerStr.toLowerCase())) return;

        checkGuess(word);
        for(int i = 0; i < word.length; i++) word[i] = ' ';
        App.getInstance().setPosition(0);
    }

    private void checkGuess(char[] word)
    {
        int guess = App.getInstance().getGuess();

        Word wordGuess = new Word(word);
        Position[] validity = App.getInstance().getAnswer().compare(wordGuess);
        
        for(int i = 0; i < word.length; i++)
        {
            AppGUI.getInstance().updateLetter(i, guess, validity[i], word[i]);
        }

        App.getInstance().setGuess(guess + 1);

        boolean isCorrect = true;
        for(int i = 0; i < validity.length; i++)
        {
            if(validity[i] != Position.PRESENT) isCorrect = false;
        }

        App.getInstance().setCorrect(isCorrect);      

        if(App.getInstance().getGuess() == 6)
            App.getInstance().setGameOver(true);
    }
}