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
@Table(name = "tipo_descuento_trabajador", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDescuentoTrabajador.findAll", query = "SELECT t FROM TipoDescuentoTrabajador t")
    , @NamedQuery(name = "TipoDescuentoTrabajador.findByTipoDescuentoTrabajadorId", query = "SELECT t FROM TipoDescuentoTrabajador t WHERE t.tipoDescuentoTrabajadorId = :tipoDescuentoTrabajadorId")
    , @NamedQuery(name = "TipoDescuentoTrabajador.findByTipoDescuentoTrabajadorNombre", query = "SELECT t FROM TipoDescuentoTrabajador t WHERE t.tipoDescuentoTrabajadorNombre = :tipoDescuentoTrabajadorNombre")
    , @NamedQuery(name = "TipoDescuentoTrabajador.findByTipoDescuentoTrabajadorMontoDefecto", query = "SELECT t FROM TipoDescuentoTrabajador t WHERE t.tipoDescuentoTrabajadorMontoDefecto = :tipoDescuentoTrabajadorMontoDefecto")
    })
public class TipoDescuentoTrabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipo_descuento_trabajador_id")
    private Integer tipoDescuentoTrabajadorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tipo_descuento_trabajador_nombre")
    private String tipoDescuentoTrabajadorNombre;
    @Column(name = "tipo_descuento_trabajador_monto_defecto")
    private Integer tipoDescuentoTrabajadorMontoDefecto;    
    @JoinColumn(name = "tipo_descuento_trabajador_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta tipoDescuentoTrabajadorIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "descuentoTrabajadorIdDescuento")
    private List<DescuentoTrabajador> descuentoTrabajadorList;

    public TipoDescuentoTrabajador() {
    }

    public TipoDescuentoTrabajador(Integer tipoDescuentoTrabajadorId) {
        this.tipoDescuentoTrabajadorId = tipoDescuentoTrabajadorId;
    }

    public TipoDescuentoTrabajador(Integer tipoDescuentoTrabajadorId, String tipoDescuentoTrabajadorNombre) {
        this.tipoDescuentoTrabajadorId = tipoDescuentoTrabajadorId;
        this.tipoDescuentoTrabajadorNombre = tipoDescuentoTrabajadorNombre;
    }

    public Integer getTipoDescuentoTrabajadorId() {
        return tipoDescuentoTrabajadorId;
    }

    public void setTipoDescuentoTrabajadorId(Integer tipoDescuentoTrabajadorId) {
        this.tipoDescuentoTrabajadorId = tipoDescuentoTrabajadorId;
    }

    public String getTipoDescuentoTrabajadorNombre() {
        return tipoDescuentoTrabajadorNombre;
    }

    public void setTipoDescuentoTrabajadorNombre(String tipoDescuentoTrabajadorNombre) {
        this.tipoDescuentoTrabajadorNombre = tipoDescuentoTrabajadorNombre;
    }

    public Integer getTipoDescuentoTrabajadorMontoDefecto() {
        return tipoDescuentoTrabajadorMontoDefecto;
    }

    public void setTipoDescuentoTrabajadorMontoDefecto(Integer tipoDescuentoTrabajadorMontoDefecto) {
        this.tipoDescuentoTrabajadorMontoDefecto = tipoDescuentoTrabajadorMontoDefecto;
    }

    public Cuenta getTipoDescuentoTrabajadorIdCuenta() {
        return tipoDescuentoTrabajadorIdCuenta;
    }

    public void setTipoDescuentoTrabajadorIdCuenta(Cuenta tipoDescuentoTrabajadorIdCuenta) {
        this.tipoDescuentoTrabajadorIdCuenta = tipoDescuentoTrabajadorIdCuenta;
    }

    @XmlTransient
    public List<DescuentoTrabajador> getDescuentoTrabajadorList() {
        return descuentoTrabajadorList;
    }

    public void setDescuentoTrabajadorList(List<DescuentoTrabajador> descuentoTrabajadorList) {
        this.descuentoTrabajadorList = descuentoTrabajadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoDescuentoTrabajadorId != null ? tipoDescuentoTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDescuentoTrabajador)) {
            return false;
        }
        TipoDescuentoTrabajador other = (TipoDescuentoTrabajador) object;
        if ((this.tipoDescuentoTrabajadorId == null && other.tipoDescuentoTrabajadorId != null) || (this.tipoDescuentoTrabajadorId != null && !this.tipoDescuentoTrabajadorId.equals(other.tipoDescuentoTrabajadorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.TipoDescuentoTrabajador[ tipoDescuentoTrabajadorId=" + tipoDescuentoTrabajadorId + " ]";
    }
    
}
