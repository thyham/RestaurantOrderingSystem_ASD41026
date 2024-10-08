package com.uts.restaurant.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AccessLogs implements Serializable {
    ArrayList<AccessLog> accesslogs;

    public AccessLogs(ArrayList<AccessLog> accesslogs) {
        this.accesslogs = accesslogs;
    }

    public ArrayList<AccessLog> getAccessLogs() {
        return accesslogs;
    }

    public void setAccessLogs(ArrayList<AccessLog> accesslogs) {
        this.accesslogs = accesslogs;
    }
}
