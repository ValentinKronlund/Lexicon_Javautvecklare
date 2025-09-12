import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import day3.Student;

public class Main{
    public static void main(String[] args) {
         String[] names = {
            "Ava",    
            "Bella",  
            "Caleb",  
            "Dylan",  
            "Emma",   
            "Finn",   
            "Grace",  
            "Hazel",  
            "Isla",   
            "Jack",   
            "Kai",    
            "Liam",   
            "Mia",    
            "Noah",   
            "Olivia", 
            "Parker", 
            "Quinn",  
            "Ruby",   
            "Sophia", 
            "Theo",   
            "Uma",    
            "Violet", 
            "Wyatt",  
            "Xavier", 
            "Yara",   
            "Zoe"     
        };
        List<Student> students = new ArrayList<Student>();

        for(String name : names){
            students.add(new Student(name));
        }

        int allResultsSum = 0;
        int lowestResult = 100;
        String[] nameLowestResult = {""};
        String[] nameHighestResult = {""};
        int highestResult = 0;

        for(Student student : students){
            System.out.println("Name: " + student.getName() + ", Results: " + student.getTestResults());
            int studentResult = student.getTestResults();
            if(studentResult <= lowestResult){
                lowestResult = studentResult;
                nameLowestResult[0] = student.getName();
            }
            if(studentResult >= highestResult){
                highestResult = studentResult;
                nameHighestResult[0] = student.getName();
            }
            allResultsSum += studentResult;
        }

        double averageResults = allResultsSum / students.size();
        System.out.println("\n📜 Average results = " + averageResults);

        System.out.println("⬆️ Highest result = " + highestResult);
        System.out.println("Highest result student = " + nameHighestResult[0]);

        System.out.println("\n⬇️ Lowest result = " + lowestResult);
        System.out.println("Lowest result student = " + nameLowestResult[0] + "\n");

        sortByScore(students, true);

        for(Student student : students){
            System.out.println(student.getName() + " " + student.getTestResults());
        }

    }


    static void sortByScore(List<Student> students, boolean ascending){
        Comparator<Student> comparator = Comparator.comparingInt(Student::getTestResults);
        comparator = !ascending ? comparator.reversed() : comparator;
        comparator = comparator.thenComparing(Student::getName);
        students.sort(comparator);
    }

}
