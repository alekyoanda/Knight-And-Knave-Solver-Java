package knightAndKnaveSolver.GUI;

import javax.swing.*;

public class MainFrameGUI extends JFrame{
    public MainFrameGUI(){
        setTitle("Knight and Knave Solver");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new HomeGUI(this);
    }

}
