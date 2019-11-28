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

import com.example.task.data.DummyGeneratorAPI;
import com.example.task.data.model.TaskResponse;
import com.example.task.data.viewmodel.ListDetailViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class) public class ItemDetailViewModelTest {

    private ListDetailViewModel listDetailViewModel;
    private TaskResponse.Row row;

    @Before public void setUpDetailViewModelTest() {
        row = DummyGeneratorAPI.getListItem();
        listDetailViewModel = new ListDetailViewModel(row);
    }

    @Test public void shouldGetURLImage() {
        assertEquals(row.getImageHref(), listDetailViewModel.getPictureProfile());
    }

    @Test public void shouldGetTitle() {
        assertEquals(row.getTitle(), listDetailViewModel.getTitle());
    }

    @Test public void shouldGetDesc() {
        assertEquals(row.getDescription(), listDetailViewModel.getDesc());
    }

}
