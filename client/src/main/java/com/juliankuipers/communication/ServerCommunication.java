package com.juliankuipers.communication;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

public class ServerCommunication {

    public static JSONArray getRecentGames() throws IOException, InterruptedException, JSONException {
        return new JSONArray(RequestMaker.request("/game/recent/10", false).body());
    }

}
