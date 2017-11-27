/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.Recaudacion;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IRecaudacionDao<T> extends IGenericDAO<T> {

    public Recaudacion findByCuentaFolio(Cuenta cuenta, int folio);

    public List<Recaudacion> findByProcesoFechaRecaudacion(ProcesoRecaudacion procesoRecaudacion, Date fechaRecaudacion);

    public List<Recaudacion> findByProcesoCajaFechaRecaudacion(ProcesoRecaudacion procesoRecaudacion, CajaRecaudacion caja, Date fechaRecaudacion);

    public List<Recaudacion> findByCajaFechaRecaudacion(CajaRecaudacion caja, Date fechaRecaudacion);

    public List<Recaudacion> findByTerminalCajaFechaRecaudacion(Terminal terminal, CajaRecaudacion caja, Date fechaRecaudacion);

    public List<Recaudacion> findByProcesoFechaGuia(ProcesoRecaudacion procesoRecaudacion, Date fechaGuia);

    public List<Recaudacion> findByBusFechaRecaudacion(Bus bus, Date fecha);

    public List<Recaudacion> findByBusBetweenFechaRecaudacion(Bus bus, Date inicio, Date termino);
    
    public List<Recaudacion> findByTrabajadorBetweenFechaRecaudacion(Trabajador trabajador, Date inicio, Date termino);

    public List<Recaudacion> findByCuentaFecha(Cuenta cuenta, Date fecha);

    public List<Recaudacion> findByFechaGrupoServicio(GrupoServicio grupoServicio, Date fecha);

    public void delete(Recaudacion guia);

}
