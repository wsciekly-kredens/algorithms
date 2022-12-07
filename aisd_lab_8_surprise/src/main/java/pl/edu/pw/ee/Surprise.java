package pl.edu.pw.ee;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kowalsm6
 */
public class Surprise {

    public int countChanges(String seats, int k) {
        if (seats.length() < k) {
            return -1;
        }
        
        int numberOfTakenSeats = seats.length();
        int numberOfMenNextToEachother = 0;
        boolean isThereKMen = false;
        boolean wasArraySerched = false;
        int numberOfChanges = 0;
        char[] seatsArr = seats.toCharArray();
        for (int j = 0; j < 2; j++){
            if(isThereKMen){
                return numberOfChanges;
            }
            numberOfMenNextToEachother = 0;
            for (int i = 0; i<numberOfTakenSeats ;i++){
                if (seatsArr[i] == 'M'){
                    numberOfMenNextToEachother++;
                }
                
                if(numberOfMenNextToEachother == k){
                    isThereKMen = true;
                }
                else if(numberOfMenNextToEachother > k){
                    seatsArr[i] = 'K';
                    numberOfMenNextToEachother = 0;
                    numberOfChanges++;
                }

                if(seatsArr[i] == 'K'){
                    if(!wasArraySerched){
                        numberOfMenNextToEachother = 0;
                    }
                    else{
                        if(!isThereKMen){
                            seatsArr[i] = 'M';
                            numberOfChanges++;
                            numberOfMenNextToEachother++;
                        }
                        else{
                            numberOfMenNextToEachother = 0;
                        }
                    }
                }
            }
            wasArraySerched = true;
        }
        return numberOfChanges;
    }

}
