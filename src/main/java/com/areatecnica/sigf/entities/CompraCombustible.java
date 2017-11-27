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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "compra_combustible", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompraCombustible.findAll", query = "SELECT c FROM CompraCombustible c")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleId", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleId = :compraCombustibleId")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleFechaEmisionFactura", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleFechaEmisionFactura = :compraCombustibleFechaEmisionFactura")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleFechaPago", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleFechaPago = :compraCombustibleFechaPago")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleCantidadLitros", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleCantidadLitros = :compraCombustibleCantidadLitros")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustiblePbaseSiu", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustiblePbaseSiu = :compraCombustiblePbaseSiu")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleIevU", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleIevU = :compraCombustibleIevU")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleIefU", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleIefU = :compraCombustibleIefU")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleDiaVigIe", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleDiaVigIe = :compraCombustibleDiaVigIe")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleBaseAfecta", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleBaseAfecta = :compraCombustibleBaseAfecta")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleFeep", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleFeep = :compraCombustibleFeep")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleIev", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleIev = :compraCombustibleIev")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleIef", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleIef = :compraCombustibleIef")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleIva", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleIva = :compraCombustibleIva")
    , @NamedQuery(name = "CompraCombustible.findByCompraCombustibleTotalCompra", query = "SELECT c FROM CompraCombustible c WHERE c.compraCombustibleTotalCompra = :compraCombustibleTotalCompra")
    })
