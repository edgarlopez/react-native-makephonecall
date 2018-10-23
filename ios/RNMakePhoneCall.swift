//
//  RNMakePhoneCall.swift
//  RNMakePhoneCall
//
//  Created by Edgar Lopez on 10/23/18.
//  Copyright © 2018 Facebook. All rights reserved.
//

import Foundation
import UIKit
import Foundation

@objc(PhoneCall)
class MakePhoneCall: NSObject {
    
    func constantsToExport() -> [AnyHashable : Any]! {
        return ["phoneNumber": 0]
    }
    
    static func requiresMainQueueSetup() -> Bool {
        return true
    }
    
    func makeCall(number: String) {
        number.makeACall()
    }
    
}

extension String {
    
    enum RegularExpressions: String {
        case phone = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$"
    }
    
    func isValid(regex: RegularExpressions) -> Bool {
        return isValid(regex: regex.rawValue)
    }
    
    func isValid(regex: String) -> Bool {
        let matches = range(of: regex, options: .regularExpression)
        return matches != nil
    }
    
    func onlyDigits() -> String {
        let filtredUnicodeScalars = unicodeScalars.filter{CharacterSet.decimalDigits.contains($0)}
        return String(String.UnicodeScalarView(filtredUnicodeScalars))
    }
    
    func makeACall() {
        if isValid(regex: .phone) {
            if let url = URL(string: "tel://\(self.onlyDigits())"), UIApplication.shared.canOpenURL(url) {
                if #available(iOS 10, *) {
                    UIApplication.shared.open(url)
                } else {
                    UIApplication.shared.openURL(url)
                }
            }
        }
    }
}