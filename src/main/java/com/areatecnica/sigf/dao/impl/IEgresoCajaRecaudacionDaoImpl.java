/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IEgresoCajaRecaudacionDao;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.EgresoCajaRecaudacion;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IEgresoCajaRecaudacionDaoImpl extends GenericDAOImpl<EgresoCajaRecaudacion> implements IEgresoCajaRecaudacionDao<EgresoCajaRecaudacion> {

    @Override
    public List<EgresoCajaRecaudacion> findAllByCajaRecaudacion(CajaRecaudacion cajaRecaudacion) {
        try {
            return this.entityManager.createNamedQuery("EgresoCajaRecaudacion.findByEgresoCajaRecaudacionegresoCajaRecaudacionIdCaja").setParameter("egresoCajaRecaudacionIdCaja", cajaRecaudacion).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
