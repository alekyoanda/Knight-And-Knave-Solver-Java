package knightAndKnaveSolver.GUI;

import knightAndKnaveSolver.GUI.JCustomTemplate.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeGUI {
    public HomeGUI(JFrame frame){
        // Mengatur layout dari frame utama menjadi BoxLayout dengan orientasi vertikal
        JPanelTemplate mainPanel = new JPanelTemplate();
        mainPanel.setLayout(new GridLayout(2,1));
        // Membuat panel baru untuk menampung label
        JPanelTemplate panel1 = new JPanelTemplate();
        // Mengatur layout dari panel ini menjadi GridBagLayout agar label terletak di tengah panel
        panel1.setLayout(new GridBagLayout());
        // Menginstansiasi GridBagConstraints untuk mengatur layout komponen-komponen di dalam panel ini
        GridBagConstraints gbc = new GridBagConstraints();
        // Mengatur margin dari tiap komponen yang berada di panel ini
        gbc.insets = new Insets(10,0, 0, 0);
        // Membuat label
        JLabelTemplate welcomeLabel = new JLabelTemplate("Knight and Knave Solver", 24);
        // Mengatur posisi welcome Label menjadi urutan pertama dari atas
        gbc.gridy = 0;
        // Menambahkan label ke dalam panel
        panel1.add(welcomeLabel, gbc);
        // Membuat panel baru lagi untuk menampung button
        JPanelTemplate panel2 = new JPanelTemplate();
        // Mengatur layout dari panel ini menjadi GridBagLayout agar button terletak di tengah panel
        panel2.setLayout(new GridBagLayout());

        // Membuat login button
        JButtonTemplate startBtn = new JButtonTemplate("Start", 300, 40);
        // Mengatur posisi login button menjadi urutan pertama dari atas
        gbc.gridy = 0;
        // Menambahkan login button ke panel ini dan memasukkan GridBagConstraintsnya juga
        panel2.add(startBtn, gbc);
        // Membuat exit button
        JButtonTemplate exitBtn = new JButtonTemplate("Exit", 300, 40);
        // Mengatur posisi login button menjadi urutan pertama dari atas
        gbc.gridy = 1;
        // Menambahkan exit button ke panel ini dan memasukkan GridBagConstraintsnya juga
        panel2.add(exitBtn, gbc);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(mainPanel);
                new IndexGUI(frame);
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        // Menambahkan panel1 dan panel2 ke frame
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
