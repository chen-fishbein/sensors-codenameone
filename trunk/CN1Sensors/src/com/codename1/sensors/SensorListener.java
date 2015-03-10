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
    
    public void onSensorChanged(long timeStamp, float x, float y, float z);
            
}
