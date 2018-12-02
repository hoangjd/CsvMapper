package com.joe.csvMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {

    public String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
