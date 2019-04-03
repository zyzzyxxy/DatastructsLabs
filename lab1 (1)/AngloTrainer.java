// Author(s):
// Email: 
// Date:	

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class AngloTrainer {
    private Random randomGenerator = new Random();
    private TreeSet dictTree = new TreeSet();
    private int longestWord;
    private String randomLetters;
    private int running;

    public AngloTrainer(String dictionaryFile) throws IOException {
        // ... define!
        loadDictionary(dictionaryFile);
        longestWord = sizeOfLongestWord();
        randomLetters = randomLetters(longestWord);
        System.out.println("Your letters are: " + randomLetters);
        //System.out.println(sort(randomLetters));
        runGame();

    }
    private void runGame()
    {
        Scanner sc = new Scanner(System.in);
        running = 1;
        String answer="";
        while (running ==1)
        {
            System.out.println("Enter your word: ");
            try {
                answer = sc.nextLine();

                running = validate3(answer);
                if(running==1)
                    System.out.println("Gratz! your word fits");
                else if(running == -1)
                {
                    System.out.println("You have used a latter too many times...");
                }

                else if(running == -2)
                {
                    System.out.println("Your suggestion was not found in the dictionary.\n" +
                            "I found: ");
                }
            }
            catch (Exception e)
            {
                running=-3;
            }

        }
        for (Object o:dictTree)
        {
            if(validate((String)o))
                System.out.println((String)o);
        }

    }

    private boolean validate(String answer)
    {
        /*1. Endast bokstäver i S får användas.
        2. Varje bokstavsförekomst i S får användas högst en gång i det bildade ordet. Finns det
        t.ex. två förekomster av bokstaven t så får det bildade ordet innehålla högst två t.
        */
        List<Character> chars = randomLetters.chars().mapToObj(e->(char)e).collect(Collectors.toList());
        char[] a = answer.toCharArray();
        for (char c:a)
        {
            if(chars.contains(c))
            {
                chars.remove(chars.indexOf(c));
            }
            else
                return false;
        }

        //3 word in dictionary
        if(!dictTree.contains(answer))
            return false;

        //We are certain that the word is valid.
        return true;
    }
    private boolean validate2(String answer)
    {
        if(includes(sort(randomLetters),sort(answer))&&dictTree.contains(answer))
            return true;
        return false;

    }
    private int validate3(String answer)
    {
        if(!includes(sort(randomLetters),sort(answer)))
            return -1;
        if(!dictTree.contains(answer))
            return -2;
        return 1;

    }

    private int sizeOfLongestWord() {
        int largest = 0;
        for (Object s : dictTree) {
            if (((String) s).length() > largest)
                largest = ((String) s).length();
        }
        return largest;
    }

    // use this to verify loadDictionary
    private void dumpDict() {
        // Print out the dictionary at the screen.
        // ... define!
        for (Object s : dictTree) {
            System.out.println((String) s);
        }
    }

    private String sort(String s)
    {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    private void loadDictionary(String fileName) throws FileNotFoundException {
        // Read the dictionary into a suitable container.
        // The file is a simple text file. One word per line.
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextLine())
            dictTree.add(sc.nextLine());
        System.out.println("Loaded " + dictTree.size() + " words from " + fileName);
    }

    private String randomLetters(int length) {
        // this makes vovels a little more likely
        String letters = "aabcdeefghiijklmnoopqrstuuvwxyyz";
        StringBuffer buf = new StringBuffer(length);
        for (int i = 0; i < length; i++)
            buf.append(letters.charAt(randomGenerator.nextInt(letters.length())));

        return buf.toString();
    }


    /* Def. includes
     * Let #(x,s) = the number of occurrences of the charcter x in the string s.
     * includes(a,b) holds iff for every character x in b, #(x,b) <= #(x,a)
     *
     * A neccessary precondition for includes is that both strings are sorted
     * in ascending order.
     */
    private boolean includes(String a, String b) {
        if (b == null || b.length() == 0)
            return true;
        else if (a == null || a.length() == 0)
            return false;

        //precondition: a.length() > 0 && b.length() > 0
        int i = 0, j = 0;
        while (j < b.length()) {
            if (i >= a.length() || b.charAt(j) < a.charAt(i))
                return false;
            else if (b.charAt(j) == a.charAt(i)) {
                i++;
                j++;
            } else if (b.charAt(j) > a.charAt(i))
                i++;
        }
        //postcondition: j == b.length()
        return true;
    }

    // This is just for demonstration purposes.
    private void testIncludes() {
        //                                            expected value
        System.out.println(includes("abc", ""));        //t
        System.out.println(includes("", "abc"));        //f
        System.out.println(includes("abc", "abc"));    //t
        System.out.println(includes("abc", "bcd"));    //f
        System.out.println(includes("abc", "a"));    //t
        System.out.println(includes("abc", "b"));    //t
        System.out.println(includes("abc", "c"));    //t
        System.out.println(includes("abc", "ab"));    //t
        System.out.println(includes("abc", "bc"));    //t
        System.out.println(includes("abc", "ac"));    //t
        System.out.println(includes("abc", "abcd"));    //f
        System.out.println(includes("abc", "abd"));    //f
        System.out.println(includes("abc", "d"));    //f
        System.out.println(includes("", ""));        //t
        System.out.println(includes("abc", "ca"));    //f
        System.out.println(includes("abc", "bac"));    //f
        System.out.println(includes("abc", "abbc"));    //f
        System.out.println(includes("abbc", "abc"));    //t
        System.out.println(includes(null, null));    //t
        System.out.println(includes(null, ""));        //t
        System.out.println(includes(null, "abc"));    //f
        System.out.println(includes("", null));        //t
        System.out.println(includes("abc", null));   //t
    }

    public static void main(String[] args) throws IOException {
        AngloTrainer at = new AngloTrainer(args[0]);
        // ... define!
    }
}












