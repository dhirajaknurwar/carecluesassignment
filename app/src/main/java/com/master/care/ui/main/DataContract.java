package com.master.care.ui.main;

import android.support.annotation.NonNull;


import com.master.care.model.DoctorsDataModel;
import com.master.care.ui.base.BasePresenter;
import com.master.care.ui.base.BaseView;

public interface DataContract {

    interface View extends BaseView<Presenter> {

        void showData(@NonNull DoctorsDataModel doctorsDataModel);

        void showNoData();
    }

    interface Presenter extends BasePresenter<View> {

        void loadData(boolean forceUpdate,int pageNo);

        void takeView(DataContract.View view);

        void dropView();
    }
}
