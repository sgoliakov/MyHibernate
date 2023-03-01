import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\w{4,15}");
        Matcher matcher = pattern.matcher("tes");
        System.out.println(matcher.matches());
    }
}
