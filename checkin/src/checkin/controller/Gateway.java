package checkin.controller;

import apoio.GatewayHttp;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Gateway {

    private GatewayHttp gateway;
    private String accesstoken;

    public Gateway() {
        this.gateway = new GatewayHttp();
    }

    public String getAccessToken() {
        return this.accesstoken;
    }

    public String login(String email, String password) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("password", password);
        JSONObject res = this.gateway.post("/login", params);
        String status = res.get("success").toString();
        JSONObject data = (JSONObject) res.get("data");
        if (status.equals("true")) {
            this.accesstoken = data.get("access_token").toString();
        }
        return status;
    }

    public JSONArray getAllEvents() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "OAuth " + getAccessToken());
        JSONObject res = this.gateway.get("/event/", headers);
        JSONArray data = (JSONArray) res.get("data");
        return data;
    }

    public JSONArray getAllUsers() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "OAuth " + getAccessToken());
        JSONObject res = this.gateway.get("/users/", headers);
        JSONArray data = (JSONArray) res.get("data");
        return data;
    }

    public JSONArray getAllInscriptions() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "OAuth " + getAccessToken());
        JSONObject res = this.gateway.get("/event/inscriptions", headers);
        JSONArray data = (JSONArray) res.get("data");
        return data;
    }

    public String checkin(String email, String event) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "OAuth " + getAccessToken());
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_email", email);
        params.put("event_id", event);
        String status = "";
        try {
            JSONObject res = this.gateway.post("/checkin", params, headers);
            status = res.get("success").toString();
            sendEmail(email, "Checkin in event", "Checkin successfully!");
        } catch (Exception e) {
            status = "false";
        }

        return status;
    }

    public String registration(String email, String event) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "OAuth " + getAccessToken());
        Map<String, String> params = new HashMap<String, String>();
        params.put("user_email", email);
        params.put("event_id", event);
        params.put("checkin", "1");
        String status = "";
        try {
            JSONObject res = this.gateway.post("/event/registration", params, headers);
            status = res.get("success").toString();
        } catch (Exception e) {
            status = "false";
        }

        return status;
    }

    public String createUser(String email) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", email);
        params.put("email", email);
        params.put("password", "12345678");
        String status = "";
        try {
            JSONObject res = this.gateway.post("/register", params);
            status = res.get("success").toString();
            sendEmail(email, "User created", "User email: " + email + " \nPassword: 12345678");
        } catch (Exception e) {
            status = "false";
        }

        return status;
    }

    public void sendEmail(String email, String subject, String message) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "OAuth " + getAccessToken());
        Map<String, String> params = new HashMap<String, String>();
        params.put("to", email);
        params.put("subject", subject);
        params.put("message", message);
        this.gateway.post("/email/", params, headers);
    }
}
