package brickBreakerGame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // creating a frame and setting up it's basic properties
         JFrame obj = new JFrame();
         GamePlay gp = new GamePlay();
         obj.setBounds(10,10,700,600);
         obj.setTitle("Brick-Breaker-Ball");
         obj.setResizable(false);
         obj.setVisible(true);
         obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         // adding the panel into the frame
        obj.add(gp);
    }
}