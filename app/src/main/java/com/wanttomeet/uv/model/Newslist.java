package com.wanttomeet.uv.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by UV on 5/16/2017.
 */
public class Newslist {
    @SerializedName("result")
    @Expose
    public List<Result> result = null;

    public Newslist withResult(List<Result> result) {
        this.result = result;
        return this;
    }

    public class Result {

        @SerializedName("nw_id")
        @Expose
        public String nwId;
        @SerializedName("nw_title")
        @Expose
        public String nwTitle;
        @SerializedName("nw_body")
        @Expose
        public String nwBody;
        @SerializedName("nw_image")
        @Expose
        public String nwImage;

        public Result withNwId(String nwId) {
            this.nwId = nwId;
            return this;
        }

        public Result withNwTitle(String nwTitle) {
            this.nwTitle = nwTitle;
            return this;
        }

        public Result withNwBody(String nwBody) {
            this.nwBody = nwBody;
            return this;
        }

        public Result withNwImage(String nwImage) {
            this.nwImage = nwImage;
            return this;
        }

    }


}
