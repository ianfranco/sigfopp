/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.beans.util;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ianfr
 */
@Named(value = "booleanBarController")
@ViewScoped
public class BooleanBarController implements Serializable {

    private Boolean status;

    /**
     * Creates a new instance of BooleanBarController
     */
    public BooleanBarController() {
        this.status = false;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void onChange() {
        if (status) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('bar').show();");
        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('bar').hide();");
        }
    }

}
