/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Egreso;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IEgresoProcesoRecaudacionDao<T> extends IGenericDAO<T> {
    
    public List<Egreso> findAllByProceso(ProcesoRecaudacion procesoRecaudacion);
    
}
