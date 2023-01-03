package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import pl.edu.pw.ee.LongestCommonSubsequence;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
        String expected = "CZ__RA\nIE";
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
    public void findLCSForBackslashCharacter() {
        //given
        String firstString = "Wiedzmin\b";
        String secondString = "Yenefer\b";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();

        //then
        String expected = "EN\b";
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

    @Test
    public void findLCSForLCSLengthOFDoubleDigit() {
        //given
        String firstString = "abcdefghbbijk";
        String secondString = "abcdefghgijkl";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();

        //then
        String expected = "ABCDEFGHIJK";
        assertEquals(expected, result);
    }

    private ByteArrayOutputStream standardOutput = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(standardOutput));
    }

    @Test(expected = IllegalStateException.class)
    public void callingDisplayBeforeFindingLCSTest() {
        //given
        String firstString = "ALA";
        String secondString = "KOTA";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //then
        lcs.display();

        //then
        assert false;
    }

    @Test
    public void displayTestForTrivialExample() {
        //given
        String firstString = "mikołaj";
        String secondString = "ikona";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();
        lcs.display();

        //then
        int expectedLength = ((secondString.length() + 2) * 4 + 1) * ((firstString.length() + 2) * ((String.valueOf(result.length()).length() + 5)) + 2) - 1;
        assertEquals(expectedLength, standardOutput.toString().length());
    }

    @Test
    public void displayWithNewLineCharacterTest() {
        //given
        String firstString = "często_z_odkrywa\nniem";
        String secondString = "rzeczy_nie_trzeba\n_się_spieszyć";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();
        lcs.display();

        //then
        int expectedLength = ((secondString.length() + 2) * 4 + 1) * ((firstString.length() + 2) * ((String.valueOf(result.length()).length() + 5)) + 2) - 1;
        assertEquals(expectedLength, standardOutput.toString().length());
    }

    @Test
    public void displayWithBackspaceCharacter() {
        //given
        String firstString = "taras_w_bloku\b";
        String secondString = "kanapka_z_serem1";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();
        lcs.display();

        //then
        int expectedLength = ((secondString.length() + 2) * 4 + 1) * ((firstString.length() + 2) * ((String.valueOf(result.length()).length() + 5)) + 2) - 1;
        assertEquals(expectedLength, standardOutput.toString().length());
    }

    @Test
    public void displayTestForLongerWords() {
        //given
        String firstString = "Polska od pół wieku przedstawia widok z jednej strony tak ciągłego, niezmordowanego i niezbłaganego okrucieństwa tyranów, z drugiej tak nieograniczonego poświęcenia się ludu i tak uporczywej wytrwałości, jakich nie było przykładu od czasu prześladowania chrześcijaństwa. Zdaje się, że królowie mają przeczucie Herodowe o zjawieniu się nowego światła na ziemi i o bliskim swoim upadku, a lud coraz mocniej wierzy w swoje odrodzenie się i zmartwychwstanie.\n" +
                "Dzieje męczeńskiej Polski obejmują wiele pokoleń i niezliczone mnóstwo ofiar; krwawe sceny toczą się po wszystkich stronach ziemi naszej i po obcych krajach. — Poema, które dziś ogłaszamy, zawiera kilka drobnych rysów tego ogromnego obrazu, kilka wypadków z czasu prześladowania podniesionego przez Imperatora Aleksandra.";
        String secondString = "Około roku 1822 polityka Imperatora Aleksandra, przeciwna wszelkiej wolności, zaczęła się wyjaśniać, gruntować i pewny brać kierunek. W ten czas podniesiono na cały ród polski prześladowanie powszechne, które coraz stawało się gwałtowniejsze i krwawsze. Wystąpił na scenę pamiętny w naszych dziejach senator Nowosilcow. On pierwszy instynktową i zwierzęcą nienawiść rządu rosyjskiego ku Polakom wyrozumował jak zbawienną i polityczną, wziął ją za podstawę swoich działań, a za cel położył zniszczenie polskiej narodowości. W ten czas całą przestrzeń ziemi od Prosny aż do Dniepru i od Galicji do Bałtyckiego morza zamknięto i urządzono jako ogromne więzienie. Całą administracją nakręcono jako jedną wielką Polaków torturę, której koło obracali carewicz Konstanty i senator Nowosilcow.";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence(firstString, secondString);

        //when
        String result = lcs.findLCS();
        lcs.display();

        //then
        int expectedLength = ((secondString.length() + 2) * 4 + 1) * ((firstString.length() + 2) * ((String.valueOf(result.length()).length() + 5)) + 2) - 1;
        assertEquals(expectedLength, standardOutput.toString().length());
    }
}
