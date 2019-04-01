// Author(s):
// Version: 
// Date:	

import java.io.*;
import java.util.*;

public class WordLists {
    private Reader in = null;
    private String inputFileName;
    private HashMap<String, Integer> freqMap;
    private File file;
    private ArrayList<String> wordList;
    private Set<String> wordSet;

    public WordLists(String inputFileName) throws IOException {
        this.inputFileName = inputFileName;
        file = new File(inputFileName);
        System.out.println("Loaded: " + inputFileName);

    }

    private boolean isPunctuationChar(char c) {
        final String punctChars = ",.:;?!";
        return punctChars.indexOf(c) != -1;
    }

    private String getWord() throws IOException {
        int state = 0;
        int i;
        String word = "";
        while (true) {
            i = in.read();
            char c = (char) i;
            switch (state) {
                case 0:
                    if (i == -1)
                        return null;
                    if (Character.isLetter(c)) {
                        word += Character.toLowerCase(c);
                        state = 1;
                    }
                    break;
                case 1:
                    if (i == -1 || isPunctuationChar(c) || Character.isWhitespace(c))
                        return word;
                    else if (Character.isLetter(c))
                        word += Character.toLowerCase(c);
                    else {
                        word = "";
                        state = 0;
                    }
            }
        }
    }

    private String reverse(String s) {
        String result = "";
        for(int i = 0; i<s.length();i++)
            result+=s.substring(s.length()-(i+1),s.length()-i);

        return result;
    }

    private void computeWordFrequencies() throws IOException {
        System.out.println("\n-------Alpha and Frequencis--------\n");
        freqMap = new HashMap<>();
        in = new BufferedReader(new FileReader(file));
        String word = "";
        wordList = new ArrayList<>();
        wordSet = new TreeSet<>();
        while (in.ready()) {
            String s = getWord();
            wordList.add(s);
            wordSet.add(s);
        }

        for (String s : wordList) {
            if (freqMap.containsKey(s)) {
                int i = freqMap.get(s);
                i++;
                freqMap.put(s, i);
            } else {
                freqMap.put(s, 1);
            }
        }
        FileWriter fw = new FileWriter(new File("alfaSorted.txt"));
        for (String s : wordSet) {
            fw.write(s + "\n");
           // System.out.println(s + "  " + " 	" + freqMap.get(s));
        }
        fw.close();


    }

    private int getMaxFromMap()
    {
        int result = 0;
        for (String s:wordSet) {
            if (freqMap.get(s)>result)
                result = freqMap.get(s);
        }
        return result;
    }

    private void computeFrequencyMap() throws IOException {

        System.out.println("\n-------Sorted by Frequency--------\n");

        //Getting max value
        int i = getMaxFromMap();
        int start = i;
        System.out.println(freqMap.values());
        Set tempSet = new TreeSet();
        for (int j:freqMap.values())
        {
            tempSet.add(j);
        }
        tempSet = ((TreeSet) tempSet).descendingSet();
        System.out.println(tempSet.toString());
                ;
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        while (i>0)
        {
            ArrayList<String> tempList = new ArrayList<>();
            for (String s : wordSet)
                {
                if (freqMap.get(s) == i)
                  tempList.add(s);
                }
            if(!tempList.isEmpty())
                map.put(i, tempList);
            i--;
        }
        FileWriter fw = new FileWriter(new File("frequencySorted.txt"));
        while (start>0)
        {
            if(map.containsKey(start)) {
                fw.write(start + ":" + "\n");
                for (String s : map.get(start)) {
                    fw.write("    " + s + "\n");
                }
            }
            start--;
        }
        fw.close();
    }


    private void computeBackwardsOrder() throws IOException {
        System.out.println("\n-------Backwards--------\n");
        ArrayList<String> backWardList = new ArrayList<>();
        FileWriter fw= new FileWriter(new File("backwardsSorted.txt"));
        for (String s:wordSet) {
            backWardList.add(reverse(s));
        }
        Collections.sort(backWardList);
        for (String s:backWardList) {
            fw.write(reverse(s) + "\n");
        }
        fw.close();

    }

    public static void main(String[] args) throws IOException {
        WordLists wl = new WordLists(args[0]);  // arg[0] contains the input file name
        wl.computeWordFrequencies();
        wl.computeFrequencyMap();
        wl.computeBackwardsOrder();

        System.out.println("Finished!");
    }
}



















