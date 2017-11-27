/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IUnidadNegocioDao<T> extends IGenericDAO<T> {

    public UnidadNegocio findById(int idUnidadNegocio);
    
    public List<UnidadNegocio> findByCuenta(Cuenta cuenta);
    
}
