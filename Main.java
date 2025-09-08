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

        // while(index != 9){
        //     Greeting greeting = new Greeting();
        //     System.out.println(index);
        //     greeting.getGreeting(index);
        //     double num = Math.floor(Math.random() * 10);
        //     index = (int) Math.abs(num);
        // }

        

    }
}

class Greeting{
    public void getGreeting(int choice){
        switch(choice){
            case 0:{ System.out.println("Ska sova, hej hej!");break;}
            case 1:{ System.out.println("Goddag!");break;}
            case 2:{ System.out.println("Godeftermiddag!");break;}
            case 3:{ System.out.println("Var hälsad!");break;}
            case 4:{ System.out.println("En fröjd!");break;}
            case 5:{ System.out.println("Go'kväll!");break;}
            case 6:{ System.out.println("Din mamma!");break;}
            case 7:{ System.out.println("Tja!");break;}
            case 8:{ System.out.println("Mitt nöje!");break;}
            default: System.out.println("Hej");
        }

    }
}