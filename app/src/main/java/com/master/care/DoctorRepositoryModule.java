package com.master.care;


import com.master.care.core.ApiService;
import com.master.care.remote.CareRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class DoctorRepositoryModule {


    @Provides
    @Singleton
    @Remote
    DoctorDataSource provideCareRemoteDataSource(ApiService apiService) {
        return new CareRemoteDataSource(apiService);
    }
}
