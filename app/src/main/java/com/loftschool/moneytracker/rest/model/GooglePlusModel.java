package com.loftschool.moneytracker.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GooglePlusModel {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("verified_email")
    @Expose
    private Boolean verifiedEmail;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("given_name")
    @Expose
    private String givenName;
    @SerializedName("family_name")
    @Expose
    private String familyName;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("locale")
    @Expose
    private String locale;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The verifiedEmail
     */
    public Boolean getVerifiedEmail() {
        return verifiedEmail;
    }

    /**
     *
     * @param verifiedEmail
     * The verified_email
     */
    public void setVerifiedEmail(Boolean verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The givenName
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     *
     * @param givenName
     * The given_name
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     *
     * @return
     * The familyName
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     *
     * @param familyName
     * The family_name
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     * The picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     *
     * @return
     * The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     *
     * @param locale
     * The locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

}
