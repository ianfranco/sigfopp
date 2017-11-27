/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IEstadoBusDao;
import com.areatecnica.sigf.entities.EstadoBus;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IEstadoBusDaoImpl extends GenericDAOImpl<EstadoBus> implements IEstadoBusDao<EstadoBus> {

    @Override
    public EstadoBus findById(int id) {
        try {
            return (EstadoBus) this.entityManager.createNamedQuery("EstadoBus.findByEstadoBusId").setParameter("estadoBusId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
