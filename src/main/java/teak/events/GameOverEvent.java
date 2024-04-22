package teak.events;

import java.awt.*;

import teak.App;
import teak.AppGUI;

public class GameOverEvent implements EventListener<Graphics> {
    public void onEvent(Graphics g)
    {
        g.setColor(AppGUI.ABSENT_COLOR);
        g.fillRect(200, 200, 400, 400);
        g.setFont(new Font("Arial", Font.PLAIN, 24));

        if(App.getInstance().getCorrect())
        {
            g.setColor(AppGUI.TEXT_COLOR);
            g.drawString("Correct!", 350, 325);
        } else
        {
            g.setColor(AppGUI.TEXT_COLOR);
            g.drawString("WRONG!! The word was: " + App.getInstance().getAnswer(), 230, 375);
        }
    }
}
