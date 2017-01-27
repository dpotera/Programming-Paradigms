package Cwiczenia.W11.zad5;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher extends Thread{
    private int dinners,left,right;
    private Semaphore[] forks;
    private Semaphore doorMan;
    private Random random;

    public Philosopher(int id, Semaphore[] forks, Semaphore doorMan) {
        super("Philosopher "+id);
        this.forks = forks;
        this.doorMan = doorMan;
        dinners = 0;
        left = id;
        right = (id+1)%4;
        random = new Random();
    }

    private void meditate(){
        try {
            print(" meditating");
            sleep(random.nextInt(500));
            eat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat(){
        try {
            print(" waiting for seat");
            doorMan.acquire();
            print(" taken seat");
            forks[left].acquire();
            print(" taken left["+left+"] fork");
            forks[right].acquire();
            print(" taken left["+right+"] fork");
            sleep(100);
            forks[left].release();
            notifyAll();
            print(" released left["+left+"] fork");
            forks[right].release();
            notifyAll();
            print(" released right["+right+"] fork");
            doorMan.release();
            print(" released seat, eaten = "+ ++dinners);
            meditate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        eat();
    }

    private void print(String msg){
        System.out.println(Thread.currentThread().getName()+msg);
    }
}
