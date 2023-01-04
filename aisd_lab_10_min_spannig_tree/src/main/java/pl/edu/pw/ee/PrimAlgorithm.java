package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {
    
    private int[][] adjacencymMatrix;
    
    public String findMST(String pathToFile){
        // TODO
        return null;
    }
    
    public void readData(String path)throws IOException{
        BufferedReader fileReader = new BufferedReader(new FileReader(path));
        String readedLine;
        ArrayList<String> fileLines = new ArrayList();
        ArrayList<Character> vortexes = new ArrayList<>();
        while((readedLine = fileReader.readLine()) != null){
            char[] lineAsCharArray = readedLine.replace(" ","").toCharArray();
            if(lineAsCharArray[0] == lineAsCharArray[1]){
                throw new InputMismatchException();
            }
            else{
                if(!vortexes.contains(lineAsCharArray[0])){
                    vortexes.add(lineAsCharArray[0]);
                }
                else if(!vortexes.contains(lineAsCharArray[1])){
                    vortexes.add(lineAsCharArray[1]);
                }
            }
        }
    }

}
