package com.master.care.ui.base;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

}
