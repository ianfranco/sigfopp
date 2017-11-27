/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.IGrupoServicioDao;
import com.areatecnica.sigf.entities.GrupoServicio;
import com.areatecnica.sigf.entities.Terminal;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IGrupoServicioDaoImpl extends GenericDAOImpl<GrupoServicio> implements IGrupoServicioDao<GrupoServicio> {

    @Override
    public List<GrupoServicio> findByTerminal(Terminal terminal) {
        try {
            return this.entityManager.createNamedQuery("GrupoServicio.findByTerminal").setParameter("grupoServicioIdTerminal", terminal).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
