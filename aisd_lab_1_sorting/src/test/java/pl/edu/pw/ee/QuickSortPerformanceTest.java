package test.java.pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import pl.edu.pw.ee.services.Sorting;

public class QuickSortPerformanceTest {

    Sorting sorter;

    @Before
    public void setUp() {
        sorter = new pl.edu.pw.ee.QuickSort();
    }

    @Test
    public void bestCaseScenarioPerformanceTest() {
        double optymisticData[] = {};
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("aisd_lab_1_sorting/optymisticData.csv"));
            line = br.readLine();
            String[] values = line.split(",");
            optymisticData = Arrays.stream(values).mapToDouble(Double::parseDouble).toArray();
        } catch (IOException e) {
        }
        int n = optymisticData.length;
        for (int i = 199; i < n; i += 200) {
            double nums[] = Arrays.copyOfRange(optymisticData, 0, i + 1);
            long start = 0;
            long end = 0;
            for (int j = 0; j < 10; j++) {
                start += System.nanoTime();
                sorter.sort(nums);
                end += System.nanoTime();
            }
            try {
                PrintWriter printWriter = new PrintWriter(new FileWriter("QuickTestTimeOptimistic", true));
                printWriter.printf("%d %d%n", i + 1, ((end - start) / 10));
                printWriter.close();
            } catch (IOException e) {
            }
        }
    }

    @Test
    public void worstCaseScenarioPerformanceTest() {
        double pesymisticData[] = {};
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("aisd_lab_1_sorting/pesymisticData.csv"));
            line = br.readLine();
            String[] values = line.split(",");
            pesymisticData = Arrays.stream(values).mapToDouble(Double::parseDouble).toArray();
        } catch (IOException e) {
        }
        int n = pesymisticData.length;
        for (int i = 199; i < n; i += 200) {
            double nums[] = Arrays.copyOfRange(pesymisticData, 0, i + 1);
            long start = 0;
            long end = 0;
            for (int j = 0; j < 10; j++) {
                start += System.nanoTime();
                sorter.sort(nums);
                end += System.nanoTime();
            }
            try {
                PrintWriter printWriter = new PrintWriter(new FileWriter("QuickTestTimePessimistic", true));
                printWriter.printf("%d %d%n", i + 1, ((end - start) / 10));
                printWriter.close();
            } catch (IOException e) {
            }
        }
    }

    @Test
    public void randomDataPerformanceTest() {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("aisd_lab_1_sorting/randomData.csv"));
            int i = 200;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double randomData[] = Arrays.stream(values).mapToDouble(Double::parseDouble).toArray();
                long start = 0;
                long end = 0;
                for (int j = 0; j < 10; j++) {
                    start += System.nanoTime();
                    sorter.sort(randomData);
                    end += System.nanoTime();
                }
                PrintWriter printWriter = new PrintWriter(new FileWriter("QuickTestTimeRandom", true));
                printWriter.printf("%d %d%n", i, ((end - start) / 10));
                printWriter.close();
                i += 200;
            }
        } catch (IOException e) {
        }
    }

}