/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IRecaudacionCombustibleDao;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.Terminal;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IRecaudacionCombustibleDaoImpl extends GenericDAOImpl<RecaudacionCombustible> implements IRecaudacionCombustibleDao<RecaudacionCombustible> {

    @Override
    public List<RecaudacionCombustible> findByDate(Date fecha) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionCombustible.findAll").
                    setParameter("ventaCombustibleFecha", fecha).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionCombustible> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta) {
        try {
            return this.entityManager.createNamedQuery("RecaudacionCombustible.findByFechaRecaudacion").
                    setParameter("recaudacionIdCaja", cajaRecaudacion).
                    setParameter("recaudacionFecha", fechaVenta).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionCombustible> findByBusAndDate(Bus bus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecaudacionCombustible> findByDefaultBus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecaudacionCombustible findByBus(Bus bus, Boleto boleto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecaudacionCombustible> findByBusSinRecaudar(Bus bus) {
        try {
            return this.entityManager.createNamedQuery("VentaCombustible.findByVentaCombustibleBusRecaudado").
                    setParameter("ventaCombustibleIdBus", bus).
                    setParameter("ventaCombustibleRecaudado", Boolean.FALSE).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionCombustible> findByTerminalDate(Terminal terminal, Date fecha) {
        try {
            return this.entityManager.createNamedQuery("VentaCombustible.findByVentaCombustibleTerminalFecha").
                    setParameter("surtidorIdTerminal", terminal).
                    setParameter("ventaCombustibleFecha", fecha).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<RecaudacionCombustible> findTodos() {
        try {
            return this.entityManager.createNamedQuery("VentaCombustible.findAll").
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public int findLastNumeroBoleta(Terminal terminal) {
        try {

            return (int) entityManager.createQuery("SELECT MAX(v.ventaCombustibleNumeroBoleta) FROM VentaCombustible v where v.ventaCombustibleIdSurtidor.surtidorIdTerminal = :idTerminal")
                    .setParameter("idTerminal", terminal)
                    .getSingleResult() + 1;
        } catch (NoResultException ne) {
            return 1;
        }
    }

    @Override
    public void update2(RecaudacionCombustible venta) {
        try {
            this.entityManager.merge(venta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void detach(RecaudacionCombustible t) {
        super.detach(t); //To change body of generated methods, choose Tools | Templates.
    }

}
