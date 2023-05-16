package org.example.p43163;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.MethodName.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SolutionTests {
    @Test
    @DisplayName("hit, cog, {hot,dot,dog,lot,log,cog} -> 4")
    void t1() {
        assertThat(
                new Solution().solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"})
        ).isEqualTo(4);
    }
}