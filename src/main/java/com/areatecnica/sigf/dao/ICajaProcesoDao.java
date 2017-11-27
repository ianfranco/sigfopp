/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.CajaProceso;
import com.areatecnica.sigf.entities.CajaRecaudacion;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface ICajaProcesoDao<T> extends IGenericDAO<T> {
    
    public List<CajaProceso> findByCaja(CajaRecaudacion cajaRecaudacion);
    
    public CajaProceso findByCajaProceso(CajaRecaudacion cajaRecaudacion, ProcesoRecaudacion procesoRecaudacion);
}
