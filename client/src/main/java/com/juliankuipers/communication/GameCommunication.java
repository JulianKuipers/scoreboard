package com.juliankuipers.communication;

import org.json.JSONObject;

import java.io.IOException;

public class GameCommunication {

    public static JSONObject getGameDetails(int id) throws IOException, InterruptedException {
        String response = RequestMaker.request("/game/id/" + id, false).body();
        return new JSONObject(response);
    }
}
