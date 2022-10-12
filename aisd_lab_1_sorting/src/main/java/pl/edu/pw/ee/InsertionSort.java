package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums==null){
            throw new IllegalArgumentException("Input array cannot be null");
        }
    }

}
