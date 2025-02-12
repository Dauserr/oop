import java.util.ArrayList;
import java.util.List;

public class StringSplitTest {
    public static void main(String[] args) {
        String[] shirts = {
                "S001,Black Polo Shirt,Black,XL",
                "S002,Black Polo Shirt,Black,L",
                "S003,Blue Polo Shirt,Blue,XL",
                "S004,Blue Polo Shirt,Blue,M",
                "S005,Tan Polo Shirt,Tan,XL",
                "S006,Black T-Shirt,Black,XL",
                "S007,White T-Shirt,White,XL",
                "S008,White T-Shirt,White,L",
                "S009,Green T-Shirt,Green,S",
                "S010,Orange T-Shirt,Orange,S",
                "S011,Maroon Polo Shirt,Maroon,S"
        };

        List<Shirt> shirtList = new ArrayList<>();

        for (String curLine : shirts) {
            String[] e = curLine.split(",");
            shirtList.add(new Shirt(e[0], e[1], e[2], e[3]));
        }

        System.out.println("=== Shirt List ===");
        for (Shirt shirt : shirtList) {
            System.out.println(shirt);
        }
    }
}

