package com.example.hopeart.DataModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ArtistArtworkOrderModel implements Serializable {

    public ArtistArtworkOrderModel(){}

    String artistId;
    String artworktype;
    String artworkimg;
    String artworkFrameSize;
    float artworkPrice;
    String artworkOrderStatus;
    String artworkPaperType;

    public void setArtistId(String artistId) {
        this.artistId = artistId;
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

    public String getArtworkOrderStatus() {
        return artworkOrderStatus;
    }



    public ArtistArtworkOrderModel(String artistId, String artworktype, String artworkimg, String artworkFrameSize, float artworkPrice, String artworkPaperType,String artworkOrderStatus) {

        this.artistId = artistId;
        this.artworktype = artworktype;
        this.artworkimg = artworkimg;
        this.artworkFrameSize = artworkFrameSize;
        this.artworkPrice = artworkPrice;
        this.artworkPaperType = artworkPaperType;
        this.artworkOrderStatus=artworkOrderStatus;

    }

    public Map<String,Object> toMap() {
        HashMap<String, Object> mapobj1 = new HashMap<>();

        mapobj1.put("artistId", this.artistId);
        mapobj1.put("artworktype", this.artworktype);
        mapobj1.put("artworkimg", this.artworkimg);
        mapobj1.put("artworkFrameSize", this.artworkFrameSize);
        mapobj1.put("artworkPrice", this.artworkPrice);
        mapobj1.put("artworkPaperType", this.artworkPaperType);
        mapobj1.put("artworkOrderStatus", this.artworkOrderStatus);

        return mapobj1;
    }
}

