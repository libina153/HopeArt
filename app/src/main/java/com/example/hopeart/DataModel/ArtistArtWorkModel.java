package com.example.hopeart.DataModel;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ArtistArtWorkModel implements Serializable
{
    public ArtistArtWorkModel() {
    }

    String strArtWorkId,strArtWorkType,strArtWorkFrameSize,strArtWorkPaperType,strArtWorkImage;
    float artWorkPrice;

    public ArtistArtWorkModel(String strArtWorkType, String strArtWorkFrameSize, String strArtWorkPaperType, float artWorkPrice) {
        this.strArtWorkType = strArtWorkType;
        this.strArtWorkFrameSize = strArtWorkFrameSize;
        this.strArtWorkPaperType = strArtWorkPaperType;
        this.artWorkPrice = artWorkPrice;
    }

    public void setStrArtWorkId(String strArtWorkId) {
        this.strArtWorkId = strArtWorkId;
    }

    public String getStrArtWorkId() {
        return strArtWorkId;
    }

    public String getStrArtWorkType() {
        return strArtWorkType;
    }

    public String getStrArtWorkFrameSize() {
        return strArtWorkFrameSize;
    }

    public String getStrArtWorkPaperType() {
        return strArtWorkPaperType;
    }

    public String getStrArtWorkImage() {
        return strArtWorkImage;
    }

    public float getArtWorkPrice() {
        return artWorkPrice;
    }

    public Map<String,Object> toMap() {
        HashMap<String, Object> mapobj1 = new HashMap<>();

        mapobj1.put("strArtWorkId", this.strArtWorkId);
        mapobj1.put("strArtWorkType", this.strArtWorkType);
        mapobj1.put("strArtWorkFrameSize", this.strArtWorkFrameSize);
        mapobj1.put("strArtWorkPaperType", this.strArtWorkPaperType);
        mapobj1.put("strArtWorkImage", "");
        mapobj1.put("artWorkPrice", this.artWorkPrice);

        return mapobj1;
    }
}
