package com;

import com.drone.Drone;
import com.headquarters.Dispatcher;

import java.util.concurrent.*;

public class Demo {

    public static void main(String[] args) {
//        startProgram();

        Dispatcher dispatcher = new Dispatcher();
        Drone drone1 = new Drone(1, dispatcher);
        dispatcher.addDrone(drone1);


        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.submit(dispatcher);
        service.submit(drone1);
        service.shutdown();

    }
}
