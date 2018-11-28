package com.company;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Client extends Thread{
    private String name;
    public int serviceSeconds;


    public static Semaphore pumps;
    public static int pumpNumber = 0;
    public int myPump = 0;
    public static int minSecond = 10;
    public static int maxSecond = 60;


    Client(String name){
        this.name = name;


    }

    private void generateRandomTime(){
        Random random = new Random();
        serviceSeconds = random.nextInt(maxSecond) + minSecond;
    }

    public void arrive(){
        generateRandomTime();
        try {
            this.sleep(serviceSeconds *100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(pumps.availablePermits() != 0){
            Main.print(this.name+" Arrive");
        }else {
            Main.print(this.name+" Arrive and Waiting");
        }
        try {
            pumps.acquire();
            myPump = ++pumpNumber;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Occupied();
    }

    public void Occupied(){
        generateRandomTime();

        Main.print("Pump "+this.myPump + ":" + this.name + " Occupied");
        try {
            this.sleep(serviceSeconds *100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serve();
    }

    public void serve(){
        generateRandomTime();
        Main.print("Pump "+this.myPump + ":" + this.name + " being served");
        try {
            this.sleep(serviceSeconds *100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        paying();
    }

    public void paying(){
        generateRandomTime();
        Main.print("Pump "+this.myPump + ":" + this.name + " Paying");
        try {
            this.sleep(serviceSeconds *100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        leave();
    }

    public void leave(){
        Main.print(this.name+" Leaving");
        pumpNumber--;
        pumps.release();
    }

    @Override
    public void run() {
        arrive();
    }
}
