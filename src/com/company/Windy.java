package com.company;

/**
 * Created by kordianledzion on 20.01.2016.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Windy {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Uruchamianie windy");
        System.out.println();

        int numFloors = 0;
        int numElevators = 0;
        int numRiders = 0;
        int elevatorCapacity = 0;
        List<Rider> riderList = new ArrayList<Rider>();


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Podaj liczbe pieter w budynku: ");
        try {
            numFloors = Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException e){
            System.err.println("Zly format!");
        }
        System.out.print("Podaj liczbe wind: ");
        try {
            numElevators = Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException e){
            System.err.println("Zly format!");
        }
        System.out.print("Podaj liczbe wszystkich pasazerow: ");
        try {
            numRiders = Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException e){
            System.err.println("Zly format!");
        }
        System.out.print("Podaj ilosc miejsc w 1 windzie: ");
        try {
            elevatorCapacity = Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException e){
            System.err.println("Zly format!");
        }

        bufferedReader.close();

        System.out.println();
        System.out.println("Twoje dane:");
        System.out.println("-----------");
        System.out.println("Liczba pieter w budynku: "  + numFloors);
        System.out.println("Liczba wind: " + numElevators);
        System.out.println("Liczba pasazerow: " + numRiders);
        System.out.println("Max liczba osob w windzie: " + elevatorCapacity);

        File log = new File("elevator.log");
        System.setOut(new PrintStream(new FileOutputStream(log)));

        Building building = new Building(numFloors, elevatorCapacity, numElevators);

        Random startFloor = new Random();
        Random stopFloor = new Random();
        for (int i = 0; i < numRiders; i++){
            Rider rider = new Rider(i, building, startFloor.nextInt(numFloors), stopFloor.nextInt(numFloors));
            riderList.add(rider);
        }

        //Start rider threads
        for (Rider rider : riderList){
            rider.getThread().start();
        }

        //Start elevators
        for (Elevator elevator : building.getElevators()){
            elevator.getThread().start();
        }

        //Wait for rider threads to terminate
        for (Rider rider : riderList){
            rider.getThread().join();
        }

        //Stop elevators
        for (Elevator elevator : building.getElevators()){
            elevator.getThread().interrupt();
        }

    }
}
