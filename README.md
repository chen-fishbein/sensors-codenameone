# sensors-codenameone
Sensors library for Codename One, implemented on Android and iOS.


##Usage

### Step 1. Get instance

#####Normal usage using default sensor speed.

    SensorsManager gyroscope = SensorsManager.getSensorsManager(SensorsManager.TYPE_GYROSCOPE);
    SensorsManager accelerometer = SensorsManager.getSensorsManager(SensorsManager.TYPE_ACCELEROMETER);
    SensorsManager magnetic = SensorsManager.getSensorsManager(SensorsManager.TYPE_MAGNETIC);

#####Custom sensor speed (sampling rate)
You can also add your desired sensor speed (in microseconds) as a second parameter. For example, if you want to receive (approximately) **10 samples/second** (10 hz) you would use 1000000/**10** = 100000 microseconds, like this:

    SensorsManager accelerometer = SensorsManager.getSensorsManager(SensorsManager.TYPE_ACCELEROMETER, 100000);

###Step 2. Register listener (do stuff with sensor data)

    if (accelerometer != null) {
        accelerometer.registerListener(new SensorListener() {
            public void onSensorChanged(long timeStamp, float x, float y, float z) {
                //do your stuff here...
            }
        });
    }
