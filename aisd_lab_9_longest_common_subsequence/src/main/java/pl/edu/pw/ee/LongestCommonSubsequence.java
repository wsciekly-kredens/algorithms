package pl.edu.pw.ee;

import main.java.pl.edu.pw.ee.Direction;
import pl.edu.pw.ee.MatrixCell;

class LongestCommonSubsequence {

    MatrixCell[][] matrix;
    char[] topStr;
    char[] leftStr;

    public LongestCommonSubsequence(String topStr, String leftStr) {
        if (topStr == null || leftStr == null) {
            throw new NullPointerException("Input strings can't be null!");
        }
        this.topStr = topStr.toUpperCase().toCharArray();
        this.leftStr = leftStr.toUpperCase().toCharArray();
        this.matrix = new MatrixCell[leftStr.length() + 1][topStr.length() + 1];
    }

    private void fillMatrix() {
        for (int i = 0; i < leftStr.length + 1; i++) {
            matrix[i][0] = new MatrixCell(0);
        }
        for (int i = 0; i < topStr.length + 1; i++) {
            matrix[0][i] = new MatrixCell(0);
        }
        for (int column = 1; column < topStr.length + 1; column++) {
            for (int row = 1; row < leftStr.length + 1; row++) {
                if (leftStr[row - 1] == topStr[column - 1]) {
                    matrix[row][column] = new MatrixCell(matrix[row - 1][column - 1].getValue() + 1, Direction.VERTICAL);
                } else if (matrix[row][column - 1].compareTo(matrix[row - 1][column]) > 0) {
                    matrix[row][column] = new MatrixCell(matrix[row][column - 1].getValue(), Direction.LEFT);
                } else {
                    matrix[row][column] = new MatrixCell(matrix[row - 1][column].getValue(), Direction.ABOVE);
                }
            }
        }
    }

    public String findLCS() {
        fillMatrix();
        StringBuilder longestCommonSequence = new StringBuilder();
        int column = topStr.length;
        int row = leftStr.length;
        MatrixCell nextCell = matrix[row][column];
        while (nextCell.getDirection() != Direction.NONE) {
            if (matrix[row][column].getValue() != 0) {
                matrix[row][column].setAsPartOfLongestCommonSequence();
            }
            switch (nextCell.getDirection()) {
                case LEFT:
                    column--;
                    break;
                case ABOVE:
                    row--;
                    break;
                case VERTICAL:
                    if (topStr[column - 1] == ('\n')) {
                        longestCommonSequence.append("n\\");
                    } else {
                        longestCommonSequence.append(topStr[column - 1]);
                    }
                    row--;
                    column--;
                    break;
            }
            nextCell = matrix[row][column];
        }
        return longestCommonSequence.reverse().toString();
    }

    public void display() {
        int numberOfDigitsInBiggestNumber = String.valueOf(matrix[leftStr.length][topStr.length].getValue()).length();
        int columnWidth = numberOfDigitsInBiggestNumber + 4;
        String rowSeparator = getRowSeparator(columnWidth);
        String tableContour = getTableContour(columnWidth);
        System.out.print(rowSeparator + tableContour + "|" + centerString(columnWidth, "") + "|" + centerString(columnWidth, "") + "|");
        for (int i = 0; i < topStr.length; i++) {
            System.out.print(centerString(columnWidth, "" + topStr[i]) + "|");
        }
        System.out.print(tableContour + rowSeparator);
        for (int row = 0; row < leftStr.length + 1; row++) {
            System.out.print(tableContour);
            if (row > 0) {
                System.out.print("|" + centerString(columnWidth, "" + leftStr[row - 1]) + "|");
            } else {
                System.out.print("|" + centerString(columnWidth, "") + "|");
            }
            for (int column = 0; column < topStr.length + 1; column++) {
                System.out.print(centerString(columnWidth, matrix[row][column].toString()) + "|");
            }
            System.out.print(tableContour + rowSeparator);
        }
    }

    private String centerString(int width, String toBeCentred) {
        StringBuilder centredString = new StringBuilder();
        if (toBeCentred.equals("\n")) {
            toBeCentred = "\\n";
        }
        int rightPadding = 2;
        int leftPadding = width - toBeCentred.length() - 2;

        centredString.append(" ".repeat(leftPadding));
        centredString.append(toBeCentred);
        centredString.append(" ".repeat(rightPadding));
        return centredString.toString();
    }

    private String getRowSeparator(int columnWidth) {
        StringBuilder rowSeparator = new StringBuilder();
        rowSeparator.append("+");
        for (int i = 0; i < topStr.length + 2; i++) {
            rowSeparator.append("-".repeat(columnWidth));
            rowSeparator.append("+");
        }
        return rowSeparator.toString();
    }

    private String getTableContour(int columnWidth) {
        StringBuilder tableContour = new StringBuilder();
        tableContour.append("\n|");
        for (int i = 0; i < topStr.length + 2; i++) {
            tableContour.append(" ".repeat(columnWidth));
            tableContour.append("|");
        }
        tableContour.append("\n");
        return tableContour.toString();
    }
}
