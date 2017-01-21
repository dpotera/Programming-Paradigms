package W11.zad5;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args){

        Semaphore doorMan = new Semaphore(4,true);

        Semaphore[] forks = new Semaphore[5];
        for(int i=0; i<forks.length; i++)
            forks[i] = new Semaphore(1);

        Philosopher tmp;
        for (int i=0; i<5; i++) {
            tmp = new Philosopher(i,forks,doorMan);
            tmp.start();
        }
    }
}
