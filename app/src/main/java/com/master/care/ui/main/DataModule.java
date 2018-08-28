package com.master.care.ui.main;


import com.master.care.ActivityScoped;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {

    @ActivityScoped
    @Binds abstract DataContract.Presenter dataPresenter(DataPresenter presenter);
}
