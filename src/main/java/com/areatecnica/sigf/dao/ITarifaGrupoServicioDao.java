/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.TarifaGrupoServicio;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface ITarifaGrupoServicioDao<T> extends IGenericDAO<T> {

    public List<TarifaGrupoServicio> findAllByGrupoServicio(GrupoServicio grupoServicio);
    
    public List<TarifaGrupoServicio> findAllByCuenta();
}
