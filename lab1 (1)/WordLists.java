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
    private Comparator<String> backwardsComparator = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.substring(o1.length()-1).compareTo(o2.substring(o2.length()-1));
        }
    };

    public WordLists(String inputFileName) throws IOException {
        this.inputFileName = inputFileName;
        file = new File(inputFileName);
        System.out.println("Loaded: " + inputFileName);
        wordList = new ArrayList<>();
        wordSet = new TreeSet<>();
        makeWordListAndSet();

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
        for (int i = 0; i < s.length(); i++)
            result += s.substring(s.length() - (i + 1), s.length() - i);

        return result;
    }

    private void makeWordListAndSet() throws IOException {
        in = new BufferedReader(new FileReader(file));
        String word = "";
        while (in.ready()) {
            String s = getWord();
            wordList.add(s);
            wordSet.add(s);
        }
    }
    private void computeWordFrequencies() throws IOException {
        System.out.println("\n-------Alpha and Frequencis--------\n");
        freqMap = new HashMap<>();


        for (String s : wordList) {
            if (freqMap.containsKey(s)) {
                int i = freqMap.get(s);
                i++;
                freqMap.put(s, i);
            } else {
                freqMap.put(s, 1);
            }
        }
        PrintWriter printWriter = new PrintWriter("alfaSorted.txt");
        for (String s : wordSet) {
            printWriter.println(s);
        }
        printWriter.close();


    }

    private int getMaxFromMap() {
        int result = 0;
        for (String s : wordSet) {
            if (freqMap.get(s) > result)
                result = freqMap.get(s);
        }
        return result;
    }

    public class backwardsComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            o1 = reverse(o1); o2 = reverse(o2);
            if(o1.compareTo(o2)>0)
                return 1;
            else if (o1.compareTo(o2)==0)
                return 0;
            else
                return -1;
        }
    }

    private void computeFrequencyMap() throws IOException {

        System.out.println("\n-------Sorted by Frequency--------\n");

        for (int i:freqMap.values())
        {

        }

        TreeMap<String,Integer> sortMap = new TreeMap<String, Integer>();
        sortMap.comparator();

        //Getting max value
        int i = getMaxFromMap();
        int start = i;
        Set tempSet = new TreeSet();
        for (int j : freqMap.values()) {
            tempSet.add(j);
        }
        tempSet = ((TreeSet) tempSet).descendingSet();
        System.out.println(tempSet.toString());
        ;
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        while (i > 0) {
            ArrayList<String> tempList = new ArrayList<>();
            for (String s : wordSet) {
                if (freqMap.get(s) == i)
                    tempList.add(s);
            }
            if (!tempList.isEmpty())
                map.put(i, tempList);
            i--;
        }
        PrintWriter printWriter = new PrintWriter(new File("frequencySorted.txt"));
        while (start > 0) {
            if (map.containsKey(start)) {
                printWriter.println(start + ":");
                for (String s : map.get(start)) {
                    printWriter.println("    " + s);
                }
            }
            start--;
        }
        printWriter.close();
    }


    private void computeBackwardsOrder() throws IOException {
        System.out.println("\n-------Backwards--------\n");

        ArrayList<String> backWardList = new ArrayList<>();
        PrintWriter printWriter = new PrintWriter(new File("backwardsSorted.txt"));
        for (String s : wordSet) {
            backWardList.add(s);
        }

        Collections.sort(backWardList, new backwardsComparator());

        for (String s : backWardList) {
            printWriter.println(s);
        }
        printWriter.close();

    }

    public static void main(String[] args) throws IOException {
        WordLists wl = new WordLists(args[0]);  // arg[0] contains the input file name
        wl.computeWordFrequencies();
        wl.computeFrequencyMap();
        wl.computeBackwardsOrder();

        System.out.println("Finished!");
    }
}



















