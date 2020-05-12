package com.juliankuipers.communication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GameCommunication {

    public static JSONObject getGameDetails(int id) throws IOException, InterruptedException {
        return RequestMaker.requestJSONObject("/game/id/" + id, false);
    }

    public static JSONArray getGameScores(int id) throws IOException, InterruptedException {
        return RequestMaker.requestJSONArray("/game/ranking/" + id, false);
    }

    public static JSONArray getGamePlayers(int id) throws IOException, InterruptedException {
        return RequestMaker.requestJSONArray("/game/players/" + id, false);
    }

    public static JSONObject getAllGameData(int id) throws IOException, InterruptedException {
        JSONObject game = getGameDetails(id);
        game.put("scores", getGameScores(id));
        game.put("players", getGamePlayers(id));
        return game;
    }
}
