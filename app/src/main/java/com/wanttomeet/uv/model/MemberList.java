package com.wanttomeet.uv.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by UV on 20-May-17.
 */
public class MemberList {

    @SerializedName("result")
    @Expose
    public List<Result> result = null;
    @SerializedName("st")
    @Expose
    public int st;

    public MemberList withResult(List<Result> result) {
        this.result = result;
        return this;
    }

    public MemberList withSt(int st) {
        this.st = st;
        return this;
    }


    public class Result {

        @SerializedName("m_id")
        @Expose
        public String mId;
        @SerializedName("m_name")
        @Expose
        public String mName;
        @SerializedName("m_gender")
        @Expose
        public String mGender;
        @SerializedName("m_contact")
        @Expose
        public String mContact;
        @SerializedName("m_dob")
        @Expose
        public String mDob;
        @SerializedName("m_email")
        @Expose
        public String mEmail;
        @SerializedName("m_password")
        @Expose
        public String mPassword;
        @SerializedName("m_city")
        @Expose
        public String mCity;
        @SerializedName("m_state")
        @Expose
        public String mState;
        @SerializedName("m_contry")
        @Expose
        public String mContry;
        @SerializedName("m_education")
        @Expose
        public String mEducation;
        @SerializedName("m_collage")
        @Expose
        public String mCollage;
        @SerializedName("m_employedin")
        @Expose
        public String mEmployedin;
        @SerializedName("m_occupation")
        @Expose
        public String mOccupation;
        @SerializedName("m_occupation_detail")
        @Expose
        public String mOccupationDetail;
        @SerializedName("m_about")
        @Expose
        public String mAbout;
        @SerializedName("m_image")
        @Expose
        public String mImage;
        @SerializedName("m_lat")
        @Expose
        public String mLat;
        @SerializedName("m_long")
        @Expose
        public String mLong;
        @SerializedName("m_isPhoneAllow")
        @Expose
        public String mIsPhoneAllow;

        public Result withMId(String mId) {
            this.mId = mId;
            return this;
        }

        public Result withMName(String mName) {
            this.mName = mName;
            return this;
        }

        public Result withMGender(String mGender) {
            this.mGender = mGender;
            return this;
        }

        public Result withMContact(String mContact) {
            this.mContact = mContact;
            return this;
        }

        public Result withMDob(String mDob) {
            this.mDob = mDob;
            return this;
        }

        public Result withMEmail(String mEmail) {
            this.mEmail = mEmail;
            return this;
        }

        public Result withMPassword(String mPassword) {
            this.mPassword = mPassword;
            return this;
        }

        public Result withMCity(String mCity) {
            this.mCity = mCity;
            return this;
        }

        public Result withMState(String mState) {
            this.mState = mState;
            return this;
        }

        public Result withMContry(String mContry) {
            this.mContry = mContry;
            return this;
        }

        public Result withMEducation(String mEducation) {
            this.mEducation = mEducation;
            return this;
        }

        public Result withMCollage(String mCollage) {
            this.mCollage = mCollage;
            return this;
        }

        public Result withMEmployedin(String mEmployedin) {
            this.mEmployedin = mEmployedin;
            return this;
        }

        public Result withMOccupation(String mOccupation) {
            this.mOccupation = mOccupation;
            return this;
        }

        public Result withMOccupationDetail(String mOccupationDetail) {
            this.mOccupationDetail = mOccupationDetail;
            return this;
        }

        public Result withMAbout(String mAbout) {
            this.mAbout = mAbout;
            return this;
        }

        public Result withMImage(String mImage) {
            this.mImage = mImage;
            return this;
        }

        public Result withMLat(String mLat) {
            this.mLat = mLat;
            return this;
        }

        public Result withMLong(String mLong) {
            this.mLong = mLong;
            return this;
        }

        public Result withMIsPhoneAllow(String mIsPhoneAllow) {
            this.mIsPhoneAllow = mIsPhoneAllow;
            return this;
        }

    }
}
