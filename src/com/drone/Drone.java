package com.drone;

import com.headquarters.Dispatcher;

import java.util.concurrent.ArrayBlockingQueue;


public class Drone implements DroneInterface{

    private Dispatcher dispatcher;
    private ArrayBlockingQueue<String> queue;

    private int droneId;
    private boolean cannotProcess = true;

    public Drone(int droneId, Dispatcher dispatcher) {
        this.droneId =  droneId;
        this.dispatcher = dispatcher;
        queue = new ArrayBlockingQueue<>(1);
    }

    @Override
    public void onMessage() {
        if(!queue.isEmpty()) {
            System.out.println("Drone" + " has received " + queue.peek());
            if(queue.peek().equals("Message 0")) {
                System.out.println("Drone cannot process " + queue.peek() + " sending back to Dispatcher");
                dispatcher.droneCannotProcessMessage(droneId, queue.peek());
                queue.poll();
            }
            else
                System.out.println("Drone" + " has processed " + queue.poll());
            System.out.println();
        }
    }

    @Override
    public void run() {
        while (dispatcher.isNotDone()) {
            onMessage();
        }
        System.out.println();
        System.out.println("Drone" +  droneId + " returning to base, over and out");
    }

    public int getDroneId() {
        return droneId;
    }

    public ArrayBlockingQueue<String> getQueue() {
        return queue;
    }

    private boolean cannotProcessMessage(){
        return true;
    }

}
