/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "haber_liquidacion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HaberLiquidacion.findAll", query = "SELECT h FROM HaberLiquidacion h")
    , @NamedQuery(name = "HaberLiquidacion.findByHaberLiquidacionId", query = "SELECT h FROM HaberLiquidacion h WHERE h.haberLiquidacionId = :haberLiquidacionId")
    , @NamedQuery(name = "HaberLiquidacion.findByHaberLiquidacionFecha", query = "SELECT h FROM HaberLiquidacion h WHERE h.haberLiquidacionFecha = :haberLiquidacionFecha")
    , @NamedQuery(name = "HaberLiquidacion.findByHaberLiquidacionMontoHaber", query = "SELECT h FROM HaberLiquidacion h WHERE h.haberLiquidacionMontoHaber = :haberLiquidacionMontoHaber")
    , @NamedQuery(name = "HaberLiquidacion.findByHaberLiquidacionDescripcion", query = "SELECT h FROM HaberLiquidacion h WHERE h.haberLiquidacionDescripcion = :haberLiquidacionDescripcion")
    })
public class HaberLiquidacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "haber_liquidacion_id")
    private Integer haberLiquidacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "haber_liquidacion_fecha")
    @Temporal(TemporalType.DATE)
    private Date haberLiquidacionFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "haber_liquidacion_monto_haber")
    private int haberLiquidacionMontoHaber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "haber_liquidacion_descripcion")
    private String haberLiquidacionDescripcion;    
    @JoinColumn(name = "haber_liquidacion_id_haber", referencedColumnName = "haber_trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private HaberTrabajador haberLiquidacionIdHaber;
    @JoinColumn(name = "haber_liquidacion_id_liquidacion_sueldo", referencedColumnName = "liquidacion_sueldo_id", nullable = false)
    @ManyToOne(optional = false)
    private LiquidacionSueldo haberLiquidacionIdLiquidacionSueldo;

    public HaberLiquidacion() {
    }

    public HaberLiquidacion(Integer haberLiquidacionId) {
        this.haberLiquidacionId = haberLiquidacionId;
    }

    public HaberLiquidacion(Integer haberLiquidacionId, Date haberLiquidacionFecha, int haberLiquidacionMontoHaber, String haberLiquidacionDescripcion) {
        this.haberLiquidacionId = haberLiquidacionId;
        this.haberLiquidacionFecha = haberLiquidacionFecha;
        this.haberLiquidacionMontoHaber = haberLiquidacionMontoHaber;
        this.haberLiquidacionDescripcion = haberLiquidacionDescripcion;
    }

    public Integer getHaberLiquidacionId() {
        return haberLiquidacionId;
    }

    public void setHaberLiquidacionId(Integer haberLiquidacionId) {
        this.haberLiquidacionId = haberLiquidacionId;
    }

    public Date getHaberLiquidacionFecha() {
        return haberLiquidacionFecha;
    }

    public void setHaberLiquidacionFecha(Date haberLiquidacionFecha) {
        this.haberLiquidacionFecha = haberLiquidacionFecha;
    }

    public int getHaberLiquidacionMontoHaber() {
        return haberLiquidacionMontoHaber;
    }

    public void setHaberLiquidacionMontoHaber(int haberLiquidacionMontoHaber) {
        this.haberLiquidacionMontoHaber = haberLiquidacionMontoHaber;
    }

    public String getHaberLiquidacionDescripcion() {
        return haberLiquidacionDescripcion;
    }

    public void setHaberLiquidacionDescripcion(String haberLiquidacionDescripcion) {
        this.haberLiquidacionDescripcion = haberLiquidacionDescripcion;
    }

    public HaberTrabajador getHaberLiquidacionIdHaber() {
        return haberLiquidacionIdHaber;
    }

    public void setHaberLiquidacionIdHaber(HaberTrabajador haberLiquidacionIdHaber) {
        this.haberLiquidacionIdHaber = haberLiquidacionIdHaber;
    }

    public LiquidacionSueldo getHaberLiquidacionIdLiquidacionSueldo() {
        return haberLiquidacionIdLiquidacionSueldo;
    }

    public void setHaberLiquidacionIdLiquidacionSueldo(LiquidacionSueldo haberLiquidacionIdLiquidacionSueldo) {
        this.haberLiquidacionIdLiquidacionSueldo = haberLiquidacionIdLiquidacionSueldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (haberLiquidacionId != null ? haberLiquidacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HaberLiquidacion)) {
            return false;
        }
        HaberLiquidacion other = (HaberLiquidacion) object;
        if ((this.haberLiquidacionId == null && other.haberLiquidacionId != null) || (this.haberLiquidacionId != null && !this.haberLiquidacionId.equals(other.haberLiquidacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.HaberLiquidacion[ haberLiquidacionId=" + haberLiquidacionId + " ]";
    }
    
}
