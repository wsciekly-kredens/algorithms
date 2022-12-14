package pl.edu.pw.ee;

import main.java.pl.edu.pw.ee.HuffmanNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Huffman {

    HuffmanNode huffmanTree;

    public int huffman(String pathToRootDir, boolean compress) throws IOException {
        String text = readFile(pathToRootDir);
        HashMap<Character, Integer> numberOfOccurrences = countCharactersFrequency(text);
        System.out.println(numberOfOccurrences);
        this.huffmanTree = buildHuffmanTree(numberOfOccurrences);
        printTree(this.huffmanTree);
        HashMap<Character, String> codes = new HashMap<>();
        getHuffmanTreeCodes(this.huffmanTree, "", codes);
        System.out.println(codes);
        System.out.println(codeFileToString(text,codes));
        return -1; // przy dekompresji zwraca liczbnę znaków w pliku a przy kompresji ile jestbitów w wynikowym pliku
    }

    private String readFile(String pathToRootDir) throws IOException {
        Path pathToFile = Path.of(pathToRootDir);
        String text = Files.readString(pathToFile);
        return text;
    }

    private HashMap<Character, Integer> countCharactersFrequency(String text) {
        HashMap<Character, Integer> numberOfOccurrences = new HashMap<>();
        char[] characters = text.toCharArray();
        for (char ch : characters) {
            if (!numberOfOccurrences.containsKey(ch)) {
                numberOfOccurrences.put(ch, 1);
            } else {
                numberOfOccurrences.put(ch, numberOfOccurrences.get(ch) + 1);
            }
        }
        return numberOfOccurrences;
    }

    private HuffmanNode buildHuffmanTree(HashMap<Character, Integer> numOccurrences) {
        int mapSize = numOccurrences.size();
        PriorityQueue<HuffmanNode> lowestNode = new PriorityQueue<>(mapSize);
        for (char c : numOccurrences.keySet()) {
            lowestNode.add(new HuffmanNode(c, numOccurrences.get(c)));
        }
        HuffmanNode rootNode = null;
        while (lowestNode.size() > 1) {
            HuffmanNode n1 = lowestNode.poll();
            HuffmanNode n2 = lowestNode.poll();
            HuffmanNode indirectNode = new HuffmanNode('\'', n1.getNumberOfOccurrences() + n2.getNumberOfOccurrences());
            indirectNode.setAsLNotLeaf();
            indirectNode.setLeftNode(n1);
            indirectNode.setRightNode(n2);
            rootNode = indirectNode;
            lowestNode.add(indirectNode);
        }
        return rootNode;
    }

    public void printTree(HuffmanNode rootNode) {
        if (rootNode.getLeftNode() != null) {
            printTree(rootNode.getLeftNode());
        }
        System.out.println(rootNode);
        if (rootNode.getRightNode() != null) {
            printTree(rootNode.getRightNode());
        }
    }

    public HashMap<Character, String> getHuffmanTreeCodes(HuffmanNode currentNode, String currentCode, HashMap<Character, String> codes) {
        if (currentNode.getLeftNode() != null) {
            getHuffmanTreeCodes(currentNode.getLeftNode(), currentCode + "0", codes);
        }
        if (currentNode.getRightNode() != null) {
            getHuffmanTreeCodes(currentNode.getRightNode(), currentCode + "1", codes);
        }
        if (currentNode.isLeaf()) {
            codes.put(currentNode.getCharacter(), currentCode);
        }
        return codes;
    }
    
    
    private String codeFileToString(String text, HashMap<Character,String> codes){
        String codedString = new String();
        char[] characters = text.toCharArray();
        for(char character : characters){
            codedString += codes.get(character);
        }            
        return codedString;
    }
    
    private byte[] compressFile(String codedString){
        int codedStringLength = codedString.length();
        int extraBits = codedStringLength%8;
        byte[] compresedToByte = new byte[codedStringLength/8];
        for(int i = 0; i<codedStringLength; i+=8){
            String substringToBeConvertedToByte = codedString.substring(i,i+7);
        }
        return null;
    }
}
