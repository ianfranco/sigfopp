/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "descuento_extra", catalog = "sigf_v3", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescuentoExtra.findAll", query = "SELECT d FROM DescuentoExtra d")
    , @NamedQuery(name = "DescuentoExtra.findByDescuentoExtraId", query = "SELECT d FROM DescuentoExtra d WHERE d.descuentoExtraId = :descuentoExtraId")
    , @NamedQuery(name = "DescuentoExtra.findByDescuentoExtraIdFecha", query = "SELECT d FROM DescuentoExtra d WHERE d.descuentoExtraIdFecha = :descuentoExtraIdFecha")
    , @NamedQuery(name = "DescuentoExtra.findByDescuentoExtraNombre", query = "SELECT d FROM DescuentoExtra d WHERE d.descuentoExtraNombre = :descuentoExtraNombre")
    , @NamedQuery(name = "DescuentoExtra.findByDescuentoExtraMonto", query = "SELECT d FROM DescuentoExtra d WHERE d.descuentoExtraMonto = :descuentoExtraMonto")})
public class DescuentoExtra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "descuento_extra_id")
    private Integer descuentoExtraId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_extra_id_fecha")
    @Temporal(TemporalType.DATE)
    private Date descuentoExtraIdFecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descuento_extra_nombre")
    private String descuentoExtraNombre;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "descuento_extra_descripcion")
    private String descuentoExtraDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "descuento_extra_monto")
    private int descuentoExtraMonto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "descuentoExtraBusIdDescuento")
    private List<DescuentoExtraBus> descuentoExtraBusList;
    @JoinColumn(name = "descuento_extra_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta descuentoExtraIdCuenta;

    public DescuentoExtra() {
    }

    public DescuentoExtra(Integer descuentoExtraId) {
        this.descuentoExtraId = descuentoExtraId;
    }

    public DescuentoExtra(Integer descuentoExtraId, Date descuentoExtraIdFecha, String descuentoExtraNombre, int descuentoExtraMonto) {
        this.descuentoExtraId = descuentoExtraId;
        this.descuentoExtraIdFecha = descuentoExtraIdFecha;
        this.descuentoExtraNombre = descuentoExtraNombre;
        this.descuentoExtraMonto = descuentoExtraMonto;
    }

    public Integer getDescuentoExtraId() {
        return descuentoExtraId;
    }

    public void setDescuentoExtraId(Integer descuentoExtraId) {
        this.descuentoExtraId = descuentoExtraId;
    }

    public Date getDescuentoExtraIdFecha() {
        return descuentoExtraIdFecha;
    }

    public void setDescuentoExtraIdFecha(Date descuentoExtraIdFecha) {
        this.descuentoExtraIdFecha = descuentoExtraIdFecha;
    }

    public String getDescuentoExtraNombre() {
        return descuentoExtraNombre;
    }

    public void setDescuentoExtraNombre(String descuentoExtraNombre) {
        this.descuentoExtraNombre = descuentoExtraNombre;
    }

    public String getDescuentoExtraDescripcion() {
        return descuentoExtraDescripcion;
    }

    public void setDescuentoExtraDescripcion(String descuentoExtraDescripcion) {
        this.descuentoExtraDescripcion = descuentoExtraDescripcion;
    }

    public int getDescuentoExtraMonto() {
        return descuentoExtraMonto;
    }

    public void setDescuentoExtraMonto(int descuentoExtraMonto) {
        this.descuentoExtraMonto = descuentoExtraMonto;
    }

    @XmlTransient
    public List<DescuentoExtraBus> getDescuentoExtraBusList() {
        return descuentoExtraBusList;
    }

    public void setDescuentoExtraBusList(List<DescuentoExtraBus> descuentoExtraBusList) {
        this.descuentoExtraBusList = descuentoExtraBusList;
    }

    public Cuenta getDescuentoExtraIdCuenta() {
        return descuentoExtraIdCuenta;
    }

    public void setDescuentoExtraIdCuenta(Cuenta descuentoExtraIdCuenta) {
        this.descuentoExtraIdCuenta = descuentoExtraIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (descuentoExtraId != null ? descuentoExtraId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescuentoExtra)) {
            return false;
        }
        DescuentoExtra other = (DescuentoExtra) object;
        if ((this.descuentoExtraId == null && other.descuentoExtraId != null) || (this.descuentoExtraId != null && !this.descuentoExtraId.equals(other.descuentoExtraId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DescuentoExtra[ descuentoExtraId=" + descuentoExtraId + " ]";
    }
    
}
