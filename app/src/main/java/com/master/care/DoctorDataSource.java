package com.master.care;

import com.master.care.model.DoctorsDataModel;

import io.reactivex.Observable;


public interface DoctorDataSource {

    Observable<DoctorsDataModel> getDoctorData(int pageNo);
}
