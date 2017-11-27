/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "tipo_cuenta_banco", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCuentaBanco.findAll", query = "SELECT t FROM TipoCuentaBanco t")
    , @NamedQuery(name = "TipoCuentaBanco.findByTipoCuentaBancoId", query = "SELECT t FROM TipoCuentaBanco t WHERE t.tipoCuentaBancoId = :tipoCuentaBancoId")
    , @NamedQuery(name = "TipoCuentaBanco.findByTipoCuentaBancoNombre", query = "SELECT t FROM TipoCuentaBanco t WHERE t.tipoCuentaBancoNombre = :tipoCuentaBancoNombre")
    })
public class TipoCuentaBanco extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_cuenta_banco_id")
    private Integer tipoCuentaBancoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_cuenta_banco_nombre")
    private String tipoCuentaBancoNombre;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaBancariaIdTipoCuenta")
    private List<CuentaBancaria> cuentaBancariaList;

    public TipoCuentaBanco() {
    }

    public TipoCuentaBanco(Integer tipoCuentaBancoId) {
        this.tipoCuentaBancoId = tipoCuentaBancoId;
    }

    public TipoCuentaBanco(Integer tipoCuentaBancoId, String tipoCuentaBancoNombre) {
        this.tipoCuentaBancoId = tipoCuentaBancoId;
        this.tipoCuentaBancoNombre = tipoCuentaBancoNombre;
    }

    public Integer getTipoCuentaBancoId() {
        return tipoCuentaBancoId;
    }

    public void setTipoCuentaBancoId(Integer tipoCuentaBancoId) {
        this.tipoCuentaBancoId = tipoCuentaBancoId;
    }

    public String getTipoCuentaBancoNombre() {
        return tipoCuentaBancoNombre;
    }

    public void setTipoCuentaBancoNombre(String tipoCuentaBancoNombre) {
        this.tipoCuentaBancoNombre = tipoCuentaBancoNombre;
    }

    @XmlTransient
    public List<CuentaBancaria> getCuentaBancariaList() {
        return cuentaBancariaList;
    }

    public void setCuentaBancariaList(List<CuentaBancaria> cuentaBancariaList) {
        this.cuentaBancariaList = cuentaBancariaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCuentaBancoId != null ? tipoCuentaBancoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCuentaBanco)) {
            return false;
        }
        TipoCuentaBanco other = (TipoCuentaBanco) object;
        if ((this.tipoCuentaBancoId == null && other.tipoCuentaBancoId != null) || (this.tipoCuentaBancoId != null && !this.tipoCuentaBancoId.equals(other.tipoCuentaBancoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoCuentaBanco[ tipoCuentaBancoId=" + tipoCuentaBancoId + " ]";
    }
    
}
