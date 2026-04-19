package parking.facility;

import java.util.ArrayList;

public class Gate
{
    private final ArrayList<vehicle.Car> cars;

    private final parking.ParkingLot parkingLot;



    public Gate(parking.ParkingLot parkingLot)
    {
        this.parkingLot = parkingLot;
        this.cars = new ArrayList<vehicle.Car>();
    }

    private parking.facility.Space findTakenSpaceByCar(vehicle.Car c)
    {
        parking.facility.Space[][] floorPlan = parkingLot.getFloorPlan();

        for(int i = 0; i < parkingLot.getFloorPlan().length; i++)
        {
            for(int j = 0; j< parkingLot.getFloorPlan()[i].length; j++)
            {
                parking.facility.Space space = parkingLot.getFloorPlan()[i][j];
                if(space.isTaken() && space.getCarLicensePlate() != null && parkingLot.getFloorPlan()[i][j].getCarLicensePlate() == c.getLicensePlate())
                {
                    return space;
                }
            }
        }

        return null;
    }

    private parking.facility.Space findAvailableSpaceOnFloor(int floor, vehicle.Car c)
    {

        parking.facility.Space s = null;

        if(c.getSpotOccupation() == vehicle.Size.SMALL)
        {
            for(int j = 0; j < parkingLot.getFloorPlan()[floor].length; j++)
            {
                if(!parkingLot.getFloorPlan()[floor][j].isTaken())
                {
                    s = parkingLot.getFloorPlan()[floor][j];
                    break;
                }
            }
        }
        else
        {
            for(int j = 1; j < parkingLot.getFloorPlan()[floor].length; j++)
            {
                if(!parkingLot.getFloorPlan()[floor][j].isTaken() && !parkingLot.getFloorPlan()[floor][j-1].isTaken())
                {
                    s = parkingLot.getFloorPlan()[floor][j-1];
                    break;
                }   
            }
        }

        return s;
    }

    public parking.facility.Space findAnyAvailableSpaceForCar(vehicle.Car c)
    {
        parking.facility.Space s = null;

        for(int i = 0; i < parkingLot.getFloorPlan().length; i++)
        {
            s = findAvailableSpaceOnFloor(i,c);

            if(s!=null)
            {
                return s;
            }
        }

        return null;
    }

    public parking.facility.Space findPreferredAvailableSpaceForCar(vehicle.Car c) {
    int preferredFloor = c.getPreferredFloor();
    int totalFloors = parkingLot.getFloorPlan().length;

    for (int i = 0; i < totalFloors; i++)
    {
        int floorToCheck;
        if (i == 0)
        {
            floorToCheck = preferredFloor;
        }
        else if (i % 2 == 1)
        {
            floorToCheck = preferredFloor - (i + 1) / 2;
        }
        else
        {
            floorToCheck = preferredFloor + i / 2;
        }

        if (floorToCheck >= 0 && floorToCheck < totalFloors) {
            parking.facility.Space s = findAvailableSpaceOnFloor(floorToCheck, c);
            if (s != null)
            {
                return s;
            }
        }
    }

    return null;
    }

    public boolean registerCar(vehicle.Car c)
    {
        parking.facility.Space preferredSpace = findPreferredAvailableSpaceForCar(c);
        if (preferredSpace != null)
        {
            if(c.getSpotOccupation() == vehicle.Size.SMALL)
            {
                c.setTicketId(c.getLicensePlate());
                cars.add(c);
                parkingLot.getFloorPlan()[preferredSpace.getFloorNumber()][preferredSpace.getSpaceNumber()].addOccupyingCar(c);
            }
            else
            {
                c.setTicketId(c.getLicensePlate());
                cars.add(c);
                parkingLot.getFloorPlan()[preferredSpace.getFloorNumber()][preferredSpace.getSpaceNumber()].addOccupyingCar(c);
                parkingLot.getFloorPlan()[preferredSpace.getFloorNumber()][preferredSpace.getSpaceNumber()+1].addOccupyingCar(c);
            }

            return true;
        }

        parking.facility.Space anySpace = findAnyAvailableSpaceForCar(c);
        if (anySpace != null)
        {
            if(c.getSpotOccupation() == vehicle.Size.SMALL)
            {
                c.setTicketId(c.getLicensePlate());
                cars.add(c);
                parkingLot.getFloorPlan()[anySpace.getFloorNumber()][anySpace.getSpaceNumber()].addOccupyingCar(c);
            }
            else
            {
                c.setTicketId(c.getLicensePlate());
                cars.add(c);
                parkingLot.getFloorPlan()[anySpace.getFloorNumber()][anySpace.getSpaceNumber()].addOccupyingCar(c);
                parkingLot.getFloorPlan()[anySpace.getFloorNumber()][anySpace.getSpaceNumber()+1].addOccupyingCar(c);
            }
        }

        return false;
    }

    public void registerCars(vehicle.Car... cars)
    {

        for (vehicle.Car car : cars) 
        {
        parking.facility.Space s = findAnyAvailableSpaceForCar(car);

        if (s != null)
        {
            car.setTicketId(car.getLicensePlate());
            this.cars.add(car);
            parkingLot.getFloorPlan()[s.getFloorNumber()][s.getSpaceNumber()].addOccupyingCar(car);
        }
        else
        {
            System.err.println("No available space for car with license plate: " + car.getLicensePlate());
        }
        }
    }

    public void deRegisterCar(String ticketId)
    {
        vehicle.Car carToRemove = null;
        for (vehicle.Car car : cars)
        {
            if (car.getTicketId().equals(ticketId))
            {
                carToRemove = car;
                break;
            }
        }

        if (carToRemove != null) 
        {
            parking.facility.Space takenSpace = findTakenSpaceByCar(carToRemove);
            if(takenSpace != null)
            {
                if (carToRemove.getSpotOccupation() == vehicle.Size.SMALL) 
                {
                    parkingLot.getFloorPlan()[takenSpace.getFloorNumber()][takenSpace.getSpaceNumber()].removeOccupyingCar();
                }
                else
                {
                    parkingLot.getFloorPlan()[takenSpace.getFloorNumber()][takenSpace.getSpaceNumber()+1].removeOccupyingCar();
                }
            }
            
            cars.remove(carToRemove);
            carToRemove.setTicketId(null);
        } 
        else
        {
            System.err.println("No car found with ticket ID: " + ticketId);
        }
    }

}