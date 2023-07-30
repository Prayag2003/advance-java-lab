package brickBreakerGame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

// panel in which we run the game
public class GamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false; // inital game is no started
    private int score = 0;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 2;
    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private Map map;

    public GamePlay(){
        map  = new Map(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g) {
        // backgrounnd
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // drawing the map
        map.draw((Graphics2D) g);
        // create the border
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        // scores
        g.setColor(Color.white);
        g.setFont(new Font("serif" , Font.BOLD ,25));
        g.drawString(""+score , 590,30);


        // create the pedal
        g.setColor(Color.green);
        g.fillRect(playerX, 550,100,8);

        // create the ball
        g.setColor(Color.yellow);
        g.fillOval(ballPosX, ballPosY,20,20);

        if(totalBricks <= 0)
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("You Won", 260,300);

            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 20));
            g.drawString("Press (Enter) to Restart", 230,350);
        }

        // GAME OVER
        if(ballPosY > 570)
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("Game Over, Scores: "+score, 190,300);

            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 20));
            g.drawString("Press (Enter) to Restart", 230,350);
        }
        g.dispose();
    }

    // adding the methods that KeyListener and ActionListener has
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
          // logic to move the ball
        if(play){
            // detecting the intersection of slider and ball
            if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550,
                    30, 8)))
            {
                ballYdir = -ballYdir;
                ballXdir = -2;
            }
            else if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8)))
            {
                ballYdir = -ballYdir;
                ballXdir = ballXdir + 1;
            }
            else if(new Rectangle(ballPosX, ballPosY, 20,
                    20).intersects(new Rectangle(playerX + 30, 550, 40, 8)))
            {
                ballYdir = -ballYdir;
            }

            // first map is the object
            // second map is the "map" created in the map.java class

           A: for(int i = 0 ; i < map.map.length; ++i){
                for(int j=0; j < map.map[0].length; ++j){
                    if(map.map[i][j] > 0){
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i*map.brickWidth + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX,brickY , brickWidth,brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY,20,20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0,i,j);
                            totalBricks -=1;
                            score += 5;

                            if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width ){
                                ballXdir = -ballXdir;
                            }
                            else{
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPosX += ballXdir;
            ballPosY += ballYdir;
            // left
            if(ballPosX < 0 ){
                ballXdir = -ballXdir;
            }
            // top
            if(ballPosY < 0 ){
                ballYdir = -ballYdir;
            }
            // right
            if(ballPosX > 670 ) {
                ballXdir = -ballXdir;
            }
        }


        repaint(); // recall the paint method and draw everything again
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // if the right key is detected
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }
            else{
                moveRight();
            }
        }
        // if the left key is pressed
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }
            else{
                moveLeft();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                ballPosX = 120;
                ballPosY = 350;
                ballXdir = -1;
                ballYdir = -2;

                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new Map(3,7);
                repaint();
            }
        }
    }
    public void moveRight(){
        play=true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
