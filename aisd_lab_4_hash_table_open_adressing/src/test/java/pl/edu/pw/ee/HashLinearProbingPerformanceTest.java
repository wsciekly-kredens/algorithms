package test.java.pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

import pl.edu.pw.ee.services.HashTable;

public class HashLinearProbingPerformanceTest {
    String words[] = new String[100000];

    @Before
    public void setUp() {
        try (BufferedReader br = new BufferedReader(new FileReader("aisd_lab_4_hash_table_open_adressing/words.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                words[i] = line;
                i++;
            }
        } catch (Exception e) {
        }
    }

    @Test
    public void hashLinearProbingPutPerformanceTest() {
        int sizes[] = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144};
        for (int size : sizes) {
            long times[] = new long[30];
            HashTable<String> hash;
            for (int i = 0; i < 30; i++) {
                hash = new pl.edu.pw.ee.HashLinearProbing<>(size);
                long start = System.nanoTime();
                for (String word : words) {
                    hash.put(word);
                }
                long end = System.nanoTime();
                times[i] = end - start;
            }
            Arrays.sort(times);
            long avg = 0;
            for (int i = 10; i < 20; i++) {
                avg += times[i];
            }
            avg = avg / 10;
            try {
                PrintWriter printWriter = new PrintWriter(new FileWriter("aisd_lab_4_hash_table_open_adressing/OpenAdressingPutTime", true));
                printWriter.printf("%d %d%n", size, avg);
                printWriter.close();
            } catch (Exception e) {
            }
        }
    }
}