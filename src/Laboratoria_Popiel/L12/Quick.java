package Laboratoria_Popiel.L12;

public class Quick extends Thread{
    private int array[];
    int x,y;

    public Quick(int[] array, int x, int y) {
        this.array = array;
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        quicksort();
    }

    public static void quicksort(int[] array){
        Thread thread = new Quick(array,0,array.length-1);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void quicksort() {
        int i, j, v, temp;
        i = x;
        j = y;
        v = array[(x + y) / 2];
        do {
            while (array[i] < v)
                i++;
            while (v < array[j])
                j--;
            if (i <= j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        Thread threadLeft = null;
        Thread threadRight = null;
        if (x < j){
            threadLeft = new Quick(array,x,j);
            threadLeft.start();
        }
        if (i < y){
            threadRight = new Quick(array,i,y);
            threadRight.start();
        }
        try {
            if (threadLeft != null) threadLeft.join();
            if (threadRight != null) threadRight.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

