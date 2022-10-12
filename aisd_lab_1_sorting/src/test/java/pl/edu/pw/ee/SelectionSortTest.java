package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.services.Sorting;

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
        Assert.assertArrayEquals(expected,nums,EPS);    }

    @Test
    public void oneElementArray() {
        //given
        double nums[] = {1};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {1};
        Assert.assertArrayEquals(expected,nums,EPS);
    }

    @Test
    public void sortedArrayGiven() {
        //given
        double nums[] = {1,2,3,4,5};
        
        //when
        sorter.sort(nums);
        
        //then
        double expected[] = {1,2,3,4,5};
        Assert.assertArrayEquals(expected,nums,EPS);
    }

    @Test
    public void shouldSortCorrectlyWhenWorstCaseGiven(){
        //given
        double nums[] = {5,4,3,2,1};
        
        //when
        sorter.sort(nums);
        
        //then
        double expected[] = {1,2,3,4,5};
        Assert.assertArrayEquals(expected,nums,EPS);
    }
}
