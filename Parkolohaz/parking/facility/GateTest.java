package parking.facility;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;


public class GateTest
{
    @Test
    public void testFindAnyAvailableSpaceForCar()
    {
    parking.ParkingLot p = new parking.ParkingLot(3,3);

    parking.facility.Gate g = new parking.facility.Gate(p);

    vehicle.Car c = new vehicle.Car("ABC", vehicle.Size.SMALL, 2);

    assertTrue(g.findAnyAvailableSpaceForCar(c) != null);
    }

    @ParameterizedTest
    @CsvSource
    ({
        "ABC, SMALL, 2",
        "DEF, LARGE, 1",
        "GHI, SMALL, 3"
    })
    public void testFindPreferredAvailableSpaceForCar(String plate, vehicle.Size size, int preferredFloor) {
        parking.ParkingLot p = new parking.ParkingLot(3, 3);
        parking.facility.Gate g = new parking.facility.Gate(p);
        vehicle.Car c = new vehicle.Car(plate, size, preferredFloor);


        assertNotNull(g.findPreferredAvailableSpaceForCar(c));
    }

    @ParameterizedTest
    @CsvSource
    ({
    "ABC, SMALL, 2",
    "DEF, LARGE, 1",
    "GHI, SMALL, 3"
    })
    public void testRegisterCar(String plate, vehicle.Size size, int preferredFloor)
    {
    parking.ParkingLot p = new parking.ParkingLot(3, 3);
    parking.facility.Gate g = new parking.facility.Gate(p);
    vehicle.Car c = new vehicle.Car(plate, size, preferredFloor);

    boolean result = g.registerCar(c);

    assertTrue(result, "The car with plate " + plate + " could not be registered.");
    }

    @ParameterizedTest
    @CsvSource
    ({
    "ABC, SMALL, 2",
    "DEF, LARGE, 1",
    "GHI, SMALL, 3"
    })
    public void testDeRegisterCar(String plate, vehicle.Size size, int preferredFloor)
    {
        parking.ParkingLot p = new parking.ParkingLot(3, 3);
        parking.facility.Gate g = new parking.facility.Gate(p);
        vehicle.Car c = new vehicle.Car(plate, size, preferredFloor);

        boolean result = g.registerCar(c);

        String id = c.getTicketId();

        g.deRegisterCar(id);

        assertTrue(c.getTicketId() == null);
    }





}