/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.exceptions;

/**
 *
 * @author ianfr
 */
public class PersistenceExceptionHandler implements org.eclipse.persistence.exceptions.ExceptionHandler {

    @Override
    public Object handleException(RuntimeException re) {

        return re.fillInStackTrace();
    }

}
