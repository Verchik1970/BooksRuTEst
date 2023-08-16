import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class ReadSearch {
    public static String main() {

            String fileName = "Search.txt";
            String line;

            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    for (String value : values) {
                        System.out.print(value.toString());
                    }
                    System.out.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        return fileName;
    }
    }


