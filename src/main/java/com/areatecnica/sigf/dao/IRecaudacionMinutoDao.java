/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.RecaudacionMinuto;
import com.areatecnica.sigf.entities.Terminal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IRecaudacionMinutoDao<T> extends IGenericDAO<T> {

    public List<RecaudacionMinuto> findTodos();
    
    public List<RecaudacionMinuto> findByDate(Date fecha);
    
    public List<RecaudacionMinuto> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta);

    public List<RecaudacionMinuto> findByBusAndDate(Bus bus, Date from, Date to);

    public List<RecaudacionMinuto> findByDefaultBus();

    public List<RecaudacionMinuto> findByBusSinRecaudar(Bus bus);
    
    public RecaudacionMinuto findByBus(Bus bus, Boleto boleto);
    
    public List<RecaudacionMinuto> findByTerminalDate(Terminal terminal, Date fecha);
    
    public int findLastNumeroBoleta(Terminal terminal);

}
