package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

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
