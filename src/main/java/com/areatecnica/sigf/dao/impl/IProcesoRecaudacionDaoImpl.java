/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IProcesoRecaudacionDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IProcesoRecaudacionDaoImpl extends GenericDAOImpl<ProcesoRecaudacion> implements IProcesoRecaudacionDao<ProcesoRecaudacion> {

    @Override
    public List<ProcesoRecaudacion> findAllByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("ProcesoRecaudacion.findByProcesoRecaudacionIdCuenta").setParameter("procesoRecaudacionIdCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
