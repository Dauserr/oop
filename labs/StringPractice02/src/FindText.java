import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindText {
    private Pattern pattern;
    private Matcher m;

    public void searchFile(String fileName, String regex) {
        pattern = Pattern.compile(regex);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {// line присвается текущая строка
                lineNumber++;
                m = pattern.matcher(line);
                if (m.find()) {
                    System.out.println(lineNumber + " " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        FindText finder = new FindText();


        finder.searchFile("gettys.html", "<h4>");
        finder.searchFile("gettys.html", "\\bto\\b");
        finder.searchFile("gettys.html", "^\\s{4}");
        finder.searchFile("gettys.html", "^<[p|d]");
        finder.searchFile("gettys.html", "^</.*?>$");
    }
}
