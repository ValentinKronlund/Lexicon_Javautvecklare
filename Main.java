public class Main{
    public static void main(String[] args) {
        // byte xByte = 1;
        // short xShort = 2;
        // int x = 5;
        // long xLong = 50L;

        // float xFloat = 5.0f;
        // double xDouble = 10.0;

        // char xChar = 'a';
        // boolean xBool = true;

        // String text = "hej";

        // int index = 0;


        Person myPerson = new Person("Valentin", 300);
        ScoreConverter myScoreConverter = new ScoreConverter();
        System.out.println(myPerson.getName() + " is " + myPerson.getAge() + "years old, and " + myPerson.PrintStatus());
        System.out.println(myScoreConverter.ScoreToGrade(87));

    }
}