package net.sparksnetwork.app.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import net.sparksnetwork.app.data.DataManager;
import net.sparksnetwork.app.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<N> extends ViewModel {

    private final SchedulerProvider mSchedulerProvider;

    private final DataManager mDataManager;

    private WeakReference<N> mNavigator;

    private CompositeDisposable mCompositeDisposable;

    private final ObservableBoolean isLoading = new ObservableBoolean(false);

    public BaseViewModel(SchedulerProvider mSchedulerProvider, DataManager mDataManager) {
        this.mSchedulerProvider = mSchedulerProvider;
        this.mDataManager = mDataManager;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

}

