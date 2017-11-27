/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IUsuarioSessionDao;
import com.areatecnica.sigf.entities.Usuario;
import com.areatecnica.sigf.entities.UsuarioSession;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class IUsuarioSessionDaoImpl extends GenericDAOImpl<UsuarioSession> implements IUsuarioSessionDao<UsuarioSession> {

    @Override
    public List<UsuarioSession> findAllByUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioSession findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public void ingresar(UsuarioSession t) {
        this.entityManager.persist(t);        
    }

}
