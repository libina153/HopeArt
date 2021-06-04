package com.example.hopeart.Utility;

import android.content.Context;

import com.example.hopeart.SystemConstant.SharedPreferencesKeyConstants;

public class SharedPreferenceManger
{
    //UserName
    public static boolean setUserName(Context context,String userName)
    {
        return MyApplication.getInstance().getSharedPreferences(context).edit().putString(SharedPreferencesKeyConstants.USER_NAME,userName).commit();
    }
    public static String getUserName(Context context)
    {
        return MyApplication.getInstance().getSharedPreferences(context).getString(SharedPreferencesKeyConstants.USER_NAME,"");
    }


    //Address
    public static boolean setAddress(Context context,String address)
    {
        return MyApplication.getInstance().getSharedPreferences(context).edit()
                .putString(SharedPreferencesKeyConstants.ADDRESS,address).commit();
    }
    public static String getAddress(Context context)
    {
        return MyApplication.getInstance().getSharedPreferences(context).getString(SharedPreferencesKeyConstants.ADDRESS,"");
    }

    //myPrefName
    public static boolean setMyPrefName(Context context,String myPrefName)
    {
        return MyApplication.getInstance().getSharedPreferences(context).edit().
                putString(SharedPreferencesKeyConstants.PREF_NAME,myPrefName).commit();
    }
    public static String getMyPrefName(Context context)
    {
        return MyApplication.getInstance().getSharedPreferences(context).getString(SharedPreferencesKeyConstants.PREF_NAME,"");
    }

    //IsRegistered
    public static boolean setIsRegistered(Context context,boolean isRegistered)
    {
        return MyApplication.getInstance().getSharedPreferences(context).edit().putBoolean(SharedPreferencesKeyConstants.ISREGISTERED,isRegistered).commit();
    }
    public static boolean getIsRegistered(Context context)
    {
        return MyApplication.getInstance().getSharedPreferences(context)
                .getBoolean(SharedPreferencesKeyConstants.ISREGISTERED,false);
    }

    //AADHAR_NO
    public static boolean setAadharNo(Context context,String aadharNo)
    {
        return MyApplication.getInstance().getSharedPreferences(context).edit().putString(SharedPreferencesKeyConstants.AADHAR_NO,aadharNo).commit();
    }
    public static String getAadharNo(Context context)
    {
        return MyApplication.getInstance().getSharedPreferences(context).getString(SharedPreferencesKeyConstants.AADHAR_NO,"");
    }

    //Email
    public static boolean setEmail(Context context,String email)
    {
        return MyApplication.getInstance().getSharedPreferences(context).edit().putString(SharedPreferencesKeyConstants.Email,email).commit();
    }
    public static String getEmail(Context context)
    {
        return MyApplication.getInstance().getSharedPreferences(context).getString(SharedPreferencesKeyConstants.Email,"");
    }

    //IsLOGIN
    public static boolean setIsLogin(Context context,boolean isLogin)
    {
        return MyApplication.getInstance().getSharedPreferences(context).edit().putBoolean(SharedPreferencesKeyConstants.IsLOGIN,isLogin).commit();
    }
    public static boolean getIsLogin(Context context)
    {
        return MyApplication.getInstance().getSharedPreferences(context).getBoolean(SharedPreferencesKeyConstants.IsLOGIN,false);
    }

    //USER_ID
    public static boolean setUserId(Context context,String userId)
    {
        return MyApplication.getInstance().getSharedPreferences(context).edit().putString(SharedPreferencesKeyConstants.USER_ID,userId).commit();
    }
    public static String getUserId(Context context)
    {
        return MyApplication.getInstance().getSharedPreferences(context).getString(SharedPreferencesKeyConstants.USER_ID,"");
    }

    //MOBILE_NO
    public static boolean setMobileNo(Context context,String mobileNo)
    {
        return MyApplication.getInstance().getSharedPreferences(context).edit()
                .putString(SharedPreferencesKeyConstants.MOBILE_NO,mobileNo).commit();
    }
    public static String getMobileNo(Context context)
    {
        return MyApplication.getInstance().getSharedPreferences(context).getString(SharedPreferencesKeyConstants.MOBILE_NO,"");
    }

    //User_Type
    public static boolean setUserType(Context context,String userType)
    {
        return  MyApplication.getInstance().getSharedPreferences(context).edit()
                .putString(SharedPreferencesKeyConstants.USER_TYPE,userType).commit();
    }

    public static String getUserType(Context context)
    {
        return  MyApplication.getInstance().getSharedPreferences(context).getString(SharedPreferencesKeyConstants.USER_TYPE,"");
    }

    //IsProfile
    public static boolean setIsProfile(Context context,boolean isProfile)
    {
        return MyApplication.getInstance().getSharedPreferences(context).edit().putBoolean(SharedPreferencesKeyConstants.IsProfile,isProfile).commit();
    }
    public static boolean getIsProfile(Context context)
    {
        return MyApplication.getInstance().getSharedPreferences(context).getBoolean(SharedPreferencesKeyConstants.IsProfile,false);
    }
}
