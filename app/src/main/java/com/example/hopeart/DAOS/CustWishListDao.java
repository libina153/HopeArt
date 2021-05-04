package com.example.hopeart.DAOS;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hopeart.DataModel.CustWishListModel;

import java.util.List;

@Dao
public interface CustWishListDao {

    @Query("SELECT * FROM CustWishListModel")
    List<CustWishListModel> getWishListModel();

    @Insert
    long insertCustWishListModel(CustWishListModel cwl);

    @Update
    void  updateCustWishListModel(CustWishListModel cwl);

    @Delete
    void deleteCustWishListModel(CustWishListModel cwl);
}
