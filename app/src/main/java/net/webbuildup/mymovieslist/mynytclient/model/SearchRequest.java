package net.webbuildup.mymovieslist.mynytclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Duc Nguyen on 6/25/2017.
 */

public class SearchRequest implements Parcelable {
    private int page = 0;
    private String query;
    private String beginDate; //format: YYYYMMDD
    private String endDate; //format: YYYYMMDD
    private String order = "Newest"; // newest or oldest
    private boolean hasArts;
    private boolean hasFashionAndStyle;
    private boolean hasSports;

    public SearchRequest() {

    }

    public Map<String,String> toQueryMap() {
        Map<String,String > options = new HashMap<>();
        if (query != null) options.put("q", query);
        if (beginDate != null) options.put("begin_date", beginDate);
        if (endDate != null) options.put("end_date", endDate);
        if (order != null) options.put("fq", order.toLowerCase());
        if (order != null) options.put("fq", order.toLowerCase());
        if (getNewDesk() != null) options.put("fq", "new_desk: (" + getNewDesk() + ")");
        options.put("page", String.valueOf(page));
        return options;
    }

    private String getNewDesk() {
        if (!hasArts && !hasFashionAndStyle && !hasSports) return null;
        String value = "";
        if (hasFashionAndStyle) value += "\"Fashion & Style\" ";
        if (hasSports) value += "\"Sports\" ";
        if (hasArts) value += "\"Arts \" ";
        return value.trim();
    }

    public void resetPage() { page = 0; }
    public void nextPage() { page++; }

    public void setPage(int page) {
        this.page = page;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setHasArts(boolean hasArts) {
        this.hasArts = hasArts;
    }

    public void setHasFashionAndStyle(boolean hasFashionAndStyle) {
        this.hasFashionAndStyle = hasFashionAndStyle;
    }

    public void setHasSports(boolean hasSports) {
        this.hasSports = hasSports;
    }

    public int getPage() {
        return page;
    }

    public String getQuery() {
        return query;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getOrder() {
        return order;
    }

    public boolean isHasArts() {
        return hasArts;
    }

    public boolean isHasFashionAndStyle() {
        return hasFashionAndStyle;
    }

    public boolean isHasSports() {
        return hasSports;
    }

    protected SearchRequest(Parcel in) {
        page = in.readInt();
        query = in.readString();
        beginDate = in.readString();
        endDate = in.readString();
        order = in.readString();
        hasArts = in.readByte() != 0;
        hasFashionAndStyle = in.readByte() != 0;
        hasSports = in.readByte() != 0;
    }

    public static final Creator<SearchRequest> CREATOR = new Creator<SearchRequest>() {
        @Override
        public SearchRequest createFromParcel(Parcel in) {
            return new SearchRequest(in);
        }

        @Override
        public SearchRequest[] newArray(int size) {
            return new SearchRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeString(query);
        dest.writeString(beginDate);
        dest.writeString(endDate);
        dest.writeString(order);
        dest.writeByte((byte) (hasArts ? 1 : 0));
        dest.writeByte((byte) (hasFashionAndStyle ? 1 : 0));
        dest.writeByte((byte) (hasSports ? 1 : 0));
    }
}
