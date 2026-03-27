import Vehicle.EV;
import Vehicle.Vehicle;
import Vehicle.VehicleFactory;

public class Main {
    public static void main(String[] args) {
        Vehicle[] vehicles = {
                VehicleFactory.CAR.create(VehicleFactory.CAR),
                VehicleFactory.BUS.create(VehicleFactory.BUS),
                VehicleFactory.TRUCK.create(VehicleFactory.TRUCK),
                VehicleFactory.EV.create(VehicleFactory.EV)
        };

        for (int i = 0; i < vehicles.length; i++) {
            while (vehicles[i].getFuelLevel() > 0) {
                vehicles[i].move();
            }

            if (vehicles[i] instanceof EV) {
                while (vehicles[i].getFuelLevel() < 40) {
                    ((EV) vehicles[i]).chargeBattery();
                }
            }

            while (vehicles[i].getFuelLevel() > 0) {
                vehicles[i].move();
            }
        }
    }

}
