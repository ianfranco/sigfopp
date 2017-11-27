/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.PrecioCombustible;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IPrecioCombustibleDao<T> extends IGenericDAO<T> {
    
    public PrecioCombustible findLastPrecio(Cuenta cuenta);
    
    public List<PrecioCombustible> findPrecioBetweenDates(Cuenta cuenta, Date from, Date to);
}
