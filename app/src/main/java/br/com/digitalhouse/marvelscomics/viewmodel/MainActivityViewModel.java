package br.com.digitalhouse.marvelscomics.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.digitalhouse.marvelscomics.Repository.ComicsRepository;
import br.com.digitalhouse.marvelscomics.model.pojo.Result;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static br.com.digitalhouse.marvelscomics.Utils.AppUtils.md5;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Result>> listaComics = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<String> comicsLiveDataError = new MutableLiveData<>();

    private ComicsRepository repository = new ComicsRepository();
    private CompositeDisposable disposable = new CompositeDisposable();

    public static final String PUBLIC_KEY = "6eb7e8896ec5850c52515a8a23ee97f0";
    public static final String PRIVATE_KEY = "0dd0c16fedb8a02985977eafca66b49f5e6a526f";
    String ts = Long.toString(System.currentTimeMillis() / 1000);
    String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);

    public MutableLiveData<String> getComicsLiveDataError() {
        return comicsLiveDataError;
    }

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Result>> getListaComics() {
        return this.listaComics;
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }


    public void getAllComics() {
        disposable.add(
                repository.getComicsRepository("magazine", "comic",
                        true, "title", ts, hash, PUBLIC_KEY)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loading.setValue(true))
                        .doOnTerminate(() -> loading.setValue(false))
                        .subscribe(comics -> listaComics.setValue(comics.getData().getResults()),
                                throwable -> Log.i("LOG", "erro " + throwable.getMessage()))
        );
    }

    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
