package calculator;

import java.util.ArrayList;
import java.util.List;

public class Seperator {
    private static  String pre = "//";
    private static String post = "\\n";
    private static ArrayList<Character> sepChars = new ArrayList<Character>(List.of(',',':'));

    public int run(String sentence){
        if(sentence.startsWith(pre)){
            String newsepChar = sentence.substring(sentence.indexOf(pre)+pre.length(),sentence.indexOf(post));
            if(newsepChar.equals("")) throw new IllegalArgumentException("입력된 구분자가 없습니다.");
            sepChars.add(newsepChar.charAt(0));
            return sepAdd(sentence.substring(sentence.indexOf(post)+post.length()));
        }
        return sepAdd(sentence);
    }

    public int sepAdd(String sentence){
        int ans = 0;
        int nowNum = 0;

        for(int i = 0; i < sentence.length(); i++){
            char temp = sentence.charAt(i);
            int tempInt;

            if(sepChars.contains(temp)){
                ans+=nowNum;
                nowNum = 0;
                continue;
            }

            nowNum*=10;
            tempInt = temp - '0';
            if(tempInt <= 0) throw new IllegalArgumentException("입력된 수가 양수가 아닙니다.");

            nowNum+=tempInt;
        }
        ans+=nowNum;

        return ans;
    }
}
