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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "detalle_intervalos_mensual", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleIntervalosMensual.findAll", query = "SELECT d FROM DetalleIntervalosMensual d")
    , @NamedQuery(name = "DetalleIntervalosMensual.findByDetalleIntervalosMensualId", query = "SELECT d FROM DetalleIntervalosMensual d WHERE d.detalleIntervalosMensualId = :detalleIntervalosMensualId")
    , @NamedQuery(name = "DetalleIntervalosMensual.findByDetalleIntervalosMensualNumeroBuses", query = "SELECT d FROM DetalleIntervalosMensual d WHERE d.detalleIntervalosMensualNumeroBuses = :detalleIntervalosMensualNumeroBuses")})
public class DetalleIntervalosMensual extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detalle_intervalos_mensual_id")
    private Integer detalleIntervalosMensualId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_intervalos_mensual_numero_buses")
    private int detalleIntervalosMensualNumeroBuses;
    @JoinColumn(name = "detalle_intervalos_mensual_id_administracion", referencedColumnName = "administracion_mensual_id", nullable = false)
    @ManyToOne(optional = false)
    private AdministracionMensual detalleIntervalosMensualIdAdministracion;
    @JoinColumn(name = "detalle_intervalos_mensual_id_intervalo_administracion", referencedColumnName = "intervalos_administracion_id", nullable = false)
    @ManyToOne(optional = false)
    private IntervalosAdministracion detalleIntervalosMensualIdIntervaloAdministracion;

    public DetalleIntervalosMensual() {
    }

    public DetalleIntervalosMensual(Integer detalleIntervalosMensualId) {
        this.detalleIntervalosMensualId = detalleIntervalosMensualId;
    }

    public DetalleIntervalosMensual(Integer detalleIntervalosMensualId, int detalleIntervalosMensualNumeroBuses) {
        this.detalleIntervalosMensualId = detalleIntervalosMensualId;
        this.detalleIntervalosMensualNumeroBuses = detalleIntervalosMensualNumeroBuses;
    }

    public Integer getDetalleIntervalosMensualId() {
        return detalleIntervalosMensualId;
    }

    public void setDetalleIntervalosMensualId(Integer detalleIntervalosMensualId) {
        this.detalleIntervalosMensualId = detalleIntervalosMensualId;
    }

    public int getDetalleIntervalosMensualNumeroBuses() {
        return detalleIntervalosMensualNumeroBuses;
    }

    public void setDetalleIntervalosMensualNumeroBuses(int detalleIntervalosMensualNumeroBuses) {
        this.detalleIntervalosMensualNumeroBuses = detalleIntervalosMensualNumeroBuses;
    }

    public AdministracionMensual getDetalleIntervalosMensualIdAdministracion() {
        return detalleIntervalosMensualIdAdministracion;
    }

    public void setDetalleIntervalosMensualIdAdministracion(AdministracionMensual detalleIntervalosMensualIdAdministracion) {
        this.detalleIntervalosMensualIdAdministracion = detalleIntervalosMensualIdAdministracion;
    }

    public IntervalosAdministracion getDetalleIntervalosMensualIdIntervaloAdministracion() {
        return detalleIntervalosMensualIdIntervaloAdministracion;
    }

    public void setDetalleIntervalosMensualIdIntervaloAdministracion(IntervalosAdministracion detalleIntervalosMensualIdIntervaloAdministracion) {
        this.detalleIntervalosMensualIdIntervaloAdministracion = detalleIntervalosMensualIdIntervaloAdministracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleIntervalosMensualId != null ? detalleIntervalosMensualId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleIntervalosMensual)) {
            return false;
        }
        DetalleIntervalosMensual other = (DetalleIntervalosMensual) object;
        if ((this.detalleIntervalosMensualId == null && other.detalleIntervalosMensualId != null) || (this.detalleIntervalosMensualId != null && !this.detalleIntervalosMensualId.equals(other.detalleIntervalosMensualId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.DetalleIntervalosMensual[ detalleIntervalosMensualId=" + detalleIntervalosMensualId + " ]";
    }
    
}
