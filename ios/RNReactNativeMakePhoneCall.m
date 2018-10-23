
#import "RNReactNativeMakePhoneCall.h"

@implementation RNReactNativeMakePhoneCall

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

@interface RCT_EXPORT_MODULE(MakePhoneCall, NSObject)

RCT_EXTERN_METHOD(makeCall: (NSString *)number)

@end
  
