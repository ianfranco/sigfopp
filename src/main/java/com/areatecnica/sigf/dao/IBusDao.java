/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao;

import com.areatecnica.sigf.entities.Bus;
import com.areatecnica.sigf.entities.EstadoBus;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.ProcesoRecaudacion;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.UnidadNegocio;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IBusDao<T> extends IGenericDAO<T> {
    
    public int findMaxNumeroUnidad(UnidadNegocio unidadNegocio);
    
    public List<Bus> findByUnidad(UnidadNegocio unidadNegocio);
    
    public List<Bus> findByProceso(ProcesoRecaudacion procesoRecaudacion);
    
    public List<Bus> findByGrupoServicio(GrupoServicio grupoServicio);
    
    public List<Bus> findAllByTerminal(Terminal terminal);
    
    public Bus findDefaultBus(EstadoBus estadoBus);
}
