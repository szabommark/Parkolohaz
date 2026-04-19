package parking.facility;

public class Space
{
    private final int floorNumber;
    private final int spaceNumber;

    public Space(int floorNumber, int spaceNumber)
    {
        this.floorNumber = floorNumber;
        this.spaceNumber = spaceNumber;
    }

    public int getFloorNumber()
    {
        return floorNumber;
    }

    public int getSpaceNumber() 
    {
        return spaceNumber;
    }

    private vehicle.Car occupyingCar;

    public void addOccupyingCar(vehicle.Car c)
    {
        occupyingCar = c;
    }

    public void removeOccupyingCar()
    {
        occupyingCar = null;
    }

    public boolean isTaken()
    {
        if(occupyingCar == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public String getCarLicensePlate()
    {
        return occupyingCar.getLicensePlate();
    }

    public vehicle.Size getOccupyingCarSize()
    {
        return occupyingCar.getSpotOccupation();
    }
}