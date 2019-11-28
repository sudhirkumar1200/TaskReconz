package com.example.task.data.viewmodel;

import com.example.task.data.model.TaskResponse;

public class ListDetailViewModel {

  private TaskResponse.Row item;

  public ListDetailViewModel(TaskResponse.Row people) {
    this.item = people;
  }

  public String getTitle() {
    return item.getTitle();
  }

  public String getDesc() {
    return item.getDescription();
  }

  public String getPictureProfile() {
    return item.getImageHref();
  }


}
