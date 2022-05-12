package knightAndKnaveSolver.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexGUI {
    JTextField amountOfPeopleTextField;
    JButton submitBtn;

    public IndexGUI(JFrame frame) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        JPanel panel1 = new JPanel();
        JLabel labelTitle =  new JLabel("Welcome to Knight and Knave Solver");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        JLabel amountOfPeopleLabel = new JLabel("Amount of People: ");
        amountOfPeopleTextField = new JTextField();
        amountOfPeopleTextField.setColumns(10);

        JPanel panel3 = new JPanel();
        panel2.setLayout(new FlowLayout());
        submitBtn = new JButton("Submit");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.remove(mainPanel);
                new InputGUI(frame, amountOfPeopleTextField.getText());
            }
        });

        panel1.add(labelTitle);
        panel2.add(amountOfPeopleLabel);
        panel2.add(amountOfPeopleTextField);
        panel3.add(submitBtn);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

}
