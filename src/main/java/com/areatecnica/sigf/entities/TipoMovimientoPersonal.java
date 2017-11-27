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
@Table(name = "tipo_movimiento_personal", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMovimientoPersonal.findAll", query = "SELECT t FROM TipoMovimientoPersonal t")
    , @NamedQuery(name = "TipoMovimientoPersonal.findByTipoMovimientoPersonalId", query = "SELECT t FROM TipoMovimientoPersonal t WHERE t.tipoMovimientoPersonalId = :tipoMovimientoPersonalId")
    , @NamedQuery(name = "TipoMovimientoPersonal.findByTipoMovimientoPersonalNombre", query = "SELECT t FROM TipoMovimientoPersonal t WHERE t.tipoMovimientoPersonalNombre = :tipoMovimientoPersonalNombre")
    })
public class TipoMovimientoPersonal extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_movimiento_personal_id")
    private Integer tipoMovimientoPersonalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tipo_movimiento_personal_nombre")
    private String tipoMovimientoPersonalNombre;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "liquidacionSueldoIdMovimientoPersonal")
    private List<LiquidacionSueldo> liquidacionSueldoList;

    public TipoMovimientoPersonal() {
    }

    public TipoMovimientoPersonal(Integer tipoMovimientoPersonalId) {
        this.tipoMovimientoPersonalId = tipoMovimientoPersonalId;
    }

    public TipoMovimientoPersonal(Integer tipoMovimientoPersonalId, String tipoMovimientoPersonalNombre) {
        this.tipoMovimientoPersonalId = tipoMovimientoPersonalId;
        this.tipoMovimientoPersonalNombre = tipoMovimientoPersonalNombre;
    }

    public Integer getTipoMovimientoPersonalId() {
        return tipoMovimientoPersonalId;
    }

    public void setTipoMovimientoPersonalId(Integer tipoMovimientoPersonalId) {
        this.tipoMovimientoPersonalId = tipoMovimientoPersonalId;
    }

    public String getTipoMovimientoPersonalNombre() {
        return tipoMovimientoPersonalNombre;
    }

    public void setTipoMovimientoPersonalNombre(String tipoMovimientoPersonalNombre) {
        this.tipoMovimientoPersonalNombre = tipoMovimientoPersonalNombre;
    }

    @XmlTransient
    public List<LiquidacionSueldo> getLiquidacionSueldoList() {
        return liquidacionSueldoList;
    }

    public void setLiquidacionSueldoList(List<LiquidacionSueldo> liquidacionSueldoList) {
        this.liquidacionSueldoList = liquidacionSueldoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoMovimientoPersonalId != null ? tipoMovimientoPersonalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMovimientoPersonal)) {
            return false;
        }
        TipoMovimientoPersonal other = (TipoMovimientoPersonal) object;
        if ((this.tipoMovimientoPersonalId == null && other.tipoMovimientoPersonalId != null) || (this.tipoMovimientoPersonalId != null && !this.tipoMovimientoPersonalId.equals(other.tipoMovimientoPersonalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoMovimientoPersonal[ tipoMovimientoPersonalId=" + tipoMovimientoPersonalId + " ]";
    }
    
}
