#import <Foundation/Foundation.h>
#import <CoreMotion/CoreMotion.h>


@interface com_codename1_sensors_SensorsNativeImpl : NSObject {
}

-(void)init:(int)param;
-(void)setSamplingRate:(int)param;
-(void)deregisterListener:(int)param;
-(void)registerListener:(int)param;
-(BOOL)isSupported;
@end
