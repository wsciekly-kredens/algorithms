package pl.edu.pw.ee;

public class MatrixCell{
    //moge doimplementować to jako comparable
    private int value;
    private int direction;
    private boolean isPartOfLongestSequence;

    MatrixCell(int value) {
        this.value = value;
    }

    MatrixCell(int value, int direction) {
        this.value = value;
        this.direction = direction;
    }

    public void setAsPartOfLongestCommonSequence() {
        this.isPartOfLongestSequence = true;
    }
    
    public int getValue(){
        return value;
    }
}
