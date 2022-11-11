package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;

import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;
import pl.edu.pw.ee.HashLinearProbing;

public class HashLinearProbingTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> unusedHash = new HashLinearProbing<>(initialSize);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
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
        HashTable<String> hash = new HashLinearProbing<>(5);
        String newElem = null;

        //when
        hash.put(newElem);

        //then
        assert false;
    }

    @Test
    public void should_CorrectlyResizeHash_WhenLoadFactorIsToBig() {
        //given
        HashTable<String> hash = new HashLinearProbing<>(5);
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
        HashTable<String> hash = new HashLinearProbing<>(5);
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
        HashTable<String> hash = new HashLinearProbing<>(5);

        //when
        hash.get(null);

        //then
        assert false;
    }

    @Test
    public void should_GetElemInHashAndReturnIt_WhenElementInHash() {
        //given
        HashTable<String> hash = new HashLinearProbing<>(5);
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
        HashTable<String> hash = new HashLinearProbing<>(5);

        //when
        Object elem = hash.get("ma");

        //then
        assertNull(elem);
    }

    @Test
    public void should_ReturnNull_WhenElementNotInHash() {
        //given
        HashTable<String> hash = new HashLinearProbing<>(5);
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
        HashTable<String> hash = new HashLinearProbing<>(5);

        //when
        hash.delete(null);

        //then
        assert false;
    }

    @Test
    public void should_DeleteElement_WhenInHash() {
        //given
        HashTable<String> hash = new HashLinearProbing<>(5);
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
        HashTable<String> hash = new HashLinearProbing<>(5);

        //when
        hash.delete("Geralt");

        //then
        assert true;
    }

    @Test
    public void should_RunContinuously_WhenDeleteNonExistingElementFromHash() {
        //given
        HashTable<String> hash = new HashLinearProbing<>(5);
        String newElems[] = {"Koziołek", "Matołek", "Pacanowo"};

        //when
        for (String newElem : newElems) {
            hash.put(newElem);
        }
        hash.delete("ma");

        //then
        assert true;

    }


    private int getNumOfElems(HashTable<?> hash) {
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
