package com.example.hopeart.DataModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ArtistCustomizeOrderModel implements Serializable
{
    public ArtistCustomizeOrderModel() {}

        String customid;
        String artistId;
        String customerId;
        String customType;
        String customPhoto;
        String customFrameSize;
        String customPaperType;
        float customPrice;
        String customOrderStatus;
        String customOrderDate;

    public ArtistCustomizeOrderModel(String customid, String artistId, String customerId, String customType, String customPhoto, String customFrameSize, String customPaperType, float customPrice, String customOrderStatus, String customOrderDate) {
        this.customid = customid;
        this.artistId = artistId;
        this.customerId = customerId;
        this.customType = customType;
        this.customPhoto = customPhoto;
        this.customFrameSize = customFrameSize;
        this.customPaperType = customPaperType;
        this.customPrice = customPrice;
        this.customOrderStatus = customOrderStatus;
        this.customOrderDate = customOrderDate;
    }

    public void setCustomid(String customid) {
        this.customid = customid;
    }

    public String getCustomid () {
            return customid;
        }

        public String getArtistId () {
            return artistId;
        }

        public String getCustomerId () {
            return customerId;
        }

        public String getCustomerType () {
            return customType;
        }

        public String getCustomPhoto () {
            return customPhoto;
        }

        public String getCustomFrameSize () {
            return customFrameSize;
        }

    public float getCustomPrice() { return customPrice; }

    public String getCustomPaperType () {
            return customPaperType;
        }

        public String getCustomOrderStatus () {
            return customOrderStatus;
        }

        public String getCustomOrderDate () {
            return customOrderDate;
        }


    public Map<String,Object> toMap() {
        HashMap<String,Object> mapobj1 = new HashMap<>();

        mapobj1.put("customPhoto",this.customPhoto);
        mapobj1.put("customFrameSize",this.customFrameSize);
        mapobj1.put("customPaperType",this.customPaperType);
        mapobj1.put("customOrderStatus",this.customOrderStatus);
        mapobj1.put("customOrderDate",this.customOrderDate);
        mapobj1.put("customType",this.customType);
        mapobj1.put("customPrice",this.customPrice);
        mapobj1.put("artistId",this.artistId);
        mapobj1.put("customerId",this.customerId);

        return mapobj1;

    }

}
