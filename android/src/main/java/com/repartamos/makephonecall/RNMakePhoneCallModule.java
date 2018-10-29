//
//  RNMakePhoneCallModule
//
//  Created by Edgar Lopez on 10/17/18.
//  Copyright © 2018 Repartamos. All rights reserved.
//

package com.repartamos.makephonecall;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import android.support.annotation.NonNull;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.PermissionChecker;
import android.support.v4.app.ActivityCompat;

import java.util.Hashtable;

public class RNMakePhoneCallModule extends ReactContextBaseJavaModule {

  private static final String PERMISSION_DENIED = "denied";
  private static final String PERMISSION_AUTHORIZED = "authorized";
  private static final String PERMISSION_PHONE_CALL = Manifest.permission.CALL_PHONE;
  private static final int PERMISSION_REQUEST_CODE = 686;

  private static Callback requestCallback;

  public RNMakePhoneCallModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "RNMakePhoneCall";
  }

  @ReactMethod
  public void makeCall(String number) {
    final Activity activity = getCurrentActivity();

    if (activity != null) {
      if (isPermissionGranted().equals(PERMISSION_AUTHORIZED)) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number.replaceAll("[^0-9]+", "")));
        getReactApplicationContext().startActivity(callIntent);
      }
    }
  }
  /*
   * Check permission
   */
  @ReactMethod
  public void checkPermission(Callback callback) {
      callback.invoke(null, isPermissionGranted());
  }
  /*
   * Request permission
   */
  @ReactMethod
    public void requestPermission(Callback callback) {
        requestReadPhonePermission(callback);
    }

    private void requestReadPhonePermission(Callback callback) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            callback.invoke(null, PERMISSION_DENIED);
            return;
        }

        if (isPermissionGranted().equals(PERMISSION_AUTHORIZED)) {
            callback.invoke(null, PERMISSION_AUTHORIZED);
            return;
        }

        requestCallback = callback;
        ActivityCompat.requestPermissions(currentActivity, new String[]{PERMISSION_PHONE_CALL}, PERMISSION_REQUEST_CODE);
    }

    protected static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        if (requestCallback == null) {
            return;
        }

        if (requestCode != PERMISSION_REQUEST_CODE) {
            requestCallback.invoke(null, PERMISSION_DENIED);
            return;
        }

        Hashtable<String, Boolean> results = new Hashtable<>();
        for (int i = 0; i < permissions.length; i++) {
            results.put(permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED);
        }

        if (results.containsKey(PERMISSION_PHONE_CALL) && results.get(PERMISSION_PHONE_CALL)) {
            requestCallback.invoke(null, PERMISSION_AUTHORIZED);
        } else {
            requestCallback.invoke(null, PERMISSION_DENIED);
        }

        requestCallback = null;
    }
    /*
     * Check if CALL_PHONE permission is granted
     */
    private String isPermissionGranted() {
      // return -1 for denied and 1
      boolean result = false;

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        result = getReactApplicationContext().checkSelfPermission(PERMISSION_PHONE_CALL)
                == PackageManager.PERMISSION_GRANTED;
      } else {
        // targetSdkVersion < Android M, we have to use PermissionChecker
        result = PermissionChecker.checkSelfPermission(getReactApplicationContext(),PERMISSION_PHONE_CALL)
                == PermissionChecker.PERMISSION_GRANTED;
      }

      return result ? PERMISSION_AUTHORIZED : PERMISSION_DENIED;
    }
}
