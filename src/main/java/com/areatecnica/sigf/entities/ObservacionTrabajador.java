/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.sigf.entities;

import com.areatecnica.sigf.audit.AuditListener;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "observacion_trabajador", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObservacionTrabajador.findAll", query = "SELECT o FROM ObservacionTrabajador o")
    , @NamedQuery(name = "ObservacionTrabajador.findByObservacionTrabajadorId", query = "SELECT o FROM ObservacionTrabajador o WHERE o.observacionTrabajadorId = :observacionTrabajadorId")
    , @NamedQuery(name = "ObservacionTrabajador.findByObservacionTrabajadorFecha", query = "SELECT o FROM ObservacionTrabajador o WHERE o.observacionTrabajadorFecha = :observacionTrabajadorFecha")
    , @NamedQuery(name = "ObservacionTrabajador.findByObservacionTrabajadorDetalle", query = "SELECT o FROM ObservacionTrabajador o WHERE o.observacionTrabajadorDetalle = :observacionTrabajadorDetalle")
    })
public class ObservacionTrabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "observacion_trabajador_id")
    private Integer observacionTrabajadorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "observacion_trabajador_fecha")
    @Temporal(TemporalType.DATE)
    private Date observacionTrabajadorFecha;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "observacion_trabajador_descripcion")
    private String observacionTrabajadorDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "observacion_trabajador_detalle")
    private String observacionTrabajadorDetalle;    
    @JoinColumn(name = "observacion_trabajador_id_tipo_observacion", referencedColumnName = "tipo_observacion_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoObservacion observacionTrabajadorIdTipoObservacion;
    @JoinColumn(name = "observacion_trabajador_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador observacionTrabajadorIdTrabajador;

    public ObservacionTrabajador() {
    }

    public ObservacionTrabajador(Integer observacionTrabajadorId) {
        this.observacionTrabajadorId = observacionTrabajadorId;
    }

    public ObservacionTrabajador(Integer observacionTrabajadorId, Date observacionTrabajadorFecha, String observacionTrabajadorDescripcion, String observacionTrabajadorDetalle) {
        this.observacionTrabajadorId = observacionTrabajadorId;
        this.observacionTrabajadorFecha = observacionTrabajadorFecha;
        this.observacionTrabajadorDescripcion = observacionTrabajadorDescripcion;
        this.observacionTrabajadorDetalle = observacionTrabajadorDetalle;
    }

    public Integer getObservacionTrabajadorId() {
        return observacionTrabajadorId;
    }

    public void setObservacionTrabajadorId(Integer observacionTrabajadorId) {
        this.observacionTrabajadorId = observacionTrabajadorId;
    }

    public Date getObservacionTrabajadorFecha() {
        return observacionTrabajadorFecha;
    }

    public void setObservacionTrabajadorFecha(Date observacionTrabajadorFecha) {
        this.observacionTrabajadorFecha = observacionTrabajadorFecha;
    }

    public String getObservacionTrabajadorDescripcion() {
        return observacionTrabajadorDescripcion;
    }

    public void setObservacionTrabajadorDescripcion(String observacionTrabajadorDescripcion) {
        this.observacionTrabajadorDescripcion = observacionTrabajadorDescripcion;
    }

    public String getObservacionTrabajadorDetalle() {
        return observacionTrabajadorDetalle;
    }

    public void setObservacionTrabajadorDetalle(String observacionTrabajadorDetalle) {
        this.observacionTrabajadorDetalle = observacionTrabajadorDetalle;
    }
    
    public TipoObservacion getObservacionTrabajadorIdTipoObservacion() {
        return observacionTrabajadorIdTipoObservacion;
    }

    public void setObservacionTrabajadorIdTipoObservacion(TipoObservacion observacionTrabajadorIdTipoObservacion) {
        this.observacionTrabajadorIdTipoObservacion = observacionTrabajadorIdTipoObservacion;
    }

    public Trabajador getObservacionTrabajadorIdTrabajador() {
        return observacionTrabajadorIdTrabajador;
    }

    public void setObservacionTrabajadorIdTrabajador(Trabajador observacionTrabajadorIdTrabajador) {
        this.observacionTrabajadorIdTrabajador = observacionTrabajadorIdTrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (observacionTrabajadorId != null ? observacionTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObservacionTrabajador)) {
            return false;
        }
        ObservacionTrabajador other = (ObservacionTrabajador) object;
        if ((this.observacionTrabajadorId == null && other.observacionTrabajadorId != null) || (this.observacionTrabajadorId != null && !this.observacionTrabajadorId.equals(other.observacionTrabajadorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ObservacionTrabajador[ observacionTrabajadorId=" + observacionTrabajadorId + " ]";
    }
    
}
