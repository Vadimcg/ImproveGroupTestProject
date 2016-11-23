package com.group.improve.improvegrouptestproject.models.DTO;

/**
 * User wrapper
 */

public class User {


    private int mId;
    private String mFullName;
    private String mUserName;
    private int mHash;
    private String mBirth;
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

    public int getmHash() {
        return mHash;
    }

    public boolean compareHashes(String hash){
        return this.mHash==hash.hashCode();
    }

    public void setmHash(int mHash) {
        this.mHash = mHash;
    }

    public String getmBirth() {
        return mBirth;
    }

    public void setmBirth(String mBirth) {
        this.mBirth = mBirth;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    private User(Builder builder){
        this.mId=builder.bId;
        this.mFullName=builder.bFullName;
        this.mUserName=builder.bUserName;
        this.mHash=builder.bHash;
        this.mBirth=builder.bBirth;
        this.mEmail=builder.bEmail;
    }


    public static class Builder{

        private int bId;
        private String bFullName;
        private String bUserName;
        private int bHash;
        private String bBirth;
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

        public Builder setbHash(int bHash) {
            this.bHash = bHash;
            return this;
        }

        public Builder setbBirth(String bBirth) {
            this.bBirth = bBirth;
            return this;
        }

        public Builder setbEmail(String bEmail) {
            this.bEmail = bEmail;
            return this;
        }

        public Builder setbPass(String pass) {
            this.bHash = pass.hashCode();
            return this;
        }

        public User build(){
            return new User(this);
        }

    }




}
