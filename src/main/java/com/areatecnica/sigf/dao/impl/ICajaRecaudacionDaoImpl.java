/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICajaRecaudacionDao;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Usuario;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ICajaRecaudacionDaoImpl extends GenericDAOImpl<CajaRecaudacion> implements ICajaRecaudacionDao<CajaRecaudacion> {

    @Override
    public List<CajaRecaudacion> findAllByUser(Usuario usuario) {
        try {
            return this.entityManager.createNamedQuery("CajaRecaudacion.findByCajaRecaudacionIdUsuario").setParameter("cajaRecaudacionIdUsuario", usuario).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public CajaRecaudacion create(CajaRecaudacion t) {
        this.entityManager.persist(t);
        return t;
    }

    @Override
    public CajaRecaudacion update(CajaRecaudacion t) {
        return this.entityManager.merge(t);
    }

    @Override
    public CajaRecaudacion findById(int id) {
        try {
            return (CajaRecaudacion) this.entityManager.createNamedQuery("CajaRecaudacion.findByCajaRecaudacionId").setParameter("cajaRecaudacionId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
