/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.task;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.Observable;

import com.example.task.data.MockView;
import com.example.task.data.model.TaskResponse;
import com.example.task.data.viewmodel.RvItemViewModel;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) public class ItemViewModelTest {


    private Context mockContext = mock(Context.class);

    @Test public void shouldGetTitle() throws Exception {
        TaskResponse.Row row = new TaskResponse.Row();
        row.setTitle("Beavers");
        RvItemViewModel itemPeopleViewModel = new RvItemViewModel(row, mockContext);
        assertEquals(row.getTitle(), itemPeopleViewModel.getTitle());
    }

    @Test public void shouldGetDesc() throws Exception {
        TaskResponse.Row row = new TaskResponse.Row();
        row.setDescription("Beavers are second only to humans in their ability to manipulate and change their environment.");
        RvItemViewModel itemPeopleViewModel = new RvItemViewModel(row, mockContext);
        assertEquals(row.getDescription(), itemPeopleViewModel.getDesc());
    }

    @Ignore public void shouldGetPeoplePicture() throws Exception {
        TaskResponse.Row row = new TaskResponse.Row();
        row.setImageHref("http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg");
        RvItemViewModel itemPeopleViewModel = new RvItemViewModel(row, mockContext);
        assertEquals(row.getImageHref(), itemPeopleViewModel.getPictureProfile());
    }



    @Test public void shouldStartPeopleDetailActivityOnItemClick() throws Exception {
        TaskResponse.Row row = new TaskResponse.Row();
        RvItemViewModel itemPeopleViewModel = new RvItemViewModel(row, mockContext);
        itemPeopleViewModel.onItemClick(new MockView(mockContext));
        verify(mockContext).startActivity(ArgumentMatchers.any(Intent.class));
    }

    @Test public void shouldNotifyPropertyChangeWhenSetPeople() throws Exception {
        TaskResponse.Row row = new TaskResponse.Row();
        RvItemViewModel itemPeopleViewModel = new RvItemViewModel(row, mockContext);
        Observable.OnPropertyChangedCallback mockCallback = mock(Observable.OnPropertyChangedCallback.class);
        itemPeopleViewModel.addOnPropertyChangedCallback(mockCallback);
        itemPeopleViewModel.setRow(row);
        verify(mockCallback).onPropertyChanged(ArgumentMatchers.any(Observable.class), anyInt());
    }
}
