package com.aemo.screenofftime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.TimeUtils;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;

import java.util.concurrent.TimeUnit;


public class ScreenOffTime extends AndroidNonvisibleComponent {
  private Context context;
  private Activity activity;

  public ScreenOffTime(ComponentContainer container) {
    super(container.$form());
    context = container.$context();
    activity = container.$context();
  }

  @SimpleFunction
  public void SetScreenOffTime(int time){
    boolean value = false;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
      value = Settings.System.canWrite(context.getApplicationContext());
      if (value){
        Settings.System.putInt(activity.getContentResolver(),Settings.System.SCREEN_OFF_TIMEOUT,time);
        OnSet();
      } else {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        activity.startActivity(intent);
      }
    }else {
      Settings.System.putInt(activity.getContentResolver(),Settings.System.SCREEN_OFF_TIMEOUT,time);
    }

  }

  @SimpleEvent
  public void OnSet() {
    EventDispatcher.dispatchEvent(this,"OnSet");
  }

  @SimpleProperty
  public int TIME_OFF_15s(){
    return 15000;
  }
  @SimpleProperty
  public int TIME_OFF_30s(){
    return 30000;
  }
  @SimpleProperty
  public int TIME_OFF_1m(){
    return 60000;
  }
  @SimpleProperty
  public int TIME_OFF_10m(){
    return 600000;
  }
  @SimpleProperty
  public int TIME_OFF_30m(){
    return 1800000;
  }


  @SimpleFunction
  public int MinutesToMillis(int minutes){
    return (int) TimeUnit.MINUTES.toMillis(minutes);
  }
  @SimpleFunction
  public int SecondsToMillis(int seconds){
    return (int) TimeUnit.SECONDS.toMillis(seconds);
  }




}
