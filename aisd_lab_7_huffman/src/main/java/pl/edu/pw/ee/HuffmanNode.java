package main.java.pl.edu.pw.ee;

public class HuffmanNode implements Comparable<HuffmanNode> {
    private char character;
    private int numberOfOccurrences;
    private HuffmanNode leftNode = null;
    private HuffmanNode rightNode = null;
    private boolean isLeaf = true;

    public HuffmanNode(char character, int numberOfOccurrences) {
        this.character = character;
        this.numberOfOccurrences = numberOfOccurrences;
    }

    public void setLeftNode(HuffmanNode leftNode) {
        if (leftNode == null) {
            throw new NullPointerException();
        }
        this.leftNode = leftNode;
    }

    public void setRightNode(HuffmanNode rightNode) {
        if (rightNode == null) {
            throw new NullPointerException();
        }
        this.rightNode = rightNode;
    }

    public void setAsLNotLeaf() {
        this.isLeaf = false;
    }

    public char getCharacter() {
        return character;
    }

    public int getNumberOfOccurrences() {
        return numberOfOccurrences;
    }

    public HuffmanNode getLeftNode() {
        return leftNode;
    }

    public HuffmanNode getRightNode() {
        return rightNode;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    @Override
    public String toString() {
        return this.character + ": " + this.numberOfOccurrences + " isLeaf: " + this.isLeaf;
    }


    @Override
    public int compareTo(HuffmanNode anotherNode) {
        if (anotherNode == null) {
            throw new NullPointerException("Can't get numberOfOccurrences because anotherNode is null");
        }
        if (this.numberOfOccurrences > anotherNode.numberOfOccurrences) {
            return 1;
        } else if (this.numberOfOccurrences == anotherNode.numberOfOccurrences) {
            return 0;
        } else {
            return -1;
        }
    }
}
