/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IPrecioCombustibleDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.PrecioCombustible;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IPrecioCombustibleDaoImpl extends GenericDAOImpl<PrecioCombustible> implements IPrecioCombustibleDao<PrecioCombustible> {

    @Override
    public PrecioCombustible findLastPrecio(Cuenta cuenta) {
        try {
            return (PrecioCombustible) this.entityManager.createNamedQuery("PrecioCombustible.findLastPrecio").setParameter("precioCombustibleIdCuenta", cuenta).setMaxResults(1).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<PrecioCombustible> findPrecioBetweenDates(Cuenta cuenta, Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
