package com.test.creditsuisse;

import java.sql.Timestamp;

public class Event {

    private String id;
    private String type;
    private String host;
    private String state;
    private Timestamp timestamp;

    public Event() {
    }

    public Event(String id, String type, String host, String state, Timestamp timestamp) {
        this.id = id;
        this.type = type;
        this.host = host;
        this.state = state;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

}
