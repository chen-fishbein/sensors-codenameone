/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.sensors;

import com.codename1.system.NativeLookup;
import com.codename1.ui.Display;

/**
 * This is the SensorsManager
 * 
 * @author Chen
 */
public class SensorsManager {

    public static final int TYPE_GYROSCOPE = 0;

    public static final int TYPE_ACCELEROMETER = 1;

    public static final int TYPE_MAGNETIC = 2;
    
    private int type;

    private int samplingRate;

    private SensorListener listener;

    private static SensorsManager gyro;

    private static SensorsManager accel;

    private static SensorsManager magnetic;
    
    private static SensorsNative sensors;
    
    private SensorsManager(int type) {
        this.type = type;
    }

    /**
     * Returns SensorsManager instance.
     * @param type one of the following TYPE_GYROSCOPE, TYPE_ACCELEROMETER
     * or TYPE_MAGNETIC
     * @param samplingRate how often (in microseconds) you want to see sensor events reported. If null, default sampling rate will be used.
     * @return SensorsManager instance or null if this sensor does not exist on
     * the device.
     */ 
    public static SensorsManager getSensorsManager(int type, int samplingRate) {
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
            if(samplingRate > 0)
                sensors.setSamplingRate(samplingRate);
            return sensor;
        }
        //not supported
        return null;
    }

    public static SensorsManager getSensorsManager(int type) {
        return getSensorsManager(type, 0);
    }

    /**
     * Functional alias (with a typo) for getSensorsManager(int type). Left around to avoid errors with existing code.
     */
    public static SensorsManager getSenorsManager(int type) {
        return getSensorsManager(type, 0);
    }

    /**
     * Registers a SensorListener to get sensor notifications from the device
     */ 
    public void registerListener(SensorListener listener) {
        if(this.listener == null && listener == null) {
            return;
        }
        if(listener != null){
            sensors.registerListener(type);
        }
        this.listener = listener;
    }

    /**
     * De-registers a SensorListener from getting callbacks from the device
     */ 
    public void deregisterListener(SensorListener listener) {
        sensors.deregisterListener(type);        
        this.listener = null;
    }

    /**
     * This method is used by the underlying native platform.
     */ 
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
