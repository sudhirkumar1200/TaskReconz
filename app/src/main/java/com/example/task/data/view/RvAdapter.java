package com.example.task.data.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.data.model.TaskResponse;
import com.example.task.data.viewmodel.RvItemViewModel;
import com.example.task.databinding.ItemListBinding;

import java.util.Collections;
import java.util.List;

/*
 *
 * Adapter for inflating list
 * */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvAdapterViewHolder> {

    private List<TaskResponse.Row> itemList;

    RvAdapter() {
        this.itemList = Collections.emptyList();
    }

    @NonNull
    @Override
    public RvAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding itemPeopleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list,
                        parent, false);
        return new RvAdapterViewHolder(itemPeopleBinding);
    }

    @Override
    public void onBindViewHolder(RvAdapterViewHolder holder, int position) {
        holder.bindPeople(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    void setItemList(List<TaskResponse.Row> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    static class RvAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemListBinding mItemBinding;
        RvAdapterViewHolder(ItemListBinding itemListBinding) {
            super(itemListBinding.item);
            this.mItemBinding = itemListBinding;
        }
        void bindPeople(TaskResponse.Row people) {
            if (mItemBinding.getItemViewModel() == null) {
                mItemBinding.setItemViewModel(
                        new RvItemViewModel(people, itemView.getContext()));
            } else {
                mItemBinding.getItemViewModel().setRow(people);
            }
        }
    }
}
