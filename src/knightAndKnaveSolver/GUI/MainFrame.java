package knightAndKnaveSolver.GUI;

import javax.swing.*;

public class MainFrame extends JFrame{
    public MainFrame(){
        setTitle("Knight and Knave Solver");
        setSize(450, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new IndexGUI(this);
    }

}
