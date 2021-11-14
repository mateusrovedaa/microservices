/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package checkin.controller;

import apoio.ConexaoBD;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author mateus
 */
public class Sync {

    ResultSet resultadoQ;

    public String saveUsers(String name, String email, int sync) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "INSERT INTO users VALUES ("
                    + "DEFAULT, "
                    + "'" + name + "',"
                    + "'" + email + "',"
                    + "'" + sync + "')";

            System.out.println("sql: " + sql);

            st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Error to save user: " + e);
            return e.toString();
        }
    }

    public Boolean findUser(String email) {
        boolean find = false;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM users "
                    + "WHERE  "
                    + "email = '" + email + "'";

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                find = true;
            }

            return find;

        } catch (Exception e) {
            System.out.println("Error to find user: " + e);
        }
        return find;
    }

    public JSONArray findUsers() {

        JSONArray array = new JSONArray();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM users "
                    + "WHERE  "
                    + "sync = 0";

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                JSONObject record = new JSONObject();
                record.put("name", resultadoQ.getString("name"));
                record.put("email", resultadoQ.getString("email"));
                array.add(record);
            }
            return array;

        } catch (Exception e) {
            System.out.println("Error to find user: " + e);
        }
        return array;
    }

    public String dropUsers() {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "DELETE FROM users";

            System.out.println("sql: " + sql);

            st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Error to delete all users: " + e);
            return e.toString();
        }
    }

    public String saveEvents(int id, String description, String date, int sync) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "INSERT INTO events VALUES ("
                    + "'" + id + "',"
                    + "'" + description + "',"
                    + "'" + date + "',"
                    + "'" + sync + "')";

            System.out.println("sql: " + sql);

            st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Error to save event: " + e);
            return e.toString();
        }
    }

    public String dropEvents() {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "DELETE FROM events";

            System.out.println("sql: " + sql);

            st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Error to delete all events: " + e);
            return e.toString();
        }
    }

    public String saveInscriptions(int checkin, int activated, int email_sent, String certificate, String user_email, int event_id, int sync) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "INSERT INTO event_registrations VALUES ("
                    + "DEFAULT, "
                    + "'" + checkin + "',"
                    + "'" + activated + "',"
                    + "'" + email_sent + "',"
                    + "'" + certificate + "',"
                    + "'" + user_email + "',"
                    + "'" + event_id + "',"
                    + "'" + sync + "')";

            System.out.println("sql: " + sql);

            st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Error to save inscription: " + e);
            return e.toString();
        }
    }

    public Boolean findInscription(String email, int id) {
        boolean find = false;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM event_registrations "
                    + "WHERE  "
                    + "user_email = '" + email + "' AND "
                    + "event_id = " + id + " AND "
                    + "activated = 1";

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            if (resultadoQ.next()) {
                find = true;
            }

            return find;

        } catch (Exception e) {
            System.out.println("Error to find inscription: " + e);
        }
        return find;
    }

    public JSONArray findInscriptions() {

        JSONArray array = new JSONArray();
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "SELECT * "
                    + "FROM event_registrations "
                    + "WHERE  "
                    + "sync = 0";

            System.out.println("sql: " + sql);

            resultadoQ = st.executeQuery(sql);

            while (resultadoQ.next()) {
                JSONObject record = new JSONObject();
                record.put("checkin", resultadoQ.getInt("checkin"));
                record.put("activated", resultadoQ.getInt("activated"));
                record.put("email_sent", resultadoQ.getInt("email_sent"));
                record.put("certificate", resultadoQ.getString("certificate"));
                record.put("user_email", resultadoQ.getString("user_email"));
                record.put("event_id", resultadoQ.getInt("event_id"));
                array.add(record);
            }
            return array;

        } catch (Exception e) {
            System.out.println("Error to find inscriptions: " + e);
        }
        return array;
    }

    public Boolean checkin(String email, int id) {
        boolean checkin = false;
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "UPDATE event_registrations "
                    + "SET "
                    + "checkin = 1, "
                    + "sync = 0 "
                    + "WHERE user_email = '" + email + "' AND "
                    + "event_id = " + id + "";

            System.out.println("sql: " + sql);

            int resultado = st.executeUpdate(sql);

            if (resultado == 1) {
                checkin = true;
            }

            return checkin;

        } catch (Exception e) {
            System.out.println("Error to do checkin: " + e);
        }
        return checkin;
    }

    public String dropInscriptions() {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "DELETE FROM event_registrations";

            System.out.println("sql: " + sql);

            st.executeUpdate(sql);

            return null;
        } catch (Exception e) {
            System.out.println("Error to delete all event_registrations: " + e);
            return e.toString();
        }
    }
}
