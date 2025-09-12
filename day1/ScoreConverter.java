package day1;

import java.util.Scanner;
import helpers.Helpers;


public class ScoreConverter {
    public char scoreToGrade(Scanner input){
        Helpers helper = new Helpers();

        while (true) { 
            double score = helper.askDobule(input, "What did you score on your last test? ");
         
            if(score > 100 || score < 0) {
                System.err.println("Invalid score -- Try again!");
                continue;
            };
            if(score >= 90){return 'A';}
            if(score >= 80 && score <= 89){return 'B';}
            if(score >= 70 && score <= 79){return 'C';}
            if(score >= 60 && score <= 69){return 'D';}
            return 'F';
        }
    }
    
}
