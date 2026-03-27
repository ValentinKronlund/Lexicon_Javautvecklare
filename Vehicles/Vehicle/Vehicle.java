package Vehicle;

public abstract class Vehicle implements VehicleInterface {
    protected String type;
    protected String fuelType;
    protected int fuelPercantage;
    protected int speed;
    protected int weight;

    public Vehicle(VehicleFactory factoryDefaults) {
        this.type = factoryDefaults.type;
        this.fuelType = factoryDefaults.fuelType;
        this.fuelPercantage = 30;
        this.speed = factoryDefaults.speed;
        this.weight = factoryDefaults.weight;
    }

    public int getFuelLevel() {
        return this.fuelPercantage;
    }

}
