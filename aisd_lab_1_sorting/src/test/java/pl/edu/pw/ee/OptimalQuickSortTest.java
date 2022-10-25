package test.java.pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptimalQuickSortTest {
    private static final double EPS = 0;
    private pl.edu.pw.ee.services.Sorting sorter;

    @Before
    public void setUp() {
        sorter = new pl.edu.pw.ee.OptimalQuickSort();
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
    public void ShouldSortDescendingArrayGiven() {
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
        double nums[] = {13, 12, 6, 7, 74, 44, 52, 81, 54, 23, 18, 13, 24, 56, 42, 88};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {6, 7, 12, 13, 13, 18, 23, 24, 42, 44, 52, 54, 56, 74, 81, 88};
        Assert.assertArrayEquals(nums, expected, EPS);
    }

}