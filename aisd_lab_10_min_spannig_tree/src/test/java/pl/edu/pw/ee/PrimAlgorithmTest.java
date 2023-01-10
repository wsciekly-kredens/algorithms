package pl.edu.pw.ee;

import org.junit.Test;
import pl.edu.pw.ee.PrimAlgorithm;

import java.io.IOException;

public class PrimAlgorithmTest {

    @Test
    public void test() throws IOException {
        PrimAlgorithm prim = new PrimAlgorithm();
        prim.readData("C:\\Users\\r0ler\\IdeaProjects\\2022Z_AISD_git_repo_GR3_gr7\\aisd_lab_10_min_spannig_tree\\correct_small_data.txt");
    }

    @Test
    public void mstTest() throws IOException {
        PrimAlgorithm prim = new PrimAlgorithm();
        System.out.println(prim.findMST("C:\\Users\\r0ler\\IdeaProjects\\2022Z_AISD_git_repo_GR3_gr7\\aisd_lab_10_min_spannig_tree\\correct_small_data.txt"));
    }

}
