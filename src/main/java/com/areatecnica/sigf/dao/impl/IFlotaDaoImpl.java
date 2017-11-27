/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IFlotaDao;
import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Flota;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IFlotaDaoImpl extends GenericDAOImpl<CajaProceso> implements IFlotaDao<CajaProceso> {

    @Override
    public List<Flota> findByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("Flota.findAllByCuenta").setParameter("idCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {            
            return null;
        }
    }

}
