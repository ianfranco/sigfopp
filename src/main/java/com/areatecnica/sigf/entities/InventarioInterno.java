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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "inventario_interno", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioInterno.findAll", query = "SELECT i FROM InventarioInterno i")
    , @NamedQuery(name = "InventarioInterno.findByInventarioInternoId", query = "SELECT i FROM InventarioInterno i WHERE i.inventarioInternoId = :inventarioInternoId")
    , @NamedQuery(name = "InventarioInterno.findByInventarioInternoSerie", query = "SELECT i FROM InventarioInterno i WHERE i.inventarioInternoSerie = :inventarioInternoSerie")
    , @NamedQuery(name = "InventarioInterno.findByInventarioInternoEstado", query = "SELECT i FROM InventarioInterno i WHERE i.inventarioInternoEstado = :inventarioInternoEstado AND i.inventarioInternoIdBoleto.boletoIdCuenta=:idCuenta")
    , @NamedQuery(name = "InventarioInterno.findByInventarioInternoIdentificador", query = "SELECT i FROM InventarioInterno i WHERE i.inventarioInternoIdentificador = :inventarioInternoIdentificador")

})
public class InventarioInterno extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inventario_interno_id")
    private Integer inventarioInternoId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inventario_interno_serie")
    private int inventarioInternoSerie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "inventario_interno_identificador")
    private String inventarioInternoIdentificador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inventario_interno_estado")
    private boolean inventarioInternoEstado;
    @JoinColumn(name = "inventario_interno_id_boleto", referencedColumnName = "boleto_id", nullable = false)
    @ManyToOne(optional = false)
    private Boleto inventarioInternoIdBoleto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioCajaIdInventarioInterno")
    private List<InventarioCaja> inventarioCajaList;

    public InventarioInterno() {
    }

    public InventarioInterno(Integer inventarioInternoId) {
        this.inventarioInternoId = inventarioInternoId;
    }

    public InventarioInterno(Integer inventarioInternoId, int inventarioInternoSerie, String inventarioInternoIdentificador, boolean inventarioInternoEstado) {
        this.inventarioInternoId = inventarioInternoId;
        this.inventarioInternoSerie = inventarioInternoSerie;
        this.inventarioInternoIdentificador = inventarioInternoIdentificador;
        this.inventarioInternoEstado = inventarioInternoEstado;
    }

    public Integer getInventarioInternoId() {
        return inventarioInternoId;
    }

    public void setInventarioInternoId(Integer inventarioInternoId) {
        this.inventarioInternoId = inventarioInternoId;
    }

    public int getInventarioInternoSerie() {
        return inventarioInternoSerie;
    }

    public void setInventarioInternoSerie(int inventarioInternoSerie) {
        this.inventarioInternoSerie = inventarioInternoSerie;
    }

    public String getInventarioInternoIdentificador() {
        return inventarioInternoIdentificador;
    }

    public void setInventarioInternoIdentificador(String inventarioInternoIdentificador) {
        this.inventarioInternoIdentificador = inventarioInternoIdentificador;
    }

    public boolean getInventarioInternoEstado() {
        return inventarioInternoEstado;
    }

    public void setInventarioInternoEstado(boolean inventarioInternoEstado) {
        this.inventarioInternoEstado = inventarioInternoEstado;
    }

    public Boleto getInventarioInternoIdBoleto() {
        return inventarioInternoIdBoleto;
    }

    public void setInventarioInternoIdBoleto(Boleto inventarioInternoIdBoleto) {
        this.inventarioInternoIdBoleto = inventarioInternoIdBoleto;
    }

    @XmlTransient
    public List<InventarioCaja> getInventarioCajaList() {
        return inventarioCajaList;
    }

    public void setInventarioCajaList(List<InventarioCaja> inventarioCajaList) {
        this.inventarioCajaList = inventarioCajaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventarioInternoId != null ? inventarioInternoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventarioInterno)) {
            return false;
        }
        InventarioInterno other = (InventarioInterno) object;
        if ((this.inventarioInternoId == null && other.inventarioInternoId != null) || (this.inventarioInternoId != null && !this.inventarioInternoId.equals(other.inventarioInternoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.InventarioInterno[ inventarioInternoId=" + inventarioInternoId + " ]";
    }

}
