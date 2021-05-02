package com.example.hopeart.DataModel;

public class CustOrderModel
{
   public CustOrderModel()
   {
   }
   String strOrderId,strOrderDate,strOrderStatus,strOrderArtworkImg,strOrderCustId,strOrderFrameSize,strOrderType;
   Float Amount;

    public CustOrderModel(String strOrderId, String strOrderDate, String strOrderStatus, String strOrderArtworkImg, String strOrderCustId, String strOrderFrameSize, String strOrderType, Float amount) {
        this.strOrderId = strOrderId;
        this.strOrderDate = strOrderDate;
        this.strOrderStatus = strOrderStatus;
        this.strOrderArtworkImg = strOrderArtworkImg;
        this.strOrderCustId = strOrderCustId;
        this.strOrderFrameSize = strOrderFrameSize;
        this.strOrderType = strOrderType;
        this.Amount = amount;
    }

    public String getStrOrderId() {
        return strOrderId;
    }

    public void setStrOrderId(String strOrderId) {
        this.strOrderId = strOrderId;
    }

    public String getStrOrderDate() {
        return strOrderDate;
    }

    public void setStrOrderDate(String strOrderDate) {
        this.strOrderDate = strOrderDate;
    }

    public String getStrOrderStatus() {
        return strOrderStatus;
    }

    public void setStrOrderStatus(String strOrderStatus) {
        this.strOrderStatus = strOrderStatus;
    }

    public String getStrOrderArtworkImg() {
        return strOrderArtworkImg;
    }

    public void setStrOrderArtworkImg(String strOrderArtworkImg) {
        this.strOrderArtworkImg = strOrderArtworkImg;
    }

    public String getStrOrderCustId() {
        return strOrderCustId;
    }

    public void setStrOrderCustId(String strOrderCustId) {

        this.strOrderCustId = strOrderCustId;
    }

    public String getStrOrderFrameSize() {
        return strOrderFrameSize;
    }

    public void setStrOrderFrameSize(String strOrderFrameSize) {
        this.strOrderFrameSize = strOrderFrameSize;
    }

    public String getStrOrderType() {
        return strOrderType;
    }

    public void setStrOrderType(String strOrderType) {
        this.strOrderType = strOrderType;
    }

    public Float getAmount() {
        return this.Amount;
     }

    public void setAmount(Float amount) {
        this.Amount = amount;
    }
}
