package com.example.task.data;
import android.content.Context;
import androidx.multidex.MultiDexApplication;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
public class TaskApplication extends MultiDexApplication {

  private ApiService apiService;
  private Scheduler scheduler;

  private static TaskApplication get(Context context) {
    return (TaskApplication) context.getApplicationContext();
  }

  public static TaskApplication create(Context context) {
    return TaskApplication.get(context);
  }

  public ApiService getApiService() {
    if (apiService == null) {
      apiService = ApiFactory.create();
    }

    return apiService;
  }

  public Scheduler subscribeScheduler() {
    if (scheduler == null) {
      scheduler = Schedulers.io();
    }
    return scheduler;
  }
}
