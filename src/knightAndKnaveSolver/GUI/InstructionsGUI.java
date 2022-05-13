package knightAndKnaveSolver.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class InstructionsGUI {
    int amountOfPeople;

    public InstructionsGUI(JFrame frame, String amountOfPeople, HashMap<String, String> peoplesName){
        this.amountOfPeople = Integer.parseInt(amountOfPeople);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        JPanel panel1 = new JPanel();

        String eachPersonSymbol = "";
        for (String key: peoplesName.keySet()){
            eachPersonSymbol += String.format("<html>%s corresponds to %s<br>", key, peoplesName.get(key));
        }
        eachPersonSymbol += "</html>";
        JLabel label1 =  new JLabel(eachPersonSymbol);

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
                new InputSentencesGUI(frame, amountOfPeople, peoplesName);
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
