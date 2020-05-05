package com.community.cyd.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.ID
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.ACCOUNT_ID
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.NAME
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.TOKEN
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.GMT_CREATE
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.GMT_MODIFIED
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.AVATAR_URL
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    private String avatarUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USER.CONSULT_FEE
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    private Long consultFee;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.ID
     *
     * @return the value of USER.ID
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.ID
     *
     * @param id the value for USER.ID
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.ACCOUNT_ID
     *
     * @return the value of USER.ACCOUNT_ID
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.ACCOUNT_ID
     *
     * @param accountId the value for USER.ACCOUNT_ID
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.NAME
     *
     * @return the value of USER.NAME
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.NAME
     *
     * @param name the value for USER.NAME
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.TOKEN
     *
     * @return the value of USER.TOKEN
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.TOKEN
     *
     * @param token the value for USER.TOKEN
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.GMT_CREATE
     *
     * @return the value of USER.GMT_CREATE
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.GMT_CREATE
     *
     * @param gmtCreate the value for USER.GMT_CREATE
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.GMT_MODIFIED
     *
     * @return the value of USER.GMT_MODIFIED
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.GMT_MODIFIED
     *
     * @param gmtModified the value for USER.GMT_MODIFIED
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.AVATAR_URL
     *
     * @return the value of USER.AVATAR_URL
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.AVATAR_URL
     *
     * @param avatarUrl the value for USER.AVATAR_URL
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USER.CONSULT_FEE
     *
     * @return the value of USER.CONSULT_FEE
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public Long getConsultFee() {
        return consultFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USER.CONSULT_FEE
     *
     * @param consultFee the value for USER.CONSULT_FEE
     *
     * @mbg.generated Thu Apr 30 12:20:50 CST 2020
     */
    public void setConsultFee(Long consultFee) {
        this.consultFee = consultFee;
    }
}