import java.util.Scanner;

public class Person {
    private int age;
    private String name;
    private String status;

    public Person(Scanner input){
        Helpers helper = new Helpers();

        String inputName = helper.askLine(input, "What's your name? ");
        int inputAge = helper.askInt(input, "How old are you? ");

        this.name = java.util.Objects.requireNonNull(inputName);
        if (inputAge < 0) throw new Error();

        this.age = inputAge;

        if(inputAge < 13){this.status = "Barn";}
        else if(inputAge >= 13 && inputAge <= 19){this.status = "Tonåring";}
        else if(inputAge >= 20 && inputAge <= 64){this.status = "Vuxen";}
        else if(inputAge >= 65 && inputAge < 95){this.status = "Senior";}
        else {this.status = "Död ... förmodligen";}
    };

    public String printStatus(){
        return this.status;
    }

    public String getName(){return this.name;}
    public int getAge(){return this.age;}
}