package com.example.task.data;

import com.example.task.data.model.TaskResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

  @GET
  Observable<TaskResponse> fetchDataList(@Url String url);

}
