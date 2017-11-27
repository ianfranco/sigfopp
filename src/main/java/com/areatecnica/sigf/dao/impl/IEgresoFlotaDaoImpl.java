/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IEgresoFlotaDao;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoFlota;
import com.areatecnica.sigf.entities.Flota;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IEgresoFlotaDaoImpl extends GenericDAOImpl<EgresoFlota> implements IEgresoFlotaDao<EgresoFlota> {

    @Override
    public List<Egreso> findAllByFlota(Flota flota) {
        try {
            return this.entityManager.createNamedQuery("EgresoFlota.findByEgresoFlotaIdFlota").setParameter("egresoFlotaIdFlota", flota).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
