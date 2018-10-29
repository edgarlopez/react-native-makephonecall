//
//  MakePhoneCall
//
//  Created by Edgar Lopez on 10/29/18.
//  Copyright © 2018 Repartamos. All rights reserved.
//

declare module 'react-native-makephonecall' {
  export function makeCall(number: string) : void;
  export function checkPermission(callback: (error: any, result: 'authorized' | 'denied' | 'undefined') => void): void;
  export function requestPermission(callback: (error: any, result: 'authorized' | 'denied' | 'undefined') => void): void;
}
