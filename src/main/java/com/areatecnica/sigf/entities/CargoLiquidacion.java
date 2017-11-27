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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "cargo_liquidacion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargoLiquidacion.findAll", query = "SELECT c FROM CargoLiquidacion c")
    , @NamedQuery(name = "CargoLiquidacion.findByCargoLiquidacionId", query = "SELECT c FROM CargoLiquidacion c WHERE c.cargoLiquidacionId = :cargoLiquidacionId")
    , @NamedQuery(name = "CargoLiquidacion.findByCargoLiquidacionMonto", query = "SELECT c FROM CargoLiquidacion c WHERE c.cargoLiquidacionMonto = :cargoLiquidacionMonto")
    , @NamedQuery(name = "CargoLiquidacion.findByCargoLiquidacionDescripcion", query = "SELECT c FROM CargoLiquidacion c WHERE c.cargoLiquidacionDescripcion = :cargoLiquidacionDescripcion")
    })
public class CargoLiquidacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cargo_liquidacion_id")
    private Integer cargoLiquidacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cargo_liquidacion_monto")
    private int cargoLiquidacionMonto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cargo_liquidacion_descripcion")
    private String cargoLiquidacionDescripcion;    
    @JoinColumn(name = "cargo_liquidacion_id_cargo", referencedColumnName = "cargo_bus_id", nullable = false)
    @ManyToOne(optional = false)
    private CargoBus cargoLiquidacionIdCargo;
    @JoinColumn(name = "cargo_liquidacion_id_liquidacion", referencedColumnName = "liquidacion_empresa_id", nullable = false)
    @ManyToOne(optional = false)
    private LiquidacionEmpresa cargoLiquidacionIdLiquidacion;

    public CargoLiquidacion() {
    }

    public CargoLiquidacion(Integer cargoLiquidacionId) {
        this.cargoLiquidacionId = cargoLiquidacionId;
    }

    public CargoLiquidacion(Integer cargoLiquidacionId, int cargoLiquidacionMonto, String cargoLiquidacionDescripcion) {
        this.cargoLiquidacionId = cargoLiquidacionId;
        this.cargoLiquidacionMonto = cargoLiquidacionMonto;
        this.cargoLiquidacionDescripcion = cargoLiquidacionDescripcion;
    }

    public Integer getCargoLiquidacionId() {
        return cargoLiquidacionId;
    }

    public void setCargoLiquidacionId(Integer cargoLiquidacionId) {
        this.cargoLiquidacionId = cargoLiquidacionId;
    }

    public int getCargoLiquidacionMonto() {
        return cargoLiquidacionMonto;
    }

    public void setCargoLiquidacionMonto(int cargoLiquidacionMonto) {
        this.cargoLiquidacionMonto = cargoLiquidacionMonto;
    }

    public String getCargoLiquidacionDescripcion() {
        return cargoLiquidacionDescripcion;
    }

    public void setCargoLiquidacionDescripcion(String cargoLiquidacionDescripcion) {
        this.cargoLiquidacionDescripcion = cargoLiquidacionDescripcion;
    }

    public CargoBus getCargoLiquidacionIdCargo() {
        return cargoLiquidacionIdCargo;
    }

    public void setCargoLiquidacionIdCargo(CargoBus cargoLiquidacionIdCargo) {
        this.cargoLiquidacionIdCargo = cargoLiquidacionIdCargo;
    }

    public LiquidacionEmpresa getCargoLiquidacionIdLiquidacion() {
        return cargoLiquidacionIdLiquidacion;
    }

    public void setCargoLiquidacionIdLiquidacion(LiquidacionEmpresa cargoLiquidacionIdLiquidacion) {
        this.cargoLiquidacionIdLiquidacion = cargoLiquidacionIdLiquidacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cargoLiquidacionId != null ? cargoLiquidacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargoLiquidacion)) {
            return false;
        }
        CargoLiquidacion other = (CargoLiquidacion) object;
        if ((this.cargoLiquidacionId == null && other.cargoLiquidacionId != null) || (this.cargoLiquidacionId != null && !this.cargoLiquidacionId.equals(other.cargoLiquidacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CargoLiquidacion[ cargoLiquidacionId=" + cargoLiquidacionId + " ]";
    }

}
