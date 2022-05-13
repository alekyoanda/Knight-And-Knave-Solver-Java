package knightAndKnaveSolver.GUI;

import javax.swing.*;

public class MainFrameGUI extends JFrame{
    public MainFrameGUI(){
        setTitle("Knight and Knave Solver");
        setSize(450, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new IndexGUI(this);
    }

}
