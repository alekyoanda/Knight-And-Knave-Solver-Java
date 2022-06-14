package knightAndKnaveSolver.GUI;

import knightAndKnaveSolver.GUI.JCustomTemplate.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class InstructionsGUI {
    int amountOfPeople;
    HashMap<String, String> peoplesName;

    public InstructionsGUI(JFrame frame, String amountOfPeople, HashMap<String, String> peoplesName){
        this.amountOfPeople = Integer.parseInt(amountOfPeople);
        this.peoplesName = peoplesName;

        JPanelTemplate mainPanel = new JPanelTemplate();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.insets = new Insets(10,0,0,0);
        JLabelTemplate labelTitle = new JLabelTemplate("Instruksi", 24);
        gbcMain.gridy = 0;
        mainPanel.add(labelTitle, gbcMain);

        String eachPersonSymbol = "";
        for (String key: peoplesName.keySet()){
            eachPersonSymbol += String.format("<html>%s berkorespondensi dengan %s adalah knight<br>", key, peoplesName.get(key));
        }
        eachPersonSymbol += "</html>";

        JPanel keteranganPanel = new JPanelTemplate();
        keteranganPanel.setLayout(new FlowLayout());
        keteranganPanel.setBorder(new LineBorder(Color.BLACK, 1));

        JPanel keteranganOrangPanel = new JPanelTemplate();
        keteranganOrangPanel.setLayout(new GridBagLayout());
        JLabelTemplate labelInstructions =  new JLabelTemplate(eachPersonSymbol);
        gbcMain.insets = new Insets(5,10,5,10);
        gbcMain.gridy = 0;
        keteranganOrangPanel.add(labelInstructions, gbcMain);
        keteranganPanel.add(keteranganOrangPanel);

        JPanel keteranganSimbolPanel = new JPanelTemplate();
        keteranganSimbolPanel.setBorder(new LineBorder(Color.BLACK, 1));
        String simbolString = "<html> Simbol (&&) == DAN <br> Simbol (||) == ATAU <br> Simbol (=>) == IMPLIKASI " +
                "<br> Simbol (&lt=>) == BIIMPLIKASI <br> Simbol (') == NEGASI </html>";
        JLabelTemplate keteranganSimbol = new JLabelTemplate(simbolString);
        gbcMain.insets = new Insets(5,10,5,10);
        gbcMain.gridy = 0;
        keteranganSimbolPanel.add(keteranganSimbol, gbcMain);
        keteranganPanel.add(keteranganSimbolPanel);

        gbcMain.insets = new Insets(10,0,0,0);
        gbcMain.gridy = 1;
        mainPanel.add(keteranganPanel, gbcMain);

        JPanel contohSintaksPanel1 = new JPanelTemplate();
        contohSintaksPanel1.setBorder(new LineBorder(Color.BLACK, 1));

        String randomKey1 = getRandomKey();
        String randomKey2 = getRandomKey();
        String randomKey3 = getRandomKey();
        String contohSintaksStr = String.format("<html> Contoh 1: %s mengatakan bahwa \"Jika %s adalah Knave dan %s " +
                "<br> adalah Knight, maka %s adalah Knave\". <br> Pada textfield \"%s mengatakan\" isilah sintaks (%s'&&%s)=>%s' </html>",
                peoplesName.get("A"), peoplesName.get(randomKey1), peoplesName.get(randomKey2),
                peoplesName.get(randomKey3), peoplesName.get("A"), randomKey1, randomKey2, randomKey3);
        JLabelTemplate contohSintaks = new JLabelTemplate(contohSintaksStr);
        gbcMain.insets = new Insets(5,10,5,10);
        gbcMain.gridy = 0;
        contohSintaksPanel1.add(contohSintaks, gbcMain);

        gbcMain.insets = new Insets(10,0,0,0);
        gbcMain.gridy = 2;
        mainPanel.add(contohSintaksPanel1, gbcMain);

        JPanel contohSintaksPanel2 = new JPanelTemplate();
        contohSintaksPanel2.setBorder(new LineBorder(Color.BLACK, 1));

        randomKey1 = getRandomKey();
        randomKey2 = getRandomKey();
        String contohSintaksStr2 = String.format("<html> Contoh 2: %s mengatakan bahwa \"%s dan %s bertipe berbeda\"" +
                        "<br>(Artinya, jika %s knave, maka pasti %s juga knight, begitu juga sebaliknya)" +
                        " <br> Pada textfield \"%s mengatakan\" isilah sintaks %s'&lt=>%s </html>",
                peoplesName.get("A"), peoplesName.get(randomKey1), peoplesName.get(randomKey2),
                peoplesName.get(randomKey1), peoplesName.get(randomKey2) , peoplesName.get("A"), randomKey1, randomKey2);
        JLabelTemplate contohSintaks2 = new JLabelTemplate(contohSintaksStr2);
        gbcMain.insets = new Insets(5,10,5,10);
        gbcMain.gridy = 0;
        contohSintaksPanel2.add(contohSintaks2, gbcMain);

        gbcMain.insets = new Insets(10,0,0,0);
        gbcMain.gridy = 3;
        mainPanel.add(contohSintaksPanel2, gbcMain);

        JPanelTemplate panelBtn = new JPanelTemplate();
        JButtonTemplate btnNext =  new JButtonTemplate("Next");
        JButtonTemplate btnBack =  new JButtonTemplate("Back");
        panelBtn.add(btnNext);
        panelBtn.add(btnBack);

        gbcMain.gridy = 4;
        mainPanel.add(panelBtn, gbcMain);

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

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private String getRandomKey(){
        Random randomGenerator = new Random();
        Object[] values = peoplesName.keySet().toArray();
        String randomName = (String) values[randomGenerator.nextInt(values.length)];

        return randomName;
    }
}
