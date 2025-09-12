import java.util.Random;

public class Student {
    private final String name;
    private final int testResults;

    public String getName(){return name;};
    public int getTestResults(){return testResults;};

    public Student(String name){
        Random rng = new Random();
        this.name = name;
        this.testResults = rng.nextInt(0, 100 + 1);
    }
    
}