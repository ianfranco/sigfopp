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
@Table(name = "venta_boleto", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaBoleto.findAll", query = "SELECT v FROM VentaBoleto v")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoId", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoId = :ventaBoletoId")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoFecha", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoFecha = :ventaBoletoFecha")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoNumeroBoleta", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoNumeroBoleta = :ventaBoletoNumeroBoleta")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoIdCajaDate", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoIdInventarioCaja.inventarioCajaIdCaja = :inventarioCajaIdCaja AND v.ventaBoletoFecha  = :ventaBoletoFecha ORDER BY v.ventaBoletoIdBus.busNumero ASC")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoValor", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoValor = :ventaBoletoValor")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoRecaudado", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoRecaudado = :ventaBoletoRecaudado")
    , @NamedQuery(name = "VentaBoleto.findByVentaBoletoUtilizado", query = "SELECT v FROM VentaBoleto v WHERE v.ventaBoletoUtilizado = :ventaBoletoUtilizado")
    })
public class VentaBoleto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "venta_boleto_id")
    private Integer ventaBoletoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_fecha")
    @Temporal(TemporalType.DATE)
    private Date ventaBoletoFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_numero_boleta")
    private int ventaBoletoNumeroBoleta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_valor")
    private int ventaBoletoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "venta_boleto_recaudado")
    private boolean ventaBoletoRecaudado;
    @Column(name = "venta_boleto_utilizado")
    private Boolean ventaBoletoUtilizado;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recaudacionBoletoIdVentaBoleto")
    private List<RecaudacionBoleto> recaudacionBoletoList;
    @JoinColumn(name = "venta_boleto_id_bus", referencedColumnName = "bus_id", nullable = false)
    @ManyToOne(optional = false)
    private Bus ventaBoletoIdBus;
    @JoinColumn(name = "venta_boleto_id_inventario_caja", referencedColumnName = "inventario_caja_id", nullable = false)
    @ManyToOne(optional = false)
    private InventarioCaja ventaBoletoIdInventarioCaja;
    @JoinColumn(name = "venta_boleto_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador ventaBoletoIdTrabajador;

    public VentaBoleto() {
    }

    public VentaBoleto(Integer ventaBoletoId) {
        this.ventaBoletoId = ventaBoletoId;
    }

    public VentaBoleto(Integer ventaBoletoId, Date ventaBoletoFecha, int ventaBoletoNumeroBoleta, int ventaBoletoValor, boolean ventaBoletoRecaudado) {
        this.ventaBoletoId = ventaBoletoId;
        this.ventaBoletoFecha = ventaBoletoFecha;
        this.ventaBoletoNumeroBoleta = ventaBoletoNumeroBoleta;
        this.ventaBoletoValor = ventaBoletoValor;
        this.ventaBoletoRecaudado = ventaBoletoRecaudado;
    }

    public Integer getVentaBoletoId() {
        return ventaBoletoId;
    }

    public void setVentaBoletoId(Integer ventaBoletoId) {
        this.ventaBoletoId = ventaBoletoId;
    }

    public Date getVentaBoletoFecha() {
        return ventaBoletoFecha;
    }

    public void setVentaBoletoFecha(Date ventaBoletoFecha) {
        this.ventaBoletoFecha = ventaBoletoFecha;
    }

    public int getVentaBoletoNumeroBoleta() {
        return ventaBoletoNumeroBoleta;
    }

    public void setVentaBoletoNumeroBoleta(int ventaBoletoNumeroBoleta) {
        this.ventaBoletoNumeroBoleta = ventaBoletoNumeroBoleta;
    }

    public int getVentaBoletoValor() {
        return ventaBoletoValor;
    }

    public void setVentaBoletoValor(int ventaBoletoValor) {
        this.ventaBoletoValor = ventaBoletoValor;
    }

    public boolean getVentaBoletoRecaudado() {
        return ventaBoletoRecaudado;
    }

    public void setVentaBoletoRecaudado(boolean ventaBoletoRecaudado) {
        this.ventaBoletoRecaudado = ventaBoletoRecaudado;
    }

    public Boolean getVentaBoletoUtilizado() {
        return ventaBoletoUtilizado;
    }

    public void setVentaBoletoUtilizado(Boolean ventaBoletoUtilizado) {
        this.ventaBoletoUtilizado = ventaBoletoUtilizado;
    }
    
    @XmlTransient
    public List<RecaudacionBoleto> getRecaudacionBoletoList() {
        return recaudacionBoletoList;
    }

    public void setRecaudacionBoletoList(List<RecaudacionBoleto> recaudacionBoletoList) {
        this.recaudacionBoletoList = recaudacionBoletoList;
    }

    public Bus getVentaBoletoIdBus() {
        return ventaBoletoIdBus;
    }

    public void setVentaBoletoIdBus(Bus ventaBoletoIdBus) {
        this.ventaBoletoIdBus = ventaBoletoIdBus;
    }

    public InventarioCaja getVentaBoletoIdInventarioCaja() {
        return ventaBoletoIdInventarioCaja;
    }

    public void setVentaBoletoIdInventarioCaja(InventarioCaja ventaBoletoIdInventarioCaja) {
        this.ventaBoletoIdInventarioCaja = ventaBoletoIdInventarioCaja;
    }

    public Trabajador getVentaBoletoIdTrabajador() {
        return ventaBoletoIdTrabajador;
    }

    public void setVentaBoletoIdTrabajador(Trabajador ventaBoletoIdTrabajador) {
        this.ventaBoletoIdTrabajador = ventaBoletoIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaBoletoId != null ? ventaBoletoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaBoleto)) {
            return false;
        }
        VentaBoleto other = (VentaBoleto) object;
        if ((this.ventaBoletoId == null && other.ventaBoletoId != null) || (this.ventaBoletoId != null && !this.ventaBoletoId.equals(other.ventaBoletoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.VentaBoleto[ ventaBoletoId=" + ventaBoletoId + " ]";
    }
    
}
