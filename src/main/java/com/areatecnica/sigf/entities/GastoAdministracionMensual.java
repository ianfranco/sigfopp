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
@Table(name = "gasto_administracion_mensual", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GastoAdministracionMensual.findAll", query = "SELECT g FROM GastoAdministracionMensual g")
    , @NamedQuery(name = "GastoAdministracionMensual.findByGastoAdministracionMensualId", query = "SELECT g FROM GastoAdministracionMensual g WHERE g.gastoAdministracionMensualId = :gastoAdministracionMensualId")
    , @NamedQuery(name = "GastoAdministracionMensual.findByGastoAdministracionMensualFolio", query = "SELECT g FROM GastoAdministracionMensual g WHERE g.gastoAdministracionMensualFolio = :gastoAdministracionMensualFolio")
    , @NamedQuery(name = "GastoAdministracionMensual.findByGastoAdministracionMensualNombre", query = "SELECT g FROM GastoAdministracionMensual g WHERE g.gastoAdministracionMensualNombre = :gastoAdministracionMensualNombre")
    , @NamedQuery(name = "GastoAdministracionMensual.findByGastoAdministracionMensualFechaGasto", query = "SELECT g FROM GastoAdministracionMensual g WHERE g.gastoAdministracionMensualFechaGasto = :gastoAdministracionMensualFechaGasto")
    , @NamedQuery(name = "GastoAdministracionMensual.findByGastoAdministracionMensualValor", query = "SELECT g FROM GastoAdministracionMensual g WHERE g.gastoAdministracionMensualValor = :gastoAdministracionMensualValor")
    })
public class GastoAdministracionMensual extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gasto_administracion_mensual_id")
    private Integer gastoAdministracionMensualId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gasto_administracion_mensual_folio")
    private int gastoAdministracionMensualFolio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "gasto_administracion_mensual_nombre")
    private String gastoAdministracionMensualNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gasto_administracion_mensual_fecha_gasto")
    @Temporal(TemporalType.DATE)
    private Date gastoAdministracionMensualFechaGasto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gasto_administracion_mensual_valor")
    private int gastoAdministracionMensualValor;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "gasto_administracion_mensual_observacion")
    private String gastoAdministracionMensualObservacion;    
    @JoinColumn(name = "gasto_administracion_mensual_id_departamento", referencedColumnName = "departamento_id", nullable = false)
    @ManyToOne(optional = false)
    private Departamento gastoAdministracionMensualIdDepartamento;

    public GastoAdministracionMensual() {
    }

    public GastoAdministracionMensual(Integer gastoAdministracionMensualId) {
        this.gastoAdministracionMensualId = gastoAdministracionMensualId;
    }

    public GastoAdministracionMensual(Integer gastoAdministracionMensualId, int gastoAdministracionMensualFolio, String gastoAdministracionMensualNombre, Date gastoAdministracionMensualFechaGasto, int gastoAdministracionMensualValor) {
        this.gastoAdministracionMensualId = gastoAdministracionMensualId;
        this.gastoAdministracionMensualFolio = gastoAdministracionMensualFolio;
        this.gastoAdministracionMensualNombre = gastoAdministracionMensualNombre;
        this.gastoAdministracionMensualFechaGasto = gastoAdministracionMensualFechaGasto;
        this.gastoAdministracionMensualValor = gastoAdministracionMensualValor;
    }

    public Integer getGastoAdministracionMensualId() {
        return gastoAdministracionMensualId;
    }

    public void setGastoAdministracionMensualId(Integer gastoAdministracionMensualId) {
        this.gastoAdministracionMensualId = gastoAdministracionMensualId;
    }

    public int getGastoAdministracionMensualFolio() {
        return gastoAdministracionMensualFolio;
    }

    public void setGastoAdministracionMensualFolio(int gastoAdministracionMensualFolio) {
        this.gastoAdministracionMensualFolio = gastoAdministracionMensualFolio;
    }

    public String getGastoAdministracionMensualNombre() {
        return gastoAdministracionMensualNombre;
    }

    public void setGastoAdministracionMensualNombre(String gastoAdministracionMensualNombre) {
        this.gastoAdministracionMensualNombre = gastoAdministracionMensualNombre;
    }

    public Date getGastoAdministracionMensualFechaGasto() {
        return gastoAdministracionMensualFechaGasto;
    }

    public void setGastoAdministracionMensualFechaGasto(Date gastoAdministracionMensualFechaGasto) {
        this.gastoAdministracionMensualFechaGasto = gastoAdministracionMensualFechaGasto;
    }

    public int getGastoAdministracionMensualValor() {
        return gastoAdministracionMensualValor;
    }

    public void setGastoAdministracionMensualValor(int gastoAdministracionMensualValor) {
        this.gastoAdministracionMensualValor = gastoAdministracionMensualValor;
    }

    public String getGastoAdministracionMensualObservacion() {
        return gastoAdministracionMensualObservacion;
    }

    public void setGastoAdministracionMensualObservacion(String gastoAdministracionMensualObservacion) {
        this.gastoAdministracionMensualObservacion = gastoAdministracionMensualObservacion;
    }

    public Departamento getGastoAdministracionMensualIdDepartamento() {
        return gastoAdministracionMensualIdDepartamento;
    }

    public void setGastoAdministracionMensualIdDepartamento(Departamento gastoAdministracionMensualIdDepartamento) {
        this.gastoAdministracionMensualIdDepartamento = gastoAdministracionMensualIdDepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gastoAdministracionMensualId != null ? gastoAdministracionMensualId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GastoAdministracionMensual)) {
            return false;
        }
        GastoAdministracionMensual other = (GastoAdministracionMensual) object;
        if ((this.gastoAdministracionMensualId == null && other.gastoAdministracionMensualId != null) || (this.gastoAdministracionMensualId != null && !this.gastoAdministracionMensualId.equals(other.gastoAdministracionMensualId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.GastoAdministracionMensual[ gastoAdministracionMensualId=" + gastoAdministracionMensualId + " ]";
    }
    
}
