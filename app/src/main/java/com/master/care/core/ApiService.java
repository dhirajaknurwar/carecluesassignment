package com.master.care.core;

import com.master.care.model.DoctorsDataModel;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(APIConstants.GET_DOCTOR_LIST)
    Observable<DoctorsDataModel> getDoctorsData(@Query("page_no") int pageNo);
}
