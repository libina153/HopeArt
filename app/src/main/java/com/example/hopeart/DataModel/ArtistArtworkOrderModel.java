package com.example.hopeart.DataModel;

import java.util.HashMap;
import java.util.Map;

public class ArtistArtworkOrderModel {

    String artistId;
    String artworktype;
    String artworkimg;
    String artworkFrameSize;
    float artworkPrice;
    String artworkPaperType;
    String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtworktype() {
        return artworktype;
    }

    public String getArtworkimg() {
        return artworkimg;
    }

    public String getArtworkFrameSize() {
        return artworkFrameSize;
    }

    public float getArtworkPrice() {
        return artworkPrice;
    }

    public String getArtworkPaperType() {
        return artworkPaperType;
    }

    public ArtistArtworkOrderModel(String artistId, String artworktype, String artworkimg, String artworkFrameSize, float artworkPrice, String artworkPaperType,String orderStatus)
    {
        this.artistId = artistId;
        this.artworktype = artworktype;
        this.artworkimg = artworkimg;
        this.artworkFrameSize = artworkFrameSize;
        this.artworkPrice = artworkPrice;
        this.artworkPaperType = artworkPaperType;
        this.orderStatus = orderStatus;

    }
    public Map<String,Object> toMap() {
        HashMap<String, Object> mapobj1 = new HashMap<>();

        mapobj1.put("artistId", this.artistId);
        mapobj1.put("artworktype", this.artworktype);
        mapobj1.put("artworkimg", this.artworkimg);
        mapobj1.put("artworkFrameSize", this.artworkFrameSize);
        mapobj1.put("artworkPrice", this.artworkPrice);
        mapobj1.put("orderStatus",this.orderStatus);
        mapobj1.put("artworkPaperType", this.artworkPaperType);

        return mapobj1;
    }
}

