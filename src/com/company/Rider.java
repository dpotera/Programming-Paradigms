package com.company;

/**
 * Created by kordianledzion on 20.01.2016.
 */

public class Rider implements Runnable {

    private int riderId;
    private Building building;
    private int startFloor;
    private int stopFloor;
    private Thread thread;

    public Rider(int riderId, Building building, int startFloor, int stopFloor){
        this.riderId = riderId;
        this.building = building;
        this.startFloor = startFloor;
        this.stopFloor = stopFloor;
        this.thread = new Thread(this, ""+this.riderId);
    }

    public Thread getThread(){
        return this.thread;
    }

    public void run(){
        Elevator elevator;
        while(true){
            if(startFloor < stopFloor)
            {
                System.out.println("Pasazer " + this.riderId + " chce jechac do gory z pietra " + startFloor);
                elevator = building.callUp(startFloor, riderId);
            }
            else
            {
                System.out.println("Pasazer " + this.riderId + " chce jechac na dol z pietra " + startFloor);
                elevator = building.callDown(startFloor, riderId);
            }
            System.out.println("Pasazer " + this.riderId + " ma zamiar wejsc do winy " + elevator.getElevatorId());

            if (elevator.enter(this.riderId))
            {
                System.out.println("Pasazer " + this.riderId + " wszedl do windy " + elevator.getElevatorId());
                break;
            }
        }

        System.out.println("Pasazer " + this.riderId + " z windy " + elevator.getElevatorId()+ " chce jechac na pietro " + stopFloor);
        elevator.requestFloor(stopFloor, this.riderId);

        System.out.println("Pasazer " + this.riderId + " wychodzi z winy " + elevator.getElevatorId() + " na pietrze " + stopFloor);
        elevator.exit(this.riderId);

    }
}