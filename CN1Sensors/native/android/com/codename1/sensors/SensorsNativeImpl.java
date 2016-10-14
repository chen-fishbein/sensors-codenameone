package com.codename1.sensors;

import com.codename1.impl.android.AndroidNativeUtil;
import android.hardware.*;
import android.content.Context;

public class SensorsNativeImpl {

    private SensorManager sensorManager;
    private Sensor accel;
    private Sensor gyro;
    private Sensor magnet;
    private android.hardware.SensorEventListener accelListener;
    private android.hardware.SensorEventListener gyroListener;
    private android.hardware.SensorEventListener magnetListener;

    private int samplingRate = SensorManager.SENSOR_DELAY_NORMAL;

    public void init(int type) {
        if (sensorManager == null) {
            sensorManager = (SensorManager) AndroidNativeUtil.getActivity().getSystemService(Context.SENSOR_SERVICE);
        }
        if (type == SensorsManager.TYPE_GYROSCOPE) {
            gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        } else if (type == SensorsManager.TYPE_ACCELEROMETER) {
            accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }else if (type == SensorsManager.TYPE_MAGNETIC) {
            magnet = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }
    }

    public void setSamplingRate(int desiredSamplingRate) {
        samplingRate = desiredSamplingRate;
    }

    public void deregisterListener(int type) {
        if (type == SensorsManager.TYPE_GYROSCOPE) {
            sensorManager.unregisterListener(gyroListener);
        } else if (type == SensorsManager.TYPE_ACCELEROMETER) {
            sensorManager.unregisterListener(accelListener);
        }else if (type == SensorsManager.TYPE_MAGNETIC) {
            sensorManager.unregisterListener(magnetListener);
        }
    }

    public void registerListener(int type) {
        if (type == SensorsManager.TYPE_GYROSCOPE) {
            gyroListener = new android.hardware.SensorEventListener() {

                public void onSensorChanged(SensorEvent event) {
                    com.codename1.sensors.SensorsManager.onSensorChanged(com.codename1.sensors.SensorsManager.TYPE_GYROSCOPE,
                            event.values[0], event.values[1], event.values[2]);
                }

                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                }

            };
            sensorManager.registerListener(gyroListener, gyro, samplingRate);
        } else if (type == SensorsManager.TYPE_ACCELEROMETER) {
            accelListener = new android.hardware.SensorEventListener() {
 
                public void onSensorChanged(SensorEvent event) {
                    com.codename1.sensors.SensorsManager.onSensorChanged(com.codename1.sensors.SensorsManager.TYPE_ACCELEROMETER,
                            event.values[0], event.values[1], event.values[2]);
                }

                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                }

            };
            sensorManager.registerListener(accelListener, accel, samplingRate);
        } else if (type == SensorsManager.TYPE_MAGNETIC) {
            magnetListener = new android.hardware.SensorEventListener() {
 
                public void onSensorChanged(SensorEvent event) {
                    com.codename1.sensors.SensorsManager.onSensorChanged(com.codename1.sensors.SensorsManager.TYPE_MAGNETIC,
                            event.values[0], event.values[1], event.values[2]);
                }

                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                }

            };
            sensorManager.registerListener(magnetListener, magnet, samplingRate);
        }
    }

    public boolean isSupported() {
        return true;
    }

}
