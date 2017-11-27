/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "valor_utm", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorUtm.findAll", query = "SELECT v FROM ValorUtm v")
    , @NamedQuery(name = "ValorUtm.findByValorUtmId", query = "SELECT v FROM ValorUtm v WHERE v.valorUtmId = :valorUtmId")
    , @NamedQuery(name = "ValorUtm.findByValorUtmFecha", query = "SELECT v FROM ValorUtm v WHERE v.valorUtmFecha = :valorUtmFecha")
    , @NamedQuery(name = "ValorUtm.findByValorUtmMonto", query = "SELECT v FROM ValorUtm v WHERE v.valorUtmMonto = :valorUtmMonto")
    })
public class ValorUtm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "valor_utm_id")
    private Integer valorUtmId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_utm_fecha")
    @Temporal(TemporalType.DATE)
    private Date valorUtmFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_utm_monto")
    private float valorUtmMonto;
    
    public ValorUtm() {
    }

    public ValorUtm(Integer valorUtmId) {
        this.valorUtmId = valorUtmId;
    }

    public ValorUtm(Integer valorUtmId, Date valorUtmFecha, float valorUtmMonto) {
        this.valorUtmId = valorUtmId;
        this.valorUtmFecha = valorUtmFecha;
        this.valorUtmMonto = valorUtmMonto;
    }

    public Integer getValorUtmId() {
        return valorUtmId;
    }

    public void setValorUtmId(Integer valorUtmId) {
        this.valorUtmId = valorUtmId;
    }

    public Date getValorUtmFecha() {
        return valorUtmFecha;
    }

    public void setValorUtmFecha(Date valorUtmFecha) {
        this.valorUtmFecha = valorUtmFecha;
    }

    public float getValorUtmMonto() {
        return valorUtmMonto;
    }

    public void setValorUtmMonto(float valorUtmMonto) {
        this.valorUtmMonto = valorUtmMonto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valorUtmId != null ? valorUtmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorUtm)) {
            return false;
        }
        ValorUtm other = (ValorUtm) object;
        if ((this.valorUtmId == null && other.valorUtmId != null) || (this.valorUtmId != null && !this.valorUtmId.equals(other.valorUtmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ValorUtm[ valorUtmId=" + valorUtmId + " ]";
    }
    
}
