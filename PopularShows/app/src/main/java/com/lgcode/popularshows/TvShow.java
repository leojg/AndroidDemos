package com.lgcode.popularshows;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by leojg on 11/5/16.
 */
public class TvShow implements Serializable {

    private int id;
    @SerializedName("name") private String title;
    private String overview;
    @SerializedName("poster_path") private String image;
    @SerializedName("backdrop_path") private String heroImage;
    @SerializedName("vote_average") private Double voteAverage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeroImage() {
        return heroImage;
    }

    public void setHeroImage(String heroImage) {
        this.heroImage = heroImage;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }
}
