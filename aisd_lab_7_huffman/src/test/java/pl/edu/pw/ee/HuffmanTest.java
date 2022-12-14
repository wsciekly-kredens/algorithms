package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.pw.ee.Huffman;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class HuffmanTest {

    @Test(expected = IOException.class)
    public void pathToNonExistingFileGiven() throws IOException {
        //given
        String path = new String("ala/ma/kota");
        Huffman huffman = new Huffman();

        //when
        huffman.huffman(path, false);

        //then
        assert false;
    }

    @Test
    public void readTest() throws IOException {
        //given
        Huffman huffman = new Huffman();

        //when
        huffman.huffman("C:\\Users\\kowalsm6\\Documents\\huffmanTreeTestText.txt", false);

        //then
        assert true;
    }

    @Test
    public void huffmanTreeBuildTest() throws IOException {
        //given
        String path = new String("C:\\Users\\kowalsm6\\Documents\\huffmanTreeTestText.txt");
        Huffman huffman = new Huffman();

        //when
        HashMap<Character, String> huffmanTreeCodes = new HashMap<>();
        huffman.huffman(path, false);
        huffman.getHuffmanTreeCodes(huffman.huffmanTree, "", huffmanTreeCodes);

        //then
        HashMap<Character, String> expected = new HashMap<>();
        expected.put('b', "0");
        expected.put('o', "10");
        expected.put('a', "110");
        expected.put(' ', "1110");
        expected.put('\n', "11110");
        expected.put('\r', "11111");
        Assert.assertEquals(expected, huffmanTreeCodes);
    }
    
    @Test
    public void huffmanCodingTest(){
        //given
        Huffman huffman = new Huffman();
    }

    @Test
    public void encodingTest() throws UnsupportedEncodingException {
        String ala = "Alą ma kotę    !";
        byte[] alakociara = ala.getBytes("UTF-8");
        for (byte x : alakociara) {
            System.out.println(x);
        }
        System.out.println(alakociara);
    }

}
