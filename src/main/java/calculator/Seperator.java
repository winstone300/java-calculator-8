package calculator;

import java.util.ArrayList;
import java.util.List;

public class Seperator {
    private static String PRE = "//";
    private static String POST = "\\n";
    private static ArrayList<Character> sepChars = new ArrayList<Character>(List.of(',', ':'));

    public int run(String sentence) {
        if (sentence.contains(PRE)) {
            if (sentence.startsWith(PRE) && sentence.contains(POST)) {
                String newsepChar = sentence.substring(sentence.indexOf(PRE) + PRE.length(), sentence.indexOf(POST));
                if (newsepChar.equals("")) {
                    throw new IllegalArgumentException("입력된 구분자가 없습니다.");
                }
                sepChars.add(newsepChar.charAt(0));
                return sepAdd(sentence.substring(sentence.indexOf(POST) + POST.length()));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return sepAdd(sentence);
    }

    public int sepAdd(String sentence) {
        int ans = 0;
        int nowNum = 0;

        for (int i = 0; i < sentence.length(); i++) {
            char temp = sentence.charAt(i);
            int tempInt;

            if (sepChars.contains(temp)) {
                ans += nowNum;
                nowNum = 0;
                continue;
            }
            if (!Character.isDigit(temp)) {
                throw new IllegalArgumentException("올바른 입력이 아닙니다.");
            }

            nowNum *= 10;
            tempInt = temp - '0';
            if (tempInt <= 0) {
                throw new IllegalArgumentException("입력된 수가 양수가 아닙니다.");
            }

            nowNum += tempInt;
        }
        ans += nowNum;

        return ans;
    }
}
