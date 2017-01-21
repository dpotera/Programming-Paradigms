package com.company;

/**
 * Created by kordianledzion on 20.01.2016.
 */

import java.util.TreeSet;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

public class Elevator implements Runnable {

    private int elevatorId;
    private int numFloors;
    private int currentFloor;
    private int numRiders;
    private int elevatorCapacity;
    private boolean isGoingUp;
    private boolean isDoorOpen;
    private Building building;
    private TreeSet<Integer> upRequests;
    private TreeSet<Integer> downRequests;
    private EventBarrier[] floors;
    private Thread thread;


    public Elevator(int id, int numFloors, int elevatorCapacity, Building building){
        this.elevatorId = id;
        this.numFloors = numFloors;
        this.currentFloor = -1;
        this.numRiders = 0;
        this.elevatorCapacity = elevatorCapacity;
        this.isGoingUp = true;
        this.isDoorOpen = false;
        this.building = building;
        this.upRequests = new TreeSet<Integer>();
        this.downRequests = new TreeSet<Integer>();

        this.floors = new EventBarrier[numFloors];
        for (int i = 0; i < this.numFloors; i++)
        {
            this.floors[i] = new EventBarrier();
        }
        this.thread = new Thread(this, ""+this.elevatorId);
    }

    public Thread getThread()
    {
        return this.thread;
    }

    public boolean reachedFullCapacity()
    {
        return this.numRiders == this.elevatorCapacity;
    }

    public boolean isGoingUp()
    {
        return this.isGoingUp;
    }

    public boolean isDoorOpen()
    {
        return this.isDoorOpen;
    }

    public int getElevatorId()
    {
        return this.elevatorId;
    }
    /**
     * Elevator control interface: invoked by Elevator thread.
     */

	/* Signal incoming and outgoing riders */
    public void openDoors(){
        this.isDoorOpen = true;
        System.out.println("Winda otwiera drzwi na pietrze " + this.currentFloor);
        int numWaiters = this.floors[this.currentFloor].waiters();
        if (numWaiters == 1){
            System.out.println("Pietro " + this.currentFloor + " ma " + numWaiters + " pasazerow czekajacych na winde " + this.elevatorId);
        } else {
            System.out.println("Pietro " + this.currentFloor + " has " + numWaiters + " pasazerow czekajacych na winde " + this.elevatorId);
        }
        this.floors[this.currentFloor].raise(this.elevatorId, this.currentFloor);
    }

    /**
     * When elevatorCapacity is reached or the outgoing riders are exited and
     * incoming riders are in.
     */
    public void closedDoors()
    {
        this.isDoorOpen = false;
        synchronized (this.building){
            this.building.notifyAll();
        }
        System.out.println("Winda " + this.elevatorId +" zamknela drzwi na pietrze " + this.currentFloor);
    }

    /**
     * Go to a requested floor
     *
     * @param floor floor elevator is requested to visit
     */
    public synchronized void visitFloor(int floor)
    {
        if (this.isGoingUp)
        {
            this.upRequests.remove(floor);
        }
        else
        {
            this.downRequests.remove(floor);
        }
        this.building.removeStop(floor, this.isGoingUp);
        this.currentFloor = floor;
        if(this.isGoingUp)
        {
            System.out.println("Winda " + this.elevatorId + " jedzie do gory na pietro " + this.currentFloor);
        }
        else
        {
            System.out.println("Winda " + this.elevatorId + " jedzie na dol na pietro " + this.currentFloor);
        }
    }

    /**
     * Enter the elevator
     *
     * @param riderId id of rider
     * */
    public synchronized boolean enter(int riderId)
    {
        this.numRiders++;
        System.out.println("Pasazer " + riderId + " wsiada do windy na pietrze " + this.currentFloor);
        this.floors[this.currentFloor].complete(this.elevatorId, this.currentFloor);
        return true;
    }

