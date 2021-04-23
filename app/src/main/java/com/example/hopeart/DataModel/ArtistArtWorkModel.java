package com.example.hopeart.DataModel;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class ArtistArtWorkModel
{
    String strArtWorkId,strArtWorkType,strArtWorkFrameSize,strArtWorkPaperType,strArtWorkImage;
    float artWorkPrice;

    public ArtistArtWorkModel(String strArtWorkType, String strArtWorkFrameSize, String strArtWorkPaperType, float artWorkPrice) {
        this.strArtWorkType = strArtWorkType;
        this.strArtWorkFrameSize = strArtWorkFrameSize;
        this.strArtWorkPaperType = strArtWorkPaperType;
        this.artWorkPrice = artWorkPrice;
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

        mapobj1.put("ArtWorkId", this.strArtWorkId);
        mapobj1.put("ArtWorkType", this.strArtWorkType);
        mapobj1.put("ArtWorkFrameSize", this.strArtWorkFrameSize);
        mapobj1.put("ArtWorkPaperType", this.strArtWorkPaperType);
        mapobj1.put("ArtWorkKImage", this.strArtWorkImage);
        mapobj1.put("ArtWorkPrice", this.artWorkPrice);

        return mapobj1;
    }
}
