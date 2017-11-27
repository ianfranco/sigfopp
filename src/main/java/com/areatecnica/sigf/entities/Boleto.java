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
@Table(name = "boleto", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleto.findAll", query = "SELECT b FROM Boleto b")
    , @NamedQuery(name = "Boleto.findAllByCuenta", query = "SELECT b FROM Boleto b WHERE b.boletoIdCuenta = :idCuenta")
    , @NamedQuery(name = "Boleto.findByBoletoId", query = "SELECT b FROM Boleto b WHERE b.boletoId = :boletoId")
    , @NamedQuery(name = "Boleto.findByBoletoNombre", query = "SELECT b FROM Boleto b WHERE b.boletoNombre = :boletoNombre")
    , @NamedQuery(name = "Boleto.findByBoletoActivo", query = "SELECT b FROM Boleto b WHERE b.boletoActivo = :boletoActivo")
    })
public class Boleto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "boleto_id")
    private Integer boletoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "boleto_nombre")
    private String boletoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "boleto_activo")
    private boolean boletoActivo;    
    @JoinColumn(name = "boleto_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta boletoIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventarioInternoIdBoleto")
    private List<InventarioInterno> inventarioInternoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleCompraBoletoIdBoleto")
    private List<DetalleCompraBoleto> detalleCompraBoletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroBoletoIdBoleto")
    private List<RegistroBoleto> registroBoletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarifaGrupoServicioIdBoleto")
    private List<TarifaGrupoServicio> tarifaGrupoServicioList;

    public Boleto() {
    }

    public Boleto(Integer boletoId) {
        this.boletoId = boletoId;
    }

    public Boleto(Integer boletoId, String boletoNombre, boolean boletoActivo) {
        this.boletoId = boletoId;
        this.boletoNombre = boletoNombre;
        this.boletoActivo = boletoActivo;
    }

    public Integer getBoletoId() {
        return boletoId;
    }

    public void setBoletoId(Integer boletoId) {
        this.boletoId = boletoId;
    }

    public String getBoletoNombre() {
        return boletoNombre;
    }

    public void setBoletoNombre(String boletoNombre) {
        this.boletoNombre = boletoNombre;
    }

    public boolean getBoletoActivo() {
        return boletoActivo;
    }

    public void setBoletoActivo(boolean boletoActivo) {
        this.boletoActivo = boletoActivo;
    }
    
    public Cuenta getBoletoIdCuenta() {
        return boletoIdCuenta;
    }

    public void setBoletoIdCuenta(Cuenta boletoIdCuenta) {
        this.boletoIdCuenta = boletoIdCuenta;
    }

    @XmlTransient
    public List<InventarioInterno> getInventarioInternoList() {
        return inventarioInternoList;
    }

    public void setInventarioInternoList(List<InventarioInterno> inventarioInternoList) {
        this.inventarioInternoList = inventarioInternoList;
    }

    @XmlTransient
    public List<DetalleCompraBoleto> getDetalleCompraBoletoList() {
        return detalleCompraBoletoList;
    }

    public void setDetalleCompraBoletoList(List<DetalleCompraBoleto> detalleCompraBoletoList) {
        this.detalleCompraBoletoList = detalleCompraBoletoList;
    }

    @XmlTransient
    public List<RegistroBoleto> getRegistroBoletoList() {
        return registroBoletoList;
    }

    public void setRegistroBoletoList(List<RegistroBoleto> registroBoletoList) {
        this.registroBoletoList = registroBoletoList;
    }

    @XmlTransient
    public List<TarifaGrupoServicio> getTarifaGrupoServicioList() {
        return tarifaGrupoServicioList;
    }

    public void setTarifaGrupoServicioList(List<TarifaGrupoServicio> tarifaGrupoServicioList) {
        this.tarifaGrupoServicioList = tarifaGrupoServicioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (boletoId != null ? boletoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Boleto)) {
            return false;
        }
        Boleto other = (Boleto) object;
        if ((this.boletoId == null && other.boletoId != null) || (this.boletoId != null && !this.boletoId.equals(other.boletoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Boleto[ boletoId=" + boletoId + " ]";
    }

}
