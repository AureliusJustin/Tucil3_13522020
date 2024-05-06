import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.HashSet;
import java.util.Scanner; // Import the Scanner class to read text files

class WordReader {
    public static HashSet<String> readDictionary(String path) {
        HashSet<String> hs = new HashSet<>();
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().stripTrailing();
                hs.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        return hs;
    }
}