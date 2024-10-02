import java.util.*;
import java.util.regex.Pattern;
import java.io.*;
import java.nio.file.*;

public class SpellChecker{
    public static void main(String[] args) throws FileNotFoundException, IOException {

        Scanner sc = new Scanner(System.in);
        String word;
        char[] wordArray;
        List<String> misspelled = new ArrayList<>();
        File dictionary = new File("en_GB-large.txt");
        Scanner readDict = new Scanner(dictionary);

        System.out.println("Enter your file name (with .txt): ");
        Scanner reader = new Scanner(new File(sc.nextLine()));
        

        while (reader.hasNextLine()){
            word = reader.next(); // gets the next word
            wordArray = word.toCharArray();
            word = "";
            for (int i = 0; i<wordArray.length; i++){// gets rid of punctuation except hyphens and apostraphes
                if (wordArray[i] != '-' && wordArray[i] != '\''&& Pattern.matches("\\p{Punct}", Character.toString(wordArray[i]))){ 
                    wordArray[i] = ' ';
                }
                word = word + wordArray[i];
            }
            word = word.strip();
            boolean found = false;
            readDict = new Scanner(dictionary);

            while (!found && readDict.hasNext()){
                if (readDict.next().toLowerCase().equals(word.toLowerCase())){
                    found = true;
                }
            }

            if (!found){
                misspelled.add(word);
            }
        }
        readDict.close();
        reader.close();

        if (!misspelled.isEmpty()){
            boolean k = false;
            System.out.println("The words misspelled are: ");
            System.out.print(Arrays.toString(misspelled.toArray()) + "\n");
            do{
                System.out.println("If there are any words you think should be correct enter it here or press Ctrl+c to exit: ");
                Files.write(Paths.get("en_GB-large.txt"), (System.lineSeparator() + sc.nextLine()).getBytes(), StandardOpenOption.APPEND);
                System.out.println("Added successfully!");
            } while (!k);
            
        }
        else{
            System.out.println("No errors!");
        }
        sc.close();
    }
}