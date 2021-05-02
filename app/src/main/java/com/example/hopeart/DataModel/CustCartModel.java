package com.example.hopeart.DataModel;

public class CustCartModel
{
    public CustCartModel(){
    }

    String strCartImg,strFrameSize;
    Float custCardPrice;
    int custCardId;

    public CustCartModel(String strCartImg, String strFrameSize, Float custCardPrice, int custCardId) {
        this.custCardId = custCardId;
        this.strCartImg = strCartImg;
        this.strFrameSize = strFrameSize;
        this.custCardPrice = custCardPrice;

    }

    public String getStrCartImg() {
        return strCartImg;
    }

    public String getStrFrameSize() {
        return strFrameSize;
    }

    public Float getCustCardPrice() {
        return custCardPrice;
    }

    public int getCustCardId() {
        return custCardId;
    }

    public void setStrCartImg(String strCartImg) {
        this.strCartImg = strCartImg;
    }

    public void setStrFrameSize(String strFrameSize) {
        this.strFrameSize = strFrameSize;
    }

    public void setCustCardPrice(Float custCardPrice) {
        this.custCardPrice = custCardPrice;
    }

    public void setCustCardId(int custCardId) {
        this.custCardId = custCardId;
    }
}
