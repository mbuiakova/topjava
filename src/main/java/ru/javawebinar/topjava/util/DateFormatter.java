package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatter implements Formatter<LocalDate> {

//    private final String pattern;
//
//    public DateFormatter(String pattern) {
//        this.pattern = pattern;
//    }

    public String print(LocalDate date, Locale locale) {
//        if (date == null) {
//            return "";
//        }
//        return getDateFormat(locale).format(date);
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }


    public LocalDate parse(String formatted, Locale locale) throws java.text.ParseException {
//        if (formatted.length() == 0) {
//            return null;
//        }
//        return getDateFormat(locale).parse(formatted).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return DateTimeUtil.parseLocalDate(formatted);
    }

//    protected DateFormat getDateFormat(Locale locale) {
//        DateFormat dateFormat = new SimpleDateFormat(this.pattern, locale);
//        dateFormat.setLenient(false);
//        return dateFormat;
//    }
}

