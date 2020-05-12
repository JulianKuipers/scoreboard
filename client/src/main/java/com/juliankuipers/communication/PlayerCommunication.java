package com.juliankuipers.communication;

import org.json.JSONObject;

import java.io.IOException;

public class PlayerCommunication {

    public static JSONObject getPlayerById(int id) throws IOException, InterruptedException {
        return RequestMaker.requestJSONObject("/player/id/" + id, false);
    }
}
