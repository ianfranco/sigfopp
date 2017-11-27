/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.sockets;

import com.areatecnica.sigf.controllers.login.LoginController;
import com.areatecnica.sigf.entities.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author ianfr
 */
@Named(value = "recaudacionNotify")
@ViewScoped
public class RecaudacionNotify implements Serializable {

    @Inject
    @Push(channel = "recaudacion")
    private PushContext push;
    @Inject
    private LoginController controller;
    private Usuario user;
    private String recaudacion;
    private int numero;

    /**
     * Creates a new instance of RecaudacionNotify
     */
    public RecaudacionNotify() {
        this.numero = 0;
        this.user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("staff");
        //this.userCount = this.currentUser.getUsuarioIdCuenta();
    }

    @PostConstruct
    public void init() {
        
    }

    public void notificar() {
        this.recaudacion = "Prueba Numero:" + numero;
        int userId = this.user.getUsuarioId();
        push.send(recaudacion, userId);
        numero++;
    }

    public Usuario getUser() {
        return user;
    }

    public int getNumero() {
        return numero;
    }

}
