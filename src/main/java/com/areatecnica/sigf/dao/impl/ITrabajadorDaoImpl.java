/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;

import com.areatecnica.sigf.dao.ITrabajadorDao;
import com.areatecnica.sigf.entities.Cuenta;
import com.areatecnica.sigf.entities.Terminal;
import com.areatecnica.sigf.entities.Trabajador;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ITrabajadorDaoImpl extends GenericDAOImpl<Trabajador> implements ITrabajadorDao<Trabajador> {

    @Override
    public int findMaxCodigoCuenta(Cuenta cuenta) {
        try {
            Trabajador trabajador = (Trabajador) this.entityManager.createNamedQuery("Trabajador.findMaxCuenta").setParameter("trabajadorIdCuenta", cuenta).setMaxResults(1).getSingleResult();
            return trabajador.getTrabajadorCodigo() + 1;
        } catch (NoResultException ne) {
            return 1;
        }
    }

    @Override
    public List<Trabajador> findByTerminal(Terminal terminal) {
        try {
            return this.entityManager.createNamedQuery("Trabajador.findByTrabajadorIdTerminal").setParameter("trabajadorIdTerminal", terminal).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
    @Override
    public Trabajador findByTrabajadorRutAndCuenta(String rut, Cuenta cuenta) {
        try {
            return (Trabajador) this.entityManager.createNamedQuery("Trabajador.findByTrabajadorRutAndCuenta").setParameter("trabajadorRut", rut).setParameter("trabajadorIdCuenta", cuenta).setMaxResults(1).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
    @Override
    public Trabajador findByDefaultTrabajador(Integer id, Cuenta cuenta) {
        try {
            return (Trabajador) this.entityManager.createNamedQuery("Trabajador.findDefaultTrabajador").setParameter("trabajadorIdCuenta", cuenta).setParameter("trabajadorCodigo", id).setMaxResults(1).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
}
