import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class ReadSearch {
    public static String[] readFromFile (String fileName) throws IOException {
        fileName = "Search.txt";
        String[] values = new String[6];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < values.length; i++) {
                values[i] = br.readLine();
                System.out.println(values[i]);
            }
            br.close();
            return values;
        }
    }
}


