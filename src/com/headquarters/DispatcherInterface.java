package com.headquarters;

import com.drone.Drone;

public interface DispatcherInterface extends Runnable{

    void sendMessageToDrone();
    void addDrone(Drone drone);
}
