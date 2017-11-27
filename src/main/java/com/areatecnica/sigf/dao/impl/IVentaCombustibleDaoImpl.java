/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IVentaCombustibleDao;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.VentaCombustible;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IVentaCombustibleDaoImpl extends GenericDAOImpl<VentaCombustible> implements IVentaCombustibleDao<VentaCombustible> {

    @Override
    public List<VentaCombustible> findByDate(Date fecha) {
        try {
            return this.entityManager.createNamedQuery("VentaCombustible.findByVentaCombustibleFecha").
                    setParameter("ventaCombustibleFecha", fecha).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<VentaCombustible> findBySurtidorDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VentaCombustible> findByBusAndDate(Bus bus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VentaCombustible> findByDefaultBus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VentaCombustible findByBus(Bus bus, Boleto boleto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VentaCombustible findByBoleta(int folio) {
        try {
            return (VentaCombustible) this.entityManager.createNamedQuery("VentaCombustible.findByVentaCombustibleNumeroBoleta").
                    setParameter("ventaCombustibleNumeroBoleta", folio).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<VentaCombustible> findByBusSinRecaudar(Bus bus) {
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
    public List<VentaCombustible> findByTerminalDate(Terminal terminal, Date fecha) {
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
    public List<VentaCombustible> findTodos() {
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
    public void update2(VentaCombustible venta) {
        try {
            this.entityManager.merge(venta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<VentaCombustible> findByTerminalSinRecaudar(Terminal terminal, Boolean recaudado) {
        try {
            return this.entityManager.createNamedQuery("VentaCombustible.findByVentaCombustibleTerminalRecaudado").
                    setParameter("surtidorIdTerminal", terminal).
                    setParameter("ventaCombustibleRecaudado", Boolean.FALSE).
                    getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
