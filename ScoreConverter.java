public class ScoreConverter {
    public char ScoreToGrade(double score){
        if(score > 100 || score < 0) throw new Error();
        if(score >= 90){return 'A';}
        if(score >= 80 && score <= 89){return 'B';}
        if(score >= 70 && score <= 79){return 'C';}
        if(score >= 60 && score <= 69){return 'D';}
        return 'F';
    }
    
}
