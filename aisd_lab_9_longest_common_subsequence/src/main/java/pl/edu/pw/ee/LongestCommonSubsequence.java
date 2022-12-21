package pl.edu.pw.ee;

class LongestCommonSubsequence {

    MatrixCell[][] matrix;
    char[] topStr;
    char[] leftStr;

    public LongestCommonSubsequence(String topStr, String leftStr) {
        if (topStr == null || leftStr == null) {
            throw new NullPointerException("Input strings can't be null!");
        }
        this.topStr = topStr.toCharArray();
        this.leftStr = leftStr.toCharArray();
        this.matrix = new MatrixCell[topStr.length() + 1][leftStr.length() + 1];
    }
    
    private void fillMatrix(){
        for (int i = 0; i < topStr.length + 1; i++) {
            matrix[i][0] = new MatrixCell(0);
        }
        for (int i = 0; i < leftStr.length + 1; i++) {
            matrix[0][i] = new MatrixCell(0);
        }
        for (int i = 1; i < topStr.length + 1; i++) {
            for (int j = 1; j < leftStr.length + 1; i++) {
                if (topStr[i - 1] == leftStr[j - 1]) {
                    matrix[i][j] = new MatrixCell(matrix[i - 1][j - 1].getValue(), 3);
                } else if (matrix[i][j - 1].getValue() >= matrix[i - 1][j].getValue()) {
                    matrix[i][j] = new MatrixCell(matrix[i][j - 1].getValue(), 1);
                } else {
                    matrix[i][j] = new MatrixCell(matrix[i - 1][j].getValue(), 2);
                }
            }
        }
    }

    public String findLCS() {
        fillMatrix();
        StringBuilder longestCommonSequence = new StringBuilder();
        return longestCommonSequence.toString();

    }

    public void display() {
        // TODO

    }

}
