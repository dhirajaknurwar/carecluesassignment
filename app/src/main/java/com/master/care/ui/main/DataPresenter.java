package com.master.care.ui.main;

import android.support.annotation.NonNull;

import com.master.care.ActivityScoped;
import com.master.care.DoctorsRepository;
import com.master.care.model.DoctorsDataModel;

import javax.annotation.Nullable;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@ActivityScoped
final class DataPresenter implements DataContract.Presenter {

    private final DoctorsRepository mDoctorsRepository;

    @Nullable
    private DataContract.View mView;

    private int pageNo = 0;

    @NonNull
    private CompositeDisposable mDisposables;

    @Inject
    DataPresenter(DoctorsRepository tasksRepository) {
        mDoctorsRepository = tasksRepository;
        mDisposables = new CompositeDisposable();
    }

    private void loadDoctorsData(final boolean showLoadingUI, int pageNo) {
        this.pageNo = pageNo;
        if (showLoadingUI) {
            if (mView != null) {
                mView.showLoading(true);
            }
        }

        mDisposables.clear();
        Disposable disposable = mDoctorsRepository
                .getDoctorData(pageNo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<DoctorsDataModel>() {
                    @Override
                    public void onNext(DoctorsDataModel doctorsDataModel) {
                        processDoctorData(doctorsDataModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.showLoading(false);
                            mView.showError(e);
                        }

                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                            mView.showLoading(false);
                        }
                    }
                });

        mDisposables.add(disposable);
    }

    private void processDoctorData(DoctorsDataModel doctorsDataModel) {
        if (doctorsDataModel == null || doctorsDataModel.getData().size() == 0) {
            if (mView != null) {
                mView.showNoData();
            }
        } else {
            if (mView != null) {
                mView.showData(doctorsDataModel);
            }
        }
    }


    @Override
    public void loadData(boolean forceUpdate, int pageNo) {
        loadDoctorsData(true, pageNo);
        boolean mFirstLoad = false;
    }

    @Override
    public void takeView(DataContract.View view) {
        mView = view;
        this.loadData(false, pageNo);
    }

    @Override
    public void dropView() {
        mView = null;
        mDisposables.clear();
    }
}
