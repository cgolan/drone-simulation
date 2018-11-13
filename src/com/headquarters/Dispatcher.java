package com.headquarters;

import com.drone.Drone;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Dispatcher implements DispatcherInterface {

    private boolean isNotDone = true;
    private boolean cannotProcessMessage = false;

    private ArrayBlockingQueue<String> geoPoints1 = new ArrayBlockingQueue<>(100);
    private ArrayBlockingQueue<String> unprocessedMessages = new ArrayBlockingQueue<>(100);
    private List<Drone> drones = new ArrayList<>();


    public Dispatcher(){
        prepareDronesForTakeOff();
    }

    private void prepareDronesForTakeOff (){
        fillQueue();
    }

    private void fillQueue(){
        for (int i = 0; i < 100; i++) {
            geoPoints1.add("Message " + i);
        }
    }
    //Creation complete with coordinates set in place


    @Override
    public void run() {
        sendMessageToDrone();
        while (!drones.get(0).getQueue().isEmpty()){
        }
        isNotDone = false;
    }

    @Override
    public void sendMessageToDrone() {
        while(!geoPoints1.isEmpty()) {
            boolean isReceived = false;
            System.out.println();
            while (!isReceived){
                System.out.println("Dispatcher attempting to send " + geoPoints1.peek());
                isReceived = offerMessage(drones.get(0));
            }
            if(cannotProcessMessage) {
                System.out.println("Dispatcher will move " + unprocessedMessages.peek() + " to another queue");
                cannotProcessMessage = false;
                continue;
            }
            System.out.println(geoPoints1.peek() +  " sent");
            geoPoints1.poll();
        }
    }

    private boolean offerMessage(Drone drone) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return drone.getQueue().offer(geoPoints1.peek());
    }

    @Override
    public void addDrone(Drone drone) {
        drones.add(drone);
    }


    public boolean isNotDone() {
        return isNotDone;
    }

    public void droneCannotProcessMessage(int droneId, String message) {
        //to send this message to another drone
        cannotProcessMessage = true;
        unprocessedMessages.add(message);
    }
}
