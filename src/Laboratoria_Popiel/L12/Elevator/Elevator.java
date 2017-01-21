package Laboratoria_Popiel.L12.Elevator;

import java.util.SortedSet;
import java.util.TreeSet;

import static Laboratoria_Popiel.L12.Elevator.Direction.*;

public class Elevator implements Runnable {
    private int ID;
    private int currentFloor;
    private int maxCapacity;
    private Building building;
    private Direction direction;
    private SortedSet<Integer> destinations;

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Elevator(int ID, int capacity, Building building) {
        this.ID = ID;
        this.building = building;
        maxCapacity = capacity;
        destinations = new TreeSet<>();
        direction = WAITING;
    }

    public void call(int destinationFloor){
        direction = currentFloor < destinationFloor ? UP : DOWN;
        destinations.add(destinationFloor);
        if(direction == UP){

        }

    }

    public boolean isGoingTo(int floor){
        return destinations.contains(floor);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void run() {
        while(true){



        }
    }


}
