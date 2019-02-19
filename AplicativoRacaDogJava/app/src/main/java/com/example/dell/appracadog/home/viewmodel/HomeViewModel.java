package com.example.dell.appracadog.home.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.dell.appracadog.database.Database;
import com.example.dell.appracadog.home.model.CategoryResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;
import static com.example.dell.appracadog.retrofit.RetrofitService.getApiService;
import static com.example.dell.appracadog.util.NetworkUtils.isNetworkConnected;

public class HomeViewModel extends AndroidViewModel {

    public MutableLiveData<CategoryResponse> objectLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> objectLiveDataError = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    private SharedPreferences preferences = getApplication().getBaseContext().getSharedPreferences("APP", MODE_PRIVATE);
    private String token = preferences.getString("TOKEN","");

    private static String CONTENT_TYPE = "application/json";

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void getCategory(String category) {

        if (isNetworkConnected(getApplication())) {
            disposable.add(
                    getApiService().getCategory(token, CONTENT_TYPE, category)
                            .map(this::saveCategory)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response ->
                                            objectLiveData.setValue(response),
                                    throwable -> Log.i("LOG", "Error: " + throwable.getMessage()))
            );
        } else {
            try {
                disposable.add(
                        Database.getDatabase(getApplication()).categoryDAO().getAll()
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(response -> objectLiveData.setValue(response),
                                        throwable -> Log.i("LOG", "Error: " + throwable.getMessage()))
                );
            } catch (Error e){
                e.getMessage();
                e.printStackTrace();
            }
        }
    }
    public void getCategory() {
        if (isNetworkConnected(getApplication())) {
            disposable.add(
                    getApiService().getCategory(token, CONTENT_TYPE)
                            .map(this::saveCategory)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(response -> objectLiveData.setValue(response),
                                    throwable -> Log.i("LOG", "Error: " + throwable.getMessage()))
            );
        } else {
            try {
                disposable.add(
                        Database.getDatabase(getApplication()).categoryDAO().getAll()
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(response -> objectLiveData.setValue(response),
                                        throwable -> Log.i("LOG", "Error: " + throwable.getMessage()))
                );
            } catch (Error e){
                e.getMessage();
                e.printStackTrace();
            }
        }
    }

    private CategoryResponse saveCategory(CategoryResponse response) {
        Database.getDatabase(getApplication()).categoryDAO().insert(response);
        return response;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
