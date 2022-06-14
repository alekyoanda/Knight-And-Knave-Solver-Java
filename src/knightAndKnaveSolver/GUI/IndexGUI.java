package knightAndKnaveSolver.GUI;

import knightAndKnaveSolver.GUI.JCustomTemplate.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexGUI {
    JTextFieldTemplate amountOfPeopleTextField;

    public IndexGUI(JFrame frame) {
        JPanelTemplate mainPanel = new JPanelTemplate();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3,0,0,0);
        // Membuat label baru, lalu menambahkannya ke dalam panel sesuai layout constraints
        JLabelTemplate inputIdLabel = new JLabelTemplate("Masukkan jumlah orang yang berinteraksi:");
        gbc.gridy = 0;
        mainPanel.add(inputIdLabel, gbc);
        // Membuat textfield baru, lalu menambahkannya ke dalam panel sesuai layout constraints
        amountOfPeopleTextField = new JTextFieldTemplate();
        gbc.gridy = 1;
        mainPanel.add(amountOfPeopleTextField, gbc);
        // Membuat button baru, lalu menambahkannya ke dalam panel sesuai layout constraints
        JButtonTemplate submitBtn = new JButtonTemplate("Submit");
        gbc.gridy = 2;
        mainPanel.add(submitBtn, gbc);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String message = "";
                if (amountOfPeopleTextField.getText().equals("")){
                    message = "Textfield tidak boleh kosong!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if (!isNumeric(amountOfPeopleTextField.getText())){
                    message = "Isi textfield harus berupa bilangan bulat (1 sampai 5)!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else if (Integer.parseInt(amountOfPeopleTextField.getText()) <= 0 ||
                        Integer.parseInt(amountOfPeopleTextField.getText()) > 5){
                    message = "Isi textfield harus berupa bilangan bulat (1 sampai 5)!";
                    JOptionPane.showMessageDialog(new JFrame(), message, "Warning", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    frame.remove(mainPanel);
                    new InputNameGUI(frame, amountOfPeopleTextField.getText());
                }
                amountOfPeopleTextField.setText("");
            }
        });
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
