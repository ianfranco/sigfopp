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
@Table(name = "administracion_mensual", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministracionMensual.findAll", query = "SELECT a FROM AdministracionMensual a")
    , @NamedQuery(name = "AdministracionMensual.findByAdministracionMensualId", query = "SELECT a FROM AdministracionMensual a WHERE a.administracionMensualId = :administracionMensualId")
    , @NamedQuery(name = "AdministracionMensual.findByAdministracionMensualFecha", query = "SELECT a FROM AdministracionMensual a WHERE a.administracionMensualFecha = :administracionMensualFecha")
    , @NamedQuery(name = "AdministracionMensual.findByAdministracionMensualValor", query = "SELECT a FROM AdministracionMensual a WHERE a.administracionMensualValor = :administracionMensualValor")
    })
public class AdministracionMensual extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "administracion_mensual_id")
    private Integer administracionMensualId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "administracion_mensual_fecha")
    @Temporal(TemporalType.DATE)
    private Date administracionMensualFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "administracion_mensual_valor")
    private int administracionMensualValor;
    @JoinColumn(name = "administracion_mensual_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta administracionMensualIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleIntervalosMensualIdAdministracion")
    private List<DetalleIntervalosMensual> detalleIntervalosMensualList;

    public AdministracionMensual() {
    }

    public AdministracionMensual(Integer administracionMensualId) {
        this.administracionMensualId = administracionMensualId;
    }

    public AdministracionMensual(Integer administracionMensualId, Date administracionMensualFecha, int administracionMensualValor) {
        this.administracionMensualId = administracionMensualId;
        this.administracionMensualFecha = administracionMensualFecha;
        this.administracionMensualValor = administracionMensualValor;
    }

    public Integer getAdministracionMensualId() {
        return administracionMensualId;
    }

    public void setAdministracionMensualId(Integer administracionMensualId) {
        this.administracionMensualId = administracionMensualId;
    }

    public Date getAdministracionMensualFecha() {
        return administracionMensualFecha;
    }

    public void setAdministracionMensualFecha(Date administracionMensualFecha) {
        this.administracionMensualFecha = administracionMensualFecha;
    }

    public int getAdministracionMensualValor() {
        return administracionMensualValor;
    }

    public void setAdministracionMensualValor(int administracionMensualValor) {
        this.administracionMensualValor = administracionMensualValor;
    }

    public Cuenta getAdministracionMensualIdCuenta() {
        return administracionMensualIdCuenta;
    }

    public void setAdministracionMensualIdCuenta(Cuenta administracionMensualIdCuenta) {
        this.administracionMensualIdCuenta = administracionMensualIdCuenta;
    }

    @XmlTransient
    public List<DetalleIntervalosMensual> getDetalleIntervalosMensualList() {
        return detalleIntervalosMensualList;
    }

    public void setDetalleIntervalosMensualList(List<DetalleIntervalosMensual> detalleIntervalosMensualList) {
        this.detalleIntervalosMensualList = detalleIntervalosMensualList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (administracionMensualId != null ? administracionMensualId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministracionMensual)) {
            return false;
        }
        AdministracionMensual other = (AdministracionMensual) object;
        if ((this.administracionMensualId == null && other.administracionMensualId != null) || (this.administracionMensualId != null && !this.administracionMensualId.equals(other.administracionMensualId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.AdministracionMensual[ administracionMensualId=" + administracionMensualId + " ]";
    }
    
}
