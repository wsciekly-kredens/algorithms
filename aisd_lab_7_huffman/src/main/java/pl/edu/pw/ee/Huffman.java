package pl.edu.pw.ee;

import main.java.pl.edu.pw.ee.HuffmanNode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Huffman {

    public int huffman(String pathToRootDir, boolean compress) throws IOException {
        if (pathToRootDir == null) {
            throw new NullPointerException("Can't open file because null pathToDir given");
        }
        if (compress) {
            return compress(pathToRootDir);
        } else {
            return decompress(pathToRootDir);
        }
    }

    private int compress(String pathToRootDir) throws IOException {
        String text = readFile(pathToRootDir);
        HashMap<Character, Integer> numberOfOccurrences = countCharactersFrequency(text);
        HuffmanNode huffmanTree = buildHuffmanTree(numberOfOccurrences);
        HashMap<Character, String> codes = new HashMap<>();
        if (numberOfOccurrences.size() == 1) {
            getHuffmanTreeCodes(huffmanTree, "0", codes);
        } else {
            getHuffmanTreeCodes(huffmanTree, "", codes);
        }
        return saveFile(codeFileToString(text, codes), true, pathToRootDir) + saveHuffmanTreeIntoFile(codes, pathToRootDir);
    }

    private int decompress(String pathToRootDir) throws IOException {
        String codedText = readCompressedFile(pathToRootDir + "\\text");
        String codedTree = readCompressedFile(pathToRootDir + "\\tree");
        HashMap<String, Character> codes = decodeTree(codedTree);
        String decodedText = decodeTextUsingCodes(codedText, codes);
        saveDecodedFile(pathToRootDir, decodedText);
        return decodedText.length();
    }

    private String readFile(String pathToRootDir) throws IOException {
        Path pathToFile = Path.of(pathToRootDir + "\\index");
        String text = Files.readString(pathToFile);
        if (text.length() == 0) {
            throw new IllegalArgumentException("Can't compress empty file");
        }
        return text;
    }

    private HashMap<Character, Integer> countCharactersFrequency(String text) throws UnsupportedEncodingException {
        HashMap<Character, Integer> numberOfOccurrences = new HashMap<>();
        char[] characters = text.toCharArray();
        for (char ch : characters) {
            if ((int) ch > 255) {
                throw new UnsupportedEncodingException("Character bigger than 1 byte");
            }
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
        HuffmanNode rootNode = lowestNode.peek();
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
        if (rootNode == null) {
            throw new NullPointerException("Can't print tree because null rootNode given");
        }
        if (rootNode.getLeftNode() != null) {
            printTree(rootNode.getLeftNode());
        }
        System.out.println(rootNode);
        if (rootNode.getRightNode() != null) {
            printTree(rootNode.getRightNode());
        }
    }

    private HashMap<Character, String> getHuffmanTreeCodes(HuffmanNode currentNode, String currentCode, HashMap<Character, String> codes) {
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

    private String codeFileToString(String text, HashMap<Character, String> codes) {
        StringBuilder codedString = new StringBuilder();
        char[] characters = text.toCharArray();
        for (char character : characters) {
            codedString.append(codes.get(character));
        }
        return codedString.toString();
    }

    private int saveFile(String codedString, boolean isText, String pathToRootDir) {
        int codedStringLength = codedString.length();
        int extraBits = 0;
        if ((codedStringLength + 3) % 8 != 0) {
            extraBits = 8 - (codedStringLength + 3) % 8;
        }
        String numberOfZerosAtTheEnd = String.format("%" + 3 + "s", Integer.toBinaryString(extraBits)).replaceAll(" ", "0");
        String toBeConvertedToBytes = numberOfZerosAtTheEnd + codedString + getStringOfZeros(extraBits);
        byte[] compresedToByte = new byte[toBeConvertedToBytes.length() / 8];
        for (int i = 0; i <= codedStringLength; i += 8) {
            String substringToBeConvertedToByte = toBeConvertedToBytes.substring(i, i + 8);
            int twoComplimentValueOfEightBits = Integer.parseInt(substringToBeConvertedToByte, 2) - 128;
            compresedToByte[i / 8] = Byte.parseByte(String.valueOf(twoComplimentValueOfEightBits), 10);
        }
        if (isText) {
            writeByteArrayToFile(compresedToByte, pathToRootDir + "\\text");
        } else {
            writeByteArrayToFile(compresedToByte, pathToRootDir + "\\tree");
        }
        return compresedToByte.length * 8;
    }

    private void writeByteArrayToFile(byte[] data, String filename) {
        try (FileOutputStream outputStream = new FileOutputStream(filename)) {
            outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStringOfZeros(int length) {
        char[] arrayOfZeros = new char[length];
        Arrays.fill(arrayOfZeros, '0');
        return new String(arrayOfZeros);
    }

    private int saveHuffmanTreeIntoFile(HashMap<Character, String> codes, String pathToRootDir) {
        StringBuilder codedHuffmanTree = new StringBuilder();
        // pierwsze 3 bity to liczba wolnych bitów w pliku
        for (char c : codes.keySet()) {
            int codeLength = codes.get(c).length();
            for (int i = 0; i < codeLength - 1; i++) {
                codedHuffmanTree.append("0");
            }
            codedHuffmanTree.append("1");
            codedHuffmanTree.append(String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(' ', '0'));
            codedHuffmanTree.append(codes.get(c));
        }
        return saveFile(codedHuffmanTree.toString(), false, pathToRootDir);
    }

    private String readCompressedFile(String filename) throws IOException {
        byte[] codedBytes = Files.readAllBytes(Path.of(filename));
        StringBuilder binaryBytes = new StringBuilder();
        for (byte b : codedBytes) {
            int decimalValueOfBit = b + 128;
            binaryBytes.append(String.format("%8s", Integer.toBinaryString(decimalValueOfBit)).replaceAll(" ", "0"));
        }
        return binaryBytes.toString();
    }

    private HashMap<String, Character> decodeTree(String codedTree) {
        HashMap<String, Character> codes = new HashMap<>();
        int numberOfZerosAtTheEnd = Integer.parseInt(codedTree.substring(0, 3), 2);
        String treeCodesString = codedTree.substring(3, codedTree.length() - numberOfZerosAtTheEnd);
        char[] treeCodes = treeCodesString.toCharArray();
        int codeLength = 1;
        for (int i = 0; i < treeCodes.length; i++) {
            if (treeCodes[i] == '0') {
                codeLength += 1;
            } else {
                String pom = treeCodesString.substring(i + 1, i + 9);
                char character = (char) Integer.parseInt(pom, 2);
                String code = treeCodesString.substring(i + 9, i + 9 + codeLength);
                codes.put(code, character);
                i += 8 + codeLength;
                codeLength = 1;
            }
        }
        return codes;
    }

    private String decodeTextUsingCodes(String codedText, HashMap<String, Character> codes) {
        StringBuilder decodeText = new StringBuilder();
        int numberOfZerosAtTheEnd = Integer.parseInt(codedText.substring(0, 3), 2);
        String rawCodedText = codedText.substring(3, codedText.length() - numberOfZerosAtTheEnd);
        char[] rawCodedTextArray = rawCodedText.toCharArray();
        StringBuilder currentCode = new StringBuilder();
        for (char c : rawCodedTextArray) {
            currentCode.append(c);
            if (codes.containsKey(currentCode.toString())) {
                decodeText.append(codes.get(currentCode.toString()));
                currentCode.delete(0, currentCode.length());
            }
        }
        return decodeText.toString();
    }

    private void saveDecodedFile(String path, String text) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(path + "\\index"));
        writer.print(text);
        writer.close();
    }
}