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
import android.view.View;

import com.example.task.data.ApiService;
import com.example.task.data.DummyGeneratorAPI;
import com.example.task.data.model.TaskResponse;
import com.example.task.data.viewmodel.MainViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.Silent.class) public class MainViewModelTest {

    private static final String URL_TEST = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json/";

    @Mock private ApiService peopleService;
    private Context mockContext = mock(Context.class);

    private MainViewModel mainViewModel;

    @Before public void setUpMainViewModelTest() {
        mainViewModel = new MainViewModel(mockContext);
    }

    @Test public void simulateGivenTheUserCallListFromApi() {
        List<TaskResponse.Row> items = DummyGeneratorAPI.getItemList();
        doReturn(Observable.just(items)).when(peopleService).fetchDataList(URL_TEST);
    }

    @Test public void ensureTheViewsAreInitializedCorrectly() {
        mainViewModel.initializeViews();
        assertEquals(View.GONE, mainViewModel.peopleLabel.get());
        assertEquals(View.GONE, mainViewModel.peopleRecycler.get());
        assertEquals(View.VISIBLE, mainViewModel.peopleProgress.get());
    }
}
