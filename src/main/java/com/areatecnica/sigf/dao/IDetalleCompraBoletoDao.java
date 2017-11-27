/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.CompraBoleto;
import com.areatecnica.sigf.entities.DetalleCompraBoleto;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IDetalleCompraBoletoDao<T> extends IGenericDAO<T> {
    
    public List<DetalleCompraBoleto> findByCompraBoleto(CompraBoleto compraBoleto);
}
