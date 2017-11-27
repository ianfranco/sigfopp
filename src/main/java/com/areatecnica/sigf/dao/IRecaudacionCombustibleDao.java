/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.RecaudacionCombustible;
import com.areatecnica.sigf.entities.Terminal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IRecaudacionCombustibleDao<T> extends IGenericDAO<T> {

    public List<RecaudacionCombustible> findTodos();

    public List<RecaudacionCombustible> findByDate(Date fecha);

    public List<RecaudacionCombustible> findByCajaDate(CajaRecaudacion cajaRecaudacion, Date fechaVenta);

    public List<RecaudacionCombustible> findByBusAndDate(Bus bus);

    public List<RecaudacionCombustible> findByDefaultBus();

    public List<RecaudacionCombustible> findByBusSinRecaudar(Bus bus);

    public RecaudacionCombustible findByBus(Bus bus, Boleto boleto);

    public List<RecaudacionCombustible> findByTerminalDate(Terminal terminal, Date fecha);

    public int findLastNumeroBoleta(Terminal terminal);

    public void update2(RecaudacionCombustible venta);

    public void detach(RecaudacionCombustible venta);
}
