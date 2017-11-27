/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IResumenRecaudacionDao;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.ResumenRecaudacion;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author ianfr
 */
public class IResumenRecaudacionDaoImpl extends GenericDAOImpl<ResumenRecaudacion> implements IResumenRecaudacionDao<ResumenRecaudacion> {

    @Override
    public ResumenRecaudacion findByCajaProcesoDate(CajaRecaudacion cajaRecaudacion, ProcesoRecaudacion procesoRecaudacion, Date fechaRecaudacion) {
        try {
            return (ResumenRecaudacion) this.entityManager.createNamedQuery("ResumenRecaudacion.findByCajaProcesoDate").setParameter("resumenRecaudacionIdCaja", cajaRecaudacion).setParameter("resumenRecaudacionIdProceso", procesoRecaudacion).setParameter("resumenRecaudacionFecha", fechaRecaudacion).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<ResumenRecaudacion> findAllByCajaProcesoBetweenDates(CajaRecaudacion cajaRecaudacion, ProcesoRecaudacion procesoRecaudacion, Date from, Date to) {
        try {
            return this.entityManager.createNamedQuery("ResumenRecaudacion.findAllByCajaProcesoBetweenDates").setParameter("resumenRecaudacionIdCaja", cajaRecaudacion).setParameter("resumenRecaudacionIdProceso", procesoRecaudacion).setParameter("from", from).setParameter("to", to).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public void editar(ResumenRecaudacion resumen) {
        try {
            this.entityManager.persist(resumen);
        } catch (IllegalArgumentException | TransactionRequiredException ne) {
            System.err.println("ERROR AAAAAAAAA");
        }
    }

}
