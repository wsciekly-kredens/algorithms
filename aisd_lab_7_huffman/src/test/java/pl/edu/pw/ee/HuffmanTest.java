package pl.edu.pw.ee;

import main.java.pl.edu.pw.ee.HuffmanNode;
import org.junit.Assert;
import org.junit.Test;
import pl.edu.pw.ee.Huffman;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class HuffmanTest {

    @Test(expected = NullPointerException.class)
    public void setLeftNodeNullProvided() {
        //given
        HuffmanNode huffmanNode = new HuffmanNode('a', 1);

        //when
        huffmanNode.setLeftNode(null);

        //then
        assert false;
    }

    @Test(expected = NullPointerException.class)
    public void setRightNodeNullProvided() {
        //given
        HuffmanNode huffmanNode = new HuffmanNode('a', 1);

        //when
        huffmanNode.setRightNode(null);

        //then
        assert false;
    }

    @Test(expected = NullPointerException.class)
    public void huffmanNodeCompareToNullProvided() {
        //given
        HuffmanNode huffmanNode = new HuffmanNode('a', 1);

        //when
        huffmanNode.compareTo(null);

        //then
        assert false;
    }

    @Test(expected = IOException.class)
    public void pathToNonExistingFileGiven() throws IOException {
        //given
        String path = "ala/ma/kota";
        Huffman huffman = new Huffman();

        //when
        huffman.huffman(path, false);

        //then
        assert false;
    }

    @Test(expected = NullPointerException.class)
    public void nullPathGiven() throws IOException {
        //given
        Huffman huffman = new Huffman();
        String path = null;

        //when
        huffman.huffman(path, true);

        //then
        assert false;
    }

    @Test
    public void readTest() throws IOException {
        //given
        Huffman huffman = new Huffman();

        //when
        huffman.huffman("aisd_lab_7_huffman/src/test/java/pl/edu/pw/ee/randomFileTest", true);

        //then
        assert true;
    }

    @Test
    public void randomFileEncodingAndDecodingTest() throws IOException {
        //given
        Huffman huffman = new Huffman();
        String path = "aisd_lab_7_huffman/src/test/java/pl/edu/pw/ee/randomFileTest";

        //when
        huffman.huffman(path, true);
        int charactersInDecodedFile = huffman.huffman(path, false);

        //then
        assertEquals(58, charactersInDecodedFile);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyFileGiven() throws IOException {
        //given
        Huffman huffman = new Huffman();
        String path = "aisd_lab_7_huffman/src/test/java/pl/edu/pw/ee/emptyFileTest";

        //when
        huffman.huffman(path, true);

        //then
        assert false;
    }

    @Test
    public void oneCharacterFileTest() throws IOException {
        //given
        String path = "aisd_lab_7_huffman/src/test/java/pl/edu/pw/ee/oneCharacterFile";
        Huffman huffman = new Huffman();

        //when
        huffman.huffman(path, true);
        int charactersInDecompressedFile = huffman.huffman(path, false);

        //then
        assertEquals(1, charactersInDecompressedFile);
    }

    @Test(expected = UnsupportedEncodingException.class)
    public void fileWithPolishCharactersProvided() throws IOException {
        //given
        Huffman huffman = new Huffman();
        String path = "aisd_lab_7_huffman/src/test/java/pl/edu/pw/ee/polishCharactersInFileTest";

        //when
        huffman.huffman(path, true);

        //then
        assert false;
    }

    @Test
    public void fileWithOnlyNewLineCharactersGiven() throws IOException {
        //given
        Huffman huffman = new Huffman();
        String path = "aisd_lab_7_huffman/src/test/java/pl/edu/pw/ee/onlyNewLineCharactersInFile";

        //when
        huffman.huffman(path, true);
        int charactersInCompressFile = huffman.huffman(path, false);

        //then
        assertEquals(34, charactersInCompressFile);
    }

    @Test
    public void biggerFileTest() throws IOException {
        //given
        Huffman huffman = new Huffman();
        String path = "aisd_lab_7_huffman/src/test/java/pl/edu/pw/ee/biggerFileTest";

        //when
        huffman.huffman(path, true);
        int charactersInCompressFile = huffman.huffman(path, false);

        //then
        assertEquals(4102, charactersInCompressFile);
    }

    @Test
    public void fileWithFiveThousandWordsGivenTest() throws IOException {
        //given
        Huffman huffman = new Huffman();
        String path = "aisd_lab_7_huffman/src/test/java/pl/edu/pw/ee/FiveThousandWordsFile";

        //when
        huffman.huffman(path, true);
        int charactersInCompressFile = huffman.huffman(path, false);

        //then
        assertEquals(33683, charactersInCompressFile);
    }

    @Test
    public void fileWithFiftyThousandWordsGivenTest() throws IOException {
        //given
        Huffman huffman = new Huffman();
        String path = "aisd_lab_7_huffman/src/test/java/pl/edu/pw/ee/FiftyThousandWordsTest";

        //when
        huffman.huffman(path, true);
        int charactersInCompressFile = huffman.huffman(path, false);

        //then
        assertEquals(295119, charactersInCompressFile);
    }

    @Test(expected = NullPointerException.class)
    public void printTreeWithProvidedNull() {
        //given
        Huffman huffman = new Huffman();

        //when
        huffman.printTree(null);

        //then
        assert false;
    }

}
