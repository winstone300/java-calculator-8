package calculator;

import java.util.HashSet;
import java.util.Set;

public class Seperator {
    private static String CUSTOM_PREFIX = "//";
    private static String CUSTOM_SUFFIX = "\\n";
    private static final Set<Character> DEFAULT_SEPS = Set.of(',', ':');

    public int run(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        Set<Character> seps = new HashSet<>(DEFAULT_SEPS);
        String body = input;

        if (input.startsWith(CUSTOM_PREFIX)) {
            int nlIDX = input.indexOf(CUSTOM_SUFFIX);
            if (nlIDX < 0) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }
            String decl = input.substring(CUSTOM_PREFIX.length(), nlIDX);
            if (decl.isEmpty()) {
                throw new IllegalArgumentException("입력된 구분자가 없습니다.");
            }
            if (decl.length() != 1) {
                throw new IllegalArgumentException("올바르지 않은 구분자 입니다."); //구분자는 문자만 받음
            }
            seps.add(decl.charAt(0));
            body = body.substring(nlIDX + CUSTOM_SUFFIX.length());
        }

        return sum(body, seps);
    }

    public int sum(String sentence, Set<Character> seps) {
        int ans = 0;
        int nowNum = 0;
        boolean check = false;

        for (int i = 0; i < sentence.length(); i++) {
            char temp = sentence.charAt(i);

            if (seps.contains(temp)) {
                if (check) {
                    validate(nowNum);
                    ans += nowNum;
                    nowNum = 0;
                    check = false;
                }
                continue;
            }
            if (!Character.isDigit(temp)) {
                throw new IllegalArgumentException("올바른 입력이 아닙니다.");
            }

            nowNum = nowNum * 10 + (temp - '0');
            check = true;
        }
        ans += nowNum;

        return ans;
    }

    public void validate(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("입력된 수가 양수가 아닙니다.");
        }
    }
}
