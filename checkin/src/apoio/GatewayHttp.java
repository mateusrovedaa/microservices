/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apoio;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author mateus
 */
public class GatewayHttp {

    private String gatewayUrl = "http://177.44.248.84:8000";

    public void setGatewayUrl(String url) {
        this.gatewayUrl = url;
    }

    public String getGatewayUrl() {
        return this.gatewayUrl;
    }

    public JSONObject post(String endpoint, Map<String, String> params, Map<String, String> headers) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            String res = HttpUtil.postForm(getGatewayUrl() + endpoint, params, headers);
            json = (JSONObject) parser.parse(res);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return json;
    }

    public JSONObject post(String endpoint, Map<String, String> params) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            String res = HttpUtil.postForm(getGatewayUrl() + endpoint, params);
            json = (JSONObject) parser.parse(res);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return json;
    }
    
    public JSONObject get(String endpoint, Map<String, String> headers ) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            String res = HttpUtil.get(getGatewayUrl() + endpoint, headers);
            json = (JSONObject) parser.parse(res);
        } catch (ParseException ex) {
           System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return json;
    }
}
