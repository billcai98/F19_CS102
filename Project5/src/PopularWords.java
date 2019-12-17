import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class PopularWords {

    public static void main(String[] args) {

        // Input Interface
        // Try to open the parameter file
        File paramFile = new File ("src/parameter.txt");
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(paramFile);
        }
        catch (FileNotFoundException e1) {
            System.out.println("\n[ERROR]: Cannot find the file.");
        }

        // Read Lines
        String fileName     = "src/" + fileInput.nextLine();
        String outParam     = fileInput.nextLine().toLowerCase();
        String outSize_str  = fileInput.nextLine();

        // Special Case of Parameter 3
        int outSize;
        if (outSize_str.equals("")) {
            outSize = Integer.MAX_VALUE;
        } else {
            outSize = Integer.parseInt(outSize_str);
        }

        // Process and Output
        PopularityList pop = new PopularityList(readFile(fileName));    // Read Files
        pop.printMap(outParam, outSize);                                // print the popularity chart

    }


    /**
     * The method read the file and returns a frequency map of words
     */
    public static HashMap<String, Integer> readFile(String fileName) {

        // Utils
        HashMap<String, Integer> freqMap = new HashMap<String, Integer>();
        File myFile = new File (fileName);
        Scanner fileInput = null;

        // Try to open the file
        try {
            fileInput = new Scanner(myFile);
        }
        catch (FileNotFoundException e1) {
            System.out.println("\n[ERROR]: Cannot find the file.");
        }

        // Read Lines
        while (fileInput.hasNextLine()) {
            // Read the next line in the file, Split the line into an array
            String buffline = fileInput.nextLine();
            String[] data_line = buffline.split("[\\W\\_]{2,}|[\\W&&[^'-]]+|\\_{2,}");  // Split line into words

            // Put words into the frequency map
            addNew(freqMap, data_line);             // Add words of new line into the map
        }

        // Remove Overlaps (if any)
        if (freqMap.containsKey("")){ freqMap.remove(""); }

        return freqMap;
    }

    /**
     * This method add words in a new line into the frequency map
     */
    public static void addNew(HashMap<String, Integer> freqMap, String[] words) {

        // iterate over each word
        for (int i = 0; i < words.length; i++) {
            String word = words[i].toLowerCase();

            if (word.matches(".*[0-9].*")) { continue; }         // Check if the word is a number. If so, skip it.

            if (!freqMap.containsKey(word)) {           // Case 1: new word
                freqMap.put(word, 1);
            } else {                                    // Case 2: word already exists
                int current_freq = freqMap.get(word);
                freqMap.replace(word, current_freq+1);
            }
        }

        return;
    }

}

/***
 * Popularity List Class
 */
class PopularityList {

    // Member Variables
    private HashMap<String, Integer> map;

    // Constructor
    public PopularityList(HashMap<String, Integer> map) { this.map = map; }


    // ----- Accessor Methods -----
    public String[] getKeySet() {
        String[] keySet = new String[map.keySet().size()];
        int counter = 0;
        for(String key: map.keySet()) { keySet[counter++] = key; }
        return keySet;
    }

    public void printMap(String outParams, int outSize) {

        String[] sortedKeys = getSortedKeys(outParams);             // sort the keys
        if (outSize > sortedKeys.length) { outSize = sortedKeys.length; }
        for (int i = 0; i < outSize; i++) {                         // print the map
            String key = sortedKeys[i];
            System.out.println(key + " " + map.get(key));
        }

    }


    // ----- Private Methods -----
    private String[] getSortedKeys(String sortParams) {
        // Create a new key set
        String[] newKeys = getKeySet();

        switch (sortParams) {                           // sort the keys according to required comparators
            case "name": mergeSort(newKeys, new NameComparator());break;
            case "frequency": mergeSort(newKeys, new FreqComparator(map));break;
            case "scarcity": mergeSort(newKeys, new ScarcityComparator(map));break;
        }

        return newKeys;
    }

    /** Merge contents of arrays S1 and S2 into properly sized array S. */
    private void merge(String[] S1, String[] S2, String[] S, Comparator<String> comp) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && comp.compare(S1[i], S2[j]) < 0))
                S[i+j] = S1[i++];                     // copy ith element of S1 and increment i
            else
                S[i+j] = S2[j++];                     // copy jth element of S2 and increment j
        }
    }

    /** Merge-sort contents of array S. */
    private void mergeSort(String[] S, Comparator<String> comp) {
        int n = S.length;
        if (n < 2) return;                        // array is trivially sorted
        // divide
        int mid = n/2;
        String[] S1 = Arrays.copyOfRange(S, 0, mid);   // copy of first half
        String[] S2 = Arrays.copyOfRange(S, mid, n);   // copy of second half
        // conquer (with recursion)
        mergeSort(S1, comp);                      // sort copy of first half
        mergeSort(S2, comp);                      // sort copy of second half
        // merge results
        merge(S1, S2, S, comp);               // merge sorted halves back into original
    }


}


/***
 *  Comparator Classes : 1. Frequency 2.Scarcity 3.Name
 */
class FreqComparator implements Comparator<String> {

    private HashMap<String, Integer> compareMap;
    public FreqComparator (HashMap<String,Integer> map) { this.compareMap = map; }

    public int compare(String s1, String s2) {
        int result = compareMap.get(s1) - compareMap.get(s2);
        if (result ==  0){
            return s1.compareTo(s2);
        } else {
            return -result;
        }
    }
}

class ScarcityComparator implements Comparator<String> {

    private HashMap<String, Integer> compareMap;
    public ScarcityComparator (HashMap<String,Integer> map) { this.compareMap = map; }

    public int compare(String s1, String s2) {
        int result = compareMap.get(s1) - compareMap.get(s2);
        if (result ==  0){
            return s1.compareTo(s2);
        } else {
            return result;
        }
    }
}

class NameComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
}