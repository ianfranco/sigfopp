/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ILoginDao;
import com.areatecnica.sigf.entities.Usuario;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ILoginDaoImpl extends GenericDAOImpl<Usuario> implements ILoginDao<Usuario>{

    private static final long serialVersionUID = 1602695723923335094L;

    public ILoginDaoImpl() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario login(String rut, String pass) {
        try {
            return (Usuario) this.entityManager.createNamedQuery("Usuario.findByUsuarioRutAndPass").setParameter("usuarioRut", rut).setParameter("usuarioPass", pass).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
