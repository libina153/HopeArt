package com.example.hopeart.DataModel;

import java.util.HashMap;
import java.util.Map;

public class ArtistPaymentModel {

    String paymentId;
    String artistId;
    String customerId;
    String paymentMode;
    float paymentAmount;
    String orderId;
    String paymentStatus;
    String paymentDate;
    String paymentimg;

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentimg() {
        return paymentimg;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public float getPaymentAmount() { return paymentAmount; }

    public String getOrderId() {
        return orderId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public ArtistPaymentModel(String paymentId, String artistId, String customerId, String paymentMode, float paymentAmount, String orderId, String paymentStatus, String paymentDate, String paymentimg) {
        this.paymentId = paymentId;
        this.artistId = artistId;
        this.customerId = customerId;
        this.paymentMode = paymentMode;
        this.paymentAmount = paymentAmount;
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.paymentimg = paymentimg;
    }

    public ArtistPaymentModel() {
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> mapobj1 = new HashMap<>();

        mapobj1.put("paymentId", this.paymentId);
        mapobj1.put("artistId", this.artistId);
        mapobj1.put("customerId", this.customerId);
        mapobj1.put("paymentMode", this.paymentMode);
        mapobj1.put("paymentAmount", this.paymentAmount);
        mapobj1.put("orderId", this.orderId);
        mapobj1.put("paymentStatus", this.paymentStatus);
        mapobj1.put("paymentDate", this.paymentDate);
        mapobj1.put("paymentimg",this.paymentimg);

        return mapobj1;
    }
}

