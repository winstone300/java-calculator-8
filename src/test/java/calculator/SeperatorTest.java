package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class SeperatorTest extends NsTest {

    private static final Seperator adder = new Seperator();

    @Test
    void 빈_문자열() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 쉼표() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 콜론() {
        assertSimpleTest(() -> {
            run("1:2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 쉼표_콜론() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 빈_문자열2() {
        assertSimpleTest(() -> {
            run("1,2,");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    void 커스텀_구분자() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");  // "//;\n1;2;3"
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자만_있는경우() {
        assertSimpleTest(() -> {
            run("//;\\n");       // "//;\n"
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 음수인_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 영인_경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("0,1,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자와_구분자_이외의_문자를_전달하는경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("a,1,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_시작문자가_없는경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(";\\n1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_종료문자가_없는경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자가_빈문자열인경우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
    

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}