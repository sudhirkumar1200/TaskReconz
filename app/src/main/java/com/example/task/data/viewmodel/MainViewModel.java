package com.example.task.data.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;


import com.example.task.R;
import com.example.task.data.TaskApplication;
import com.example.task.data.ApiFactory;
import com.example.task.data.ApiService;
import com.example.task.data.model.TaskResponse;
import com.example.task.data.utils.InternetConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainViewModel extends Observable {

  public ObservableInt peopleProgress;
  public ObservableInt peopleRecycler;
  public ObservableInt peopleLabel;
  public ObservableInt swipeTORefresh;
  public ObservableField<String> messageLabel;
  public ObservableField<String> titleLabel;
  public ObservableBoolean isLoading = new ObservableBoolean();
  private List<TaskResponse.Row> peopleList;
  private Context context;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();

  public MainViewModel(@NonNull Context context) {

    this.context = context;
    this.peopleList = new ArrayList<>();
    peopleProgress = new ObservableInt(View.GONE);
    peopleRecycler = new ObservableInt(View.GONE);
    peopleLabel = new ObservableInt(View.VISIBLE);
    swipeTORefresh = new ObservableInt(View.VISIBLE);
    messageLabel = new ObservableField<>(context.getString(R.string.default_loading_people));
    titleLabel = new ObservableField<>(context.getString(R.string.default_title));
    if (InternetConnection.checkConnection(context)){
      onClickFabLoad();
    }else{
      messageLabel.set(context.getString(R.string.default_internet_connction));
    }

  }

  public void onClickFabLoad(/*View view*/) {
    initializeViews();
    fetchPeopleList();
  }

  //It is "public" to show an example of test
  public void initializeViews() {
    peopleLabel.set(View.GONE);
    peopleRecycler.set(View.GONE);
    peopleProgress.set(View.GONE);
  }
  public void onRefresh(){
    isLoading.set(true);
    if (InternetConnection.checkConnection(context)){
      onClickFabLoad();
    }else{
      onReady();
      messageLabel.set(context.getString(R.string.default_internet_connction));
    }

  }
  public void onReady(){
    isLoading.set(false);
  }

  public void onError(Exception oops){
    isLoading.set(false);
  }
  // NOTE: This method can return the observer and just subscribe to it in the activity or fragment,
  // an Activity or Fragment needn't to implement from the Observer java class
  // (it was my first approach to avoid RX in UI now we can use LiveData instead)
  private void fetchPeopleList() {

    TaskApplication peopleApplication = TaskApplication.create(context);
    ApiService peopleService = peopleApplication.getApiService();

    Disposable disposable = peopleService.fetchDataList(ApiFactory.BASE_URL)
        .subscribeOn(peopleApplication.subscribeScheduler())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<TaskResponse>() {
          @Override
          public void accept(TaskResponse peopleResponse) {
            changePeopleDataSet(peopleResponse.getRows());
            titleLabel.set(peopleResponse.getTitle());
            peopleProgress.set(View.GONE);
            peopleLabel.set(View.GONE);
            peopleRecycler.set(View.VISIBLE);
            onReady();
          }
        }, new Consumer<Throwable>() {
          @Override
          public void accept(Throwable throwable) {
            messageLabel.set(context.getString(R.string.error_loading_people));
            onReady();
            titleLabel = new ObservableField<>(context.getString(R.string.default_title));
            peopleProgress.set(View.GONE);
            peopleLabel.set(View.VISIBLE);
            peopleRecycler.set(View.GONE);
            throwable.printStackTrace();

          }
        });

    compositeDisposable.add(disposable);
  }

  private void changePeopleDataSet(List<TaskResponse.Row> peoples) {
    peopleList.clear();
    peopleList.addAll(peoples);
    setChanged();
    notifyObservers();
  }

  public List<TaskResponse.Row> getPeopleList() {
    return peopleList;
  }

  private void unSubscribeFromObservable() {
    if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
      compositeDisposable.dispose();
    }
  }

  public void reset() {
    unSubscribeFromObservable();
    compositeDisposable = null;
    context = null;
  }
}
