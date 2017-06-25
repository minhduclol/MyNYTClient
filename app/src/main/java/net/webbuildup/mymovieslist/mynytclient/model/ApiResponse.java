package net.webbuildup.mymovieslist.mynytclient.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Duc Nguyen on 6/26/2017.
 */

public class ApiResponse {
    @SerializedName("response")
    private JsonObject response;

    @SerializedName("status")
    private String status;

    public JsonObject getReponse() {
        if (response == null) {
            return new JsonObject();
        }
        return response;
    }
    public String getStatus() {
        return status;
    }
}
