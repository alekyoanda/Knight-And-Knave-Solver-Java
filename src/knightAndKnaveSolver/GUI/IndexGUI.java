package knightAndKnaveSolver.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexGUI {
    JTextField amountOfPeopleTextField;
    JLabel invalidMessage;
    JButton submitBtn;

    public IndexGUI(JFrame frame) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        JPanel panel1 = new JPanel();
        JLabel labelTitle =  new JLabel("Welcome to Knight and Knave Solver");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        JLabel amountOfPeopleLabel = new JLabel("Amount of People: ");
        amountOfPeopleTextField = new JTextField();
        amountOfPeopleTextField.setColumns(10);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        invalidMessage = new JLabel();
        invalidMessage.setForeground(Color.RED);

        JPanel panel4 = new JPanel();
        panel2.setLayout(new FlowLayout());
        submitBtn = new JButton("Submit");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (amountOfPeopleTextField.getText().equals("")){
                    invalidMessage.setText("Textfield can't be empty!!");
                }
                else if (!isNumeric(amountOfPeopleTextField.getText())){
                    invalidMessage.setText("Input must be an integer between 0 and 6!!");
                }
                else if (Integer.parseInt(amountOfPeopleTextField.getText()) <= 0 ||
                        Integer.parseInt(amountOfPeopleTextField.getText()) > 5){
                    invalidMessage.setText("Amount of people must be greater than 0 and less than 6!!");
                }
                else {
                    frame.remove(mainPanel);
                    new InputNameGUI(frame, amountOfPeopleTextField.getText());
                }
            }
        });

        panel1.add(labelTitle);
        panel2.add(amountOfPeopleLabel);
        panel2.add(amountOfPeopleTextField);
        panel3.add(invalidMessage);
        panel4.add(submitBtn);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
