package com.rizzoli.fatturoio.business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {

    public static String data(String data) {
        try {
            Long l = Long.valueOf(data);
            Date d = new Date(l);
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            return format.format(d);
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

}
