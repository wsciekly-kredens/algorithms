package pl.edu.pw.ee;

import main.java.pl.edu.pw.ee.Direction;

public class MatrixCell implements Comparable<MatrixCell> {
    //moge doimplementować to jako comparable
    private int value;
    private Direction direction;
    private boolean isPartOfLongestSequence;

    MatrixCell(int value) {
        this.value = value;
        this.direction = Direction.NONE;
    }

    MatrixCell(int value, Direction direction) {
        this.value = value;
        if (direction == null) {
            throw new NullPointerException("Direction can't be null!");
        } else {
            this.direction = direction;
        }
    }

    public void setAsPartOfLongestCommonSequence() {
        this.isPartOfLongestSequence = true;
    }

    public int getValue() {
        return this.value;
    }

    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public int compareTo(MatrixCell otherCell) {
        if (otherCell == null) {
            throw new NullPointerException("otherValue cant be null!");
        }
        int otherValue = otherCell.getValue();
        if (this.value > otherValue) {
            return 1;
        } else if (this.value == otherValue) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        if (this.isPartOfLongestSequence) {
            if (this.direction == Direction.ABOVE) {
                output.append("^");
            } else if (this.direction == Direction.LEFT) {
                output.append("<");
            } else {
                output.append("\\");
            }
        }
        output.append(this.value);
        return output.toString();
    }
}
