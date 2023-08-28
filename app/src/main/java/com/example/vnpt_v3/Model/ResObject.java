package com.example.vnpt_v3.Model;

import org.json.JSONObject;

public class ResObject {
    private JSONObject jsonObject;

    public ResObject (JSONObject jsonObject)
    {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
