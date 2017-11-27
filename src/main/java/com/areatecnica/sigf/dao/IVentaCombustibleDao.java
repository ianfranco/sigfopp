/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.VentaBoleto;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.VentaCombustible;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IVentaCombustibleDao<T> extends IGenericDAO<T> {
    
    public VentaCombustible findByBoleta(int folio);
    
    public List<VentaCombustible> findTodos();
    
    public List<VentaCombustible> findByDate(Date fecha);
    
    public List<VentaCombustible> findBySurtidorDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta);

    public List<VentaCombustible> findByBusAndDate(Bus bus);

    public List<VentaCombustible> findByDefaultBus();

    public List<VentaCombustible> findByBusSinRecaudar(Bus bus);

    public VentaCombustible findByBus(Bus bus, Boleto boleto);
    
    public List<VentaCombustible> findByTerminalDate(Terminal terminal, Date fecha);
    
    public List<VentaCombustible> findByTerminalSinRecaudar(Terminal terminal, Boolean recaudado);
    
    public int findLastNumeroBoleta(Terminal terminal);

    public void update2(VentaCombustible venta);
}