public class CompraCombustible extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "compra_combustible_id")
    private Integer compraCombustibleId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_fecha_emision_factura")
    @Temporal(TemporalType.DATE)
    private Date compraCombustibleFechaEmisionFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date compraCombustibleFechaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_cantidad_litros")
    private int compraCombustibleCantidadLitros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_pbase_siu")
    private float compraCombustiblePbaseSiu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_iev_u")
    private float compraCombustibleIevU;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_ief_u")
    private float compraCombustibleIefU;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_dia_vig_ie")
    @Temporal(TemporalType.DATE)
    private Date compraCombustibleDiaVigIe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_base_afecta")
    private int compraCombustibleBaseAfecta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_feep")
    private int compraCombustibleFeep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_iev")
    private int compraCombustibleIev;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_ief")
    private int compraCombustibleIef;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_iva")
    private int compraCombustibleIva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_combustible_total_compra")
    private int compraCombustibleTotalCompra;    
    @JoinColumn(name = "compra_combustible_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta compraCombustibleIdCuenta;
    @JoinColumn(name = "compra_combustible_id_tipo", referencedColumnName = "tipo_combustible_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoCombustible compraCombustibleIdTipo;

    public CompraCombustible() {
    }

    public CompraCombustible(Integer compraCombustibleId) {
        this.compraCombustibleId = compraCombustibleId;
    }

    public CompraCombustible(Integer compraCombustibleId, Date compraCombustibleFechaEmisionFactura, Date compraCombustibleFechaPago, int compraCombustibleCantidadLitros, float compraCombustiblePbaseSiu, float compraCombustibleIevU, float compraCombustibleIefU, Date compraCombustibleDiaVigIe, int compraCombustibleBaseAfecta, int compraCombustibleFeep, int compraCombustibleIev, int compraCombustibleIef, int compraCombustibleIva, int compraCombustibleTotalCompra) {
        this.compraCombustibleId = compraCombustibleId;
        this.compraCombustibleFechaEmisionFactura = compraCombustibleFechaEmisionFactura;
        this.compraCombustibleFechaPago = compraCombustibleFechaPago;
        this.compraCombustibleCantidadLitros = compraCombustibleCantidadLitros;
        this.compraCombustiblePbaseSiu = compraCombustiblePbaseSiu;
        this.compraCombustibleIevU = compraCombustibleIevU;
        this.compraCombustibleIefU = compraCombustibleIefU;
        this.compraCombustibleDiaVigIe = compraCombustibleDiaVigIe;
        this.compraCombustibleBaseAfecta = compraCombustibleBaseAfecta;
        this.compraCombustibleFeep = compraCombustibleFeep;
        this.compraCombustibleIev = compraCombustibleIev;
        this.compraCombustibleIef = compraCombustibleIef;
        this.compraCombustibleIva = compraCombustibleIva;
        this.compraCombustibleTotalCompra = compraCombustibleTotalCompra;
    }

    public Integer getCompraCombustibleId() {
        return compraCombustibleId;
    }

    public void setCompraCombustibleId(Integer compraCombustibleId) {
        this.compraCombustibleId = compraCombustibleId;
    }

    public Date getCompraCombustibleFechaEmisionFactura() {
        return compraCombustibleFechaEmisionFactura;
    }

    public void setCompraCombustibleFechaEmisionFactura(Date compraCombustibleFechaEmisionFactura) {
        this.compraCombustibleFechaEmisionFactura = compraCombustibleFechaEmisionFactura;
    }

    public Date getCompraCombustibleFechaPago() {
        return compraCombustibleFechaPago;
    }

    public void setCompraCombustibleFechaPago(Date compraCombustibleFechaPago) {
        this.compraCombustibleFechaPago = compraCombustibleFechaPago;
    }

    public int getCompraCombustibleCantidadLitros() {
        return compraCombustibleCantidadLitros;
    }

    public void setCompraCombustibleCantidadLitros(int compraCombustibleCantidadLitros) {
        this.compraCombustibleCantidadLitros = compraCombustibleCantidadLitros;
    }

    public float getCompraCombustiblePbaseSiu() {
        return compraCombustiblePbaseSiu;
    }

    public void setCompraCombustiblePbaseSiu(float compraCombustiblePbaseSiu) {
        this.compraCombustiblePbaseSiu = compraCombustiblePbaseSiu;
    }

    public float getCompraCombustibleIevU() {
        return compraCombustibleIevU;
    }

    public void setCompraCombustibleIevU(float compraCombustibleIevU) {
        this.compraCombustibleIevU = compraCombustibleIevU;
    }

    public float getCompraCombustibleIefU() {
        return compraCombustibleIefU;
    }

    public void setCompraCombustibleIefU(float compraCombustibleIefU) {
        this.compraCombustibleIefU = compraCombustibleIefU;
    }

    public Date getCompraCombustibleDiaVigIe() {
        return compraCombustibleDiaVigIe;
    }

    public void setCompraCombustibleDiaVigIe(Date compraCombustibleDiaVigIe) {
        this.compraCombustibleDiaVigIe = compraCombustibleDiaVigIe;
    }

    public int getCompraCombustibleBaseAfecta() {
        return compraCombustibleBaseAfecta;
    }

    public void setCompraCombustibleBaseAfecta(int compraCombustibleBaseAfecta) {
        this.compraCombustibleBaseAfecta = compraCombustibleBaseAfecta;
    }

    public int getCompraCombustibleFeep() {
        return compraCombustibleFeep;
    }

    public void setCompraCombustibleFeep(int compraCombustibleFeep) {
        this.compraCombustibleFeep = compraCombustibleFeep;
    }

    public int getCompraCombustibleIev() {
        return compraCombustibleIev;
    }

    public void setCompraCombustibleIev(int compraCombustibleIev) {
        this.compraCombustibleIev = compraCombustibleIev;
    }

    public int getCompraCombustibleIef() {
        return compraCombustibleIef;
    }

    public void setCompraCombustibleIef(int compraCombustibleIef) {
        this.compraCombustibleIef = compraCombustibleIef;
    }

    public int getCompraCombustibleIva() {
        return compraCombustibleIva;
    }

    public void setCompraCombustibleIva(int compraCombustibleIva) {
        this.compraCombustibleIva = compraCombustibleIva;
    }

    public int getCompraCombustibleTotalCompra() {
        return compraCombustibleTotalCompra;
    }

    public void setCompraCombustibleTotalCompra(int compraCombustibleTotalCompra) {
        this.compraCombustibleTotalCompra = compraCombustibleTotalCompra;
    }

    public Cuenta getCompraCombustibleIdCuenta() {
        return compraCombustibleIdCuenta;
    }

    public void setCompraCombustibleIdCuenta(Cuenta compraCombustibleIdCuenta) {
        this.compraCombustibleIdCuenta = compraCombustibleIdCuenta;
    }

    public TipoCombustible getCompraCombustibleIdTipo() {
        return compraCombustibleIdTipo;
    }

    public void setCompraCombustibleIdTipo(TipoCombustible compraCombustibleIdTipo) {
        this.compraCombustibleIdTipo = compraCombustibleIdTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compraCombustibleId != null ? compraCombustibleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraCombustible)) {
            return false;
        }
        CompraCombustible other = (CompraCombustible) object;
        if ((this.compraCombustibleId == null && other.compraCombustibleId != null) || (this.compraCombustibleId != null && !this.compraCombustibleId.equals(other.compraCombustibleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CompraCombustible[ compraCombustibleId=" + compraCombustibleId + " ]";
    }
    
}
