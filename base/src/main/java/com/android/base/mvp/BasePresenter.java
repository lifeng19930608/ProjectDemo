package com.android.base.mvp;

import com.android.base.network.retrofit.ApiClient;
import com.android.base.network.retrofit.UrlApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * author  : 指尖的力量
 * date    : 2019-08-07 13:08
 * desc    :
 * modify  :
 * version : 1.0
 */

public class BasePresenter<V> {

    public V view;
    protected UrlApi urlApi;
    private CompositeDisposable compositeDisposable;

    public void attachView(V view) {
        this.view = view;
        urlApi = ApiClient.retrofit().create(UrlApi.class);
    }

    public void detachView() {
        this.view = null;
        onUnSubscribe();
    }

    public <T> void onSubscribe(Observable<T> observable, DisposableObserver<T> disposableObserver) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposableObserver);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);
    }

    public void onUnSubscribe() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

}
