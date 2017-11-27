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
@Table(name = "tipo_cargo", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCargo.findAll", query = "SELECT t FROM TipoCargo t")
    , @NamedQuery(name = "TipoCargo.findByTipoCargoId", query = "SELECT t FROM TipoCargo t WHERE t.tipoCargoId = :tipoCargoId")
    , @NamedQuery(name = "TipoCargo.findByTipoCargoNombre", query = "SELECT t FROM TipoCargo t WHERE t.tipoCargoNombre = :tipoCargoNombre")
    , @NamedQuery(name = "TipoCargo.findByTipoCargoMontoDefecto", query = "SELECT t FROM TipoCargo t WHERE t.tipoCargoMontoDefecto = :tipoCargoMontoDefecto")
    })
public class TipoCargo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_cargo_id")
    private Integer tipoCargoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_cargo_nombre")
    private String tipoCargoNombre;
    @Column(name = "tipo_cargo_monto_defecto")
    private Integer tipoCargoMontoDefecto;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoBusIdTipo")
    private List<CargoBus> cargoBusList;
    @JoinColumn(name = "tipo_cargo_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta tipoCargoIdCuenta;

    public TipoCargo() {
    }

    public TipoCargo(Integer tipoCargoId) {
        this.tipoCargoId = tipoCargoId;
    }

    public TipoCargo(Integer tipoCargoId, String tipoCargoNombre) {
        this.tipoCargoId = tipoCargoId;
        this.tipoCargoNombre = tipoCargoNombre;
    }

    public Integer getTipoCargoId() {
        return tipoCargoId;
    }

    public void setTipoCargoId(Integer tipoCargoId) {
        this.tipoCargoId = tipoCargoId;
    }

    public String getTipoCargoNombre() {
        return tipoCargoNombre;
    }

    public void setTipoCargoNombre(String tipoCargoNombre) {
        this.tipoCargoNombre = tipoCargoNombre;
    }

    public Integer getTipoCargoMontoDefecto() {
        return tipoCargoMontoDefecto;
    }

    public void setTipoCargoMontoDefecto(Integer tipoCargoMontoDefecto) {
        this.tipoCargoMontoDefecto = tipoCargoMontoDefecto;
    }

    @XmlTransient
    public List<CargoBus> getCargoBusList() {
        return cargoBusList;
    }

    public void setCargoBusList(List<CargoBus> cargoBusList) {
        this.cargoBusList = cargoBusList;
    }

    public Cuenta getTipoCargoIdCuenta() {
        return tipoCargoIdCuenta;
    }

    public void setTipoCargoIdCuenta(Cuenta tipoCargoIdCuenta) {
        this.tipoCargoIdCuenta = tipoCargoIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoCargoId != null ? tipoCargoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCargo)) {
            return false;
        }
        TipoCargo other = (TipoCargo) object;
        if ((this.tipoCargoId == null && other.tipoCargoId != null) || (this.tipoCargoId != null && !this.tipoCargoId.equals(other.tipoCargoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoCargo[ tipoCargoId=" + tipoCargoId + " ]";
    }
    
}
