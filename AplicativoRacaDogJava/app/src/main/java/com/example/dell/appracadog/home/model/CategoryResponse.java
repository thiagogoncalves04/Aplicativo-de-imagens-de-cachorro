package com.example.dell.appracadog.home.model;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import java.util.List;

@Entity(tableName = "Categories")
public class CategoryResponse {

    @Expose
    @PrimaryKey
    @NonNull
    private String category;

    @Expose
    private List<String> list;

    public String getCategory() {
        return category;
    }

    public List<String> getList() {
        return list;
    }

    public static class Builder {

        private String category;
        private List<String> list;

        public CategoryResponse.Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public CategoryResponse.Builder withList(List<String> list) {
            this.list = list;
            return this;
        }

        public CategoryResponse build() {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.category = this.category;
            categoryResponse.list = list;
            return categoryResponse;
        }
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
