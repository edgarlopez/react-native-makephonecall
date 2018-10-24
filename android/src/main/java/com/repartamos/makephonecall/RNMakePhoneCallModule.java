
//
//  RNMakePhoneCallModule
//
//  Created by Edgar Lopez on 10/17/18.
//  Copyright © 2018 Repartamos. All rights reserved.
//

package com.repartamos.makephonecall;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.PermissionChecker;

public class RNMakePhoneCallModule extends ReactContextBaseJavaModule {

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

      boolean result = true;
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        result = getReactApplicationContext().checkSelfPermission(Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED;
      } else {
        // targetSdkVersion < Android M, we have to use PermissionChecker
        result = PermissionChecker.checkSelfPermission(getReactApplicationContext(), Manifest.permission.CALL_PHONE)
                == PermissionChecker.PERMISSION_GRANTED;
      }

      if (result) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        getReactApplicationContext().startActivity(callIntent);
      }
    }
  }
}
