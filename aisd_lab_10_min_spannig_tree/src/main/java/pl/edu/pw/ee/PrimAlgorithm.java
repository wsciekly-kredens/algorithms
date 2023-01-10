package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;

import main.java.pl.edu.pw.ee.ListNode;
import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {

    private int[][] adjacencyMatrix;
    private String[] vortexesOrder;

    public String findMST(String pathToFile) throws IOException {
        readData(pathToFile);
        boolean visited[] = new boolean[vortexesOrder.length];
        Arrays.fill(visited, false);
        StringBuilder mstSolution = new StringBuilder();
        visited[0] = true;
        int numberOfEdges = 0;
        while (numberOfEdges < vortexesOrder.length - 1) {
            int min = Integer.MAX_VALUE;
            int x = 0;
            int y = 0;
            for (int i = 0; i < vortexesOrder.length; i++) {
                if (visited[i]) {
                    for (int j = 0; j < vortexesOrder.length; j++) {
                        if (!visited[j] && adjacencyMatrix[i][j] != 0 && min > adjacencyMatrix[i][j]) {
                            min = adjacencyMatrix[i][j];
                            y = j;
                            x = i;
                        }
                    }
                }
            }
            visited[y] = true;
            mstSolution.append(vortexesOrder[x] + "_" + adjacencyMatrix[x][y] + "_" + vortexesOrder[y] + "|");
            numberOfEdges++;
        }
        return mstSolution.toString();
    }

    public void readData(String path) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(path));
        String readedLine;
        ArrayList<String> fileLines = new ArrayList();
        ArrayList<String> vortexes = new ArrayList<>();
        ArrayList<String[]> nodes = new ArrayList<>();
        while ((readedLine = fileReader.readLine()) != null) {
            String[] newLine = readedLine.split(" ");
            if (newLine.length != 3 || newLine[0].equals(newLine[1])) {
                throw new InputMismatchException();
            }
            if (!vortexes.contains(newLine[0])) {
                vortexes.add(newLine[0]);
            } else if (!vortexes.contains(newLine[1])) {
                vortexes.add(newLine[1]);
            }
            nodes.add(newLine);
        }
        vortexesOrder = vortexes.toArray(new String[0]);
        adjacencyMatrix = new int[vortexes.size()][vortexes.size()];
        for (String vortex : vortexes) {
            for (String[] node : nodes) {
                if (vortex.equals(node[0])) {
                    adjacencyMatrix[vortexes.indexOf(vortex)][vortexes.indexOf(node[1])] = Integer.parseInt(node[2]);
                }
            }
        }
    }

}
