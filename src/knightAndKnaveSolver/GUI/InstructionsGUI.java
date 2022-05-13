package knightAndKnaveSolver.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InstructionsGUI {
    public InstructionsGUI(JFrame frame, String amountOfPeople, ArrayList<String> peoplesName){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        JPanel panel1 = new JPanel();
        JLabel label1 =  new JLabel("Lorem ipsum");

        JPanel panel2 = new JPanel();
        JLabel label2 =  new JLabel("Lorem ipsum");

        JPanel panel3 = new JPanel();
        JLabel label3=  new JLabel("Lorem ipsum");

        JPanel panel4 = new JPanel();
        JButton btnNext =  new JButton("Next");
        JButton btnBack =  new JButton("Back");

        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.remove(mainPanel);
                new InputNameGUI(frame, amountOfPeople);
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.remove(mainPanel);
                new InputNameGUI(frame, amountOfPeople);
            }
        });

        panel1.add(label1);
        panel2.add(label2);
        panel3.add(label3);
        panel4.add(btnNext);
        panel4.add(btnBack);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
