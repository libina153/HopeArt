package com.example.hopeart.DataModel;

public class ArtistPaymentModel {

    String paymentId;
    String artistId;
    String customerId;
    String paymentMode;
    String paymentAmount;
    String orderId;
    String paymentStatus;
    String paymentDate;

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

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public ArtistPaymentModel(String paymentId, String artistId, String customerId, String paymentMode, String paymentAmount, String orderId, String paymentStatus, String paymentDate) {
        this.paymentId = paymentId;
        this.artistId = artistId;
        this.customerId = customerId;
        this.paymentMode = paymentMode;
        this.paymentAmount = paymentAmount;
        this.orderId = orderId;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }
}
