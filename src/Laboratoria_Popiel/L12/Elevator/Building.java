package Laboratoria_Popiel.L12.Elevator;

import java.util.LinkedList;
import java.util.List;

/*

 */

public class Building {
    public int floors;
    private int maxElevators;

    private List<Elevator> elevators;

    public Building(int floors, int elevators) {
        this.floors = floors;
        this.maxElevators = elevators;
        this.elevators = new LinkedList<>();
    }

    public void notifyWaiting(int floor, Direction dir){
        boolean going = false;
        for (Elevator e : elevators)
            if(e.isGoingTo(floor) && e.getDirection()==dir)
                going = true;
        if(going) elevators.get(0).call(floor);
    }



}
