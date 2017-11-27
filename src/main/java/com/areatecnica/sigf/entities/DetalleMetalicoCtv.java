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
@Table(name = "detalle_metalico_ctv", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleMetalicoCtv.findAll", query = "SELECT d FROM DetalleMetalicoCtv d")
    , @NamedQuery(name = "DetalleMetalicoCtv.findByDetalleMetalicoCtvId", query = "SELECT d FROM DetalleMetalicoCtv d WHERE d.detalleMetalicoCtvId = :detalleMetalicoCtvId")
    , @NamedQuery(name = "DetalleMetalicoCtv.findByDetalleMetalicoCtvCantidadBolsas", query = "SELECT d FROM DetalleMetalicoCtv d WHERE d.detalleMetalicoCtvCantidadBolsas = :detalleMetalicoCtvCantidadBolsas")
    , @NamedQuery(name = "DetalleMetalicoCtv.findByDetalleMetalicoCtvTotalEfectivo", query = "SELECT d FROM DetalleMetalicoCtv d WHERE d.detalleMetalicoCtvTotalEfectivo = :detalleMetalicoCtvTotalEfectivo")})
public class DetalleMetalicoCtv extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_metalico_ctv_id")
    private Integer detalleMetalicoCtvId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_metalico_ctv_cantidad_bolsas")
    private int detalleMetalicoCtvCantidadBolsas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_metalico_ctv_total_efectivo")
    private int detalleMetalicoCtvTotalEfectivo;
    @JoinColumn(name = "detalle_metalico_ctv_id_ctv_resumen", referencedColumnName = "ctv_resumen_id", nullable = false)
    @ManyToOne(optional = false)
    private CtvResumen detalleMetalicoCtvIdCtvResumen;
    @JoinColumn(name = "detalle_metalico_ctv_id_efectivo_caja", referencedColumnName = "efectivo_caja_id", nullable = false)
    @ManyToOne(optional = false)
    private EfectivoCaja detalleMetalicoCtvIdEfectivoCaja;

    public DetalleMetalicoCtv() {
    }

    public DetalleMetalicoCtv(Integer detalleMetalicoCtvId) {
        this.detalleMetalicoCtvId = detalleMetalicoCtvId;
    }

    public DetalleMetalicoCtv(Integer detalleMetalicoCtvId, int detalleMetalicoCtvCantidadBolsas, int detalleMetalicoCtvTotalEfectivo) {
        this.detalleMetalicoCtvId = detalleMetalicoCtvId;
        this.detalleMetalicoCtvCantidadBolsas = detalleMetalicoCtvCantidadBolsas;
        this.detalleMetalicoCtvTotalEfectivo = detalleMetalicoCtvTotalEfectivo;
    }

    public Integer getDetalleMetalicoCtvId() {
        return detalleMetalicoCtvId;
    }

    public void setDetalleMetalicoCtvId(Integer detalleMetalicoCtvId) {
        this.detalleMetalicoCtvId = detalleMetalicoCtvId;
    }

    public int getDetalleMetalicoCtvCantidadBolsas() {
        return detalleMetalicoCtvCantidadBolsas;
    }

    public void setDetalleMetalicoCtvCantidadBolsas(int detalleMetalicoCtvCantidadBolsas) {
        this.detalleMetalicoCtvCantidadBolsas = detalleMetalicoCtvCantidadBolsas;
    }

    public int getDetalleMetalicoCtvTotalEfectivo() {
        return detalleMetalicoCtvTotalEfectivo;
    }

    public void setDetalleMetalicoCtvTotalEfectivo(int detalleMetalicoCtvTotalEfectivo) {
        this.detalleMetalicoCtvTotalEfectivo = detalleMetalicoCtvTotalEfectivo;
    }

    public CtvResumen getDetalleMetalicoCtvIdCtvResumen() {
        return detalleMetalicoCtvIdCtvResumen;
    }

    public void setDetalleMetalicoCtvIdCtvResumen(CtvResumen detalleMetalicoCtvIdCtvResumen) {
        this.detalleMetalicoCtvIdCtvResumen = detalleMetalicoCtvIdCtvResumen;
    }

    public EfectivoCaja getDetalleMetalicoCtvIdEfectivoCaja() {
        return detalleMetalicoCtvIdEfectivoCaja;
    }

    public void setDetalleMetalicoCtvIdEfectivoCaja(EfectivoCaja detalleMetalicoCtvIdEfectivoCaja) {
        this.detalleMetalicoCtvIdEfectivoCaja = detalleMetalicoCtvIdEfectivoCaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleMetalicoCtvId != null ? detalleMetalicoCtvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleMetalicoCtv)) {
            return false;
        }
        DetalleMetalicoCtv other = (DetalleMetalicoCtv) object;
        if ((this.detalleMetalicoCtvId == null && other.detalleMetalicoCtvId != null) || (this.detalleMetalicoCtvId != null && !this.detalleMetalicoCtvId.equals(other.detalleMetalicoCtvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleMetalicoCtv[ detalleMetalicoCtvId=" + detalleMetalicoCtvId + " ]";
    }
    
}
