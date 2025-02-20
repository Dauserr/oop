import java.io.*;
import java.util.regex.*;

public class SearchReplace {

    public String replaceTag(String tag, String targetTag, String replaceTag) {
        Pattern p = Pattern.compile(targetTag);
        Matcher m = p.matcher(tag);
        if (m.find()) {
            return m.replaceFirst(replaceTag);
        }
        return tag;
    }

    public String replaceAttribute(String tag, String attribute, String value) {
        Pattern p = Pattern.compile(attribute + "=\".*?\"");
        Matcher m = p.matcher(tag);
        if (m.find()) {
            return m.replaceFirst(attribute + "=\"" + value + "\"");
        }
        return tag;
    }

    public void processFile(String fileName, String targetTag, String replaceTag, String attribute, String value) {
        Pattern pattern1 = Pattern.compile("(<" + targetTag + ".*?>)(.*?)(</" + targetTag + ".*?>)");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                Matcher m = pattern1.matcher(line);

                if (m.find()) {
                    String newStart = replaceTag(m.group(1), targetTag, replaceTag);// <p> на <span>
                    newStart = replaceAttribute(newStart, attribute, value);//class="line" на class="sentence"
                    String newEnd = replaceTag(m.group(3), targetTag, replaceTag);// </p> на </span>
                    String newLine = newStart + m.group(2) + newEnd;// внутри не трогаем

                    System.out.printf("%3d %s\n", lineNumber, newLine);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SearchReplace searchReplace = new SearchReplace();
        searchReplace.processFile("gettys.html", "p", "span", "class", "sentence");
    }
}
