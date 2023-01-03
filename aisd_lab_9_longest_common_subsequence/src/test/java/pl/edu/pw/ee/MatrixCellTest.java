package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import main.java.pl.edu.pw.ee.Direction;
import org.junit.Test;
import pl.edu.pw.ee.MatrixCell;

public class MatrixCellTest {

    @Test(expected = NullPointerException.class)
    public void compareToNullObjectTest() {
        //given
        MatrixCell cell = new MatrixCell(10);

        //when
        cell.compareTo(null);

        //then
        assert false;
    }

    @Test(expected = NullPointerException.class)
    public void compareToWithThisObjectEqualToNull() {
        //given
        MatrixCell nullCell = null;
        MatrixCell cell = new MatrixCell(10);

        //when
        nullCell.compareTo(cell);

        //then
        assert false;
    }

    @Test
    public void compareToWhileFirstCellGreaterThanSecond() {
        //given
        MatrixCell firstCell = new MatrixCell(10);
        MatrixCell secondCell = new MatrixCell(5);

        //when
        int compareToResult = firstCell.compareTo(secondCell);

        //then
        assertTrue(compareToResult > 0);
    }

    @Test
    public void compareToWhileCellsEqual() {
        //given
        MatrixCell firstCell = new MatrixCell(10);
        MatrixCell secondCell = new MatrixCell(10);

        //when
        int compareToResult = firstCell.compareTo(secondCell);

        //then
        assertEquals(0, compareToResult);
    }

    @Test
    public void compareToWhileFirstCellSmallerThanSecond() {
        //given
        MatrixCell firstCell = new MatrixCell(5);
        MatrixCell secondCell = new MatrixCell(10);

        //when
        int compareToResult = firstCell.compareTo(secondCell);

        //then
        assertTrue(compareToResult < 0);
    }

    @Test(expected = NullPointerException.class)
    public void nullObjectToStringTest() {
        //given
        MatrixCell cell = null;

        //when
        cell.toString();

        //then
        assert false;
    }

    @Test(expected = NullPointerException.class)
    public void directionEqualToNull() {
        //given
        Direction direction = null;

        //when
        MatrixCell cell = new MatrixCell(10, direction);

        //then
        assert false;
    }

    @Test
    public void toStringForNoneDirection() {
        //given
        MatrixCell cell = new MatrixCell(10, Direction.NONE);

        //when
        String result = cell.toString();

        //then
        String expected = "10";
        assertEquals(expected, result);
    }

    @Test
    public void toStringForAboveDirection() {
        //given
        MatrixCell cell = new MatrixCell(10, Direction.ABOVE);

        //when
        String result = cell.toString();

        //then
        String expected = "10";
        assertEquals(expected, result);
    }

    @Test
    public void toStringForLeftDirection() {
        //given
        MatrixCell cell = new MatrixCell(10, Direction.LEFT);

        //when
        String result = cell.toString();

        //then
        String expected = "10";
        assertEquals(expected, result);
    }

    @Test
    public void toStringForVerticalDirection() {
        //given
        MatrixCell cell = new MatrixCell(10, Direction.VERTICAL);

        //when
        String result = cell.toString();

        //then
        String expected = "10";
        assertEquals(expected, result);
    }

    @Test
    public void toStringForAboveDirectionWhilePartOfLongestCommonSequence() {
        //given
        MatrixCell cell = new MatrixCell(10, Direction.ABOVE);

        //when
        cell.setAsPartOfLongestCommonSequence();
        String result = cell.toString();

        //then
        String expected = "^10";
        assertEquals(expected, result);
    }

    @Test
    public void toStringForLeftDirectionWhilePartOfLongestCommonSequence() {
        //given
        MatrixCell cell = new MatrixCell(10, Direction.LEFT);

        //when
        cell.setAsPartOfLongestCommonSequence();
        String result = cell.toString();

        //then
        String expected = "<10";
        assertEquals(expected, result);
    }

    @Test
    public void toStringForVerticalDirectionWhilePartOfLongestCommonSequence() {
        //given
        MatrixCell cell = new MatrixCell(10, Direction.VERTICAL);

        //when
        cell.setAsPartOfLongestCommonSequence();
        String result = cell.toString();

        //then
        String expected = "\\10";
        assertEquals(expected, result);
    }
}
