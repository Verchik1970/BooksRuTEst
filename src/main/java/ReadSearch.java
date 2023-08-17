import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class ReadSearch {
    public static String[] readFromFile (String fileName) throws IOException {

        fileName = "Search.txt";
        String line;
        String[] values = new String[10];
        int i =0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null && i< 10) {
                values[i++] = br.readLine();}
            br.close();
            return values;
        }
    }
}


