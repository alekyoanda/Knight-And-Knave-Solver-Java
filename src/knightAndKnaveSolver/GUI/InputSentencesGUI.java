package knightAndKnaveSolver.GUI;

import knightAndKnaveSolver.GUI.JCustomTemplate.*;
import knightAndKnaveSolver.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InputSentencesGUI {
    int amountOfPeople;
    HashMap<String, String > peoplesName;

    ArrayList<JPanelTemplate> textFieldsPanels= new ArrayList<>();
    ArrayList<JLabelTemplate> textFieldsLabels= new ArrayList<>();
    ArrayList<JTextFieldTemplate> textFields= new ArrayList<>();

    HashMap<String, String> sentencesEachPerson = new HashMap<>();

    public InputSentencesGUI(JFrame frame, String amountOfPeople, HashMap<String, String> peoplesName){
        this.amountOfPeople = Integer.parseInt(amountOfPeople);
        this.peoplesName = peoplesName;

        JPanelTemplate mainPanel = new JPanelTemplate();
        mainPanel.setLayout(new GridLayout(this.amountOfPeople + 2, 1));

        JLabelTemplate title = new JLabelTemplate("Input Premis", 24);

        createTextFieldPanels();

        JPanelTemplate panelBtn = new JPanelTemplate();
        panelBtn.setLayout(new FlowLayout());
        JButtonTemplate solveBtn = new JButtonTemplate("Solve");
        JButtonTemplate instructionsBtn = new JButtonTemplate("Instruksi");
        JButtonTemplate homeBtn = new JButtonTemplate("Home");

        solveBtn.addActionListener(new ActionListener() {
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
                }
                if (isValid){
                    Model program = new Model();
                    HashMap<String , Boolean> result;
                    for (int i=0; i<Integer.parseInt(amountOfPeople); i++){
                        sentencesEachPerson.put(String.valueOf((char)(i+65)), textFields.get(i).getText());
                    }
                    System.out.println(sentencesEachPerson.toString());
                    result = Model.solveModel(sentencesEachPerson);

                    if (result == null){
                        message = "Tidak ada solusi / Input tidak valid";
                        JOptionPane.showMessageDialog(new JFrame(), message, "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        for (String key: result.keySet()){
                            if (result.get(key)){
                                message += String.format("<html>%s adalah Knight<br>", peoplesName.get(key));
                            }
                            else {
                                message += String.format("<html>%s adalah Knave<br>", peoplesName.get(key));
                            }
                        }
                        message += "</html>";
                        JOptionPane.showMessageDialog(new JFrame(), message, "Solusi", JOptionPane.INFORMATION_MESSAGE);;
                    }
                }
            }
        });

        instructionsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.remove(mainPanel);
                new InstructionsGUI(frame, amountOfPeople, peoplesName);
            }
        });

        homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.remove(mainPanel);
                new HomeGUI(frame);
            }
        });

        panelBtn.add(solveBtn);
        panelBtn.add(instructionsBtn);
        panelBtn.add(homeBtn);

        mainPanel.add(title);
        for (int i=0; i<this.amountOfPeople; i++){
            mainPanel.add(textFieldsPanels.get(i));
        }

        mainPanel.add(panelBtn);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createTextFieldPanels(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,0,0,0);
        for (int i=0; i<this.amountOfPeople; i++){
            textFieldsPanels.add(new JPanelTemplate());
            textFieldsPanels.get(i).setLayout(new GridBagLayout());

            textFieldsLabels.add(new JLabelTemplate(String.format("%s(%s) mengatakan: ", peoplesName.get(String.valueOf((char) (i+65))), (char) (i+65))));
            gbc.gridy = 0;
            textFieldsPanels.get(i).add(textFieldsLabels.get(i), gbc);

            textFields.add(new JTextFieldTemplate());
            gbc.gridy = 1;
            textFieldsPanels.get(i).add(textFields.get(i), gbc);
        }
    }
}
