/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.dao.impl;


import com.areatecnica.sigf.dao.IRegistroBoletoDao;
import com.areatecnica.sigf.entities.Boleto;
import com.areatecnica.sigf.entities.Guia;
import com.areatecnica.sigf.entities.RegistroBoleto;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class IRegistroBoletoDaoImpl extends GenericDAOImpl<RegistroBoleto> implements IRegistroBoletoDao<RegistroBoleto>{

    public IRegistroBoletoDaoImpl() {
        super(RegistroBoleto.class);
    }

    
    
    @Override
    public List<RegistroBoleto> findByGuia(Guia guia) {
        try {
            return this.entityManager.createNamedQuery("SerieBoletoGuia.findByGuia").setParameter("serieBoletoGuiaIdGuia", guia).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public RegistroBoleto findByGuiaBoleto(Guia guia, Boleto boleto) {
        try {
            return (RegistroBoleto) this.entityManager.createNamedQuery("SerieBoletoGuia.findByGuiaBoleto").setParameter("serieBoletoGuiaIdGuia", guia).setParameter("serieBoletoGuiaIdBoleto", boleto).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
