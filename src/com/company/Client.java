package com.company;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Client extends Thread{
    private String name;
    public int serviceSeconds;


    public static Semaphore pumps;
    private int pump;
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
        Main.print(this.name+" Arrive");
        try {
            pumps.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Occupied();
    }

    public void Occupied(){
        generateRandomTime();
        this.pump = pumps.availablePermits()+1;
        Main.print("Pump "+this.pump + ":" + this.name + " Occupied");
        try {
            this.sleep(serviceSeconds *100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serve();
    }

    public void serve(){
        generateRandomTime();
        Main.print("Pump "+this.pump + ":" + this.name + " being served");
        try {
            this.sleep(serviceSeconds *100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        paying();
    }

    public void paying(){
        generateRandomTime();
        Main.print("Pump "+this.pump + ":" + this.name + " Paying");
        try {
            this.sleep(serviceSeconds *100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        leave();
    }

    public void leave(){
        Main.print(this.name+" Leaving");
        pumps.release();

    }

    @Override
    public void run() {
        arrive();
    }
}
