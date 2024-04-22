package teak.events;

import java.awt.event.KeyEvent;
import java.awt.Graphics;
import teak.*;

public class KeystrokeEvent implements EventListener<KeyEvent> {
    private char[] word;

    public KeystrokeEvent()
    {
        word = new char[5];
    }

    @Override
    public void onEvent(KeyEvent c)
    {
        App app = App.getInstance();
        int position = app.getPosition();

        if(app.getIntro())
        {
            App.getInstance().setIntro(false);
            word = new char[5];
            AppGUI.getInstance().repaint();
            return;
        }

        if(app.getGameOver() || app.getCorrect())
        {
            if(Character.toLowerCase(c.getKeyChar()) == 'y')
            {
                EventSource startSrc = new EventSource();
                GameStartEvent startEvent = new GameStartEvent();
                startSrc.addListener(startEvent);

                TeakEvent<Graphics> event = new TeakEvent<Graphics>(AppGUI.getInstance().getGraphics());
                startSrc.fireEvent(event);
            }
            if(Character.toLowerCase(c.getKeyChar()) == 'n')
            {
                System.exit(0);
            }
        }

        if(c.getKeyCode() == KeyEvent.VK_BACK_SPACE && position != 0) 
        {
            app.setPosition(position - 1);
            word[position - 1] = ' ';
        }

        if(Character.isLetter(c.getKeyCode()))
        {
            if(position != 5)
                word[position] = Character.toUpperCase(c.getKeyChar());

            if(position != 5)
                app.setPosition(position + 1);
        }

        if(c.getKeyCode() == KeyEvent.VK_ENTER && position == 5)
        {
            EventSource submitSrc = new EventSource();
            SubmitEvent submitEvent = new SubmitEvent();

            submitSrc.addListener(submitEvent);

            TeakEvent<char[]> event = new TeakEvent<char[]>(word);
            submitSrc.fireEvent(event);
        }

        for(int i = 0; i < word.length; i++)
        {
            int guess = App.getInstance().getGuess();
            if(guess == 6) return;
            AppGUI.getInstance().updateLetter(i, guess, Position.INITIAL, word[i]);
        }
    }
}
