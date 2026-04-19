package parking;

public class ParkingLot
{
    private final parking.facility.Space[][] floorPlan;

    public ParkingLot(int floorNumber, int spaceNumber)
    {
        if(floorNumber < 1 || spaceNumber < 1)
        {
            throw new IllegalArgumentException();
        }

        floorPlan = new parking.facility.Space[floorNumber][spaceNumber];

        for(int i = 0; i < floorNumber; i++)
        {
            for(int j = 0; j < spaceNumber; j++)
            {
                floorPlan[i][j] = new parking.facility.Space(i, j);
            }
        }
    }

    public parking.facility.Space[][] getFloorPlan()
    {
        return floorPlan;
    }

    @Override
    public String toString()
    {
        String f = "";

        for(int i = 0; i < floorPlan.length; i++)
        {
            for(int j = 0; j < floorPlan[i].length; j++)
            {
                if(!floorPlan[i][j].isTaken())
                {
                    if(j != floorPlan[i].length-1)
                    {
                        f += ("X ");
                    }
                    else
                    {
                        f += ("X\n");
                    }                   
                }
                else
                {
                    if(floorPlan[i][j].getOccupyingCarSize()==vehicle.Size.SMALL)
                    {
                        if(j != floorPlan[i].length-1)
                        {
                            f += ("S ");
                        }
                        else
                        {
                            f += ("S\n");
                        }
                    }
                    else
                    {
                        if(j != floorPlan[i].length-1)
                        {
                            f += ("L ");
                        }
                        else
                        {
                            f += ("L\n");
                        }                        
                    }
                }

            }
        }

        return f;
    }


}