    /**
     * Exit the elevator
     *
     * @param riderId
     */
    public synchronized void exit(int riderId)
    {
        this.numRiders--;
        System.out.println("Pasazer " + riderId + " wysiadl z windy na pietrze " + this.currentFloor);
        this.floors[this.currentFloor].complete(this.elevatorId, this.currentFloor);
    }

    /**
     * Pass elevator if elevator not going in rider's desired direction
     */
    public void pass()
    {
        this.floors[this.currentFloor].complete(this.elevatorId, this.currentFloor);
    }

    /**
     * Request floor
     * @param floorRequested
     * @param riderId
     */
    public void requestFloor(int floorRequested, int riderId)
    {
        boolean goingUp = floorRequested > this.currentFloor;
        requestFloor(floorRequested, goingUp, riderId);
    }

    /**
     * Request floor
     * @param floorRequested
     * @param goingUp
     * @param riderId
     */
    public void requestFloor(int floorRequested, boolean goingUp, int riderId)
    {
        synchronized (this)
        {
            if(goingUp)
            {
                this.upRequests.add(floorRequested);
                System.out.println("Winda " + this.elevatorId + " otrzymala zapytanie od pasazera " + riderId + " aby jechac do gory na pietro " + floorRequested);
            }
            else
            {
                this.downRequests.add(floorRequested);
                System.out.println("Winda " + this.elevatorId + " otrzymala zapytanie od pasazera " + riderId + " aby jechac na dol na pietro " + floorRequested);
            }
            this.building.addStop(floorRequested, goingUp, this);
            notifyAll();
        }
        this.floors[floorRequested].arrive(this.elevatorId, floorRequested, riderId);
    }

    /**
     * Elevator gets the floor of its next request
     * @return nearest floor of the next request
     */
    private synchronized int getNextFloor()
    {
        if (isGoingUp())
        {
            Integer next = this.upRequests.higher(this.currentFloor);
            boolean hasNext = next != null;
            if (hasNext)
            {
                System.out.println("Winda " + this.elevatorId + " wykonuje zapytanie od paszaera aby jechac do gory na pietro " + next);
                return next;
            }
            else
            {
                this.isGoingUp = false;
                this.currentFloor = this.floors.length;
                next = this.downRequests.lower(this.currentFloor);
                boolean hasNextRequestFromLowerFloor = next != null;
                if (hasNextRequestFromLowerFloor)
                {
                    System.out.println("Winda " + this.elevatorId + " zaczyna zapytanie od pasazera aby jechac na dol na pietro " + next);
                    return next;
                }
                else
                {
                    return -1;
                }
            }
        }
        else
        {
            Integer next = this.downRequests.lower(this.currentFloor);
            boolean hasNext = next != null;
            if (hasNext)
            {
                System.out.println("Winda " + this.elevatorId + " konczy zapytanie od pasazera aby jechac na dol na pietro " + next);
                return next;
            } else {
                this.isGoingUp = true;
                this.currentFloor = -1;
                next = this.upRequests.higher(this.currentFloor);
                boolean hasNextRequestFromHigherFloor = next != null;
                if (hasNextRequestFromHigherFloor)
                {
                    System.out.println("Winda " + this.elevatorId + "  konczy zapytanie od pasazera aby jechac na gore na pietro " + next);
                    return next;
                }
                else
                {
                    return -1;
                }
            }
        }
    }

    public void run()
    {
        while(true)
        {
            if(interrupted()) return;

            int nextFloor = getNextFloor();
            boolean noMoreRequests = this.upRequests.isEmpty() && this.downRequests.isEmpty();

            if (noMoreRequests){
                synchronized (this){
                    this.currentFloor = -1;
                    try
                    {
                        System.out.println("Winda " + this.elevatorId + " czeka na zapytania od pasazerow");
                        wait();
                    }
                    catch (InterruptedException e)
                    {
                        return;
                    }
                }
            }
            else if (nextFloor != -1)
            {
                System.out.println("Winda " + this.elevatorId + " przemieszcza sie do gory na pietro " + nextFloor);
                visitFloor(nextFloor);
                openDoors();
                closedDoors();
            }
        }
    }
}