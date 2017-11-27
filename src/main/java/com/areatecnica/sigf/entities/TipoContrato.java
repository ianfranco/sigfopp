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
@Table(name = "tipo_contrato", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoContrato.findAll", query = "SELECT t FROM TipoContrato t")
    , @NamedQuery(name = "TipoContrato.findByTipoContratoId", query = "SELECT t FROM TipoContrato t WHERE t.tipoContratoId = :tipoContratoId")
    , @NamedQuery(name = "TipoContrato.findByTipoContratoNombre", query = "SELECT t FROM TipoContrato t WHERE t.tipoContratoNombre = :tipoContratoNombre")
    })
public class TipoContrato extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_contrato_id")
    private Integer tipoContratoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo_contrato_nombre")
    private String tipoContratoNombre;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liquidacionSueldoIdTipoContrato")
    private List<LiquidacionSueldo> liquidacionSueldoList;
    @JoinColumn(name = "tipo_contrato_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta tipoContratoIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relacionLaboralIdTipoContrato")
    private List<RelacionLaboral> relacionLaboralList;

    public TipoContrato() {
    }

    public TipoContrato(Integer tipoContratoId) {
        this.tipoContratoId = tipoContratoId;
    }

    public TipoContrato(Integer tipoContratoId, String tipoContratoNombre) {
        this.tipoContratoId = tipoContratoId;
        this.tipoContratoNombre = tipoContratoNombre;
    }

    public Integer getTipoContratoId() {
        return tipoContratoId;
    }

    public void setTipoContratoId(Integer tipoContratoId) {
        this.tipoContratoId = tipoContratoId;
    }

    public String getTipoContratoNombre() {
        return tipoContratoNombre;
    }

    public void setTipoContratoNombre(String tipoContratoNombre) {
        this.tipoContratoNombre = tipoContratoNombre;
    }

    @XmlTransient
    public List<LiquidacionSueldo> getLiquidacionSueldoList() {
        return liquidacionSueldoList;
    }

    public void setLiquidacionSueldoList(List<LiquidacionSueldo> liquidacionSueldoList) {
        this.liquidacionSueldoList = liquidacionSueldoList;
    }

    public Cuenta getTipoContratoIdCuenta() {
        return tipoContratoIdCuenta;
    }

    public void setTipoContratoIdCuenta(Cuenta tipoContratoIdCuenta) {
        this.tipoContratoIdCuenta = tipoContratoIdCuenta;
    }

    @XmlTransient
    public List<RelacionLaboral> getRelacionLaboralList() {
        return relacionLaboralList;
    }

    public void setRelacionLaboralList(List<RelacionLaboral> relacionLaboralList) {
        this.relacionLaboralList = relacionLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoContratoId != null ? tipoContratoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoContrato)) {
            return false;
        }
        TipoContrato other = (TipoContrato) object;
        if ((this.tipoContratoId == null && other.tipoContratoId != null) || (this.tipoContratoId != null && !this.tipoContratoId.equals(other.tipoContratoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoContrato[ tipoContratoId=" + tipoContratoId + " ]";
    }
    
}
