/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "liquidacion_empresa", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LiquidacionEmpresa.findAll", query = "SELECT l FROM LiquidacionEmpresa l")
    , @NamedQuery(name = "LiquidacionEmpresa.findByLiquidacionEmpresaId", query = "SELECT l FROM LiquidacionEmpresa l WHERE l.liquidacionEmpresaId = :liquidacionEmpresaId")
    , @NamedQuery(name = "LiquidacionEmpresa.findByLiquidacionEmpresaFechaLiquidacion", query = "SELECT l FROM LiquidacionEmpresa l WHERE l.liquidacionEmpresaFechaLiquidacion = :liquidacionEmpresaFechaLiquidacion")
    , @NamedQuery(name = "LiquidacionEmpresa.findByLiquidacionEmpresaFechaPago", query = "SELECT l FROM LiquidacionEmpresa l WHERE l.liquidacionEmpresaFechaPago = :liquidacionEmpresaFechaPago")
    , @NamedQuery(name = "LiquidacionEmpresa.findByLiquidacionEmpresaTotalAbonos", query = "SELECT l FROM LiquidacionEmpresa l WHERE l.liquidacionEmpresaTotalAbonos = :liquidacionEmpresaTotalAbonos")
    , @NamedQuery(name = "LiquidacionEmpresa.findByLiquidacionEmpresaTotalCargos", query = "SELECT l FROM LiquidacionEmpresa l WHERE l.liquidacionEmpresaTotalCargos = :liquidacionEmpresaTotalCargos")
    , @NamedQuery(name = "LiquidacionEmpresa.findByLiquidacionEmpresaSaldo", query = "SELECT l FROM LiquidacionEmpresa l WHERE l.liquidacionEmpresaSaldo = :liquidacionEmpresaSaldo")
    })
public class LiquidacionEmpresa extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "liquidacion_empresa_id")
    private Integer liquidacionEmpresaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_empresa_fecha_liquidacion")
    @Temporal(TemporalType.DATE)
    private Date liquidacionEmpresaFechaLiquidacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_empresa_fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date liquidacionEmpresaFechaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_empresa_total_abonos")
    private int liquidacionEmpresaTotalAbonos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_empresa_total_cargos")
    private int liquidacionEmpresaTotalCargos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liquidacion_empresa_saldo")
    private int liquidacionEmpresaSaldo;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoLiquidacionIdLiquidacion")
    private List<CargoLiquidacion> cargoLiquidacionList;
    @JoinColumn(name = "liquidacion_empresa_id_empresa", referencedColumnName = "empresa_id", nullable = false)
    @ManyToOne(optional = false)
    private Empresa liquidacionEmpresaIdEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abonoLiquidacionIdLiquidacionEmpresa")
    private List<AbonoLiquidacion> abonoLiquidacionList;

    public LiquidacionEmpresa() {
    }

    public LiquidacionEmpresa(Integer liquidacionEmpresaId) {
        this.liquidacionEmpresaId = liquidacionEmpresaId;
    }

    public LiquidacionEmpresa(Integer liquidacionEmpresaId, Date liquidacionEmpresaFechaLiquidacion, Date liquidacionEmpresaFechaPago, int liquidacionEmpresaTotalAbonos, int liquidacionEmpresaTotalCargos, int liquidacionEmpresaSaldo) {
        this.liquidacionEmpresaId = liquidacionEmpresaId;
        this.liquidacionEmpresaFechaLiquidacion = liquidacionEmpresaFechaLiquidacion;
        this.liquidacionEmpresaFechaPago = liquidacionEmpresaFechaPago;
        this.liquidacionEmpresaTotalAbonos = liquidacionEmpresaTotalAbonos;
        this.liquidacionEmpresaTotalCargos = liquidacionEmpresaTotalCargos;
        this.liquidacionEmpresaSaldo = liquidacionEmpresaSaldo;
    }

    public Integer getLiquidacionEmpresaId() {
        return liquidacionEmpresaId;
    }

    public void setLiquidacionEmpresaId(Integer liquidacionEmpresaId) {
        this.liquidacionEmpresaId = liquidacionEmpresaId;
    }

    public Date getLiquidacionEmpresaFechaLiquidacion() {
        return liquidacionEmpresaFechaLiquidacion;
    }

    public void setLiquidacionEmpresaFechaLiquidacion(Date liquidacionEmpresaFechaLiquidacion) {
        this.liquidacionEmpresaFechaLiquidacion = liquidacionEmpresaFechaLiquidacion;
    }

    public Date getLiquidacionEmpresaFechaPago() {
        return liquidacionEmpresaFechaPago;
    }

    public void setLiquidacionEmpresaFechaPago(Date liquidacionEmpresaFechaPago) {
        this.liquidacionEmpresaFechaPago = liquidacionEmpresaFechaPago;
    }

    public int getLiquidacionEmpresaTotalAbonos() {
        return liquidacionEmpresaTotalAbonos;
    }

    public void setLiquidacionEmpresaTotalAbonos(int liquidacionEmpresaTotalAbonos) {
        this.liquidacionEmpresaTotalAbonos = liquidacionEmpresaTotalAbonos;
    }

    public int getLiquidacionEmpresaTotalCargos() {
        return liquidacionEmpresaTotalCargos;
    }

    public void setLiquidacionEmpresaTotalCargos(int liquidacionEmpresaTotalCargos) {
        this.liquidacionEmpresaTotalCargos = liquidacionEmpresaTotalCargos;
    }

    public int getLiquidacionEmpresaSaldo() {
        return liquidacionEmpresaSaldo;
    }

    public void setLiquidacionEmpresaSaldo(int liquidacionEmpresaSaldo) {
        this.liquidacionEmpresaSaldo = liquidacionEmpresaSaldo;
    }

    @XmlTransient
    public List<CargoLiquidacion> getCargoLiquidacionList() {
        return cargoLiquidacionList;
    }

    public void setCargoLiquidacionList(List<CargoLiquidacion> cargoLiquidacionList) {
        this.cargoLiquidacionList = cargoLiquidacionList;
    }

    public Empresa getLiquidacionEmpresaIdEmpresa() {
        return liquidacionEmpresaIdEmpresa;
    }

    public void setLiquidacionEmpresaIdEmpresa(Empresa liquidacionEmpresaIdEmpresa) {
        this.liquidacionEmpresaIdEmpresa = liquidacionEmpresaIdEmpresa;
    }

    @XmlTransient
    public List<AbonoLiquidacion> getAbonoLiquidacionList() {
        return abonoLiquidacionList;
    }

    public void setAbonoLiquidacionList(List<AbonoLiquidacion> abonoLiquidacionList) {
        this.abonoLiquidacionList = abonoLiquidacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (liquidacionEmpresaId != null ? liquidacionEmpresaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LiquidacionEmpresa)) {
            return false;
        }
        LiquidacionEmpresa other = (LiquidacionEmpresa) object;
        if ((this.liquidacionEmpresaId == null && other.liquidacionEmpresaId != null) || (this.liquidacionEmpresaId != null && !this.liquidacionEmpresaId.equals(other.liquidacionEmpresaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.LiquidacionEmpresa[ liquidacionEmpresaId=" + liquidacionEmpresaId + " ]";
    }
    
}
