package com.vladder2312.studentdocs

import android.app.Application
import android.util.Log
import com.vladder2312.studentdocs.di.AppComponent
import com.vladder2312.studentdocs.di.AppModule
import com.vladder2312.studentdocs.di.DaggerAppComponent
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins

/**
 * Класс приложения
 */
class App : Application() {

    companion object{
        lateinit var appComponent : AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler RxJavaPlugins@{
            if(it is UndeliverableException){
                Log.e("RxJava","Global Error")
            }
        }
        appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
    }
}