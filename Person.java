public class Person {
    private int age;
    private String name;
    private String status;

    public Person(String name, int age){
        this.name = java.util.Objects.requireNonNull(name);
        if (age < 0) throw new Error();

        this.age = age;

        if(age < 13){this.status = "Barn";}
        else if(age >= 13 && age <= 19){this.status = "Tonåring";}
        else if(age >= 20 && age <= 64){this.status = "Vuxen";}
        else if(age >= 65 && age < 95){this.status = "Senior";}
        else {this.status = "Död ... förmodligen";}
    };

    public String printStatus(){
        return this.status;
    }

    public String getName(){return this.name;}
    public int getAge(){return this.age;}
}