package calculator;

import java.util.ArrayList;
import java.util.List;

public class Seperator {
    private static  String pre = "//";
    private static String post = "\n";
    private static ArrayList<Character> sepChars = new ArrayList<Character>(List.of(',',';'));

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

            nowNum+=tempInt;
        }
        ans+=nowNum;

        return ans;
    }
}
