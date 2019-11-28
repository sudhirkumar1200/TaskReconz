package com.example.task.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/*
 * Model Class contain api response
 *
 * */
public class TaskResponse implements Serializable {

    @SerializedName("rows")
    private List<Row> mRows;
    @SerializedName("title")
    private String mTitle;

    public List<Row> getRows() {
        return mRows;
    }

    public void setRows(List<Row> rows) {
        mRows = rows;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    /*
     * Internal class of item
     * */
    public static class Row implements Serializable {

        @SerializedName("description")
        private String mDescription;
        @SerializedName("imageHref")
        private String mImageHref;
        @SerializedName("title")
        private String mTitle;

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public String getImageHref() {
            return mImageHref;
        }

        public void setImageHref(String imageHref) {
            mImageHref = imageHref;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(String title) {
            mTitle = title;
        }

    }
}
