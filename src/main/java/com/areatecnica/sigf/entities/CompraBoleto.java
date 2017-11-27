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
@Table(name = "compra_boleto", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompraBoleto.findAll", query = "SELECT c FROM CompraBoleto c")
    , @NamedQuery(name = "CompraBoleto.findByCompraBoletoId", query = "SELECT c FROM CompraBoleto c WHERE c.compraBoletoId = :compraBoletoId")
    , @NamedQuery(name = "InventarioInterno.findByInventarioInternoBoletoEstado", query = "SELECT i FROM InventarioInterno i WHERE i.inventarioInternoIdBoleto = :inventarioInternoIdBoleto AND  i.inventarioInternoEstado =:inventarioInternoEstado")
    , @NamedQuery(name = "CompraBoleto.findByCompraBoletoIdCuenta", query = "SELECT c FROM CompraBoleto c WHERE c.compraBoletoIdCuenta = :compraBoletoIdCuenta ORDER BY c.compraBoletoFecha DESC")
    , @NamedQuery(name = "CompraBoleto.findByCompraBoletoFolioFactura", query = "SELECT c FROM CompraBoleto c WHERE c.compraBoletoFolioFactura = :compraBoletoFolioFactura AND c.compraBoletoIdCuenta = :compraBoletoIdCuenta")
    , @NamedQuery(name = "CompraBoleto.findByCompraBoletoTotal", query = "SELECT c FROM CompraBoleto c WHERE c.compraBoletoTotal = :compraBoletoTotal")
    , @NamedQuery(name = "CompraBoleto.findByCompraBoletoFecha", query = "SELECT c FROM CompraBoleto c WHERE c.compraBoletoFecha = :compraBoletoFecha AND c.compraBoletoIdCuenta = :compraBoletoIdCuenta")})
public class CompraBoleto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "compra_boleto_id")
    private Integer compraBoletoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_boleto_folio_factura")
    private int compraBoletoFolioFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_boleto_total")
    private int compraBoletoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compra_boleto_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date compraBoletoFecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleCompraBoletoIdCompraBoleto")
    private List<DetalleCompraBoleto> detalleCompraBoletoList;
    @JoinColumn(name = "compra_boleto_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta compraBoletoIdCuenta;

    public CompraBoleto() {
    }

    public CompraBoleto(Integer compraBoletoId) {
        this.compraBoletoId = compraBoletoId;
    }

    public CompraBoleto(Integer compraBoletoId, int compraBoletoFolioFactura, int compraBoletoTotal, Date compraBoletoFecha) {
        this.compraBoletoId = compraBoletoId;
        this.compraBoletoFolioFactura = compraBoletoFolioFactura;
        this.compraBoletoTotal = compraBoletoTotal;
        this.compraBoletoFecha = compraBoletoFecha;
    }

    public Integer getCompraBoletoId() {
        return compraBoletoId;
    }

    public void setCompraBoletoId(Integer compraBoletoId) {
        this.compraBoletoId = compraBoletoId;
    }

    public int getCompraBoletoFolioFactura() {
        return compraBoletoFolioFactura;
    }

    public void setCompraBoletoFolioFactura(int compraBoletoFolioFactura) {
        this.compraBoletoFolioFactura = compraBoletoFolioFactura;
    }

    public int getCompraBoletoTotal() {
        return compraBoletoTotal;
    }

    public void setCompraBoletoTotal(int compraBoletoTotal) {
        this.compraBoletoTotal = compraBoletoTotal;
    }

    public Date getCompraBoletoFecha() {
        return compraBoletoFecha;
    }

    public void setCompraBoletoFecha(Date compraBoletoFecha) {
        this.compraBoletoFecha = compraBoletoFecha;
    }

    @XmlTransient
    public List<DetalleCompraBoleto> getDetalleCompraBoletoList() {
        return detalleCompraBoletoList;
    }

    public void setDetalleCompraBoletoList(List<DetalleCompraBoleto> detalleCompraBoletoList) {
        this.detalleCompraBoletoList = detalleCompraBoletoList;
    }

    public Cuenta getCompraBoletoIdCuenta() {
        return compraBoletoIdCuenta;
    }

    public void setCompraBoletoIdCuenta(Cuenta compraBoletoIdCuenta) {
        this.compraBoletoIdCuenta = compraBoletoIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compraBoletoId != null ? compraBoletoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraBoleto)) {
            return false;
        }
        CompraBoleto other = (CompraBoleto) object;
        if ((this.compraBoletoId == null && other.compraBoletoId != null) || (this.compraBoletoId != null && !this.compraBoletoId.equals(other.compraBoletoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CompraBoleto[ compraBoletoId=" + compraBoletoId + " ]";
    }

}
