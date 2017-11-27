/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.controllers;

import com.areatecnica.sigf.entities.TipoTrabajador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ianfr
 */
@Stateless
public class TipoTrabajadorFacade extends AbstractFacade<TipoTrabajador> {

    @PersistenceContext(unitName = "com.areatecnica_sigf-admin_war_2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoTrabajadorFacade() {
        super(TipoTrabajador.class);
    }
    
}
