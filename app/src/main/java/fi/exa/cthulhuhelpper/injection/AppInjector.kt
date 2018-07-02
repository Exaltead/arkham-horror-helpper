package fi.exa.cthulhuhelpper.injection

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import fi.exa.cthulhuhelpper.App

///Marks fragment so that injection will occur
interface Injectable{}

///Automatically injects dependencies to activities and contained fragments
object AppInjector {
    fun inject(app: App){
        DaggerAppComponent.builder().application(app).build().inject(app)
        app.registerActivityLifecycleCallbacks(ActivityLifecycleInjector)
    }
}
private object ActivityLifecycleInjector: Application.ActivityLifecycleCallbacks{
    override fun onActivityPaused(p0: Activity?) {}
    override fun onActivityResumed(p0: Activity?) {}
    override fun onActivityStarted(p0: Activity?) {}
    override fun onActivityDestroyed(p0: Activity?) {}
    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}
    override fun onActivityStopped(p0: Activity?) {}
    override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
        // Only at creation injection is needed
        inject(activity)
    }

    private fun inject(activity: Activity?){
        if(activity is HasSupportFragmentInjector){
            AndroidInjection.inject(activity)
        }
        if(activity is FragmentActivity){
            activity
                .supportFragmentManager
                .registerFragmentLifecycleCallbacks(FragmentLifecycleInjector, true)
        }
    }
}

private object FragmentLifecycleInjector: FragmentManager.FragmentLifecycleCallbacks(){
    override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
        if(f is Injectable){
            AndroidSupportInjection.inject(f)
        }
    }
}
