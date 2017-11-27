/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.RegistroMinuto;
import com.areatecnica.sigf.entities.Terminal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IRegistroMinutoDao<T> extends IGenericDAO<T> {
    
    public List<RegistroMinuto> findByDate(Date fecha);
    
    public List<RegistroMinuto> findByBusPagaDate(Bus bus, Date fecha);
    
    public List<RegistroMinuto> findByBusSinRecaudar(Bus bus);
    
    public List<RegistroMinuto> findByBusRecibeDate(Bus bus, Date fecha);
    
    public List<RegistroMinuto> findBySinRecaudar();
    
    public List<RegistroMinuto> findByTerminalSinRecaudar(Terminal terminal);
}
