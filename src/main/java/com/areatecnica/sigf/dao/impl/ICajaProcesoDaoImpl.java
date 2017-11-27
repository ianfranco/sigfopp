/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ICajaProcesoDao;
import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ICajaProcesoDaoImpl extends GenericDAOImpl<CajaProceso> implements ICajaProcesoDao<CajaProceso> {

    @Override
    public List<CajaProceso> findByCaja(CajaRecaudacion cajaRecaudacion) {
        try {
            return this.entityManager.createNamedQuery("CajaProceso.findByCajaProcesoIdCaja").setParameter("cajaProcesoIdCaja", cajaRecaudacion).getResultList();
        } catch (NoResultException ne) {            
            return null;
        }
    }

    @Override
    public CajaProceso findByCajaProceso(CajaRecaudacion cajaRecaudacion, ProcesoRecaudacion procesoRecaudacion) throws NoResultException {
        return (CajaProceso) this.entityManager.createNamedQuery("CajaProceso.findByCajaProcesoIdCajaIdProceso").setParameter("cajaProcesoIdCaja", cajaRecaudacion).setParameter("cajaProcesoIdProceso", procesoRecaudacion).getSingleResult();
    }

    @Override
    public CajaProceso create(CajaProceso t) {
        this.entityManager.persist(t);
        return t;
    }

    @Override
    public CajaProceso update(CajaProceso t) {
        return this.entityManager.merge(t);
    }

}
