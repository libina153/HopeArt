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
        String customerType;
        String customPhoto;
        String customFrameSize;
        String customPaperType;
        String customOrderStatus;
        String customOrderDate;

    public ArtistCustomizeOrderModel(String artistId, String customerId, String customerType, String customFrameSize, String customPaperType, String customOrderStatus, String customOrderDate) {
        this.artistId = artistId;
        this.customerId = customerId;
        this.customerType = customerType;
        this.customFrameSize = customFrameSize;
        this.customPaperType = customPaperType;
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
            return customerType;
        }

        public String getCustomPhoto () {
            return customPhoto;
        }

        public String getCustomFrameSize () {
            return customFrameSize;
        }

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

        mapobj1.put("customPhoto","");
        mapobj1.put("customFrameSize",this.customFrameSize);
        mapobj1.put("customPaperType",this.customPaperType);
        mapobj1.put("customOrderStatus",this.customOrderStatus);
        mapobj1.put("customOrderDate",this.customOrderDate);
        mapobj1.put("customerType",this.customerType);

        mapobj1.put("artistId",this.artistId);
        mapobj1.put("customerId",this.customerId);

        return mapobj1;

    }

}
