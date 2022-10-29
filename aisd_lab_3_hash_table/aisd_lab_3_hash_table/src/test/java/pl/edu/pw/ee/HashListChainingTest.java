package pl.edu.pw.ee;

import jdk.jfr.StackTrace;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashListChainingTest {
    
    @Test
    public void addingTest() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(7);

        //when
        hash.add(15);

        //then
        assertEquals(hash.getSize(), 1);
    }

    @Test
    public void addExsistingValueToHashSizeShouldStayTheSame() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(13);

        //when
        hash.add(15);
        hash.add(15);

        //then
        assertEquals(hash.getSize(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullToHashShouldThrowException() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(7);

        //when
        hash.add(null);

        //then
        assert false;
    }

    @Test
    public void getFromEmptyHashShouldReturnNil() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(7);

        //when
        Object value = hash.get(13);

        //then
        assertEquals(value, hash.getNil());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNullFromHashShouldThrowException() { //a teraz co na robić
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(13);

        //when
        Object value = hash.get(null);

        //then
        assert false;
    }

    @Test
    public void getNotExistingElementShouldReturnNil() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(7);

        //when
        hash.add(15);
        Object value = hash.get(13);

        //then
        assertEquals(value, hash.getNil());
    }

    @Test
    public void getTestReturnedValueShouldEqualsAdded() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(7);

        //when
        hash.add(15);
        Object value = hash.get(15);

        //then
        assertEquals(value, 15);
    }

    @Test
    public void delateNotExsistingValue() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(7);

        //when
        hash.add(15);
        hash.delate(17);

        //then
        assert true;
    }

    @Test
    public void delateFromEmptyHash() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(7);

        //when
        hash.delate(17);

        //then
        assert true;
    }

    @Test(expected = IllegalArgumentException.class)
    public void delateNullFromHashShouldThrowException() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(11);

        //when
        hash.delate(null);

        //then
        assert false;
    }

    @Test
    public void delateElementFromBeginingOfListSizeShouldBeZero() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(7);

        //when
        hash.add(15);
        hash.delate(15);
        hash.delate(15);

        //then
        assertEquals(hash.getSize(), 0);
    }

    @Test
    public void delateElementFromMiddleOfHash() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(7);

        //when
        hash.add(15);
        hash.add(22);
        hash.add(8);
        hash.delate(22);
        hash.delate(22);

        //then
        assertEquals(hash.getSize(), 2);
    }

    @Test
    public void delateElementFromEndOfList() {
        //given
        pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(7);

        //when
        hash.add(15);
        hash.add(22);
        hash.delate(15);
        hash.delate(15);

        //then
        assertEquals(hash.getSize(), 1);
    }
}
