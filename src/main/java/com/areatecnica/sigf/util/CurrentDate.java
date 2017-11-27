/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.util;

import com.areatecnica.sigf.beans.util.JsfUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ianfr
 */
public class CurrentDate {

    private Calendar calendar;
    private static SimpleDateFormat sdf;
    private Date date;
    private int month;
    private int year;
    private int day;

    public CurrentDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;

        this.calendar = Calendar.getInstance();
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String from = day+"/" + month + "/" + year;
        try {
            this.date = format.parse(from);
            this.calendar.setTime(date);

        } catch (ParseException p) {
            JsfUtil.addErrorMessage("Error al parsear la Fecha");
        }
    }

    public CurrentDate() {
        this.calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void setDate(Date date) {
        this.calendar.setTime(date);
    }

    public Date date() {
        return calendar.getTime();
    }

    public Date getMaxDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date maxDate;
        String from = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + "/" + month + "/" + year;
        try {
            maxDate = format.parse(from);
            this.calendar.setTime(date);
            return maxDate;
        } catch (ParseException p) {
            JsfUtil.addErrorMessage("Error al parsear la Fecha");
        }
        return null;
    }

    public int day() {
        return calendar.get(Calendar.DATE);
    }

    public int month() {
        return calendar.get(Calendar.MONTH);
    }

    public int year() {
        return calendar.get(Calendar.YEAR);
    }

    public String format(Date toFormat) {
        return sdf.format(toFormat);
    }

}
