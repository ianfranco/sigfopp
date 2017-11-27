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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "caja_compensacion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CajaCompensacion.findAll", query = "SELECT c FROM CajaCompensacion c")
    , @NamedQuery(name = "CajaCompensacion.findByCajaCompensacionId", query = "SELECT c FROM CajaCompensacion c WHERE c.cajaCompensacionId = :cajaCompensacionId")
    , @NamedQuery(name = "CajaCompensacion.findByCajaCompensacionNombre", query = "SELECT c FROM CajaCompensacion c WHERE c.cajaCompensacionNombre = :cajaCompensacionNombre")
    })
public class CajaCompensacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "caja_compensacion_id")
    private Integer cajaCompensacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "caja_compensacion_nombre")
    private String cajaCompensacionNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaIdCajaCompensacion")
    private List<Empresa> empresaList;
    @JoinColumn(name = "caja_compensacion_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta cajaCompensacionIdCuenta;

    public CajaCompensacion() {
    }

    public CajaCompensacion(Integer cajaCompensacionId) {
        this.cajaCompensacionId = cajaCompensacionId;
    }

    public CajaCompensacion(Integer cajaCompensacionId, String cajaCompensacionNombre) {
        this.cajaCompensacionId = cajaCompensacionId;
        this.cajaCompensacionNombre = cajaCompensacionNombre;
    }

    public Integer getCajaCompensacionId() {
        return cajaCompensacionId;
    }

    public void setCajaCompensacionId(Integer cajaCompensacionId) {
        this.cajaCompensacionId = cajaCompensacionId;
    }

    public String getCajaCompensacionNombre() {
        return cajaCompensacionNombre;
    }

    public void setCajaCompensacionNombre(String cajaCompensacionNombre) {
        this.cajaCompensacionNombre = cajaCompensacionNombre;
    }
    
    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    public Cuenta getCajaCompensacionIdCuenta() {
        return cajaCompensacionIdCuenta;
    }

    public void setCajaCompensacionIdCuenta(Cuenta cajaCompensacionIdCuenta) {
        this.cajaCompensacionIdCuenta = cajaCompensacionIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cajaCompensacionId != null ? cajaCompensacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CajaCompensacion)) {
            return false;
        }
        CajaCompensacion other = (CajaCompensacion) object;
        if ((this.cajaCompensacionId == null && other.cajaCompensacionId != null) || (this.cajaCompensacionId != null && !this.cajaCompensacionId.equals(other.cajaCompensacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CajaCompensacion[ cajaCompensacionId=" + cajaCompensacionId + " ]";
    }
    
}
