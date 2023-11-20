package org.fasttrackit.GUI;

import org.fasttrackit.business.WordCounter;

import javax.swing.*;
import java.util.Map;

public class Controller {
    private String sentences; //date de intrare
    public JLabel[] labelList; //date de iesire

    public Controller(String sentences, JLabel[] labelList) {
        this.sentences = sentences;
        this.labelList = labelList;
        WordCounter wordCounter = new WordCounter(sentences);
        labelList[0].setText(String.valueOf(wordCounter.wordsCount()));
        labelList[1].setText(String.valueOf(wordCounter.charactersCount()));
        labelList[2].setText(String.valueOf(wordCounter.sentencesCount()));
        labelList[3].setText(String.valueOf(wordCounter.paragraphsCount()));

        Map<String, Integer> top4Words = wordCounter.top4Words();
        String[] wordList = new String[4];
        int[] countList = new int[4];
        int i = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Extrag din map si pun in lista de cuvinte (wordsList) respectiv in lista de aparitii (countList)
        for (Map.Entry<String, Integer> entry : top4Words.entrySet()) {
            if (i >= 4)  break;

            wordList[i] = entry.getKey();
            countList[i] = entry.getValue();
            i++;
        }

        //Realizez sortarea propriu zisa(interschimbare directa)
        for (i = 0; i < 3; i++) {
            for (int j = 0; j < 3 - i; j++) {
                if (countList[j] < countList[j + 1]) {
                    int tempCount = countList[j];
                    countList[j] = countList[j + 1];
                    countList[j + 1] = tempCount;

                    String tempWord = wordList[j];
                    wordList[j] = wordList[j + 1];
                    wordList[j + 1] = tempWord;
                }
            }
        }

        //Setez pentru label-urile de la "top 4 words used" valoarea acestora
        for (i = 0; i < 4; i++)
            if (wordList[i] == null) labelList[i + 4].setText("");
            else labelList[i + 4].setText(wordList[i] + ": " + countList[i]);
    }
}
