package com.master.care.ui.base;

public interface BaseView<T> {

    void showLoading(boolean active);

    void showError(Throwable throwable);

}
