package humazed.github.com.autismkidsgame.puzzle.model;

import java.io.Serializable;

/**
 * Created by lenovo on 2016/3/22.
 */
public class User implements Serializable {

    //    Integer user_id;
    String phoneNum;
    String pwd;
    String nickName;
    String imgUrl;
    String mailNum;

    public User() {
    }

    public User(String phoneNum, String pwd, String nickName, String imgUrl, String mailNum) {
        this.phoneNum = phoneNum;
        this.pwd = pwd;
        this.nickName = nickName;
        this.imgUrl = imgUrl;
        this.mailNum = mailNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMailNum() {
        return mailNum;
    }

    public void setMailNum(String mailNum) {
        this.mailNum = mailNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "phoneNum='" + phoneNum + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", mailNum='" + mailNum + '\'' +
                '}';
    }
}
