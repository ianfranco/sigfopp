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
@Table(name = "asignacion_familiar", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsignacionFamiliar.findAll", query = "SELECT a FROM AsignacionFamiliar a")
    , @NamedQuery(name = "AsignacionFamiliar.findByAsignacionFamiliarId", query = "SELECT a FROM AsignacionFamiliar a WHERE a.asignacionFamiliarId = :asignacionFamiliarId")
    , @NamedQuery(name = "AsignacionFamiliar.findByAsignacionFamiliarCodigo", query = "SELECT a FROM AsignacionFamiliar a WHERE a.asignacionFamiliarCodigo = :asignacionFamiliarCodigo")
    , @NamedQuery(name = "AsignacionFamiliar.findByAsignacionFamiliarTramo", query = "SELECT a FROM AsignacionFamiliar a WHERE a.asignacionFamiliarTramo = :asignacionFamiliarTramo")
    , @NamedQuery(name = "AsignacionFamiliar.findByAsignacionFamiliarMonto", query = "SELECT a FROM AsignacionFamiliar a WHERE a.asignacionFamiliarMonto = :asignacionFamiliarMonto")
    , @NamedQuery(name = "AsignacionFamiliar.findByAsignacionFamiliarDesde", query = "SELECT a FROM AsignacionFamiliar a WHERE a.asignacionFamiliarDesde = :asignacionFamiliarDesde")
    , @NamedQuery(name = "AsignacionFamiliar.findByAsignacionFamiliarHasta", query = "SELECT a FROM AsignacionFamiliar a WHERE a.asignacionFamiliarHasta = :asignacionFamiliarHasta")})
public class AsignacionFamiliar extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "asignacion_familiar_id")
    private Integer asignacionFamiliarId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "asignacion_familiar_codigo")
    private String asignacionFamiliarCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "asignacion_familiar_tramo")
    private String asignacionFamiliarTramo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "asignacion_familiar_monto")
    private int asignacionFamiliarMonto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "asignacion_familiar_desde")
    private int asignacionFamiliarDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "asignacion_familiar_hasta")
    private int asignacionFamiliarHasta;
    @JoinColumn(name = "asignacion_familiar_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta asignacionFamiliarIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdAsignacionFamiliar")
    private List<Trabajador> trabajadorList;

    public AsignacionFamiliar() {
    }

    public AsignacionFamiliar(Integer asignacionFamiliarId) {
        this.asignacionFamiliarId = asignacionFamiliarId;
    }

    public AsignacionFamiliar(Integer asignacionFamiliarId, String asignacionFamiliarCodigo, String asignacionFamiliarTramo, int asignacionFamiliarMonto, int asignacionFamiliarDesde, int asignacionFamiliarHasta) {
        this.asignacionFamiliarId = asignacionFamiliarId;
        this.asignacionFamiliarCodigo = asignacionFamiliarCodigo;
        this.asignacionFamiliarTramo = asignacionFamiliarTramo;
        this.asignacionFamiliarMonto = asignacionFamiliarMonto;
        this.asignacionFamiliarDesde = asignacionFamiliarDesde;
        this.asignacionFamiliarHasta = asignacionFamiliarHasta;
    }

    public Integer getAsignacionFamiliarId() {
        return asignacionFamiliarId;
    }

    public void setAsignacionFamiliarId(Integer asignacionFamiliarId) {
        this.asignacionFamiliarId = asignacionFamiliarId;
    }

    public String getAsignacionFamiliarCodigo() {
        return asignacionFamiliarCodigo;
    }

    public void setAsignacionFamiliarCodigo(String asignacionFamiliarCodigo) {
        this.asignacionFamiliarCodigo = asignacionFamiliarCodigo;
    }

    public String getAsignacionFamiliarTramo() {
        return asignacionFamiliarTramo;
    }

    public void setAsignacionFamiliarTramo(String asignacionFamiliarTramo) {
        this.asignacionFamiliarTramo = asignacionFamiliarTramo;
    }

    public int getAsignacionFamiliarMonto() {
        return asignacionFamiliarMonto;
    }

    public void setAsignacionFamiliarMonto(int asignacionFamiliarMonto) {
        this.asignacionFamiliarMonto = asignacionFamiliarMonto;
    }

    public int getAsignacionFamiliarDesde() {
        return asignacionFamiliarDesde;
    }

    public void setAsignacionFamiliarDesde(int asignacionFamiliarDesde) {
        this.asignacionFamiliarDesde = asignacionFamiliarDesde;
    }

    public int getAsignacionFamiliarHasta() {
        return asignacionFamiliarHasta;
    }

    public void setAsignacionFamiliarHasta(int asignacionFamiliarHasta) {
        this.asignacionFamiliarHasta = asignacionFamiliarHasta;
    }

    public Cuenta getAsignacionFamiliarIdCuenta() {
        return asignacionFamiliarIdCuenta;
    }

    public void setAsignacionFamiliarIdCuenta(Cuenta asignacionFamiliarIdCuenta) {
        this.asignacionFamiliarIdCuenta = asignacionFamiliarIdCuenta;
    }

    @XmlTransient
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asignacionFamiliarId != null ? asignacionFamiliarId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionFamiliar)) {
            return false;
        }
        AsignacionFamiliar other = (AsignacionFamiliar) object;
        if ((this.asignacionFamiliarId == null && other.asignacionFamiliarId != null) || (this.asignacionFamiliarId != null && !this.asignacionFamiliarId.equals(other.asignacionFamiliarId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AsignacionFamiliar[ asignacionFamiliarId=" + asignacionFamiliarId + " ]";
    }
    
}
