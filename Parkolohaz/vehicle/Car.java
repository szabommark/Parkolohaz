package vehicle;

public class Car
{
    private final String licensePlate;
    private final Size spotOccupation;
    private int preferredFloor;

    public Car(String licensePlate, vehicle.Size spotOccupation, int preferredFloor)
    {
        this.licensePlate = licensePlate;
        this.spotOccupation = spotOccupation;
        this.preferredFloor = preferredFloor;
    }

    private String ticketId = new String();

    public String getTicketId()
    {
        return ticketId;
    }

    public void setTicketId(String ticketId)
    {
        this.ticketId = ticketId;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }

    public vehicle.Size getSpotOccupation()
    {
        return spotOccupation;
    }

    public int getPreferredFloor()
    {
        return preferredFloor;
    }

    public void setPreferredFloor(int preferredFloor)
    {
        this.preferredFloor = preferredFloor;
    }




    

}