package teak;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import teak.events.*;

public class AppGUI extends JComponent implements KeyListener {
    private JFrame frame;
    private WordleLetter[][] letterBoard;
    private EventSource keystrokeSrc;
    private KeystrokeEvent keystrokeEvent;

    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    public static final String TITLE = "Slordle";
    public static final Color BG_COLOR = new Color(18,18,19);
    public static final Color ABSENT_COLOR = new Color(58, 58, 60);
    public static final Color WRONGPOS_COLOR = new Color(181, 169, 59);
    public static final Color CORRECT_COLOR = new Color(83, 141, 78);
    public static final Color TEXT_COLOR = new Color(248, 248, 248);

    private static AppGUI instance;

    public static AppGUI getInstance()
    {
        if(instance == null) instance = new AppGUI();
        return instance;
    }

    public void init()
    {
        AppGUI.getInstance();

        keystrokeSrc = new EventSource();
        keystrokeEvent = new KeystrokeEvent();
        keystrokeSrc.addListener(keystrokeEvent);
    }

    public AppGUI()
    {
        letterBoard = new WordleLetter[5][6];
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                letterBoard[i][j] = new WordleLetter();
            }
        }

        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.getContentPane().add(this);
        frame.getContentPane().setBackground(BG_COLOR);
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        if(App.getInstance().getIntro())
        {
            EventSource gameStartSrc = new EventSource();
            GameStartEvent gameStartEvent = new GameStartEvent();
            gameStartSrc.addListener(gameStartEvent);

            TeakEvent<Graphics> event = new TeakEvent<Graphics>(g);
            gameStartSrc.fireEvent(event);

            return;
        }

        drawGame(g);
        
        if(App.getInstance().getGameOver() || App.getInstance().getCorrect())
        {
            EventSource gameOverSrc = new EventSource();
            GameOverEvent gameOverEvent = new GameOverEvent();
            gameOverSrc.addListener(gameOverEvent);
    
            TeakEvent<Graphics> event = new TeakEvent<Graphics>(g);
            gameOverSrc.fireEvent(event);
        }
    }

    private void drawGame(Graphics g)
    {
        g.setFont(new Font("Arial", Font.BOLD, 26));
        g.setColor(AppGUI.TEXT_COLOR);
        g.drawString(TITLE, 340, 40);

        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 6; j++)
            {
                drawWordleSquare(g, 115 + (110 * i), 75 + (110 * j), letterBoard[i][j]);
            }
        }
    }

    private void drawBorderedRect(Graphics g, int x, int y, Color color)
    {
        g.setColor(color);
        g.fillRect(x, y, 100, 100);
        g.setColor(AppGUI.BG_COLOR);
        g.fillRect(x + 2, y + 2, 100 - (2 * 2), 100 - (2 * 2));
    }

    private void drawWordleSquare(Graphics g, int x, int y, WordleLetter letter)
    {
        if(letter.pos == Position.INITIAL)
        {
            drawBorderedRect(g, x, y, ABSENT_COLOR);
        } else
        {
            Color c = BG_COLOR;
            if(letter.pos == Position.ABSENT) c = ABSENT_COLOR;
            if(letter.pos == Position.WRONGPOS) c = WRONGPOS_COLOR;
            if(letter.pos == Position.PRESENT) c = CORRECT_COLOR;
            g.setColor(c);
            g.fillRect(x, y, 100, 100);
        }
        
        g.setColor(TEXT_COLOR);
        g.setFont(new Font("Consolas", Font.BOLD, 80));
        if(Character.isLetter(letter.letter))
            g.drawString(Character.toString(letter.letter), x + 25, y + 75);

        repaint();
    }

    public void updateLetter(int x, int y, Position pos, char letter)
    {
        letterBoard[x][y].pos = pos;
        letterBoard[x][y].letter = letter;

        repaint();
    }

    public void keyPressed(KeyEvent e)
    {
        TeakEvent<KeyEvent> keyEvent = new TeakEvent<KeyEvent>(e);
        keystrokeSrc.fireEvent(keyEvent);
    }
  
    public void keyReleased(KeyEvent e)
    {
    }
  
    public void keyTyped(KeyEvent e)
    {
    }
}
