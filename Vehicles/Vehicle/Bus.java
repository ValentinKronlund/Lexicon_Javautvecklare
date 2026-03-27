package Vehicle;

public class Bus extends Vehicle implements MaintainableInterface {

    Bus(VehicleFactory factoryDefaults) {
        super(factoryDefaults);
    }

    @Override
    public void performService() {
    }

    @Override
    public void move() {
        if (this.fuelPercantage > 0) {
            System.out.println("Brrrrruuuumm!");
            System.out.println("Fuel Level: " + this.fuelPercantage + "\n");
            this.fuelPercantage -= 10;
        }
    }

}
