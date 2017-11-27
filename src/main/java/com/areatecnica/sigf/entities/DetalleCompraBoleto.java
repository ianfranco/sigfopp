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
@Table(name = "detalle_compra_boleto", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCompraBoleto.findAll", query = "SELECT d FROM DetalleCompraBoleto d")
    , @NamedQuery(name = "DetalleCompraBoleto.findByDetalleCompraBoletoId", query = "SELECT d FROM DetalleCompraBoleto d WHERE d.detalleCompraBoletoId = :detalleCompraBoletoId")
    , @NamedQuery(name = "DetalleCompraBoleto.findByDetalleCompraBoletoIdentificador", query = "SELECT d FROM DetalleCompraBoleto d WHERE d.detalleCompraBoletoIdentificador = :detalleCompraBoletoIdentificador")
    , @NamedQuery(name = "DetalleCompraBoleto.findByDetalleCompraBoletoSerie", query = "SELECT d FROM DetalleCompraBoleto d WHERE d.detalleCompraBoletoSerie = :detalleCompraBoletoSerie")
    , @NamedQuery(name = "DetalleCompraBoleto.findByDetalleCompraBoletoCantidadRollos", query = "SELECT d FROM DetalleCompraBoleto d WHERE d.detalleCompraBoletoCantidadRollos = :detalleCompraBoletoCantidadRollos")
    , @NamedQuery(name = "DetalleCompraBoleto.findByDetalleCompraBoletoTotal", query = "SELECT d FROM DetalleCompraBoleto d WHERE d.detalleCompraBoletoTotal = :detalleCompraBoletoTotal")})
public class DetalleCompraBoleto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_compra_boleto_id")
    private Integer detalleCompraBoletoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "detalle_compra_boleto_identificador")
    private String detalleCompraBoletoIdentificador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "detalle_compra_boleto_serie")
    private String detalleCompraBoletoSerie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_compra_boleto_cantidad_rollos")
    private int detalleCompraBoletoCantidadRollos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_compra_boleto_total")
    private int detalleCompraBoletoTotal;
    @JoinColumn(name = "detalle_compra_boleto_id_boleto", referencedColumnName = "boleto_id", nullable = false)
    @ManyToOne(optional = false)
    private Boleto detalleCompraBoletoIdBoleto;
    @JoinColumn(name = "detalle_compra_boleto_id_compra_boleto", referencedColumnName = "compra_boleto_id", nullable = false)
    @ManyToOne(optional = false)
    private CompraBoleto detalleCompraBoletoIdCompraBoleto;

    public DetalleCompraBoleto() {
    }

    public DetalleCompraBoleto(Integer detalleCompraBoletoId) {
        this.detalleCompraBoletoId = detalleCompraBoletoId;
    }

    public DetalleCompraBoleto(Integer detalleCompraBoletoId, String detalleCompraBoletoIdentificador, String detalleCompraBoletoSerie, int detalleCompraBoletoCantidadRollos, int detalleCompraBoletoTotal) {
        this.detalleCompraBoletoId = detalleCompraBoletoId;
        this.detalleCompraBoletoIdentificador = detalleCompraBoletoIdentificador;
        this.detalleCompraBoletoSerie = detalleCompraBoletoSerie;
        this.detalleCompraBoletoCantidadRollos = detalleCompraBoletoCantidadRollos;
        this.detalleCompraBoletoTotal = detalleCompraBoletoTotal;
    }

    public Integer getDetalleCompraBoletoId() {
        return detalleCompraBoletoId;
    }

    public void setDetalleCompraBoletoId(Integer detalleCompraBoletoId) {
        this.detalleCompraBoletoId = detalleCompraBoletoId;
    }

    public String getDetalleCompraBoletoIdentificador() {
        return detalleCompraBoletoIdentificador;
    }

    public void setDetalleCompraBoletoIdentificador(String detalleCompraBoletoIdentificador) {
        this.detalleCompraBoletoIdentificador = detalleCompraBoletoIdentificador;
    }

    public String getDetalleCompraBoletoSerie() {
        return detalleCompraBoletoSerie;
    }

    public void setDetalleCompraBoletoSerie(String detalleCompraBoletoSerie) {
        this.detalleCompraBoletoSerie = detalleCompraBoletoSerie;
    }

    public int getDetalleCompraBoletoCantidadRollos() {
        return detalleCompraBoletoCantidadRollos;
    }

    public void setDetalleCompraBoletoCantidadRollos(int detalleCompraBoletoCantidadRollos) {
        this.detalleCompraBoletoCantidadRollos = detalleCompraBoletoCantidadRollos;
    }

    public int getDetalleCompraBoletoTotal() {
        return detalleCompraBoletoTotal;
    }

    public void setDetalleCompraBoletoTotal(int detalleCompraBoletoTotal) {
        this.detalleCompraBoletoTotal = detalleCompraBoletoTotal;
    }

    public Boleto getDetalleCompraBoletoIdBoleto() {
        return detalleCompraBoletoIdBoleto;
    }

    public void setDetalleCompraBoletoIdBoleto(Boleto detalleCompraBoletoIdBoleto) {
        this.detalleCompraBoletoIdBoleto = detalleCompraBoletoIdBoleto;
    }

    public CompraBoleto getDetalleCompraBoletoIdCompraBoleto() {
        return detalleCompraBoletoIdCompraBoleto;
    }

    public void setDetalleCompraBoletoIdCompraBoleto(CompraBoleto detalleCompraBoletoIdCompraBoleto) {
        this.detalleCompraBoletoIdCompraBoleto = detalleCompraBoletoIdCompraBoleto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleCompraBoletoId != null ? detalleCompraBoletoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCompraBoleto)) {
            return false;
        }
        DetalleCompraBoleto other = (DetalleCompraBoleto) object;
        if ((this.detalleCompraBoletoId == null && other.detalleCompraBoletoId != null) || (this.detalleCompraBoletoId != null && !this.detalleCompraBoletoId.equals(other.detalleCompraBoletoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleCompraBoleto[ detalleCompraBoletoId=" + detalleCompraBoletoId + " ]";
    }
    
}
