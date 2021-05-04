package com.example.hopeart.DataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CustCartModel implements Serializable
{

    @ColumnInfo(name = "strCartImg")
    String strCartImg;
    @ColumnInfo(name = "strFrameSize")
    String strFrameSize;
    @ColumnInfo(name = "custCardPrice")
    Float custCartPrice;
    @PrimaryKey(autoGenerate = true)
    int custCartId;

    @Ignore
    public CustCartModel(String strCartImg, String strFrameSize, Float custCartPrice) {
        this.strCartImg = strCartImg;
        this.strFrameSize = strFrameSize;
        this.custCartPrice = custCartPrice;
    }

    public CustCartModel() {
    }

    public String getStrCartImg() {
        return strCartImg;
    }

    public String getStrFrameSize() {
        return strFrameSize;
    }

    public Float getCustCartPrice() {
        return custCartPrice;
    }

    public int getCustCartId() {
        return custCartId;
    }

    public void setStrCartImg(String strCartImg) {
        this.strCartImg = strCartImg;
    }

    public void setStrFrameSize(String strFrameSize) {
        this.strFrameSize = strFrameSize;
    }

    public void setCustCartPrice(Float custCartPrice) {
        this.custCartPrice = custCartPrice;
    }

    public void setCustCartId(int custCartId) {
        this.custCartId = custCartId;
    }
}
