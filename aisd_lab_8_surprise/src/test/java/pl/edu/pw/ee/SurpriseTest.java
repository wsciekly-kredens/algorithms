package pl.edu.pw.ee;


import static org.junit.Assert.*;
import org.junit.Test;


public class SurpriseTest {  
    @Test
    public void tooShortSeatsChainGiven(){
        //give
        Surprise changeCounter = new Surprise();
        String seats = "MMK";
        
        //when
        int changes = changeCounter.countChanges(seats, 4);
        
        //then
        assertEquals(-1, changes);
    }
    
    @Test
    public void mansNextToEachOther(){
        //give
        Surprise changeCounter = new Surprise();
        String seats = "MMMMM";
        
        //when
        int changes = changeCounter.countChanges(seats, 3);
        
        //then
        assertEquals(1, changes);
    }
    
    @Test
    public void simpleCaseGiven(){
        //give
        Surprise changeCounter = new Surprise();
        String seats = "MMMMKMMK";
        
        //when
        int changes = changeCounter.countChanges(seats, 3);
        
        //then
        assertEquals(1, changes);
    }
    
    @Test
    public void sortedChainInTheMiddleGiven(){
        //give
        Surprise changeCounter = new Surprise();
        String seats = "MMKMMMKMMKKK";
        
        //when
        int changes = changeCounter.countChanges(seats, 3);
        
        //then
        assertEquals(0, changes);
    }
    
    @Test
    public void randomChainGiven(){
        //give
        Surprise changeCounter = new Surprise();
        String seats = "MKMKMKMKMK";
        
        //when
        int changes = changeCounter.countChanges(seats, 3);
        
        //then
        assertEquals(1, changes);
    }
    
    @Test
    public void biggerRandomChainGiven(){
        //give
        Surprise changeCounter = new Surprise();
        String seats = "MKMKMKMKMKMKMKMKMKMK";
        
        //when
        int changes = changeCounter.countChanges(seats, 5);
        
        //then
        assertEquals(2, changes);
    }
}
