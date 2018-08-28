package com.master.care;


import com.master.care.ui.chat.ChatActivity;
import com.master.care.ui.main.DataContract;
import com.master.care.ui.main.DataModule;
import com.master.care.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = DataModule.class)
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract ChatActivity chatActivity();

}
