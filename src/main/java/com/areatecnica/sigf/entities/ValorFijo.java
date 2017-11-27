/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "valor_fijo", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorFijo.findAll", query = "SELECT v FROM ValorFijo v")
    , @NamedQuery(name = "ValorFijo.findByValorFijoId", query = "SELECT v FROM ValorFijo v WHERE v.valorFijoId = :valorFijoId")
    , @NamedQuery(name = "ValorFijo.findByValorFijoNombreValor", query = "SELECT v FROM ValorFijo v WHERE v.valorFijoNombreValor = :valorFijoNombreValor")
    , @NamedQuery(name = "ValorFijo.findByValorFijoMonto", query = "SELECT v FROM ValorFijo v WHERE v.valorFijoMonto = :valorFijoMonto")
    })
public class ValorFijo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "valor_fijo_id")
    private Integer valorFijoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "valor_fijo_nombre_valor")
    private String valorFijoNombreValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_fijo_monto")
    private Float valorFijoMonto;
    

    public ValorFijo() {
    }

    public ValorFijo(Integer valorFijoId) {
        this.valorFijoId = valorFijoId;
    }

    public ValorFijo(Integer valorFijoId, String valorFijoNombreValor) {
        this.valorFijoId = valorFijoId;
        this.valorFijoNombreValor = valorFijoNombreValor;
    }

    public Integer getValorFijoId() {
        return valorFijoId;
    }

    public void setValorFijoId(Integer valorFijoId) {
        this.valorFijoId = valorFijoId;
    }

    public String getValorFijoNombreValor() {
        return valorFijoNombreValor;
    }

    public void setValorFijoNombreValor(String valorFijoNombreValor) {
        this.valorFijoNombreValor = valorFijoNombreValor;
    }

    public Float getValorFijoMonto() {
        return valorFijoMonto;
    }

    public void setValorFijoMonto(Float valorFijoMonto) {
        this.valorFijoMonto = valorFijoMonto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (valorFijoId != null ? valorFijoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorFijo)) {
            return false;
        }
        ValorFijo other = (ValorFijo) object;
        if ((this.valorFijoId == null && other.valorFijoId != null) || (this.valorFijoId != null && !this.valorFijoId.equals(other.valorFijoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ValorFijo[ valorFijoId=" + valorFijoId + " ]";
    }
    
}
