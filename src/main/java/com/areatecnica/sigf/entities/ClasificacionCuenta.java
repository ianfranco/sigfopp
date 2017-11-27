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
@Table(name = "clasificacion_cuenta", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasificacionCuenta.findAll", query = "SELECT c FROM ClasificacionCuenta c")
    , @NamedQuery(name = "ClasificacionCuenta.findByClasificacionCuentaContable", query = "SELECT c FROM ClasificacionCuenta c WHERE c.clasificacionCuentaContable = :clasificacionCuentaContable")
    , @NamedQuery(name = "ClasificacionCuenta.findByClasificacionCuentaNombre", query = "SELECT c FROM ClasificacionCuenta c WHERE c.clasificacionCuentaNombre = :clasificacionCuentaNombre")
    })
public class ClasificacionCuenta extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clasificacion_cuenta_contable")
    private Integer clasificacionCuentaContable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "clasificacion_cuenta_nombre")
    private String clasificacionCuentaNombre;    

    public ClasificacionCuenta() {
    }

    public ClasificacionCuenta(Integer clasificacionCuentaContable) {
        this.clasificacionCuentaContable = clasificacionCuentaContable;
    }

    public ClasificacionCuenta(Integer clasificacionCuentaContable, String clasificacionCuentaNombre) {
        this.clasificacionCuentaContable = clasificacionCuentaContable;
        this.clasificacionCuentaNombre = clasificacionCuentaNombre;
    }

    public Integer getClasificacionCuentaContable() {
        return clasificacionCuentaContable;
    }

    public void setClasificacionCuentaContable(Integer clasificacionCuentaContable) {
        this.clasificacionCuentaContable = clasificacionCuentaContable;
    }

    public String getClasificacionCuentaNombre() {
        return clasificacionCuentaNombre;
    }

    public void setClasificacionCuentaNombre(String clasificacionCuentaNombre) {
        this.clasificacionCuentaNombre = clasificacionCuentaNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clasificacionCuentaContable != null ? clasificacionCuentaContable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClasificacionCuenta)) {
            return false;
        }
        ClasificacionCuenta other = (ClasificacionCuenta) object;
        if ((this.clasificacionCuentaContable == null && other.clasificacionCuentaContable != null) || (this.clasificacionCuentaContable != null && !this.clasificacionCuentaContable.equals(other.clasificacionCuentaContable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ClasificacionCuenta[ clasificacionCuentaContable=" + clasificacionCuentaContable + " ]";
    }
    
}
