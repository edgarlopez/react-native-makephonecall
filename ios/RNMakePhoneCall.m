
//
//  RNMakePhoneCall
//
//  Created by Edgar Lopez on 10/17/18.
//  Copyright © 2018 Repartamos. All rights reserved.
//
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
    
    NSString *phoneNumber = [[number componentsSeparatedByCharactersInSet:
                            [[NSCharacterSet decimalDigitCharacterSet] invertedSet]]
                           componentsJoinedByString:@""];
    
    UIApplication *application = [UIApplication sharedApplication];
    
    NSURL *URL = [NSURL URLWithString:[@"tel://" stringByAppendingString:phoneNumber]];
    
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
  