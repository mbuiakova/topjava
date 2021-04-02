package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeFormatter implements Formatter<LocalTime> {

//    private final String pattern;
//
//    public TimeFormatter(String pattern) {
//        this.pattern = pattern;
//    }

    @Override
    public LocalTime parse(String formatted, Locale locale) throws ParseException {
//        if (formatted.length() == 0) {
//            return null;
//        }
//        return getDateFormat(locale).parse(formatted).toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        return DateTimeUtil.parseLocalTime(formatted);
    }

    @Override
    public String print(LocalTime time, Locale locale) {
//        if(time == null) return "";
//        return getDateFormat(locale).format(time);
        return time.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

//    protected DateFormat getDateFormat(Locale locale) {
//        DateFormat dateFormat = new SimpleDateFormat(this.pattern, locale);
//        dateFormat.setLenient(false);
//        return dateFormat;
//    }
}
