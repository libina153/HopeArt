package com.example.hopeart.DataModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CustWishListModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int wishListId;

    @ColumnInfo(name = "wishListImg")
    String wishListImg;

    @ColumnInfo(name="wishListPrice")
    Float wishListPrice;

    @ColumnInfo(name ="wishListType" )
    String wishListType;

    public int getWishListId() {
        return wishListId;
    }

    public String getWishListImg() {
        return wishListImg;
    }

    public Float getWishListPrice() {
        return wishListPrice;
    }

    public String getWishListType() {
        return wishListType;
    }


    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public void setWishListImg(String wishListImg) {
        this.wishListImg = wishListImg;
    }

    public void setWishListPrice(Float wishListPrice) {
        this.wishListPrice = wishListPrice;
    }

    public void setWishListType(String wishListType) {
        this.wishListType = wishListType;
    }

    @Ignore
    public CustWishListModel(String wishListImg, Float wishListPrice, String wishListType) {
        this.wishListImg = wishListImg;
        this.wishListPrice = wishListPrice;
        this.wishListType = wishListType;
    }

    public CustWishListModel() {
    }
}
