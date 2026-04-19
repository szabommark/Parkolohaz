package parking;

import static check.CheckThat.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import check.*;

public class ParkingLotTest
{
    @Test
    public void testConstructorWithInvalidValues()
    {
    assertThrows(IllegalArgumentException.class, () -> new ParkingLot(0, 0));
    assertThrows(IllegalArgumentException.class, () -> new ParkingLot(-1, 5));
    assertThrows(IllegalArgumentException.class, () -> new ParkingLot(3, -2)); 
    }

    @Test
    public void testTextualRepresentation()
    {
    ParkingLot lot = new ParkingLot(2, 2);
    parking.facility.Space[][] floorPlan = lot.getFloorPlan();

    floorPlan[0][0].addOccupyingCar(new vehicle.Car("ABC", vehicle.Size.SMALL, 0));
    floorPlan[1][0].addOccupyingCar(new vehicle.Car("DEF", vehicle.Size.LARGE, 1));

    String expected = "S X\nL X\n";
    assertEquals(expected, lot.toString(), "Textual representation does not match the expected output.");
    }


}
