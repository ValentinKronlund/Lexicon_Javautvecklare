package Vehicle;


public enum VehicleFactory {
    CAR("Car", "Petrol", 180, 1800),
    BUS("Bus", "Ethanol",100,7800),
    TRUCK("Truck", "Diesel",130,7000),
    EV("Ev", "Electricity",220,2400);

    public final String type;
    public final String fuelType;
    public final int speed;
    public final int weight;

    VehicleFactory(String type, String fuelType, int speed, int weight) {
        this.type = type;
        this.fuelType = fuelType;
        this.speed = speed;
        this.weight = weight;
    }

    public Vehicle create(VehicleFactory type) {
        switch(type){
            case CAR: return new Car(this);
            case BUS: return new Bus(this);
            case TRUCK: return new Truck(this);
            case EV: return new EV(this);
            default: {
                System.out.println("There is currently no vehicle of that type!");
                return null;
            }
        }
        
    }
}
