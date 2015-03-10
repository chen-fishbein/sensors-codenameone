/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sensors;

import com.codename1.system.NativeLookup;
import com.codename1.ui.Display;

/**
 *
 * @author Chen
 */
public class SensorsManager {

    public static final int TYPE_GYROSCOPE = 0;

    public static final int TYPE_ACCELEROMETER = 1;

    public static final int TYPE_MAGNETIC = 2;
    
    private int type;

    private SensorListener listener;

    private static SensorsManager gyro;

    private static SensorsManager accel;

    private static SensorsManager magnetic;
    
    private static SensorsNative sensors;
    
    private SensorsManager(int type) {
        this.type = type;
    }

    public static SensorsManager getSenorsManager(int type) {
        sensors = (SensorsNative) NativeLookup.create(SensorsNative.class);
        if (sensors != null && sensors.isSupported()) {
            SensorsManager sensor = new SensorsManager(type);
            if (type == TYPE_ACCELEROMETER) {
                accel = sensor;
            } else if (type == TYPE_GYROSCOPE) {
                gyro = sensor;
            }else if (type == TYPE_MAGNETIC) {
                magnetic = sensor;
            }
            sensors.init(type);
            return sensor;
        }
        //not supported
        return null;
    }

    public void registerListener(SensorListener listener) {
        if(this.listener == null && listener == null){
            return;
        }
        if(listener != null){
            sensors.registerListener(type);
        }
        this.listener = listener;
    }
    
    public void deregisterListener(SensorListener listener) {
        sensors.deregisterListener(type);        
        this.listener = null;
    }

    public static void onSensorChanged(int type, final float x, final float y, final float z) {
        final long timeStamp = System.currentTimeMillis();
        if (type == TYPE_ACCELEROMETER) {
            if (accel != null && accel.listener != null) {
                Display.getInstance().callSerially(new Runnable() {

                    public void run() {
                        accel.listener.onSensorChanged(timeStamp, x, y, z);
                    }
                });
            }
        } else if (type == TYPE_GYROSCOPE) {
            if (gyro != null && gyro.listener != null) {
                Display.getInstance().callSerially(new Runnable() {

                    public void run() {
                        gyro.listener.onSensorChanged(timeStamp, x, y, z);
                    }
                });
            }
        }else if (type == TYPE_MAGNETIC) {
            if (magnetic != null && magnetic.listener != null) {
                Display.getInstance().callSerially(new Runnable() {

                    public void run() {
                        magnetic.listener.onSensorChanged(timeStamp, x, y, z);
                    }
                });
            }
        }
    }

}
