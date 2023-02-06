package com.isge.gsn.Quizz.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @ author Ulrich TRAORE version 1.0.
 */
@Component
public class Crypt {

    static ArrayList<String> listOffseted = new ArrayList<String>();

    private final static String alphabets[] = {"c", "d", "e", "f", "+", "g", "h", "i", "o", "p", "7", "8", "9", "q", "r",
            "s", "*", "t", "u", "v", "w", "x", "y", "z", "@", " ", "&", "4", "5", "6", "-", "a", "b", "A", "B", "C",
            "0", "1", "2", "D", "E", "F", "3", "G", "j", "k", "l", "m", "n", "H", "I", "J", "K", "L", "M", "N", "O",
            "U", "V", "W", "X", "Y", "Z", "é", "è", "à", "P", "Q", "R", "S", "T", "â", "ù", "ê", "ç"};

    private final static int lengthAlphabets = alphabets.length;


    public String encryption(@NotNull String message) {

        //Initialisation
        makeOffsetOfList();
        String wordEncoded = "";

        String wordSplitter[] = message.split("");

        int cpt = wordSplitter.length;


        for (int i = 0; i < cpt; i++) {
            for (int j = 0; j < lengthAlphabets; j++) {

                if (alphabets[j].contentEquals(wordSplitter[i])) {
                    //Use offset List
                    wordEncoded = wordEncoded + listOffseted.get(j);

                }
            }
        }
        return wordEncoded;
    }

    public void makeOffsetOfList() {
        listOffseted.clear();


        int offsetIndex = 0;
        int indexOutOfBounds = 0;

        //Make an offset of alphabets
        for (int i = 0; i < lengthAlphabets; i++) {
            offsetIndex = i + 7;
            if (offsetIndex >= lengthAlphabets) {
                listOffseted.add(alphabets[indexOutOfBounds]);
                indexOutOfBounds++;
            } else {
                listOffseted.add(alphabets[offsetIndex]);
            }
        }

    }


}
