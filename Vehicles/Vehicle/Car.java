package Vehicle;

public class Car extends Vehicle implements MaintainableInterface {

    Car(VehicleFactory factoryDefaults) {
        super(factoryDefaults);
    }

    @Override
    public void performService() {
    }

    @Override
    public void move() {
        if (this.fuelPercantage > 0) {
            System.out.println("Vrooom!");
            System.out.println("Fuel Level: " + this.fuelPercantage + "\n");
            this.fuelPercantage -= 10;
        }
    }

}
