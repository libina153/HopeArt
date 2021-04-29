package com.example.hopeart.Utility;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.hopeart.SystemConstant.SharedPreferencesKeyConstants;

public class MyApplication extends Application {

    private static final String TAG = "MyApp";
    private static MyApplication _oMyAppicationInstance;
    private SharedPreferences sharedPreferences;

    public static synchronized MyApplication getInstance()
    {
        if (_oMyAppicationInstance==null)
        {
            _oMyAppicationInstance=new MyApplication();
        }
        return _oMyAppicationInstance;
    }

    public SharedPreferences getSharedPreferences(Context context)
    {
        if (sharedPreferences==null)
        {
            sharedPreferences=context.getSharedPreferences(SharedPreferencesKeyConstants.PREF_NAME,0);
        }
        return sharedPreferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}


