import java.text.DecimalFormat;
import java.util.Scanner;

public class TemperatureConverter {
    private double temperatureInCelsius;
    private double temperatureInFahrenheit;

    public String convertTemperature(Scanner input){
        Helpers helper = new Helpers();
        DecimalFormat df = new DecimalFormat("0.00");
        
        while (true) { 
            char FromTempFormat = helper.askChar(input, "What temperature format are you converting from, 'F' or 'C'? ");
            double temperature = helper.askDobule(input, "What temperature is it? ");
            switch (FromTempFormat) {
                case 'C', 'c': {
                    this.temperatureInCelsius = temperature;
                    this.temperatureInFahrenheit = (temperature * 9/5) + 32;
                    return "Temperaturen är: " +  df.format(this.temperatureInCelsius) + "°C och " + df.format(this.temperatureInFahrenheit) + "°F rekommendationen för kläder är: " + this.recommendedClothes();
                }
                case 'F', 'f': {
                    this.temperatureInFahrenheit = temperature;
                    this.temperatureInCelsius = (temperature - 32) * 5/9;
                    return "Temperaturen är: " +  df.format(this.temperatureInFahrenheit) + "°F och " + df.format(this.temperatureInCelsius) + "°C rekommendationen för kläder är: " + this.recommendedClothes();
                }
                default: System.out.println("Invalid input -- Only 'C' and 'F' are available. Try again! ");
            }
        }
    };


    private String recommendedClothes(){
        if(this.temperatureInCelsius < 0){return "\nMycket kallt - ta på dig vinterkläder!";}
        if(this.temperatureInCelsius >= 0 && this.temperatureInCelsius <= 10){return "\nKallt - jacka behövs!";}
        if(this.temperatureInCelsius >= 11 && this.temperatureInCelsius <= 20){return "\nSvalt - Ta med en lätt jacka, utifall att!";}
        if(this.temperatureInCelsius >= 21 && this.temperatureInCelsius <= 30){return "\nJävligt nice - På med solbrillorna fö' fan!";}
        if(this.temperatureInCelsius > 30){return "\nWait a minut - Sun, stahp!!";}

        return "Something went wrong tbh";
    }

}
