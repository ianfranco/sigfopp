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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "detalle_resumen_recaudacion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleResumenRecaudacion.findAll", query = "SELECT d FROM DetalleResumenRecaudacion d")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacionId", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacionId = :detalleResumenRecaudacionId")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacionCantidadEfectivo", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacionCantidadEfectivo = :detalleResumenRecaudacionCantidadEfectivo")
    , @NamedQuery(name = "DetalleResumenRecaudacion.findByDetalleResumenRecaudacionTotalEfectivo", query = "SELECT d FROM DetalleResumenRecaudacion d WHERE d.detalleResumenRecaudacionTotalEfectivo = :detalleResumenRecaudacionTotalEfectivo")})
public class DetalleResumenRecaudacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_resumen_recaudacion_id")
    private Integer detalleResumenRecaudacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_cantidad_efectivo")
    private int detalleResumenRecaudacionCantidadEfectivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_resumen_recaudacion_total_efectivo")
    private int detalleResumenRecaudacionTotalEfectivo;
    @JoinColumn(name = "detalle_resumen_recaudacion_id_efectivo_caja", referencedColumnName = "efectivo_caja_id", nullable = false)
    @ManyToOne(optional = false)
    private EfectivoCaja detalleResumenRecaudacionIdEfectivoCaja;
    @JoinColumn(name = "detalle_resumen_recaudacion_id_resumen", referencedColumnName = "resumen_recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private ResumenRecaudacion detalleResumenRecaudacionIdResumen;

    public DetalleResumenRecaudacion() {
    }

    public DetalleResumenRecaudacion(Integer detalleResumenRecaudacionId) {
        this.detalleResumenRecaudacionId = detalleResumenRecaudacionId;
    }

    public DetalleResumenRecaudacion(Integer detalleResumenRecaudacionId, int detalleResumenRecaudacionCantidadEfectivo, int detalleResumenRecaudacionTotalEfectivo) {
        this.detalleResumenRecaudacionId = detalleResumenRecaudacionId;
        this.detalleResumenRecaudacionCantidadEfectivo = detalleResumenRecaudacionCantidadEfectivo;
        this.detalleResumenRecaudacionTotalEfectivo = detalleResumenRecaudacionTotalEfectivo;
    }

    public Integer getDetalleResumenRecaudacionId() {
        return detalleResumenRecaudacionId;
    }

    public void setDetalleResumenRecaudacionId(Integer detalleResumenRecaudacionId) {
        this.detalleResumenRecaudacionId = detalleResumenRecaudacionId;
    }

    public int getDetalleResumenRecaudacionCantidadEfectivo() {
        return detalleResumenRecaudacionCantidadEfectivo;
    }

    public void setDetalleResumenRecaudacionCantidadEfectivo(int detalleResumenRecaudacionCantidadEfectivo) {
        this.detalleResumenRecaudacionCantidadEfectivo = detalleResumenRecaudacionCantidadEfectivo;
    }

    public int getDetalleResumenRecaudacionTotalEfectivo() {
        return detalleResumenRecaudacionTotalEfectivo;
    }

    public void setDetalleResumenRecaudacionTotalEfectivo(int detalleResumenRecaudacionTotalEfectivo) {
        this.detalleResumenRecaudacionTotalEfectivo = detalleResumenRecaudacionTotalEfectivo;
    }

    public EfectivoCaja getDetalleResumenRecaudacionIdEfectivoCaja() {
        return detalleResumenRecaudacionIdEfectivoCaja;
    }

    public void setDetalleResumenRecaudacionIdEfectivoCaja(EfectivoCaja detalleResumenRecaudacionIdEfectivoCaja) {
        this.detalleResumenRecaudacionIdEfectivoCaja = detalleResumenRecaudacionIdEfectivoCaja;
    }

    public ResumenRecaudacion getDetalleResumenRecaudacionIdResumen() {
        return detalleResumenRecaudacionIdResumen;
    }

    public void setDetalleResumenRecaudacionIdResumen(ResumenRecaudacion detalleResumenRecaudacionIdResumen) {
        this.detalleResumenRecaudacionIdResumen = detalleResumenRecaudacionIdResumen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleResumenRecaudacionId != null ? detalleResumenRecaudacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleResumenRecaudacion)) {
            return false;
        }
        DetalleResumenRecaudacion other = (DetalleResumenRecaudacion) object;
        if ((this.detalleResumenRecaudacionId == null && other.detalleResumenRecaudacionId != null) || (this.detalleResumenRecaudacionId != null && !this.detalleResumenRecaudacionId.equals(other.detalleResumenRecaudacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleResumenRecaudacion[ detalleResumenRecaudacionId=" + detalleResumenRecaudacionId + " ]";
    }
    
}
