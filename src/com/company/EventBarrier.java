package com.company;

/**
 * Created by kordianledzion on 20.01.2016.
 */

public class EventBarrier {
    private int waiters;
    private boolean eventInProgress;

    public EventBarrier(){
        this.waiters = 0;
        this.eventInProgress = false;
    }

    public synchronized void arrive(int elevatorId, int floor, int riderId){
        this.waiters++;
        if (this.eventInProgress) {
            return;
        }
        while(!this.eventInProgress){
            try {
                if (this.waiters == 1){
                    System.out.println(this.waiters + " pasazer czeka na winde " + elevatorId +" na pietrze " + floor);
                }
                else {
                    System.out.println(this.waiters + " pasazerowie czekaja na winde " + elevatorId +" na pietrze " + floor);
                }
                super.wait();
            }
            catch (InterruptedException e){
                System.out.println("InterruptedException caught");
            }
        }
    }

    public synchronized void raise(int elevatorId, int floor){
        if (this.eventInProgress){
            return;
        }
        System.out.println("Winda " + elevatorId + " otworzyla drzwi na pietrze " + floor);
        this.eventInProgress = true;
        notifyAll(); //komunikacja miedzy watkami
        while(this.waiters != 0){
            try {
                System.out.println("Winda " + elevatorId +" czeka na pasazerow wsiadajacych na pietrze " + floor);
                super.wait();
            }
            catch (InterruptedException e){
                System.out.println("InterruptedException caught");
            }
        }
        this.eventInProgress = false;
        System.out.println("Winda " + elevatorId + " zamyka drzwi na pietrze " + floor);
    }

    public synchronized void complete(int elevatorId, int floor){
        this.waiters--;
        if (this.waiters == 0){
            System.out.println("Nie ma wiecej pasazerow czekajacych na winde " + elevatorId + " na pietrze " + floor);
            notifyAll();
        }
        else if (this.waiters == 1){
            System.out.println(this.waiters + " pasazer zostal aby wejsc do windy " + elevatorId + "na pietrze " + floor);
        }
        else {
            System.out.println(this.waiters + " pasazerow zostalo aby wejsc do windy " + elevatorId + "na pietrze " + floor);
        }

    }

    public int waiters(){
        return this.waiters;
    }
}