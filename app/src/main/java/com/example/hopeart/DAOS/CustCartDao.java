package com.example.hopeart.DAOS;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hopeart.DataModel.CustCartModel;
import java.util.List;

@Dao
public interface CustCartDao {

    @Query("SELECT * FROM CustCartModel")
    List<CustCartModel> getCustCartModel();

    @Insert
    long insertCustCartModel(CustCartModel ccm);

    @Update
    void  updateCustCartModel(CustCartModel ccm);

    @Delete
    void deleteCustCartModel(CustCartModel ccm);
}
