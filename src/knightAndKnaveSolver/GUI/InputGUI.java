package knightAndKnaveSolver.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputGUI {
    public InputGUI(JFrame frame, String amountOfPeople){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        JPanel panel1 = new JPanel(new FlowLayout());
        JLabel labelTitle =  new JLabel("Welcome to Knight and Knave Solver");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        JLabel amountOfPeopleLabel = new JLabel(String.format("Amount of People: %s", amountOfPeople));

        JPanel panel3 = new JPanel();
        panel2.setLayout(new FlowLayout());
        JButton backBtn = new JButton("Back");

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.remove(mainPanel);
                new IndexGUI(frame);
            }
        });

        panel1.add(labelTitle);
        panel2.add(amountOfPeopleLabel);
        panel3.add(backBtn);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
