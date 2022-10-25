package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.edu.pw.ee.Heap;

public class HeapTest {

    @Test
    public void passingListOfDoublesToConstructor() {
        //given
        List<Double> nums = new ArrayList(Arrays.asList(1.2, 1.3, 15d, 32.8, 17.3));

        //when
        Heap<Double> heap = new Heap<Double>(nums);

        //then
        assert true;
    }

    @Test
    public void buildWhenEmptyListGiven() {
        //given
        List<Double> nums = new ArrayList();
        Heap<Double> heap = new Heap<Double>(nums);

        //when
        heap.build();

        //then
        assert true;
    }

    @Test
    public void buildTestShouldBuildMaxHeap() {
        //given
        List<Double> nums = new ArrayList(Arrays.asList(8.1, 12.3, 4.2, 21.37, 5.2, 7.6));
        Heap<Double> heap = new Heap<Double>(nums);

        //when
        heap.build();

        //then
        List<Double> expected = new ArrayList(Arrays.asList(21.37, 12.3, 7.6, 8.1, 5.2, 4.2));
        Assert.assertEquals(expected, heap.getHeap());
    }

    @Test
    public void buildTestShouldBuildMaxHeapSecondSet() {
        //given
        List<Double> nums = new ArrayList(Arrays.asList(83.6981, 79.5597, 16.9295, -19.1077, -3.9366, 54.8574, -73.5961, 10.3752, 40.9866, -15.9468, -44.3212, -63.9776, 94.0643, 12.9722, 62.2582, -50.5137, 56.9402, 10.2751, 67.0963, -21.9592));
        Heap<Double> heap = new Heap<Double>(nums);

        //when
        heap.build();

        //then
        List<Double> expected = new ArrayList(Arrays.asList(94.0643, 79.5597, 83.6981, 67.0963, -3.9366, 54.8574, 62.2582, 56.9402, 40.9866, -15.9468, -44.3212, -63.9776, 16.9295, 12.9722, -73.5961, -50.5137, 10.3752, 10.2751, -19.1077, -21.9592));
        Assert.assertEquals(expected, heap.getHeap());
    }

    @Test
    public void putTestShouldAddElementToHeapAndKeepItMaxHeap() {
        //given
        List<Double> nums = new ArrayList(Arrays.asList(1.2, 1.3, 15d, 32.8, 17.3));
        Heap<Double> heap = new Heap<Double>(nums);

        //when
        heap.build();
        heap.put(6.8);
        heap.build();

        //then
        List<Double> expected = new ArrayList(Arrays.asList(32.8, 17.3, 15d, 1.3, 1.2, 6.8));
        Assert.assertEquals(expected, heap.getHeap());

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void popWhenEmptyListGivenShouldThrowException() {
        //given
        List<Double> nums = new ArrayList();
        Heap<Double> heap = new Heap<Double>(nums);

        //when
        heap.pop();

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void popWhenListInstHeapShouldThrowException() {
        //given
        List<Double> nums = new ArrayList(Arrays.asList(1.2, 1.3, 15d, 32.8, 17.3));
        Heap<Double> heap = new Heap<Double>(nums);

        //when
        heap.pop();

        //then
        assert false;
    }

    @Test
    public void popTestShouldReturnBiggestElementInList() {
        //given
        List<Double> nums = new ArrayList(Arrays.asList(1.2, 1.3, 15d, 32.8, 17.3));
        Heap<Double> heap = new Heap<Double>(nums);

        //when
        heap.build();

        //then
        Double expected = 32.8;
        Assert.assertEquals(0, expected.compareTo(heap.pop()));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void heapifyNegativeValuesAsArguments() {
        //given
        List<Double> nums = new ArrayList(Arrays.asList(1.2, 1.3, 15d, 32.8, 17.3));
        Heap<Double> heap = new Heap<Double>(nums);

        //when
        heap.heapify(-1, 0);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void heapifyStartOfRangeGraterThanEnd() {
        //given
        List<Double> nums = new ArrayList(Arrays.asList(1.2, 1.3, 15d, 32.8, 17.3));
        Heap<Double> heap = new Heap<Double>(nums);

        //when
        heap.heapify(21, 4);

        //then
        assert false;
    }

}
