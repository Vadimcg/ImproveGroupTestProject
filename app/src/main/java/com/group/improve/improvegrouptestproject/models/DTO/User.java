package com.group.improve.improvegrouptestproject.models.DTO;

/**
 * User wrapper
 */

public class User {


    private int mId;
    private String mFullName;
    private String mUserName;
    private String mHash;
    private int mBirth;
    private String mEmail;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmHash() {
        return mHash;
    }

    public void setmHash(String mHash) {
        this.mHash = mHash;
    }

    public int getmBirth() {
        return mBirth;
    }

    public void setmBirth(int mBirth) {
        this.mBirth = mBirth;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    private User(){
    }



    public static class Builder{

        private int bId;
        private String bFullName;
        private String bUserName;
        private String bHash;
        private int bBirth;
        private String bEmail;

        public Builder setbId(int bId) {
            this.bId = bId;
            return this;
        }

        public Builder setbFullName(String bFullName) {
            this.bFullName = bFullName;
            return this;
        }

        public Builder setbUserName(String bUserName) {
            this.bUserName = bUserName;
            return this;
        }

        public Builder setbHash(String bHash) {
            this.bHash = bHash;
            return this;
        }

        public Builder setbBirth(int bBirth) {
            this.bBirth = bBirth;
            return this;
        }

        public Builder setbEmail(String bEmail) {
            this.bEmail = bEmail;
            return this;
        }

        public User build(){
            return new User();
        }

    }




}
