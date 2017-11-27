/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.InventarioInterno;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IInventarioInternoDao<T> extends IGenericDAO<T>{
    
    public List<InventarioInterno> findByBoletoEstado(Boleto boleto, Boolean estado);
    
    public List<InventarioInterno> findByEstado(Boolean estado, Cuenta cuenta);
    
}
