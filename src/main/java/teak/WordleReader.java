package teak;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class WordleReader {

    public static String[] readWordleStrings(String fileName)
    {
        ArrayList<String> wordleStrings = new ArrayList<String>();
        try {
            Scanner inFile = new Scanner(new File(fileName));

            while(inFile.hasNextLine())
            {
                wordleStrings.add(inFile.nextLine());
            }

            inFile.close();
        } catch (Exception e) {
            System.err.println("Could not read file: " + e.getMessage());
        }

        String[] wordleArray = new String[wordleStrings.size()];

        for(int i = 0; i < wordleStrings.size(); i++)
            wordleArray[i] = wordleStrings.get(i);

        return wordleArray;
    }
}
