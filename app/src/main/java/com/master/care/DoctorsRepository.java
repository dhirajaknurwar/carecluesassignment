package com.master.care;

import com.master.care.model.DoctorsDataModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

@Singleton
public class DoctorsRepository implements DoctorDataSource {

    private final DoctorDataSource mDoctorsRemoteDataSource;

    @Inject
    DoctorsRepository(@Remote DoctorDataSource newsRemoteDataSource) {
        mDoctorsRemoteDataSource = checkNotNull(newsRemoteDataSource);
    }

    @Override
    public Observable<DoctorsDataModel> getDoctorData(int pageNo) {

        return mDoctorsRemoteDataSource.getDoctorData(pageNo);
    }

}
