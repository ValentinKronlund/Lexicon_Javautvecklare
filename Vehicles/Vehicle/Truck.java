package Vehicle;

public class Truck extends Vehicle implements MaintainableInterface {

    Truck(VehicleFactory factoryDefaults) {
        super(factoryDefaults);
    }

    @Override
    public void performService() {
    }

    @Override
    public void move() {
        if (this.fuelPercantage > 0) {
            System.out.println("Tuck! Tuck! Tuck!");
            System.out.println("Fuel Level: " + this.fuelPercantage + "\n");
            this.fuelPercantage -= 10;
        }
    }

}
