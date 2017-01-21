package com.company;

/**
 * Created by kordianledzion on 20.01.2016.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Building {
    private int numFloors;
    private int elevatorCapacity;
    private int numElevators;

    private List<Elevator> elevators;
    private Elevator[] goingUpStops; //tablica przechowywujaca windy jadace do góry
    private Elevator[] goingDownStops; //tablica przechowywujaca winy jadace na dol

    public Building(int numFloors, int elevatorCapacity, int numElevators){
        this.numFloors = numFloors;
        this.elevatorCapacity = elevatorCapacity;
        this.elevators = new ArrayList<Elevator>();

        for (int i = 0; i < numElevators; i++){
            elevators.add(new Elevator(i, this.numFloors, this.elevatorCapacity, this));
        }

        this.goingUpStops = new Elevator[this.numFloors];
        this.goingDownStops = new Elevator[this.numFloors];
    }

    public List<Elevator> getElevators(){
        return this.elevators;
    }

    public synchronized void addStop(int requestedFloor, boolean isGoingUp, Elevator elevator){
        if(isGoingUp){
            this.goingUpStops[requestedFloor] = elevator;
        }
        else {
            this.goingDownStops[requestedFloor] = elevator;
        }
    }

    public synchronized void removeStop(int arrivedFloor, boolean isGoingUp){
        if(isGoingUp){
            this.goingUpStops[arrivedFloor] = null;
        }
        else {
            this.goingDownStops[arrivedFloor] = null;
        }
    }

    public synchronized Elevator find(int floor, boolean isGoingUp){
        Elevator elevator;
        boolean hasElevatorGoingUpToFloor = this.goingUpStops[floor] != null;
        boolean hasElevatorGoingDownToFloor = this.goingDownStops[floor] != null;
        if (isGoingUp && hasElevatorGoingUpToFloor){
            elevator = this.goingUpStops[floor];
            return elevator;
        }
        else if (!isGoingUp && hasElevatorGoingDownToFloor){
            elevator = this.goingDownStops[floor];
            return elevator;
        }
        else{
            Random random = new Random();
            int randomElevatorIndex = random.nextInt(this.elevators.size());
            elevator = elevators.get(randomElevatorIndex);
            this.addStop(floor, isGoingUp, elevator);
            return elevator;
        }
    }

    private Elevator call(int fromFloor, boolean isGoingUp, int riderId){
        Elevator elevator = find(fromFloor, isGoingUp);
        synchronized (this){
            while(elevator.reachedFullCapacity()){
                try {
                    System.out.println("Winda " + elevator.getElevatorId() + " jest pelna.");
                    wait();
                } catch (InterruptedException e){

                }
            }
        }
        if (isGoingUp){
            System.out.println("Winda " + elevator.getElevatorId() +  " wezwana przez pasazera " + riderId + " na pietrze " + fromFloor +" by jechac do gory");
        }
        else {
            System.out.println("Winda " + elevator.getElevatorId() +  " wezwana przez pasazera " + riderId + " na pietrze " + fromFloor +" by jechac w dol");
        }
        elevator.requestFloor(fromFloor, isGoingUp, riderId);
        while(elevator.isGoingUp() != isGoingUp){
            elevator.pass();
            synchronized (this){
                while(elevator.isDoorOpen()){
                    try {
                        System.out.println("Winda" + elevator.getElevatorId() + " nie jedzie w tym kierunku.");
                        wait();
                    }
                    catch (InterruptedException e){}
                }
            }
            elevator.requestFloor(fromFloor, isGoingUp, riderId);
        }
        return elevator;
    }

    public Elevator callUp(int fromFloor, int riderId){
        return call(fromFloor, true, riderId);
    }

    public Elevator callDown(int fromFloor, int riderId){
        return call(fromFloor, false, riderId);
    }
}