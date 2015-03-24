# sensors-codenameone
Sensors library for Codename One, implemented on Android and iOS.


##Usage
               SensorsManager sensor = SensorsManager.getSenorsManager(SensorsManager.TYPE_ACCELEROMETER);
                if (sensor != null) {
                    sensor.registerListener(new SensorListener() {

                        public void onSensorChanged(long timeStamp, float x, float y, float z) {

                         //do your stuff here...

                        }
                    });
                }
