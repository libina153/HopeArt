package com.example.hopeart.DataModel;

public class ArtistArtworkOrderModel {
    String artworkId;
    String artistId;
    String artworktype;
    String artworkimg;
    String imgHomeMenuImage;
    String artworkFrameSize;
    float artworkPrice;
    String artworkPaperType;



    public void setArtworkId(String artworkId) {
        this.artworkId = artworkId;
    }

    public String getArtworkId() {
        return artworkId;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtworktype() {
        return artworktype;
    }

    public String getArtworkimg() {
        return artworkimg;
    }

    public String getArtworkFrameSize() {
        return artworkFrameSize;
    }

    public float getArtworkPrice() {
        return artworkPrice;
    }

    public String getArtworkPaperType() {
        return artworkPaperType;
    }



    public ArtistArtworkOrderModel(String artworkId, String artistId, String artworktype, String artworkimg, String artworkFrameSize, float artworkPrice, String artworkPaperType) {
        this.artworkId = artworkId;
        this.artistId = artistId;
        this.artworktype = artworktype;
        this.artworkimg = artworkimg;
        this.artworkFrameSize = artworkFrameSize;
        this.artworkPrice = artworkPrice;
        this.artworkPaperType = artworkPaperType;

    }
}

