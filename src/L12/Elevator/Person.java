package L12.Elevator;

import static L12.Elevator.Direction.*;

public class Person implements Runnable{
    private int personID;
    private int initFloor;
    private int destinationFloor;
    private Building building;
    private Direction direction;
    private Thread thread;

    public Person(int personID, int initFloor, int destinationFloor, Building building) {
        this.personID = personID;
        this.initFloor = initFloor;
        this.destinationFloor = destinationFloor;
        this.building = building;
        this.direction = initFloor < destinationFloor ? UP : DOWN;
        this.thread = new Thread(this,Integer.toString(personID));
    }

    @Override
    public void run() {
        building.notifyWaiting(initFloor,direction);
        try {
            wait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
