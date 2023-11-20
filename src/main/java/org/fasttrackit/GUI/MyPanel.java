package org.fasttrackit.GUI;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private JFrame frame;
    private JPanel leftPanel, rightPanel, topPanel;
    private JLabel titleLabel, descriptionLabel, wordsLabel, charactersLabel, sentencesLabel, paragraphsLabel, top4Label;
    public JLabel[] labelList;
    private JTextArea text;
    private final Color beige = new Color(238, 169, 144);

    public MyPanel() {
        frame = new JFrame("Word Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        topPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();

        //TOP PANEL -> contine titul si niste text descriptiv
        JPanel auxiliar1 = new JPanel();
        auxiliar1.setBackground(beige);
        topPanel.setBackground(beige);
        topPanel.setPreferredSize(new Dimension(80, 80)); //setam dimensiunea panel-ului de sus

        titleLabel = new JLabel("                     Word Counter");
        titleLabel.setFont(new Font("HFUniversity", Font.PLAIN, 35));
        auxiliar1.add(titleLabel); //adaug titlul sa faca parte din label

        descriptionLabel = new JLabel("A Word Counter is a tool that counts the number of words and characters in your text.");
        descriptionLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        auxiliar1.add(descriptionLabel);
        auxiliar1.setLayout(new BoxLayout(auxiliar1, BoxLayout.PAGE_AXIS));

        topPanel.add(auxiliar1);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); //le centram
        frame.add(topPanel, BorderLayout.NORTH);

        //LEFT PANEL -> contine zona de scris text
        text = new JTextArea();
        /*
        Primul parametru se refera la zona la care sa fie aplicat scroll-ul
        Urmatorii parametrii se refera la cand sa apara
         */
        JScrollPane sp = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(sp);
        sp.setPreferredSize(new Dimension(310, 308));
        text.setFont(new Font("Rockwell", Font.PLAIN, 15));

        leftPanel.add(sp);
        leftPanel.setBackground(beige);
        leftPanel.setPreferredSize(new Dimension(340, 308));
        frame.add(leftPanel, BorderLayout.LINE_START); //asta imi pune panel-ul in partea stanga

        //RIGHT PANEL -> contine doar label-uri cu rezultatele
        labelList = new JLabel[8];
        for (int i = 0; i < 8; i++)
            labelList[i] = new JLabel();

        rightPanel.setBackground(beige);
        JPanel p1 = new JPanel();
        p1.setBackground(beige);
        labelList[0].setFont(new Font("JMH Typewriter", Font.BOLD, 15));
        wordsLabel = new JLabel("Words: ");
        wordsLabel.setFont(new Font("JMH Typewriter", Font.BOLD, 15));
        p1.add(wordsLabel);
        p1.add(labelList[0]);

        JPanel p2 = new JPanel();
        p2.setBackground(beige);
        labelList[1].setFont(new Font("JMH Typewriter", Font.BOLD, 15));
        charactersLabel = new JLabel("Characters: ");
        charactersLabel.setFont(new Font("JMH Typewriter", Font.BOLD, 15));
        p2.add(charactersLabel);
        p2.add(labelList[1]);

        JPanel p3 = new JPanel();
        p3.setBackground(beige);
        labelList[2].setFont(new Font("JMH Typewriter", Font.BOLD, 15));
        sentencesLabel = new JLabel("Sentences: ");
        sentencesLabel.setFont(new Font("JMH Typewriter", Font.BOLD, 15));
        p3.add(sentencesLabel);
        p3.add(labelList[2]);

        JPanel p4 = new JPanel();
        p4.setBackground(beige);
        labelList[3].setFont(new Font("Nirmala UI", Font.BOLD, 15));
        paragraphsLabel = new JLabel("Paragraphs: ");
        paragraphsLabel.setFont(new Font("Nirmala UI", Font.BOLD, 15));
        p4.add(paragraphsLabel);
        p4.add(labelList[3]);

        JPanel p5 = new JPanel();
        p5.setBackground(beige);
        top4Label = new JLabel("Top 4 words used: ");
        top4Label.setFont(new Font("Impact", Font.PLAIN, 17));
        p5.add(top4Label);

        JPanel p6 = new JPanel();
        p6.setBackground(beige);
        labelList[4].setFont(new Font("JMH Typewriter", Font.BOLD, 15));
        p6.add(labelList[4]);

        JPanel p7 = new JPanel();
        p7.setBackground(beige);
        labelList[5].setFont(new Font("JMH Typewriter", Font.BOLD, 15));
        p7.add(labelList[5]);

        JPanel p8 = new JPanel();
        p8.setBackground(beige);
        labelList[6].setFont(new Font("JMH Typewriter", Font.BOLD, 15));
        p8.add(labelList[6]);

        JPanel p9 = new JPanel();
        p9.setBackground(beige);
        labelList[7].setFont(new Font("JMH Typewriter", Font.BOLD, 15));
        p9.add(labelList[7]);

        rightPanel.add(p1);rightPanel.add(p2);rightPanel.add(p3);rightPanel.add(p4);
        rightPanel.add(p5);rightPanel.add(p6);rightPanel.add(p7);rightPanel.add(p8); rightPanel.add(p9);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        frame.add(rightPanel, BorderLayout.CENTER);

        frame.setSize(700, 450);
        frame.setVisible(true);

        while (true) {
            new Controller(text.getText(), labelList);
        }
    }
}
