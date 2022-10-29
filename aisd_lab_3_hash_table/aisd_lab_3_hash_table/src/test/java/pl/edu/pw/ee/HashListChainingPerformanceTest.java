package test.java.pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

import static org.junit.Assert.*;

public class HashListChainingPerformanceTest {

    String words[] = new String[100000];

    @Before
    public void setUp() {
        try (BufferedReader br = new BufferedReader(new FileReader("aisd_lab_3_hash_table/aisd_lab_3_hash_table/words.txt"))) {
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
    public void primePerformanceTest() {
        int primes[] = {4093, 10009, 25037, 50951, 95987, 157243, 262147};
        for (int prime : primes) {
            long times[] = new long[30];
            pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(prime);
            for (String word : words) {
                hash.add(word);
            }
            for (int i = 0; i < 30; i++) {
                long start = System.nanoTime();
                for (String word : words) {
                    hash.get(word);
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
                PrintWriter printWriter = new PrintWriter(new FileWriter("PrimeHashSerchTime", true));
                printWriter.printf("%d %d %f%n", prime, avg, hash.countLoadFactor());
                printWriter.close();
            } catch (Exception e) {
            }
        }
    }

    @Test
    public void powerOfTwoPerformanceTest() {
        int powers[] = {4096, 8192, 16384, 32768, 65536, 131072, 262144};
        for (int power : powers) {
            long times[] = new long[30];
            pl.edu.pw.ee.HashListChaining hash = new pl.edu.pw.ee.HashListChaining(power);
            for (String word : words) {
                hash.add(word);
            }
            for (int i = 0; i < 30; i++) {
                long start = System.nanoTime();
                for (String word : words) {
                    hash.get(word);
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
                PrintWriter printWriter = new PrintWriter(new FileWriter("PowersHashSerchTime", true));
                printWriter.printf("%d %d %f%n", power, avg, hash.countLoadFactor());
                printWriter.close();
            } catch (Exception e) {
            }
        }
    }
}