package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import pl.edu.pw.ee.LongestCommonSubsequence;

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

    @Test
    public void findLCSForStringWithNoLCS() {
        //given
        String firstString = "ALA";
        String secondString = "KOT";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String longestCommonSequence = lcs.findLCS();

        //then
        String expected = "";
        assertEquals(expected, longestCommonSequence);
    }

    @Test
    public void findLCSForShortStrings() {
        //given
        String firstString = "dom";
        String secondString = "dach";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();

        //then
        String expected = "D";
        assertEquals(expected, result);
    }

    @Test
    public void findLCSForMediumLengthStrings() {
        //given
        String firstString = "mikołaj";
        String secondString = "ikona";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();

        //then
        String expected = "IKOA";
        assertEquals(expected, result);
    }

    @Test
    public void findLCSForLongStrings() {
        //given
        String firstString = "często_z_odkrywaniem";
        String secondString = "rzeczy_nie_trzeba\n_się_spieszyć";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();

        //then
        String expected = "CZ__RAIE";
        assertEquals(expected, result);
    }

    @Test
    public void findLCSForLongStringsWithCommonNewLineCharacter() {
        //given
        String firstString = "często_z_odkrywa\nniem";
        String secondString = "rzeczy_nie_trzeba\n_się_spieszyć";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();

        //then
        String expected = "CZ__RA\\nIE";
        assertEquals(expected, result);
    }

    @Test
    public void findLCSForTabCharacter() {
        //given
        String firstString = "Ala\t";
        String secondString = "tara\t";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();

        //then
        String expected = "AA\t";
        assertEquals(expected, result);
    }

    @Test
    public void findLCSForIdenticalStrings() {
        //given
        String firstString = "para";
        String secondString = "para";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();

        //then
        String expected = "PARA";
        assertEquals(expected, result);
    }

    @Test
    public void findLCSForDifferentSizeOfLetters() {
        //given
        String firstString = "ArGenTYnA";
        String secondString = "aRgENtyNa";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();

        //then
        String expected = "ARGENTYNA";
        assertEquals(expected, result);
    }

    @Test
    public void findLCSForThreeLCSs() {
        //given
        String firstString = "amloa";
        String secondString = "bmaol";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();

        //then
        String expected = "MA";
        assertEquals(expected, result);
    }

}
