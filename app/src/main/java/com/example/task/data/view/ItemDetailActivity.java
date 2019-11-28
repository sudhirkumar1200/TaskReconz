package com.example.task.data.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.task.R;
import com.example.task.data.model.TaskResponse;
import com.example.task.data.viewmodel.ListDetailViewModel;
import com.example.task.databinding.ItemDetailActivityBinding;

public class ItemDetailActivity extends AppCompatActivity {

    private static final String EXTRA_ITEM = "EXTRA_ITEM";
    private ItemDetailActivityBinding itemDetailActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemDetailActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.item_detail_activity);
        setSupportActionBar(itemDetailActivityBinding.toolbar);
        displayHomeAsUpEnabled();
        getExtrasFromIntent();
    }

    /*
     * launch this activity here
     * */
    public static Intent launchDetail(Context context, TaskResponse.Row people) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra(EXTRA_ITEM, people);
        return intent;
    }

    /*
     * Display actionbar
     * */
    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /*
     *
     * set List item viewmodel
     *
     * */
    private void getExtrasFromIntent() {
        TaskResponse.Row item = (TaskResponse.Row) getIntent().getSerializableExtra(EXTRA_ITEM);
        ListDetailViewModel itemDetailViewModel = new ListDetailViewModel(item);
        itemDetailActivityBinding.setItemDetailViewModel(itemDetailViewModel);
        setTitle(item.getTitle());
    }
}
