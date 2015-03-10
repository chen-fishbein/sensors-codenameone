/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sensors;

/**
 *
 * @author Chen
 */
public interface SensorListener {
    
    /**
     * This method called when a sensor event is being sent from the device.
     * @param timeStamp event time
     * @param x value
     * @param y value
     * @param z value
     */ 
    public void onSensorChanged(long timeStamp, float x, float y, float z);
            
}
