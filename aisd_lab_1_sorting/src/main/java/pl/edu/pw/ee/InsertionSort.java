package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums==null){
            throw new IllegalArgumentException("Input array cannot be null");
        }
        for (int i = 1; i< nums.length; i++){
            double comparedElement = nums[i];
            int j = i-1;
            while (j>=0 && nums[j] > comparedElement){
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j+1] = comparedElement;
        }
    }

}
