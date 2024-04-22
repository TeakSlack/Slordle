package teak.events;

import java.awt.event.KeyEvent;
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
            AppGUI.getInstance().repaint();
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
