package com.bank.core.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

@Slf4j
public class DateUtil {

    private static final SimpleDateFormat FORMATTER =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date createAValidDate(Date value) {
        FORMATTER.setTimeZone(TimeZone.getTimeZone("America/Guayaquil"));
        return Objects.requireNonNullElseGet(value, Date::new);
    }

    public static String dateToString(Date value) {
        return FORMATTER.format(value);
    }
}
