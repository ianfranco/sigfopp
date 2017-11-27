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
@Table(name = "descuento_liquidacion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescuentoLiquidacion.findAll", query = "SELECT d FROM DescuentoLiquidacion d")
    , @NamedQuery(name = "DescuentoLiquidacion.findByDescuentoLiquidacionId", query = "SELECT d FROM DescuentoLiquidacion d WHERE d.descuentoLiquidacionId = :descuentoLiquidacionId")
    , @NamedQuery(name = "DescuentoLiquidacion.findByDescuentoLiquidacionIdRelacionLaboral", query = "SELECT d FROM DescuentoLiquidacion d WHERE d.descuentoLiquidacionIdRelacionLaboral = :descuentoLiquidacionIdRelacionLaboral")
    , @NamedQuery(name = "DescuentoLiquidacion.findByDescuentoLiquidacionFecha", query = "SELECT d FROM DescuentoLiquidacion d WHERE d.descuentoLiquidacionFecha = :descuentoLiquidacionFecha")
    , @NamedQuery(name = "DescuentoLiquidacion.findByDescuentoLiquidacionMonto", query = "SELECT d FROM DescuentoLiquidacion d WHERE d.descuentoLiquidacionMonto = :descuentoLiquidacionMonto")
    , @NamedQuery(name = "DescuentoLiquidacion.findByDescuentoLiquidacionDescripcion", query = "SELECT d FROM DescuentoLiquidacion d WHERE d.descuentoLiquidacionDescripcion = :descuentoLiquidacionDescripcion")
    })
public class DescuentoLiquidacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "descuento_liquidacion_id")
    private Integer descuentoLiquidacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_liquidacion_id_relacion_laboral")
    private int descuentoLiquidacionIdRelacionLaboral;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_liquidacion_fecha")
    @Temporal(TemporalType.DATE)
    private Date descuentoLiquidacionFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_liquidacion_monto")
    private int descuentoLiquidacionMonto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descuento_liquidacion_descripcion")
    private String descuentoLiquidacionDescripcion;    
    @JoinColumn(name = "descuento_liquidacion_id_descuento_trabajador", referencedColumnName = "descuento_trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private DescuentoTrabajador descuentoLiquidacionIdDescuentoTrabajador;
    @JoinColumn(name = "descuento_liquidacion_id_liquidacion_sueldo", referencedColumnName = "liquidacion_sueldo_id", nullable = false)
    @ManyToOne(optional = false)
    private LiquidacionSueldo descuentoLiquidacionIdLiquidacionSueldo;

    public DescuentoLiquidacion() {
    }

    public DescuentoLiquidacion(Integer descuentoLiquidacionId) {
        this.descuentoLiquidacionId = descuentoLiquidacionId;
    }

    public DescuentoLiquidacion(Integer descuentoLiquidacionId, int descuentoLiquidacionIdRelacionLaboral, Date descuentoLiquidacionFecha, int descuentoLiquidacionMonto, String descuentoLiquidacionDescripcion) {
        this.descuentoLiquidacionId = descuentoLiquidacionId;
        this.descuentoLiquidacionIdRelacionLaboral = descuentoLiquidacionIdRelacionLaboral;
        this.descuentoLiquidacionFecha = descuentoLiquidacionFecha;
        this.descuentoLiquidacionMonto = descuentoLiquidacionMonto;
        this.descuentoLiquidacionDescripcion = descuentoLiquidacionDescripcion;
    }

    public Integer getDescuentoLiquidacionId() {
        return descuentoLiquidacionId;
    }

    public void setDescuentoLiquidacionId(Integer descuentoLiquidacionId) {
        this.descuentoLiquidacionId = descuentoLiquidacionId;
    }

    public int getDescuentoLiquidacionIdRelacionLaboral() {
        return descuentoLiquidacionIdRelacionLaboral;
    }

    public void setDescuentoLiquidacionIdRelacionLaboral(int descuentoLiquidacionIdRelacionLaboral) {
        this.descuentoLiquidacionIdRelacionLaboral = descuentoLiquidacionIdRelacionLaboral;
    }

    public Date getDescuentoLiquidacionFecha() {
        return descuentoLiquidacionFecha;
    }

    public void setDescuentoLiquidacionFecha(Date descuentoLiquidacionFecha) {
        this.descuentoLiquidacionFecha = descuentoLiquidacionFecha;
    }

    public int getDescuentoLiquidacionMonto() {
        return descuentoLiquidacionMonto;
    }

    public void setDescuentoLiquidacionMonto(int descuentoLiquidacionMonto) {
        this.descuentoLiquidacionMonto = descuentoLiquidacionMonto;
    }

    public String getDescuentoLiquidacionDescripcion() {
        return descuentoLiquidacionDescripcion;
    }

    public void setDescuentoLiquidacionDescripcion(String descuentoLiquidacionDescripcion) {
        this.descuentoLiquidacionDescripcion = descuentoLiquidacionDescripcion;
    }

    public DescuentoTrabajador getDescuentoLiquidacionIdDescuentoTrabajador() {
        return descuentoLiquidacionIdDescuentoTrabajador;
    }

    public void setDescuentoLiquidacionIdDescuentoTrabajador(DescuentoTrabajador descuentoLiquidacionIdDescuentoTrabajador) {
        this.descuentoLiquidacionIdDescuentoTrabajador = descuentoLiquidacionIdDescuentoTrabajador;
    }

    public LiquidacionSueldo getDescuentoLiquidacionIdLiquidacionSueldo() {
        return descuentoLiquidacionIdLiquidacionSueldo;
    }

    public void setDescuentoLiquidacionIdLiquidacionSueldo(LiquidacionSueldo descuentoLiquidacionIdLiquidacionSueldo) {
        this.descuentoLiquidacionIdLiquidacionSueldo = descuentoLiquidacionIdLiquidacionSueldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (descuentoLiquidacionId != null ? descuentoLiquidacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescuentoLiquidacion)) {
            return false;
        }
        DescuentoLiquidacion other = (DescuentoLiquidacion) object;
        if ((this.descuentoLiquidacionId == null && other.descuentoLiquidacionId != null) || (this.descuentoLiquidacionId != null && !this.descuentoLiquidacionId.equals(other.descuentoLiquidacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DescuentoLiquidacion[ descuentoLiquidacionId=" + descuentoLiquidacionId + " ]";
    }
    
}
