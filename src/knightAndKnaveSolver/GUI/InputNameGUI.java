package knightAndKnaveSolver.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InputNameGUI {
    int amountOfPeople;
    JLabel invalidMessage;

    ArrayList<JPanel> textFieldsPanels= new ArrayList<>();
    ArrayList<JLabel> textFieldsLabels= new ArrayList<>();
    ArrayList<JTextField> textFields= new ArrayList<>();

    ArrayList<String> peoplesName = new ArrayList<>();

    public InputNameGUI(JFrame frame, String amountOfPeople){
        this.amountOfPeople = Integer.parseInt(amountOfPeople);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(this.amountOfPeople + 2, 1));

        createTextFieldPanels();

        JPanel panelError = new JPanel();
        panelError.setLayout(new FlowLayout());
        invalidMessage = new JLabel();

        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout());
        JButton submitBtn = new JButton("Submit");
        JButton backBtn = new JButton("Back");

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                boolean isValid = true;
                for (int i=0; i<Integer.parseInt(amountOfPeople); i++){
                    if (textFields.get(i).getText().equals("")){
                        invalidMessage.setText("Textfield can't be empty!!");
                        isValid = false;
                        break;
                    }
                    else {
                        peoplesName.add(textFields.get(i).getText());
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

        addTextFieldPanels();
        panelError.add(invalidMessage);
        panelBtn.add(submitBtn);
        panelBtn.add(backBtn);

        for (int i=0; i<this.amountOfPeople; i++){
            mainPanel.add(textFieldsPanels.get(i));
        }
        mainPanel.add(panelError);
        mainPanel.add(panelBtn);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createTextFieldPanels(){
        HashMap<Integer, String> encodeNumber = new HashMap<>();
        encodeNumber.put(1, "First");
        encodeNumber.put(2, "Second");
        encodeNumber.put(3, "Third");
        encodeNumber.put(4, "Fourth");
        encodeNumber.put(5, "Fifth");
        for (int i=0; i<this.amountOfPeople; i++){
            textFieldsPanels.add(new JPanel());
            textFieldsPanels.get(i).setLayout(new FlowLayout());

            textFieldsLabels.add(new JLabel(String.format("%s person name: ", encodeNumber.get(i+1))));
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
