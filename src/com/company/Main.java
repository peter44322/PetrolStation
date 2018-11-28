package com.company;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        print("What is number of Pumps? ");
        int pumpsCount = input.nextInt();
        print("Number of Clients : ");
        int clientsCount = input.nextInt();
        print("Clients’ names : ");
        input.nextLine();
        String clientsNamesString = input.nextLine();

        String[] clientsNamesArray = clientsNamesString.split("\\s+");


        Semaphore pumps = new Semaphore(pumpsCount);
        Client.pumps = pumps;
        Client[] clients = new Client[clientsCount];

        for (int i = 0;i<clientsCount;i++){
            clients[i] = new Client(clientsNamesArray[i]);

            clients[i].start();

        }
    }

    public static void print(String something){
        System.out.println(something);
        // very long statement for so small function (I HATE JAVA)
    }
}
