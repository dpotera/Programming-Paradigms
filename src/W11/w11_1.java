package W11;

import java.util.concurrent.Semaphore;

class IntCell {
    private int n = 0;
    public int getN() {return n;}
    public void setN(int n) {this.n = n;}
}

class Count extends Thread {
    private static final IntCell n = new IntCell();
    private static Semaphore semaphore = new Semaphore(1);


    public void run1() {
        synchronized (n){
            int temp;
            for (int i = 0; i < 200000; i++) {
                temp = n.getN();
                n.setN(temp + 1);
            }
        }
    }

    @Override public void run() {
        int temp;
        try{
            semaphore.acquire();
            for (int i = 0; i < 200000; i++) {
                temp = n.getN();
                n.setN(temp + 1);
            }
            semaphore.release();
        } catch (InterruptedException e){

        }
    }

    public static void main(String[] args) {
        Count p = new Count();
        Count q = new Count();
        p.start();
        q.start();
        try { p.join(); q.join(); }
        catch (InterruptedException e) { }
        System.out.println("The value of n is " + n.getN());
    }
}