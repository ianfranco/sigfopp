/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ITarifaGrupoServicioDao;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.TarifaGrupoServicio;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ITarifaGrupoServicioDaoImpl extends GenericDAOImpl<TarifaGrupoServicio> implements ITarifaGrupoServicioDao<TarifaGrupoServicio> {

    @Override
    public List<TarifaGrupoServicio> findAllByGrupoServicio(GrupoServicio grupoServicio) {
        try {
            return this.entityManager.createNamedQuery("TarifaGrupoServicio.findAllByGrupoServicio").setParameter("tarifaGrupoServicioIdGrupo", grupoServicio).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<TarifaGrupoServicio> findAllByCuenta() {
        try {
            return this.entityManager.createNamedQuery("TarifaGrupoServicio.findAll").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
}
