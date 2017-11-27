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
@Table(name = "tipo_cuenta", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCuenta.findAll", query = "SELECT t FROM TipoCuenta t")
    , @NamedQuery(name = "TipoCuenta.findByTipoCuentaId", query = "SELECT t FROM TipoCuenta t WHERE t.tipoCuentaId = :tipoCuentaId")
    , @NamedQuery(name = "TipoCuenta.findByTipoCuentaNombre", query = "SELECT t FROM TipoCuenta t WHERE t.tipoCuentaNombre = :tipoCuentaNombre")
    , @NamedQuery(name = "TipoCuenta.findByTipoCuentaActiva", query = "SELECT t FROM TipoCuenta t WHERE t.tipoCuentaActiva = :tipoCuentaActiva")
    })
public class TipoCuenta extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_cuenta_id")
    private Integer tipoCuentaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_cuenta_nombre")
    private String tipoCuentaNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_cuenta_activa")
    private boolean tipoCuentaActiva;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "observacionTipoCuentaIdTipo")
    private List<ObservacionTipoCuenta> observacionTipoCuentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentaIdTipo")
    private List<Cuenta> cuentaList;

    public TipoCuenta() {
    }

    public TipoCuenta(Integer tipoCuentaId) {
        this.tipoCuentaId = tipoCuentaId;
    }

    public TipoCuenta(Integer tipoCuentaId, String tipoCuentaNombre, boolean tipoCuentaActiva) {
        this.tipoCuentaId = tipoCuentaId;
        this.tipoCuentaNombre = tipoCuentaNombre;
        this.tipoCuentaActiva = tipoCuentaActiva;
    }

    public Integer getTipoCuentaId() {
        return tipoCuentaId;
    }

    public void setTipoCuentaId(Integer tipoCuentaId) {
        this.tipoCuentaId = tipoCuentaId;
    }

    public String getTipoCuentaNombre() {
        return tipoCuentaNombre;
    }

    public void setTipoCuentaNombre(String tipoCuentaNombre) {
        this.tipoCuentaNombre = tipoCuentaNombre;
    }

    public boolean getTipoCuentaActiva() {
        return tipoCuentaActiva;
    }

    public void setTipoCuentaActiva(boolean tipoCuentaActiva) {
        this.tipoCuentaActiva = tipoCuentaActiva;
    }

    @XmlTransient
    public List<ObservacionTipoCuenta> getObservacionTipoCuentaList() {
        return observacionTipoCuentaList;
    }

    public void setObservacionTipoCuentaList(List<ObservacionTipoCuenta> observacionTipoCuentaList) {
        this.observacionTipoCuentaList = observacionTipoCuentaList;
    }

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCuentaId != null ? tipoCuentaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCuenta)) {
            return false;
        }
        TipoCuenta other = (TipoCuenta) object;
        if ((this.tipoCuentaId == null && other.tipoCuentaId != null) || (this.tipoCuentaId != null && !this.tipoCuentaId.equals(other.tipoCuentaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoCuenta[ tipoCuentaId=" + tipoCuentaId + " ]";
    }
    
}
