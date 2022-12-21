package pl.edu.pw.ee;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LongestCommonSubsequenceTest {

    @Test(expected = NullPointerException.class)
    public void nullStringPassed() {
        //given
        String topStr = null;
        String leftStr = "Ala_ma_kota";

        //when
        LongestCommonSubsequence lsc = new LongestCommonSubsequence(topStr, leftStr);
        
        //then
        assert false;

    }
}
