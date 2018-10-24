
#import "RNMakePhoneCall.h"
#import <UIKit/UIKit.h>

@implementation RNMakePhoneCall

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(makeCall: (NSString *)number)
{
    UIApplication *application = [UIApplication sharedApplication];
    
    NSURL *URL = [NSURL URLWithString:[@"tel://" stringByAppendingString:number]];
    
    if([[UIDevice currentDevice].systemVersion floatValue] >= 10.0){
        if ([application respondsToSelector:@selector(openURL:options:completionHandler:)]) {
            [application openURL:URL options:@{}
               completionHandler:^(BOOL success) {
               }];
        } else {
            [application openURL:URL];
        }
    } else {
        bool can = [application canOpenURL:URL];
        if(can) {
            [application openURL:URL];
        }
    }
}
@end
  