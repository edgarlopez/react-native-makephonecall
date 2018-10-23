
//
//  RNReactNativeMakePhoneCallModule
//
//  Created by Edgar Lopez on 10/17/18.
//  Copyright © 2018 Facebook. All rights reserved.
//

package com.repartamos.makephonecall;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

public class RNReactNativeMakePhoneCallModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNMakePhoneCallModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNMakePhoneCall";
  }

  @ReactMethod
  public void makeCall(String number) {
    final Activity activity = getCurrentActivity();

    if (activity != null) {
      Intent callIntent = new Intent(Intent.ACTION_CALL);
      callIntent.setData(Uri.parse("tel:" + number));

      if (ActivityCompat.checkSelfPermission(MainActivity.this,
         Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
         }
         startActivity(callIntent);
    }
  }
}
