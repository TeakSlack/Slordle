package teak;

import java.util.ArrayList;

// Manages game state
public class App {
    private Word answer;
    private ArrayList<String> answers;
    private int guess;
    private int position;
    private boolean isGameOver;
    private boolean isIntro;
    private boolean isCorrect;

    private static App instance;

    public static App getInstance()
    {
        if(instance == null) instance = new App();
        return instance;
    }

    public void run()
    {
        AppGUI.getInstance().init();

        guess = 0;
        position = 0;
        isGameOver = false;
        isIntro = true;
        isCorrect = false;

        String[] wordleAnswers = WordleReader.readWordleStrings("wordle.txt");
        String answerString = wordleAnswers[(int)(Math.random() * wordleAnswers.length)];
        answer = new Word(answerString);

        String[] validAnswers = WordleReader.readWordleStrings("valid_wordle.txt");
        answers = new ArrayList<String>();

        for(int i = 0; i < validAnswers.length; i++)
        {
            answers.add(validAnswers[i]);
        }
    }

    public Word getAnswer()
    {
        return answer;
    }

    public ArrayList<String> getAnswers()
    {
        return answers;
    }

    public int getGuess()
    {
        return guess;
    }

    public void setGuess(int guess)
    {
        this.guess = guess; 
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public boolean getGameOver()
    {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver)
    {
        this.isGameOver = isGameOver;
    }

    public boolean getIntro()
    {
        return isIntro;
    }

    public void setIntro(boolean isIntro)
    {
        this.isIntro = isIntro;
    }

    public boolean getCorrect()
    {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect)
    {
        this.isCorrect = isCorrect;
    }
}
