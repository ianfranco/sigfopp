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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "tipo_haber_trabajador", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoHaberTrabajador.findAll", query = "SELECT t FROM TipoHaberTrabajador t")
    , @NamedQuery(name = "TipoHaberTrabajador.findByTipoHaberTrabajadorId", query = "SELECT t FROM TipoHaberTrabajador t WHERE t.tipoHaberTrabajadorId = :tipoHaberTrabajadorId")
    , @NamedQuery(name = "TipoHaberTrabajador.findByTipoHaberTrabajadorNombre", query = "SELECT t FROM TipoHaberTrabajador t WHERE t.tipoHaberTrabajadorNombre = :tipoHaberTrabajadorNombre")
    , @NamedQuery(name = "TipoHaberTrabajador.findByTipoHaberTrabajadorMontoDefecto", query = "SELECT t FROM TipoHaberTrabajador t WHERE t.tipoHaberTrabajadorMontoDefecto = :tipoHaberTrabajadorMontoDefecto")
    , @NamedQuery(name = "TipoHaberTrabajador.findByTipoHaberTrabajadorImponible", query = "SELECT t FROM TipoHaberTrabajador t WHERE t.tipoHaberTrabajadorImponible = :tipoHaberTrabajadorImponible")
    })
public class TipoHaberTrabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_haber_trabajador_id")
    private Integer tipoHaberTrabajadorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tipo_haber_trabajador_nombre")
    private String tipoHaberTrabajadorNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_haber_trabajador_monto_defecto")
    private int tipoHaberTrabajadorMontoDefecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_haber_trabajador_imponible")
    private boolean tipoHaberTrabajadorImponible;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "haberTrabajadorIdHaber")
    private List<HaberTrabajador> haberTrabajadorList;
    @JoinColumn(name = "tipo_haber_trabajador_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta tipoHaberTrabajadorIdCuenta;

    public TipoHaberTrabajador() {
    }

    public TipoHaberTrabajador(Integer tipoHaberTrabajadorId) {
        this.tipoHaberTrabajadorId = tipoHaberTrabajadorId;
    }

    public TipoHaberTrabajador(Integer tipoHaberTrabajadorId, String tipoHaberTrabajadorNombre, int tipoHaberTrabajadorMontoDefecto, boolean tipoHaberTrabajadorImponible) {
        this.tipoHaberTrabajadorId = tipoHaberTrabajadorId;
        this.tipoHaberTrabajadorNombre = tipoHaberTrabajadorNombre;
        this.tipoHaberTrabajadorMontoDefecto = tipoHaberTrabajadorMontoDefecto;
        this.tipoHaberTrabajadorImponible = tipoHaberTrabajadorImponible;
    }

    public Integer getTipoHaberTrabajadorId() {
        return tipoHaberTrabajadorId;
    }

    public void setTipoHaberTrabajadorId(Integer tipoHaberTrabajadorId) {
        this.tipoHaberTrabajadorId = tipoHaberTrabajadorId;
    }

    public String getTipoHaberTrabajadorNombre() {
        return tipoHaberTrabajadorNombre;
    }

    public void setTipoHaberTrabajadorNombre(String tipoHaberTrabajadorNombre) {
        this.tipoHaberTrabajadorNombre = tipoHaberTrabajadorNombre;
    }

    public int getTipoHaberTrabajadorMontoDefecto() {
        return tipoHaberTrabajadorMontoDefecto;
    }

    public void setTipoHaberTrabajadorMontoDefecto(int tipoHaberTrabajadorMontoDefecto) {
        this.tipoHaberTrabajadorMontoDefecto = tipoHaberTrabajadorMontoDefecto;
    }

    public boolean getTipoHaberTrabajadorImponible() {
        return tipoHaberTrabajadorImponible;
    }

    public void setTipoHaberTrabajadorImponible(boolean tipoHaberTrabajadorImponible) {
        this.tipoHaberTrabajadorImponible = tipoHaberTrabajadorImponible;
    }

    @XmlTransient
    public List<HaberTrabajador> getHaberTrabajadorList() {
        return haberTrabajadorList;
    }

    public void setHaberTrabajadorList(List<HaberTrabajador> haberTrabajadorList) {
        this.haberTrabajadorList = haberTrabajadorList;
    }

    public Cuenta getTipoHaberTrabajadorIdCuenta() {
        return tipoHaberTrabajadorIdCuenta;
    }

    public void setTipoHaberTrabajadorIdCuenta(Cuenta tipoHaberTrabajadorIdCuenta) {
        this.tipoHaberTrabajadorIdCuenta = tipoHaberTrabajadorIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoHaberTrabajadorId != null ? tipoHaberTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoHaberTrabajador)) {
            return false;
        }
        TipoHaberTrabajador other = (TipoHaberTrabajador) object;
        if ((this.tipoHaberTrabajadorId == null && other.tipoHaberTrabajadorId != null) || (this.tipoHaberTrabajadorId != null && !this.tipoHaberTrabajadorId.equals(other.tipoHaberTrabajadorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoHaberTrabajador[ tipoHaberTrabajadorId=" + tipoHaberTrabajadorId + " ]";
    }
    
}
