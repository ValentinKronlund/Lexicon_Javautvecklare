package Vehicle;

public class EV extends Vehicle implements MaintainableInterface, EVInterface {

    EV(VehicleFactory factoryDefaults) {
        super(factoryDefaults);
    }

    @Override
    public void performService() {
    }

    @Override
    public void move() {
        if (this.fuelPercantage > 0) {
            System.out.println("Ziip! Ziip!");
            System.out.println("Fuel Level: " + this.fuelPercantage + "\n");
            this.fuelPercantage -= 10;
        }
    }

    @Override
    public void chargeBattery() {
        System.out.println("Charging!!");
        this.fuelPercantage += 10;
    }

}
