package test.java.pl.edu.pw.ee;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

import pl.edu.pw.ee.HashQuadraticProbing;
import pl.edu.pw.ee.services.HashTable;


public class HashQuadraticProbingTest {
    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        pl.edu.pw.ee.services.HashTable<Double> unusedHash = new pl.edu.pw.ee.HashQuadraticProbing<>(.4, .3, initialSize);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        pl.edu.pw.ee.services.HashTable<String> emptyHash = new pl.edu.pw.ee.HashQuadraticProbing<>();
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenPuttingNull() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.4, .3, 5);
        String newElem = null;

        //when
        hash.put(newElem);

        //then
        assert false;
    }

    @Test
    public void should_CorrectlyResizeHash_WhenLoadFactorIsToBig() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.4, .3, 5);
        String newElems[] = {"Ala", "ma", "rudego", "kota", "Gacka"};

        //when
        int startSize = hash.getSize();
        for (String newElem : newElems) {
            hash.put(newElem);
        }
        int finalSize = hash.getSize();

        //then
        assertEquals(5, startSize);
        assertEquals(10, finalSize);

    }

    @Test
    public void should_CorrectlyPunItemsIntoHash_WhenResized() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.4, .3, 5);
        String newElems[] = {"oto", "nadchodzi", "czas", "miecza", "i", "topora"};

        //when
        for (String newElem : newElems) {
            hash.put(newElem);
        }
        int numberOfElemsAfterPut = getNumOfElems(hash);

        //then
        assertEquals(6, numberOfElemsAfterPut);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenGettingNull() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.4, .3, 5);

        //when
        hash.get(null);

        //then
        assert false;
    }

    @Test
    public void should_GetElemInHashAndReturnIt_WhenElementInHash() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.4, .3, 5);
        String newElems[] = {"Ala", "ma", "kota"};

        //when
        for (String newElem : newElems) {
            hash.put(newElem);
        }
        Object elem = hash.get("ma");

        //then
        assertEquals(newElems[1], elem);
    }

    @Test
    public void should_ReturnNull_WhenGettingFromEmptyHash() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.4, .3, 5);

        //when
        Object elem = hash.get("ma");

        //then
        assertNull(elem);
    }

    @Test
    public void should_ReturnNull_WhenElementNotInHash() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.4, .3, 5);
        String newElems[] = {"Ala", "ma", "kota"};

        //when
        for (String newElem : newElems) {
            hash.put(newElem);
        }
        Object elem = hash.get("Filemona");

        //then
        assertNull(elem);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenDeletingNull() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.4, .3, 5);

        //when
        hash.delete(null);

        //then
        assert false;
    }

    @Test
    public void should_DeleteElement_WhenInHash() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.5, .6, 5);
        String newElems[] = {"Ala", "ma", "kota"};

        //when
        for (String newElem : newElems) {
            hash.put(newElem);
        }
        Object elem = hash.get("ma");
        hash.delete("ma");
        Object delatedElem = hash.get("ma");

        //then
        assertEquals("ma", elem);
        assertNull(delatedElem);
    }

    @Test
    public void should_RunContinuously_WhenDeleteElementFromEmptyHash() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.3, .2, 5);

        //when
        hash.delete("Geralt");

        //then
        assert true;
    }

    @Test
    public void should_RunContinuously_WhenDeleteNonExistingElementFromHash() {
        //given
        pl.edu.pw.ee.services.HashTable<String> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.7, .5, 5);
        String newElems[] = {"Koziołek", "Matołek", "Pacanowo"};

        //when
        for (String newElem : newElems) {
            hash.put(newElem);
        }
        hash.delete("ma");

        //then
        assert true;

    }

    @Test
    public void should_GetValue_WhenPreviousValueWasDeleted() {
        //given
        pl.edu.pw.ee.services.HashTable<Integer> hash = new pl.edu.pw.ee.HashQuadraticProbing<>(.3, .7, 5);
        Integer newElems[] = {1, 6, 11};

        //when
        for (Integer newElem : newElems) {
            hash.put(newElem);
        }
        hash.delete(newElems[1]);
        Object elem = hash.get(11);

        //then
        assertEquals(newElems[2], elem);
    }


    private int getNumOfElems(pl.edu.pw.ee.services.HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}