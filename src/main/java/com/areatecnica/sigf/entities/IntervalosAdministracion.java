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
@Table(name = "intervalos_administracion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IntervalosAdministracion.findAll", query = "SELECT i FROM IntervalosAdministracion i")
    , @NamedQuery(name = "IntervalosAdministracion.findByIntervalosAdministracionId", query = "SELECT i FROM IntervalosAdministracion i WHERE i.intervalosAdministracionId = :intervalosAdministracionId")
    , @NamedQuery(name = "IntervalosAdministracion.findByIntervalosAdministracionNombre", query = "SELECT i FROM IntervalosAdministracion i WHERE i.intervalosAdministracionNombre = :intervalosAdministracionNombre")
    , @NamedQuery(name = "IntervalosAdministracion.findByIntervalosAdministracionDesde", query = "SELECT i FROM IntervalosAdministracion i WHERE i.intervalosAdministracionDesde = :intervalosAdministracionDesde")
    , @NamedQuery(name = "IntervalosAdministracion.findByIntervalosAdministracionHasta", query = "SELECT i FROM IntervalosAdministracion i WHERE i.intervalosAdministracionHasta = :intervalosAdministracionHasta")})
public class IntervalosAdministracion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intervalos_administracion_id")
    private Integer intervalosAdministracionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "intervalos_administracion_nombre")
    private String intervalosAdministracionNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intervalos_administracion_desde")
    private int intervalosAdministracionDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intervalos_administracion_hasta")
    private int intervalosAdministracionHasta;
    @JoinColumn(name = "intervalos_administracion_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta intervalosAdministracionIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detalleIntervalosMensualIdIntervaloAdministracion")
    private List<DetalleIntervalosMensual> detalleIntervalosMensualList;

    public IntervalosAdministracion() {
    }

    public IntervalosAdministracion(Integer intervalosAdministracionId) {
        this.intervalosAdministracionId = intervalosAdministracionId;
    }

    public IntervalosAdministracion(Integer intervalosAdministracionId, String intervalosAdministracionNombre, int intervalosAdministracionDesde, int intervalosAdministracionHasta) {
        this.intervalosAdministracionId = intervalosAdministracionId;
        this.intervalosAdministracionNombre = intervalosAdministracionNombre;
        this.intervalosAdministracionDesde = intervalosAdministracionDesde;
        this.intervalosAdministracionHasta = intervalosAdministracionHasta;
    }

    public Integer getIntervalosAdministracionId() {
        return intervalosAdministracionId;
    }

    public void setIntervalosAdministracionId(Integer intervalosAdministracionId) {
        this.intervalosAdministracionId = intervalosAdministracionId;
    }

    public String getIntervalosAdministracionNombre() {
        return intervalosAdministracionNombre;
    }

    public void setIntervalosAdministracionNombre(String intervalosAdministracionNombre) {
        this.intervalosAdministracionNombre = intervalosAdministracionNombre;
    }

    public int getIntervalosAdministracionDesde() {
        return intervalosAdministracionDesde;
    }

    public void setIntervalosAdministracionDesde(int intervalosAdministracionDesde) {
        this.intervalosAdministracionDesde = intervalosAdministracionDesde;
    }

    public int getIntervalosAdministracionHasta() {
        return intervalosAdministracionHasta;
    }

    public void setIntervalosAdministracionHasta(int intervalosAdministracionHasta) {
        this.intervalosAdministracionHasta = intervalosAdministracionHasta;
    }

    public Cuenta getIntervalosAdministracionIdCuenta() {
        return intervalosAdministracionIdCuenta;
    }

    public void setIntervalosAdministracionIdCuenta(Cuenta intervalosAdministracionIdCuenta) {
        this.intervalosAdministracionIdCuenta = intervalosAdministracionIdCuenta;
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
        hash += (intervalosAdministracionId != null ? intervalosAdministracionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IntervalosAdministracion)) {
            return false;
        }
        IntervalosAdministracion other = (IntervalosAdministracion) object;
        if ((this.intervalosAdministracionId == null && other.intervalosAdministracionId != null) || (this.intervalosAdministracionId != null && !this.intervalosAdministracionId.equals(other.intervalosAdministracionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.IntervalosAdministracion[ intervalosAdministracionId=" + intervalosAdministracionId + " ]";
    }
    
}
