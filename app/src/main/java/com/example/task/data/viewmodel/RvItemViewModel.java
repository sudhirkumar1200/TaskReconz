package com.example.task.data.viewmodel; /**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.task.data.model.TaskResponse;
import com.example.task.data.view.ItemDetailActivity;

/*
* Recycler viewmodel
* */
public class RvItemViewModel extends BaseObservable {

    private TaskResponse.Row row;
    private Context context;

    public RvItemViewModel(TaskResponse.Row row, Context context) {
        this.row = row;
        this.context = context;
    }

    public String getTitle() {
        return row.getTitle();
    }

    public String getDesc() {
        return row.getDescription();
    }

    public String getPictureProfile() {
        return row.getImageHref();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
        context.startActivity(ItemDetailActivity.launchDetail(view.getContext(), row));
    }

    public void setRow(TaskResponse.Row row) {
        this.row = row;
        notifyChange();
    }
}
