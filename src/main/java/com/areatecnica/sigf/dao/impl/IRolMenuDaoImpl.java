/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IRolMenuDao;
import com.areatecnica.sigf.entities.Rol;
import com.areatecnica.sigf.entities.RolMenu;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IRolMenuDaoImpl extends GenericDAOImpl<RolMenu> implements IRolMenuDao<RolMenu> {

    @Override
    public List<RolMenu> findAllByRol(Rol rol) {
        try {
            return this.entityManager.createNamedQuery("RolMenu.findByRol").setParameter("rolMenuIdRol", rol).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
