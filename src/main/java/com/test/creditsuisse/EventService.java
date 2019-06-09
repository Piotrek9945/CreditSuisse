package com.test.creditsuisse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class EventService extends DbUtil {

    public EventService() {
    }

    public void persistEvents(Map<String, Set<Event>> map) {
        try (Connection con = getDbConnection()) {
            createTableIfNotExists(con);
            persistAllEvents(con, map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists(Connection con) throws SQLException {
        if (tableExist(con, "EVENT") == false) {
            con.createStatement().executeUpdate("CREATE TABLE event (id varchar(20), duration timestamp, type varchar(20), host varchar(20), alert boolean)");
        }
    }

    private void persistAllEvents(Connection con, Map<String, Set<Event>> map) {
        for (Map.Entry<String, Set<Event>> entry : map.entrySet()) {
            Set<Event> eventSet = entry.getValue();
            long timestampDifference = getTimestampDifference(eventSet);
            boolean alert = timestampDifference > 4 ? true : false;
            Event event = eventSet.iterator().next();
            persistEvent(con, event, alert);
        }
    }

    private void persistEvent(Connection con, Event event, boolean alert) {
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO event (id, duration, type, host, alert) VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, event.getId());
            ps.setTimestamp(2, event.getTimestamp());
            ps.setString(3, event.getType());
            ps.setString(4, event.getHost());
            ps.setBoolean(5, alert);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Set<Event>> getEventMap(String logFilePath) throws IOException {
        List<String> eventListStr = getEventList(logFilePath);
        List<Event> eventList = getEventList(eventListStr);
        return getEventMap(eventList);
    }

    private Map<String, Set<Event>> getEventMap(List<Event> eventList) {
        Map<String, Set<Event>> map = new HashMap<>();
        for (Event event : eventList) {
            map.put(event.getId(), new HashSet<Event>());
        }
        for (Event event : eventList) {
            map.get(event.getId()).add(event);
        }
        return map;
    }

    private boolean tableExist(Connection conn, String tableName) throws SQLException {
        boolean tExists = false;
        try (ResultSet rs = conn.getMetaData().getTables(null, "PUBLIC", tableName, null)) {
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(tableName)) {
                    tExists = true;
                    break;
                }
            }
        }
        return tExists;
    }

    private long getTimestampDifference(Set<Event> eventSet) {
        List<Timestamp> timestamp = new ArrayList<>();
        for (Event event : eventSet) {
            timestamp.add(event.getTimestamp());
        }
        return Math.abs(timestamp.get(0).getTime() - timestamp.get(1).getTime());
    }

    private List<String> getEventList(String logFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(logFilePath));
        List<String> logList = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            logList.add(line);
        }
        reader.close();
        return logList;
    }

    private List<Event> getEventList(List<String> eventListStr) throws IOException {
        List<Event> eventList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (String event : eventListStr) {
            Event result = mapper.readValue(event, Event.class);
            eventList.add(result);
        }
        return eventList;
    }
}
