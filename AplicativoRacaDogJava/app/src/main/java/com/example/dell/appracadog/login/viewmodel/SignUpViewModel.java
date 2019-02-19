package com.example.dell.appracadog.login.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.util.Log;
import com.example.dell.appracadog.database.Database;
import com.example.dell.appracadog.login.model.request.SignUpRequest;
import com.example.dell.appracadog.login.model.response.UserResponse;
import org.reactivestreams.Subscription;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;
import static com.example.dell.appracadog.retrofit.RetrofitService.getApiService;
import static com.example.dell.appracadog.util.NetworkUtils.isNetworkConnected;

public class SignUpViewModel extends AndroidViewModel {

    public MutableLiveData<UserResponse> objectLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<Throwable> objectLiveDataError = new MutableLiveData<>();

    private SharedPreferences preferences = getApplication().getSharedPreferences("APP", MODE_PRIVATE);

    public static String CONTENT_TYPE = "application/json";

    public SignUpViewModel(@android.support.annotation.NonNull Application application) {
        super(application);
    }

    public void signUp(SignUpRequest request){
        if(isNetworkConnected(getApplication())){
            disposable.add(
                    getApiService().signUp(CONTENT_TYPE, request)
                            .map(this::saveUser)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(new Consumer<Disposable>() {
                                @Override
                                public void accept(Disposable disposable) throws Exception {
                                    isLoading.setValue(true);
                                }
                            })
                            .doOnTerminate(new Action() {
                                @Override
                                public void run() throws Exception {
                                    isLoading.setValue(false);
                                }
                            })
                            .subscribe(new Consumer<UserResponse>() {
                                @Override
                                public void accept(UserResponse response) throws Exception {
                                    objectLiveData.setValue(response);
                                    preferences.edit().putString("TOKEN", response.getUser().getToken()).apply();
                                    preferences.edit().putLong("USER_ID", response.getUser().getId()).apply();
                                }
                            }, error -> Log.i("LOG", "Error: " + error))
            );
        } else {
            disposable.add(
                    Database.getDatabase(getApplication()).userDAO().getAll()
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(new Consumer<Subscription>() {
                                @Override
                                public void accept(Subscription disposable) throws Exception {
                                    isLoading.setValue(true);
                                }
                            })
                            .doOnTerminate(() -> {
                                isLoading.setValue(false);
                            })
                            .subscribe(new Consumer<UserResponse>() {
                                           @Override
                                           public void accept(UserResponse response) throws Exception {
                                               objectLiveData.setValue(response);
                                           }
                                       },
                                    throwable -> Log.i("LOG", "Error: " + throwable.getMessage()))
            );
        }
    }

    private UserResponse saveUser(UserResponse response) {
        Database.getDatabase(getApplication()).userDAO().insert(response);
        return response;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}