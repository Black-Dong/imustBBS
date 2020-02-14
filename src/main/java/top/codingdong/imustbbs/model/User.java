package top.codingdong.imustbbs.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.account_id
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.source
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    private String source;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.avatar_url
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    private String avatarUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.name
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.token
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.create_time
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    private Long createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.update_time
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    private Long updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.account_id
     *
     * @return the value of user.account_id
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.account_id
     *
     * @param accountId the value for user.account_id
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.source
     *
     * @return the value of user.source
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.source
     *
     * @param source the value for user.source
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.avatar_url
     *
     * @return the value of user.avatar_url
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.avatar_url
     *
     * @param avatarUrl the value for user.avatar_url
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.name
     *
     * @return the value of user.name
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.name
     *
     * @param name the value for user.name
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.token
     *
     * @return the value of user.token
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.token
     *
     * @param token the value for user.token
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.create_time
     *
     * @return the value of user.create_time
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.create_time
     *
     * @param createTime the value for user.create_time
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.update_time
     *
     * @return the value of user.update_time
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.update_time
     *
     * @param updateTime the value for user.update_time
     *
     * @mbg.generated Fri Feb 14 20:10:45 CST 2020
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}