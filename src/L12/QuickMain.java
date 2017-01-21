package L12;

import java.util.Random;

public class QuickMain{
    public static void main(String[] args) throws InterruptedException {

        int[] arr = {7,6,5,9,2,1,3,8,4};
        print(arr);
        Quick.quicksort(arr);
        print(arr);

/*
        Random random = new Random();
        final int size = 10000;
        int[] big = new int[size];
        for(int i=0; i<size; i++)
            big[i] = random.nextInt(size/2);
        Quick.quicksort(big);
        */
    }

    public static void print(int[] arr){
        for (int i:arr)
            System.out.print(i+", ");
        System.out.println();
    }
}