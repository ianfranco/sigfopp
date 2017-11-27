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
@Table(name = "cuenta_banco_trabajador", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuentaBancoTrabajador.findAll", query = "SELECT c FROM CuentaBancoTrabajador c")
    , @NamedQuery(name = "CuentaBancoTrabajador.findByCuentaBancoTrabajadorId", query = "SELECT c FROM CuentaBancoTrabajador c WHERE c.cuentaBancoTrabajadorId = :cuentaBancoTrabajadorId")})
public class CuentaBancoTrabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuenta_banco_trabajador_id")
    private Integer cuentaBancoTrabajadorId;
    @JoinColumn(name = "cuenta_banco_trabajador_cuenta", referencedColumnName = "cuenta_bancaria_id", nullable = false)
    @ManyToOne(optional = false)
    private CuentaBancaria cuentaBancoTrabajadorCuenta;
    @JoinColumn(name = "cuenta_banco_trabajador_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador cuentaBancoTrabajadorTrabajador;

    public CuentaBancoTrabajador() {
    }

    public CuentaBancoTrabajador(Integer cuentaBancoTrabajadorId) {
        this.cuentaBancoTrabajadorId = cuentaBancoTrabajadorId;
    }

    public Integer getCuentaBancoTrabajadorId() {
        return cuentaBancoTrabajadorId;
    }

    public void setCuentaBancoTrabajadorId(Integer cuentaBancoTrabajadorId) {
        this.cuentaBancoTrabajadorId = cuentaBancoTrabajadorId;
    }

    public CuentaBancaria getCuentaBancoTrabajadorCuenta() {
        return cuentaBancoTrabajadorCuenta;
    }

    public void setCuentaBancoTrabajadorCuenta(CuentaBancaria cuentaBancoTrabajadorCuenta) {
        this.cuentaBancoTrabajadorCuenta = cuentaBancoTrabajadorCuenta;
    }

    public Trabajador getCuentaBancoTrabajadorTrabajador() {
        return cuentaBancoTrabajadorTrabajador;
    }

    public void setCuentaBancoTrabajadorTrabajador(Trabajador cuentaBancoTrabajadorTrabajador) {
        this.cuentaBancoTrabajadorTrabajador = cuentaBancoTrabajadorTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaBancoTrabajadorId != null ? cuentaBancoTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuentaBancoTrabajador)) {
            return false;
        }
        CuentaBancoTrabajador other = (CuentaBancoTrabajador) object;
        if ((this.cuentaBancoTrabajadorId == null && other.cuentaBancoTrabajadorId != null) || (this.cuentaBancoTrabajadorId != null && !this.cuentaBancoTrabajadorId.equals(other.cuentaBancoTrabajadorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CuentaBancoTrabajador[ cuentaBancoTrabajadorId=" + cuentaBancoTrabajadorId + " ]";
    }
    
}
