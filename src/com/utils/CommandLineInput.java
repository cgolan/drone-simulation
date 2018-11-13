package com.utils;

import java.util.Scanner;

public class CommandLineInput {

    public static void startProgram(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n######################## DRONE SIMULATION ########################\n");
        System.out.println("Welcome to Drone Simulation\n");
        System.out.println("If you want to start press 1 \notherwise press anything else to quit");
        if(scanner.next().equals("1"))
            System.out.println("Lets start");
        else
            System.out.println("Bye");
    }
}
