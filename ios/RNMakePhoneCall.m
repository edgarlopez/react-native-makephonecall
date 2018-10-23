
#import "RNMakePhoneCall.h"

@implementation RNMakePhoneCall

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()
RCT_EXTERN_METHOD(makeCall: (NSString *)number)
@end
  