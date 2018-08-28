package com.master.care.remote;


import com.master.care.DoctorDataSource;
import com.master.care.core.ApiService;
import com.master.care.model.DoctorsDataModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class CareRemoteDataSource implements DoctorDataSource {

    private ApiService mApiService;

    @Inject
    public CareRemoteDataSource(ApiService apiService) {
        mApiService = apiService;
    }


    @Override
    public Observable<DoctorsDataModel> getDoctorData(int pageNo) {
        return mApiService.getDoctorsData(pageNo);
    }
}
