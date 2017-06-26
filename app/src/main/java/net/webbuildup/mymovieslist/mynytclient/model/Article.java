package net.webbuildup.mymovieslist.mynytclient.model;

import com.google.gson.annotations.SerializedName;

import net.webbuildup.mymovieslist.mynytclient.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duc Nguyen on 6/23/2017.
 */

public class Article {
    @SerializedName("web_url")
    private String webURL;
    @SerializedName("lead_paragraph")
    private String leadParagraph;
    @SerializedName("source")
    private String source;
    @SerializedName("multimedia")
    private List<Media> multimedia;
    @SerializedName("section_name")
    private String sectionName;
    @SerializedName("snippet")
    private String snippet;

    public class Media {
        private String url;
        private String type;
        private int width;
        private int height;

        public String getUrl() {
            return Constant.STATIC_BASE_URL + url;
        }

        public String getType() {
            return type;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }

    public String getWebURL() {
        return webURL;
    }

    public String getLeadParagraph() {
        return leadParagraph;
    }

    public String getSource() {
        return source;
    }

    public List<Media> getMultimedia() {
        return multimedia;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getSnippet() {
        return snippet;
    }
}
