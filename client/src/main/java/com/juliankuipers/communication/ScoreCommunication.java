package com.juliankuipers.communication;

import org.json.JSONObject;

import java.io.IOException;

public class ScoreCommunication {

    public static JSONObject getScoreById(int id) throws IOException, InterruptedException {
        return RequestMaker.requestJSONObject("/score/id/" + id, false);
    }
}
