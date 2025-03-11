package com.bank.core.util;

import com.bank.core.controller.exception.BadRequest;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

@Slf4j
public class DateUtil {

    private static final SimpleDateFormat FORMATTER =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date createDateFromString(String value) {
        FORMATTER.setTimeZone(TimeZone.getTimeZone("America/Guayaquil"));
        try {
            return  FORMATTER.parse(value);
        } catch (ParseException e) {
            throw new BadRequest("Formato incorrecto de fecha: ",e);
        }
    }

    public static Date createAValidDate(Date value) {
        FORMATTER.setTimeZone(TimeZone.getTimeZone("America/Guayaquil"));
        return Objects.requireNonNullElseGet(value, Date::new);
    }

    public static String dateToString(Date value) {
        return FORMATTER.format(value);
    }
}
