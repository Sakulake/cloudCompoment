package com.learn.springcloud.po;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table minipro_user
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class MiniproUser {
    /**
     * Database Column Remarks:
     *   用户id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column minipro_user.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * Database Column Remarks:
     *   用户昵称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column minipro_user.nick_name
     *
     * @mbg.generated
     */
    private String nickName;

    /**
     * Database Column Remarks:
     *   用户手机号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column minipro_user.mobile
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column minipro_user.id
     *
     * @return the value of minipro_user.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column minipro_user.id
     *
     * @param id the value for minipro_user.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column minipro_user.nick_name
     *
     * @return the value of minipro_user.nick_name
     *
     * @mbg.generated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column minipro_user.nick_name
     *
     * @param nickName the value for minipro_user.nick_name
     *
     * @mbg.generated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column minipro_user.mobile
     *
     * @return the value of minipro_user.mobile
     *
     * @mbg.generated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column minipro_user.mobile
     *
     * @param mobile the value for minipro_user.mobile
     *
     * @mbg.generated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }
}