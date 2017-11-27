/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans;

/**
 *
 * @author ianfr
 */
public class ColumnGuiaHelper {

    private String header;
    private String property;

    public ColumnGuiaHelper(String header, String property) {
        this.header = header;
        this.property = property;
    }

    public String getHeader() {
        return header;
    }

    public String getProperty() {
        return property;
    }

}
