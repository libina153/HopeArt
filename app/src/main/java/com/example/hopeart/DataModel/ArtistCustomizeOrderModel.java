package com.example.hopeart.DataModel;

public class ArtistCustomizeOrderModel {
    String customid;
    String artistId;
    String customerId;
    String customerType;
    String customPhoto;
    String customFrameSize;
    String customPaperType;
    String customOrderStatus;
    String customOrderDate;

    public String getCustomid() {
        return customid;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getCustomPhoto() {
        return customPhoto;
    }

    public String getCustomFrameSize() {
        return customFrameSize;
    }

    public String getCustomPaperType() {
        return customPaperType;
    }

    public String getCustomOrderStatus() {
        return customOrderStatus;
    }

    public String getCustomOrderDate() {
        return customOrderDate;
    }




    public ArtistCustomizeOrderModel(String customid, String artistId, String customerId, String customPhoto, String customFrameSize, String customPaperType, String customOrderStatus, String customOrderDate,String customerType) {
        this.customid = customid;
        this.artistId = artistId;
        this.customerId = customerId;
        this.customPhoto = customPhoto;
        this.customFrameSize = customFrameSize;
        this.customPaperType = customPaperType;
        this.customOrderStatus = customOrderStatus;
        this.customOrderDate = customOrderDate;
        this.customerType = customerType;
    }
}
