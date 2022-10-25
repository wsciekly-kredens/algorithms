package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HeapSortTest {

    private static final double EPS = 0;
    private pl.edu.pw.ee.services.Sorting sorter;

    @Before
    public void setUp() {
        sorter = new pl.edu.pw.ee.HeapSort();
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
    public void sotrtedArrayGiven() {
        //given
        double nums[] = {1, 2, 4, 7, 8, 15, 12.6, 31, 90};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {1, 2, 4, 7, 8, 12.6, 15, 31, 90};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void arrayInDescendingOrderGiven() {
        //given
        double nums[] = {70, 31, 14, 12, 4, 3, 1};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {1, 3, 4, 12, 14, 31, 70};
        Assert.assertArrayEquals(expected, nums, EPS);
    }

    @Test
    public void randomArrayGiven() {
        //given
        double nums[] = {55.9747, -31.0842, 4.5729, 61.4299, 40.4725, 29.7668, -79.5418, 31.8936, 98.3269, 96.6459, 56.7524, -48.0578, -71.1159, 94.5363, 51.8562, -65.5308, -46.6199, 9.4042, 99.8716, 9.9570};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {-79.5418, -71.1159, -65.5308, -48.0578, -46.6199, -31.0842, 4.5729, 9.4042, 9.9570, 29.7668, 31.8936, 40.4725, 51.8562, 55.9747, 56.7524, 61.4299, 94.5363, 96.6459, 98.3269, 99.8716};
        Assert.assertArrayEquals(expected, nums, EPS);

    }

    @Test
    public void randomArrayGivenSecondSet() {
        //given
        double nums[] = {-11.8577, -28.5762, -6.4480, 94.7663, 42.8147, 91.1430, 46.3901, -65.8246, 93.0581, 77.4807, 24.5702, 12.9627, 12.8550, -43.0667, -61.4519, 11.9566, -15.4016, -27.5221, 26.6780, -51.1766, 21.4502, 93.7470, -72.2695, -87.9607, -70.4512, -30.9835, 34.7414, 15.7807, -53.2845, -75.8451, 63.4462, 40.9085, -63.2615, 75.2984, -11.2137, 39.0009, -66.6440, 77.5871, -11.7877, 40.7608};

        //when
        sorter.sort(nums);

        //then
        double expected[] = {-87.9607, -75.8451, -72.2695, -70.4512, -66.6440, -65.8246, -63.2615, -61.4519, -53.2845, -51.1766, -43.0667, -30.9835, -28.5762, -27.5221, -15.4016, -11.8577, -11.7877, -11.2137, -6.4480, 11.9566, 12.8550, 12.9627, 15.7807, 21.4502, 24.5702, 26.6780, 34.7414, 39.0009, 40.7608, 40.9085, 42.8147, 46.3901, 63.4462, 75.2984, 77.4807, 77.5871, 91.1430, 93.0581, 93.7470, 94.7663};
        Assert.assertArrayEquals(expected, nums, EPS);

    }
}
