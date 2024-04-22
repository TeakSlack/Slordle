package teak.events;

import java.awt.*;
import teak.*;

public class GameStartEvent implements EventListener<Graphics> {
    public void onEvent(Graphics g)
    {
        App app = App.getInstance();
        AppGUI gui = AppGUI.getInstance();

        String[] wordleAnswers = WordleReader.readWordleStrings("wordle.txt");
        String answerString = wordleAnswers[(int)(Math.random() * wordleAnswers.length)];
        Word answer = new Word(answerString);

        app.setGuess(0);
        app.setPosition(0);
        app.setGameOver(false);
        app.setCorrect(false);
        app.setIntro(true);
        app.setAnswer(answer);

        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 6; j++)
                gui.updateLetter(i, j, Position.INITIAL, ' ');
        }

        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.setColor(AppGUI.TEXT_COLOR);
        g.drawString(AppGUI.TITLE, 340, 40);

        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Guess the " + AppGUI.TITLE + " in 6 tries.", 50, 100);
        g.drawString("After each guess, the color of the tiles will change to show how", 50, 150);
        g.drawString("close your guess was to the word.", 50, 175);

        g.setColor(AppGUI.CORRECT_COLOR);
        g.fillRect(50, 200, 50, 50);
        g.setColor(AppGUI.TEXT_COLOR);
        g.drawString("Represents a letter in the correct place.", 125, 225);

        g.setColor(AppGUI.WRONGPOS_COLOR);
        g.fillRect(50, 275, 50, 50);
        g.setColor(AppGUI.TEXT_COLOR);
        g.drawString("Represents a letter in the wrong place.", 125, 300);

        g.setColor(AppGUI.ABSENT_COLOR);
        g.fillRect(50, 350, 50, 50);
        g.setColor(AppGUI.TEXT_COLOR);
        g.drawString("Represents a letter that is not in the word.", 125, 375);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("PRESS ANY KEY TO PLAY", 250, 475);

    }
}
