package com.example.hopeart.Utility;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hopeart.DAOS.CustCartDao;
import com.example.hopeart.DAOS.CustWishListDao;
import com.example.hopeart.DataModel.CustCartModel;
import com.example.hopeart.DataModel.CustWishListModel;

@Database(entities = {CustWishListModel.class, CustCartModel.class},version = 1,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public abstract CustCartDao custCartDao();
    public abstract CustWishListDao custWishListDao();
}
