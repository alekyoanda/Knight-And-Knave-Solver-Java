package knightAndKnaveSolver.GUI;

import knightAndKnaveSolver.GUI.JCustomTemplate.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InputNameGUI {
    int amountOfPeople;

    ArrayList<JPanelTemplate> textFieldsPanels= new ArrayList<>();
    ArrayList<JLabelTemplate> textFieldsLabels= new ArrayList<>();
    ArrayList<JTextFieldTemplate> textFields= new ArrayList<>();

    HashMap<String, String> peoplesName = new HashMap<>();

    public InputNameGUI(JFrame frame, String amountOfPeople){
        this.amountOfPeople = Integer.parseInt(amountOfPeople);

        JPanelTemplate mainPanel = new JPanelTemplate();
        mainPanel.setLayout(new GridLayout(this.amountOfPeople + 2, 1));

        JLabelTemplate title = new JLabelTemplate("Input Nama", 24);

        createTextFieldPanels();

        JPanelTemplate panelBtn = new JPanelTemplate();
        panelBtn.setLayout(new FlowLayout());
        JButtonTemplate submitBtn = new JButtonTemplate("Submit");
        JButtonTemplate backBtn = new JButtonTemplate("Back");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                boolean isValid = true;
                String message = "";
                for (int i=0; i<Integer.parseInt(amountOfPeople); i++){
                    if (textFields.get(i).getText().equals("")){
                        message = "Textfield tidak boleh kosong!";
                        JOptionPane.showMessageDialog(new JFrame(), message, "Warning", JOptionPane.WARNING_MESSAGE);
                        isValid = false;
                        break;
                    }
                    else {
                        peoplesName.put(String.valueOf((char)(i + 65)), textFields.get(i).getText());
                    }
                }
                if (isValid){
                    frame.remove(mainPanel);
                    new InstructionsGUI(frame, amountOfPeople, peoplesName);
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.remove(mainPanel);
                new IndexGUI(frame);
            }
        });

        panelBtn.add(submitBtn);
        panelBtn.add(backBtn);

        mainPanel.add(title);
        for (int i=0; i<this.amountOfPeople; i++){
            mainPanel.add(textFieldsPanels.get(i));
        }
        mainPanel.add(panelBtn);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createTextFieldPanels(){
        HashMap<Integer, String> encodeNumber = new HashMap<>();
        encodeNumber.put(1, "Pertama");
        encodeNumber.put(2, "Kedua");
        encodeNumber.put(3, "Ketiga");
        encodeNumber.put(4, "Keempat");
        encodeNumber.put(5, "Kelima");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,0,0,0);
        for (int i=0; i<this.amountOfPeople; i++){
            textFieldsPanels.add(new JPanelTemplate());
            textFieldsPanels.get(i).setLayout(new GridBagLayout());

            textFieldsLabels.add(new JLabelTemplate(String.format("Nama orang %s: ", encodeNumber.get(i+1))));
            gbc.gridy = 0;
            textFieldsPanels.get(i).add(textFieldsLabels.get(i), gbc);

            textFields.add(new JTextFieldTemplate());
            gbc.gridy = 1;
            textFieldsPanels.get(i).add(textFields.get(i), gbc);
        }
    }
}
