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
@Table(name = "tipo_abono", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAbono.findAll", query = "SELECT t FROM TipoAbono t")
    , @NamedQuery(name = "TipoAbono.findByTipoAbonoId", query = "SELECT t FROM TipoAbono t WHERE t.tipoAbonoId = :tipoAbonoId")
    , @NamedQuery(name = "TipoAbono.findByTipoAbonoNombre", query = "SELECT t FROM TipoAbono t WHERE t.tipoAbonoNombre = :tipoAbonoNombre")
    , @NamedQuery(name = "TipoAbono.findByTipoAbonoMontoDefecto", query = "SELECT t FROM TipoAbono t WHERE t.tipoAbonoMontoDefecto = :tipoAbonoMontoDefecto")
    })
public class TipoAbono extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_abono_id")
    private Integer tipoAbonoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo_abono_nombre")
    private String tipoAbonoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_abono_monto_defecto")
    private int tipoAbonoMontoDefecto;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "abonoBusIdTipoAbono")
    private List<AbonoBus> abonoBusList;
    @JoinColumn(name = "tipo_abono_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta tipoAbonoIdCuenta;

    public TipoAbono() {
    }

    public TipoAbono(Integer tipoAbonoId) {
        this.tipoAbonoId = tipoAbonoId;
    }

    public TipoAbono(Integer tipoAbonoId, String tipoAbonoNombre, int tipoAbonoMontoDefecto) {
        this.tipoAbonoId = tipoAbonoId;
        this.tipoAbonoNombre = tipoAbonoNombre;
        this.tipoAbonoMontoDefecto = tipoAbonoMontoDefecto;
    }

    public Integer getTipoAbonoId() {
        return tipoAbonoId;
    }

    public void setTipoAbonoId(Integer tipoAbonoId) {
        this.tipoAbonoId = tipoAbonoId;
    }

    public String getTipoAbonoNombre() {
        return tipoAbonoNombre;
    }

    public void setTipoAbonoNombre(String tipoAbonoNombre) {
        this.tipoAbonoNombre = tipoAbonoNombre;
    }

    public int getTipoAbonoMontoDefecto() {
        return tipoAbonoMontoDefecto;
    }

    public void setTipoAbonoMontoDefecto(int tipoAbonoMontoDefecto) {
        this.tipoAbonoMontoDefecto = tipoAbonoMontoDefecto;
    }

    @XmlTransient
    public List<AbonoBus> getAbonoBusList() {
        return abonoBusList;
    }

    public void setAbonoBusList(List<AbonoBus> abonoBusList) {
        this.abonoBusList = abonoBusList;
    }

    public Cuenta getTipoAbonoIdCuenta() {
        return tipoAbonoIdCuenta;
    }

    public void setTipoAbonoIdCuenta(Cuenta tipoAbonoIdCuenta) {
        this.tipoAbonoIdCuenta = tipoAbonoIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoAbonoId != null ? tipoAbonoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAbono)) {
            return false;
        }
        TipoAbono other = (TipoAbono) object;
        if ((this.tipoAbonoId == null && other.tipoAbonoId != null) || (this.tipoAbonoId != null && !this.tipoAbonoId.equals(other.tipoAbonoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoAbono[ tipoAbonoId=" + tipoAbonoId + " ]";
    }
    
}
