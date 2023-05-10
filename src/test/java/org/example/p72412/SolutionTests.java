package org.example.p72412;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setMaxLengthForSingleLineDescription;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SolutionTests {
    private final String[] info = new String[]{
            "java backend junior pizza 150",
            "python frontend senior chicken 210",
            "python frontend senior chicken 150",
            "cpp backend senior pizza 260",
            "java backend junior chicken 80",
            "python backend senior chicken 50"
    };

    @Test
    @DisplayName("python frontend senior chicken -> 150, 210")
    void t1() {
        Map<String, List<Integer>> scoreMap = new Solution().buildScoresMap(info);

        List<Integer> scores = scoreMap.get("python frontend senior chicken");

        assertThat(scores).containsExactly(150, 210);
    }

    @Test
    @DisplayName("java - - - -> 80, 150")
    void t2() {
        Map<String, List<Integer>> scoresMap = new Solution().buildScoresMap(info);

        List<Integer> scores = scoresMap.get("java - - -");

        assertThat(scores).containsExactly(80, 150);
    }

    @Test
    @DisplayName("python - - - -> 50, 150, 210")
    void t3() {
        Map<String, List<Integer>> scoresMap = new Solution().buildScoresMap(info);

        List<Integer> scores = scoresMap.get("python - - -");

        assertThat(scores).containsExactly(50, 150, 210);
    }

    @Test
    @DisplayName("- - senior - -> 50, 150, 210, 260")
    void t4() {
        Map<String, List<Integer>> scoresMap = new Solution().buildScoresMap(info);

        List<Integer> scores = scoresMap.get("- - senior -");

        assertThat(scores).containsExactly(50, 150, 210, 260);
    }
}
