#import "com_codename1_sensors_SensorsNativeImpl.h"
#import "com_codename1_sensors_SensorsManager.h"

@implementation com_codename1_sensors_SensorsNativeImpl

CMMotionManager *motionManager = nil;

-(void) init : (int) param {
    if(motionManager == nil){
        motionManager = [[CMMotionManager alloc] init];
    }
    if (param == 0) {
        motionManager.gyroUpdateInterval = 0.2;
    } else if (param == 1) {
        motionManager.accelerometerUpdateInterval = 0.2;
    }else if (param == 2) {
        motionManager.magnetometerUpdateInterval = 0.2;
    }
}

-(void) deregisterListener : (int) param {
    
    if (param == 0) {
        [motionManager stopGyroUpdates];
    }else if (param == 1) {
        [motionManager stopAccelerometerUpdates];
    }else if (param == 2) {
        [motionManager stopMagnetometerUpdates];
    }
 
}

-(void) registerListener : (int) param {
    if (param == 0) {
        if ([motionManager isGyroAvailable]) {
            NSOperationQueue *queue = [[NSOperationQueue alloc] init];

            [motionManager startGyroUpdatesToQueue : queue withHandler : ^(CMGyroData *gyroData, NSError * error) {

                dispatch_async(dispatch_get_main_queue(), ^ {
                    com_codename1_sensors_SensorsManager_onSensorChanged___int_float_float_float(CN1_THREAD_GET_STATE_PASS_ARG 0,
                    gyroData.rotationRate.x,
                    gyroData.rotationRate.y,
                    gyroData.rotationRate.z);
                });
            }
            ];
        }
        
    } else if (param == 1) {
        if ([motionManager isAccelerometerAvailable]) {
            NSOperationQueue *queue = [[NSOperationQueue alloc] init];

            [motionManager startAccelerometerUpdatesToQueue : queue withHandler : ^(CMAccelerometerData *accelerometerData, NSError * error) {

                dispatch_async(dispatch_get_main_queue(), ^ {
                    com_codename1_sensors_SensorsManager_onSensorChanged___int_float_float_float(CN1_THREAD_GET_STATE_PASS_ARG 1,
                    accelerometerData.acceleration.x * -9.81,
                    accelerometerData.acceleration.y * -9.81,
                    accelerometerData.acceleration.z * -9.81);
                });
            }
            ];
        }
    } else if (param == 2) {
        if ([motionManager isMagnetometerAvailable]) {
            NSOperationQueue *queue = [[NSOperationQueue alloc] init];

            [motionManager startMagnetometerUpdatesToQueue : queue withHandler : ^(CMMagnetometerData *magnetometerData, NSError * error) {

                dispatch_async(dispatch_get_main_queue(), ^ {
                    com_codename1_sensors_SensorsManager_onSensorChanged___int_float_float_float(CN1_THREAD_GET_STATE_PASS_ARG 2,
                    magnetometerData.magneticField.x,
                    magnetometerData.magneticField.y,
                    magnetometerData.magneticField.z);
                });
            }
            ];
        }
    }

}

-(BOOL) isSupported {
    return YES;
}

@end
