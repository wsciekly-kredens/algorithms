package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;
import main.java.pl.edu.pw.ee.SelectionSort;

public class SelectionSortTest {

    private static final double EPS = 0;
    private Sorting sorter;

    @Before
    public void setUp() {
        sorter = new SelectionSort();
    }

    @Test(expected = IllegalArgumentException.class)
    public void chceckIfNull() {
        //given
        double nums[] = null;

        //when
        sorter.sort(nums);

        //than
        assert false;
    }

    @Test
    public void emptyArray() {
        //given
        double nums[] = {};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void oneElementArray() {
        //given
        double nums[] = {1};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {1};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void sortedArrayGiven() {
        //given
        double nums[] = {1, 2, 3, 4, 5};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldSortDescendingArrayGiven() {
        //given
        double nums[] = {5, 4, 3, 2, 1};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {1, 2, 3, 4, 5};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void shouldSortNumbersInRandomOrder() {
        //given
        double nums[] = {14, 3, 68, 1, 4, 15, 6, 3, 22, 48};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {1, 3, 3, 4, 6, 14, 15, 22, 48, 68};
        Assert.assertArrayEquals(nums, expected, EPS);
    }
}
