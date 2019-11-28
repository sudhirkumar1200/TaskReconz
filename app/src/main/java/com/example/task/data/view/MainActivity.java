package com.example.task.data.view;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.data.viewmodel.MainViewModel;
import com.example.task.databinding.MainActivityBinding;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private MainActivityBinding mainActivityBinding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupListPeopleView(mainActivityBinding.rvlist);
        setupObserver(mainViewModel);
    }

    /*
     *
     * Bind data in main view model
     *
     * */
    private void initDataBinding() {
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mainViewModel = new MainViewModel(this);
        mainActivityBinding.setMainViewModel(mainViewModel);
    }

    /*
     *
     * Recycler view setup
     *
     * */
    private void setupListPeopleView(RecyclerView itemRecyclerview) {
        RvAdapter adapter = new RvAdapter();
        itemRecyclerview.setAdapter(adapter);
        itemRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    /*
     *
     *
     * Setup observer
     *
     * */
    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.reset();
    }


    /*
     *
     * if update list it's update Recycler view
     *
     * */
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof MainViewModel) {
            RvAdapter peopleAdapter = (RvAdapter) mainActivityBinding.rvlist.getAdapter();
            MainViewModel peopleViewModel = (MainViewModel) observable;
            peopleAdapter.setItemList(peopleViewModel.getPeopleList());
        }
    }
}
