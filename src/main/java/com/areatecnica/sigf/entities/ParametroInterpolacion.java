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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "parametro_interpolacion", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroInterpolacion.findAll", query = "SELECT p FROM ParametroInterpolacion p")
    , @NamedQuery(name = "ParametroInterpolacion.findByParametroInterpolacionId", query = "SELECT p FROM ParametroInterpolacion p WHERE p.parametroInterpolacionId = :parametroInterpolacionId")
    , @NamedQuery(name = "ParametroInterpolacion.findByParametroInterpolacionNombre", query = "SELECT p FROM ParametroInterpolacion p WHERE p.parametroInterpolacionNombre = :parametroInterpolacionNombre")
    , @NamedQuery(name = "ParametroInterpolacion.findByParametroInterpolacionValor", query = "SELECT p FROM ParametroInterpolacion p WHERE p.parametroInterpolacionValor = :parametroInterpolacionValor")
    , @NamedQuery(name = "ParametroInterpolacion.findByParametroInterpolacionUnidad", query = "SELECT p FROM ParametroInterpolacion p WHERE p.parametroInterpolacionUnidad = :parametroInterpolacionUnidad")})
public class ParametroInterpolacion extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "parametro_interpolacion_id")
    private Integer parametroInterpolacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "parametro_interpolacion_nombre")
    private String parametroInterpolacionNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parametro_interpolacion_valor")
    private float parametroInterpolacionValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "parametro_interpolacion_unidad")
    private String parametroInterpolacionUnidad;

    public ParametroInterpolacion() {
    }

    public ParametroInterpolacion(Integer parametroInterpolacionId) {
        this.parametroInterpolacionId = parametroInterpolacionId;
    }

    public ParametroInterpolacion(Integer parametroInterpolacionId, String parametroInterpolacionNombre, float parametroInterpolacionValor, String parametroInterpolacionUnidad) {
        this.parametroInterpolacionId = parametroInterpolacionId;
        this.parametroInterpolacionNombre = parametroInterpolacionNombre;
        this.parametroInterpolacionValor = parametroInterpolacionValor;
        this.parametroInterpolacionUnidad = parametroInterpolacionUnidad;
    }

    public Integer getParametroInterpolacionId() {
        return parametroInterpolacionId;
    }

    public void setParametroInterpolacionId(Integer parametroInterpolacionId) {
        this.parametroInterpolacionId = parametroInterpolacionId;
    }

    public String getParametroInterpolacionNombre() {
        return parametroInterpolacionNombre;
    }

    public void setParametroInterpolacionNombre(String parametroInterpolacionNombre) {
        this.parametroInterpolacionNombre = parametroInterpolacionNombre;
    }

    public float getParametroInterpolacionValor() {
        return parametroInterpolacionValor;
    }

    public void setParametroInterpolacionValor(float parametroInterpolacionValor) {
        this.parametroInterpolacionValor = parametroInterpolacionValor;
    }

    public String getParametroInterpolacionUnidad() {
        return parametroInterpolacionUnidad;
    }

    public void setParametroInterpolacionUnidad(String parametroInterpolacionUnidad) {
        this.parametroInterpolacionUnidad = parametroInterpolacionUnidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametroInterpolacionId != null ? parametroInterpolacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroInterpolacion)) {
            return false;
        }
        ParametroInterpolacion other = (ParametroInterpolacion) object;
        if ((this.parametroInterpolacionId == null && other.parametroInterpolacionId != null) || (this.parametroInterpolacionId != null && !this.parametroInterpolacionId.equals(other.parametroInterpolacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ParametroInterpolacion[ parametroInterpolacionId=" + parametroInterpolacionId + " ]";
    }
    
}
