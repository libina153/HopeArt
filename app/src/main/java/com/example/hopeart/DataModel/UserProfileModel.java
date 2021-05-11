package com.example.hopeart.DataModel;

import java.util.HashMap;
import java.util.Map;

public class UserProfileModel {

    String userId;
    String userName;
    String userMobileNo;
    String userAddress;
    String userEmail;
    String userType;
    String userAadhar;
    String userHandicapCerti;
    String userAadharImg;
    String userProfileImg;

    public UserProfileModel() {
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMobileNo() {
        return userMobileNo;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserAadhar() {
        return userAadhar;
    }

    public String getUserHandicapCerti() {
        return userHandicapCerti;
    }

    public String getUserAadharImg() {
        return userAadharImg;
    }

    public String getUserProfileImg() {
        return userProfileImg;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserMobileNo(String userMobileNo) {
        this.userMobileNo = userMobileNo;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setUserAadhar(String userAadhar) {
        this.userAadhar = userAadhar;
    }

    public void setUserHandicapCerti(String userHandicapCerti) {
        this.userHandicapCerti = userHandicapCerti;
    }

    public void setUserAadharImg(String userAadharImg) {
        this.userAadharImg = userAadharImg;
    }

    public void setUserProfileImg(String userProfileImg) {
        this.userProfileImg = userProfileImg;
    }

    public UserProfileModel(String userId, String userName, String userMobileNo, String userAddress, String userEmail, String userType, String userAadhar, String userHandicapCerti, String userAadharImg, String userProfileImg) {
        this.userId = userId;
        this.userName = userName;
        this.userMobileNo = userMobileNo;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.userType = userType;
        this.userAadhar = userAadhar;
        this.userHandicapCerti = userHandicapCerti;
        this.userAadharImg = userAadharImg;
        this.userProfileImg = userProfileImg;
    }

    public Map<String,Object> toMap() {
        HashMap<String, Object> mapobj1 = new HashMap<>();

        mapobj1.put("userId", this.userId);
        mapobj1.put("userName", this.userName);
        mapobj1.put("userMobileNo", this.userMobileNo);
        mapobj1.put("userAddress", this.userAddress);
        mapobj1.put("userEmail", this.userEmail);
        mapobj1.put("userType", this.userType);
        mapobj1.put("userAadhar", this.userAadhar);
        mapobj1.put("userHandicapCerti", this.userHandicapCerti);
        mapobj1.put("userAadharImg", this.userAadharImg);
        mapobj1.put("userProfileImg", this.userProfileImg);

        return mapobj1;
    }
}
