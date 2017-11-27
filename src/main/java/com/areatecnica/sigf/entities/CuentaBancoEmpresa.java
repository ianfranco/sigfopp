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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "cuenta_banco_empresa", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaBancoEmpresa.findAll", query = "SELECT c FROM CuentaBancoEmpresa c")
    , @NamedQuery(name = "CuentaBancoEmpresa.findByCuentaBancoEmpresaId", query = "SELECT c FROM CuentaBancoEmpresa c WHERE c.cuentaBancoEmpresaId = :cuentaBancoEmpresaId")})
public class CuentaBancoEmpresa extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuenta_banco_empresa_id")
    private Integer cuentaBancoEmpresaId;
    @JoinColumn(name = "cuenta_banco_empresa_id_cuenta", referencedColumnName = "cuenta_bancaria_id", nullable = false)
    @ManyToOne(optional = false)
    private CuentaBancaria cuentaBancoEmpresaIdCuenta;
    @JoinColumn(name = "cuenta_banco_empresa_id_empresa", referencedColumnName = "empresa_id", nullable = false)
    @ManyToOne(optional = false)
    private Empresa cuentaBancoEmpresaIdEmpresa;

    public CuentaBancoEmpresa() {
    }

    public CuentaBancoEmpresa(Integer cuentaBancoEmpresaId) {
        this.cuentaBancoEmpresaId = cuentaBancoEmpresaId;
    }

    public Integer getCuentaBancoEmpresaId() {
        return cuentaBancoEmpresaId;
    }

    public void setCuentaBancoEmpresaId(Integer cuentaBancoEmpresaId) {
        this.cuentaBancoEmpresaId = cuentaBancoEmpresaId;
    }

    public CuentaBancaria getCuentaBancoEmpresaIdCuenta() {
        return cuentaBancoEmpresaIdCuenta;
    }

    public void setCuentaBancoEmpresaIdCuenta(CuentaBancaria cuentaBancoEmpresaIdCuenta) {
        this.cuentaBancoEmpresaIdCuenta = cuentaBancoEmpresaIdCuenta;
    }

    public Empresa getCuentaBancoEmpresaIdEmpresa() {
        return cuentaBancoEmpresaIdEmpresa;
    }

    public void setCuentaBancoEmpresaIdEmpresa(Empresa cuentaBancoEmpresaIdEmpresa) {
        this.cuentaBancoEmpresaIdEmpresa = cuentaBancoEmpresaIdEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaBancoEmpresaId != null ? cuentaBancoEmpresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaBancoEmpresa)) {
            return false;
        }
        CuentaBancoEmpresa other = (CuentaBancoEmpresa) object;
        if ((this.cuentaBancoEmpresaId == null && other.cuentaBancoEmpresaId != null) || (this.cuentaBancoEmpresaId != null && !this.cuentaBancoEmpresaId.equals(other.cuentaBancoEmpresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CuentaBancoEmpresa[ cuentaBancoEmpresaId=" + cuentaBancoEmpresaId + " ]";
    }
    
}
