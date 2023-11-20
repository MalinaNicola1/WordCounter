package org.fasttrackit.business;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCounter {
    private String sentences;
    private final List<String> wordsList;

    public WordCounter(String senteces) {
        this.sentences = senteces;
        wordsList = wordsDivider();
    }

    public int wordsCount() { //metoda pentru a ne returna numarul total de cuvinte
        return wordsList.size();
    }

    public int charactersCount() { //metoda pentru a ne returna numarul total de caractere diferite de caracterele albe
        int sum = 0;
        for (String word : wordsList)
            sum = sum + word.length();
        return sum;
    }

    public int sentencesCount() { //metoda care ne returneaza numarul total de propozitii
        if (sentences.equals("") || wordsList.size()==0) return 0; //daca nu avem nimic in caseta text
        int sum = 1;
        boolean isPreviousDot = false;
        for (int i = 0; i < sentences.length(); i++) {
            if (sentences.toCharArray()[i] != ' ' && sentences.toCharArray()[i] != '\n' && sentences.toCharArray()[i] != '\t') {
                //In momentul de fata avem doar caractere diferite de spatiile albe
                if (isPreviousDot == true && sentences.toCharArray()[i] != '.' && sentences.toCharArray()[i] != '?' && sentences.toCharArray()[i] != '!') {
                    sum++;
                    isPreviousDot = false;
                } else if (sentences.toCharArray()[i] == '.' || sentences.toCharArray()[i] == '?' || sentences.toCharArray()[i] == '!')
                    isPreviousDot = true;
                else isPreviousDot = false;
            }
        }
        return sum;
    }

    public int paragraphsCount() { //metoda care returneaza numarul total de paragrafe
        if (sentences.equals("") || wordsList.size()==0) return 0; //daca nu avem nimic in caseta text
        int sum = 1;
        boolean isPreviousNewLine = false;
        for (int i = 0; i < sentences.length(); i++) {
            if (sentences.toCharArray()[i] != ' ' && sentences.toCharArray()[i] != '\t') {
                //In momentul de fata avem doar caractere diferite de spatiile albe
                if (isPreviousNewLine == true && sentences.toCharArray()[i] != '\n') {
                    sum++;
                    isPreviousNewLine = false;
                } else if (sentences.toCharArray()[i] == '\n')
                    isPreviousNewLine = true;
                else isPreviousNewLine = false;
            }
        }
        return sum;
    }

    public Map<String, Integer> top4Words() { //metoda care returneaza primele 4 cele mai frecvente cuvinte utilizate (returnat ca MAP)
        Map<String, Integer> wordCountMap = new HashMap<>();
        /*
        Metoda getOrDefault incearca sa gaseasca valoarea asociata cheii 'word'. Daca este gasita in map, returneaza valoarea
        corespunzatoarea (the current count of the word). Daca cheia nu este gasita, va returna o valoare default (care pentru noi este 0)
        Dupa ce obtinem valoarea curenta incrementam +1 deoarece am mai depistat din nou un cuvant din map
         */
        //Setez numarul de aparitii pentru fiecare cuvant
        for (String word : wordsList)
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);

        //Sortam cuvintele in functie de aparitia lor in ordine descrescatoare
        //Am creat o noua lista care sa contina toate map-urile sortate dupa numarul de aparitii
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(wordCountMap.entrySet());
        //a si b sunt 2 'Map.Entry' care sunt comparate in expresia lambda.
        sortedEntries.sort((a, b) -> b.getValue().compareTo((a.getValue())));

        //Alegem primele 4 cuvinte si le salvam intr-un nou Map
        Map<String, Integer> top4WordsList = new HashMap<>();
        int numWordsToAdd = Math.min(4, sortedEntries.size());
        for (int i = 0; i < numWordsToAdd; i++) {
            top4WordsList.put(sortedEntries.get(i).getKey(), sortedEntries.get(i).getValue());
        }
        return top4WordsList;
    }

    private List<String> wordsDivider() { //metoda care divide textul in cuvinte
        // \s inseamna orice caracter alb(spatiu, tab, new line, etc), iar + este folosit in cazul in care exista mai multe astfel de caractere
        Pattern pattern = Pattern.compile("(?<!-)\\s+|(?<=\\S)[.,!?](?=\\s|$)");
        sentences = sentences.toLowerCase();

        //Stream-uri
        return Arrays.stream(pattern.split(sentences)) //Mai intai se 'sparge' in cuvinte bazat pe pattern-ul precizat (adica fara spatii albe)
                .filter(str -> !str.isEmpty()) //metoda filter() este folosita pentru a elimina oricare string-uri goale
                .collect(Collectors.toList()); //se colecteaza toata cuvintele obtinute si se salveaza intr-o lista List<String>
    }
}