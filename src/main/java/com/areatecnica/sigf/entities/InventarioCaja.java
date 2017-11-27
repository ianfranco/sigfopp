/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "inventario_caja", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioCaja.findAll", query = "SELECT i FROM InventarioCaja i")
    , @NamedQuery(name = "InventarioCaja.findByInventarioCajaEstado", query = "SELECT i FROM InventarioCaja i WHERE i.inventarioCajaEstado = :inventarioCajaEstado AND i.inventarioCajaIdCaja.cajaRecaudacionIdCuenta = :idCuenta")
    , @NamedQuery(name = "InventarioCaja.findByInventarioCajaBoletoEstadoCaja", query = "SELECT i FROM InventarioCaja i WHERE i.inventarioCajaEstado = :inventarioCajaEstado AND i.inventarioCajaIdInventarioInterno.inventarioInternoIdBoleto = :inventarioInternoIdBoleto AND i.inventarioCajaIdCaja =:inventarioCajaIdCaja")
    , @NamedQuery(name = "InventarioCaja.findByInventarioCajaId", query = "SELECT i FROM InventarioCaja i WHERE i.inventarioCajaId = :inventarioCajaId")
    , @NamedQuery(name = "InventarioCaja.findByInventarioCajaSerie", query = "SELECT i FROM InventarioCaja i WHERE i.inventarioCajaSerie = :inventarioCajaSerie")
    , @NamedQuery(name = "InventarioCaja.findByInventarioCajaIdentificador", query = "SELECT i FROM InventarioCaja i WHERE i.inventarioCajaIdentificador = :inventarioCajaIdentificador")

})
public class InventarioCaja extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventario_caja_id")
    private Integer inventarioCajaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inventario_caja_estado")
    private boolean inventarioCajaEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inventario_caja_serie")
    private int inventarioCajaSerie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "inventario_caja_identificador")
    private String inventarioCajaIdentificador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventaBoletoIdInventarioCaja")
    private List<VentaBoleto> ventaBoletoList;
    @JoinColumn(name = "inventario_caja_id_inventario_interno", referencedColumnName = "inventario_interno_id", nullable = false)
    @ManyToOne(optional = false)
    private InventarioInterno inventarioCajaIdInventarioInterno;
    @JoinColumn(name = "inventario_caja_id_caja", referencedColumnName = "caja_recaudacion_id", nullable = false)
    @ManyToOne(optional = false)
    private CajaRecaudacion inventarioCajaIdCaja;

    public InventarioCaja() {
    }

    public InventarioCaja(Integer inventarioCajaId) {
        this.inventarioCajaId = inventarioCajaId;
    }

    public InventarioCaja(Integer inventarioCajaId, boolean inventarioCajaEstado, int inventarioCajaSerie, String inventarioCajaIdentificador) {
        this.inventarioCajaId = inventarioCajaId;
        this.inventarioCajaEstado = inventarioCajaEstado;
        this.inventarioCajaSerie = inventarioCajaSerie;
        this.inventarioCajaIdentificador = inventarioCajaIdentificador;
    }

    public Integer getInventarioCajaId() {
        return inventarioCajaId;
    }

    public void setInventarioCajaId(Integer inventarioCajaId) {
        this.inventarioCajaId = inventarioCajaId;
    }

    public boolean getInventarioCajaEstado() {
        return inventarioCajaEstado;
    }

    public void setInventarioCajaEstado(boolean inventarioCajaEstado) {
        this.inventarioCajaEstado = inventarioCajaEstado;
    }

    public int getInventarioCajaSerie() {
        return inventarioCajaSerie;
    }

    public void setInventarioCajaSerie(int inventarioCajaSerie) {
        this.inventarioCajaSerie = inventarioCajaSerie;
    }

    public String getInventarioCajaIdentificador() {
        return inventarioCajaIdentificador;
    }

    public void setInventarioCajaIdentificador(String inventarioCajaIdentificador) {
        this.inventarioCajaIdentificador = inventarioCajaIdentificador;
    }

    @XmlTransient
    public List<VentaBoleto> getVentaBoletoList() {
        return ventaBoletoList;
    }

    public void setVentaBoletoList(List<VentaBoleto> ventaBoletoList) {
        this.ventaBoletoList = ventaBoletoList;
    }

    public InventarioInterno getInventarioCajaIdInventarioInterno() {
        return inventarioCajaIdInventarioInterno;
    }

    public void setInventarioCajaIdInventarioInterno(InventarioInterno inventarioCajaIdInventarioInterno) {
        this.inventarioCajaIdInventarioInterno = inventarioCajaIdInventarioInterno;
    }

    public CajaRecaudacion getInventarioCajaIdCaja() {
        return inventarioCajaIdCaja;
    }

    public void setInventarioCajaIdCaja(CajaRecaudacion inventarioCajaIdCaja) {
        this.inventarioCajaIdCaja = inventarioCajaIdCaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventarioCajaId != null ? inventarioCajaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioCaja)) {
            return false;
        }
        InventarioCaja other = (InventarioCaja) object;
        if ((this.inventarioCajaId == null && other.inventarioCajaId != null) || (this.inventarioCajaId != null && !this.inventarioCajaId.equals(other.inventarioCajaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.InventarioCaja[ inventarioCajaId=" + inventarioCajaId + " ]";
    }

}
