/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IEgresoBusDao;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.EgresoBus;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IEgresoBusDaoImpl extends GenericDAOImpl<EgresoBus> implements IEgresoBusDao<EgresoBus> {

    @Override
    public List<Egreso> findAllByBus(Bus bus) {
        try {
            return this.entityManager.createNamedQuery("EgresoBus.findByEgresoBusIdBus").setParameter("egresoBusIdBus", bus).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
