package knightAndKnaveSolver.GUI;

import knightAndKnaveSolver.Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InputSentencesGUI {
    int amountOfPeople;
    JLabel outputMessage;

    ArrayList<JPanel> textFieldsPanels= new ArrayList<>();
    ArrayList<JLabel> textFieldsLabels= new ArrayList<>();
    ArrayList<JTextField> textFields= new ArrayList<>();

    HashMap<String, String> sentencesEachPerson = new HashMap<>();

    public InputSentencesGUI(JFrame frame, String amountOfPeople, HashMap<String, String> peoplesName){
        this.amountOfPeople = Integer.parseInt(amountOfPeople);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(this.amountOfPeople + 2, 1));

        createTextFieldPanels();

        JPanel panelOutput = new JPanel();
        panelOutput.setLayout(new FlowLayout());
        outputMessage = new JLabel();

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout());
        JButton solveBtn = new JButton("Solve");
        JButton instructionsBtn = new JButton("Instructions");
        JButton homeBtn = new JButton("Home");

        solveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Model program = new Model();
                HashMap<String , Boolean> result = null;
                for (int i=0; i<Integer.parseInt(amountOfPeople); i++){
                    sentencesEachPerson.put(String.valueOf((char)(i+65)), textFields.get(i).getText());
                }
                System.out.println(sentencesEachPerson.toString());
                result = Model.solveModel(sentencesEachPerson);


                if (result == null){
                    outputMessage.setText("No solution");
                }
                else{
                    String outputString = "";
                    for (String key: result.keySet()){
                        if (result.get(key)){
                            outputString += String.format("<html>%s is Knight<br>", peoplesName.get(key));
                        }
                        else {
                            outputString += String.format("<html>%s is Knave<br>", peoplesName.get(key));
                        }
                    }
                    outputString += "</html>";
                    outputMessage.setText(outputString);
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
                new IndexGUI(frame);
            }
        });

        addTextFieldPanels();
        panelOutput.add(outputMessage);
        panelBtn.add(solveBtn);
        panelBtn.add(instructionsBtn);
        panelBtn.add(homeBtn);

        for (int i=0; i<this.amountOfPeople; i++){
            mainPanel.add(textFieldsPanels.get(i));
        }

        mainPanel.add(panelOutput);
        mainPanel.add(panelBtn);

        frame.add(mainPanel);
        frame.setVisible(true);
    }



    private void createTextFieldPanels(){
        for (int i=0; i<this.amountOfPeople; i++){
            textFieldsPanels.add(new JPanel());
            textFieldsPanels.get(i).setLayout(new FlowLayout());

            textFieldsLabels.add(new JLabel(String.format("%s said: ", (char) (i+65))));
            textFields.add(new JTextField());
            textFields.get(i).setColumns(10);
        }
    }

    private void addTextFieldPanels(){
        for (int i=0; i<this.amountOfPeople; i++){
            textFieldsPanels.get(i).add(textFieldsLabels.get(i));
            textFieldsPanels.get(i).add(textFields.get(i));
        }
    }
}
