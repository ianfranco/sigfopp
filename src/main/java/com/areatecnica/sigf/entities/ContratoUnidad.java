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
@Table(name = "contrato_unidad", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoUnidad.findAll", query = "SELECT c FROM ContratoUnidad c")
    , @NamedQuery(name = "ContratoUnidad.findByContratoUnidadId", query = "SELECT c FROM ContratoUnidad c WHERE c.contratoUnidadId = :contratoUnidadId")
    , @NamedQuery(name = "ContratoUnidad.findByContratoUnidadIdentificador", query = "SELECT c FROM ContratoUnidad c WHERE c.contratoUnidadIdentificador = :contratoUnidadIdentificador")
    , @NamedQuery(name = "ContratoUnidad.findByContratoUnidadDesde", query = "SELECT c FROM ContratoUnidad c WHERE c.contratoUnidadDesde = :contratoUnidadDesde")
    , @NamedQuery(name = "ContratoUnidad.findByContratoUnidadHasta", query = "SELECT c FROM ContratoUnidad c WHERE c.contratoUnidadHasta = :contratoUnidadHasta")
    , @NamedQuery(name = "ContratoUnidad.findByContratoUnidadNombre", query = "SELECT c FROM ContratoUnidad c WHERE c.contratoUnidadNombre = :contratoUnidadNombre")
    , @NamedQuery(name = "ContratoUnidad.findByContratoUnidadDescripcion", query = "SELECT c FROM ContratoUnidad c WHERE c.contratoUnidadDescripcion = :contratoUnidadDescripcion")
    })
public class ContratoUnidad extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contrato_unidad_id")
    private Integer contratoUnidadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contrato_unidad_identificador")
    private String contratoUnidadIdentificador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contrato_unidad_desde")
    @Temporal(TemporalType.DATE)
    private Date contratoUnidadDesde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contrato_unidad_hasta")
    @Temporal(TemporalType.DATE)
    private Date contratoUnidadHasta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contrato_unidad_nombre")
    private String contratoUnidadNombre;
    @Size(max = 400)
    @Column(name = "contrato_unidad_descripcion")
    private String contratoUnidadDescripcion;    
    @JoinColumn(name = "contrato_unidad_id_unidad_negocio", referencedColumnName = "unidad_negocio_id", nullable = false)
    @ManyToOne(optional = false)
    private UnidadNegocio contratoUnidadIdUnidadNegocio;

    public ContratoUnidad() {
    }

    public ContratoUnidad(Integer contratoUnidadId) {
        this.contratoUnidadId = contratoUnidadId;
    }

    public ContratoUnidad(Integer contratoUnidadId, String contratoUnidadIdentificador, Date contratoUnidadDesde, Date contratoUnidadHasta, String contratoUnidadNombre) {
        this.contratoUnidadId = contratoUnidadId;
        this.contratoUnidadIdentificador = contratoUnidadIdentificador;
        this.contratoUnidadDesde = contratoUnidadDesde;
        this.contratoUnidadHasta = contratoUnidadHasta;
        this.contratoUnidadNombre = contratoUnidadNombre;
    }

    public Integer getContratoUnidadId() {
        return contratoUnidadId;
    }

    public void setContratoUnidadId(Integer contratoUnidadId) {
        this.contratoUnidadId = contratoUnidadId;
    }

    public String getContratoUnidadIdentificador() {
        return contratoUnidadIdentificador;
    }

    public void setContratoUnidadIdentificador(String contratoUnidadIdentificador) {
        this.contratoUnidadIdentificador = contratoUnidadIdentificador;
    }

    public Date getContratoUnidadDesde() {
        return contratoUnidadDesde;
    }

    public void setContratoUnidadDesde(Date contratoUnidadDesde) {
        this.contratoUnidadDesde = contratoUnidadDesde;
    }

    public Date getContratoUnidadHasta() {
        return contratoUnidadHasta;
    }

    public void setContratoUnidadHasta(Date contratoUnidadHasta) {
        this.contratoUnidadHasta = contratoUnidadHasta;
    }

    public String getContratoUnidadNombre() {
        return contratoUnidadNombre;
    }

    public void setContratoUnidadNombre(String contratoUnidadNombre) {
        this.contratoUnidadNombre = contratoUnidadNombre;
    }

    public String getContratoUnidadDescripcion() {
        return contratoUnidadDescripcion;
    }

    public void setContratoUnidadDescripcion(String contratoUnidadDescripcion) {
        this.contratoUnidadDescripcion = contratoUnidadDescripcion;
    }

    public UnidadNegocio getContratoUnidadIdUnidadNegocio() {
        return contratoUnidadIdUnidadNegocio;
    }

    public void setContratoUnidadIdUnidadNegocio(UnidadNegocio contratoUnidadIdUnidadNegocio) {
        this.contratoUnidadIdUnidadNegocio = contratoUnidadIdUnidadNegocio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratoUnidadId != null ? contratoUnidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratoUnidad)) {
            return false;
        }
        ContratoUnidad other = (ContratoUnidad) object;
        if ((this.contratoUnidadId == null && other.contratoUnidadId != null) || (this.contratoUnidadId != null && !this.contratoUnidadId.equals(other.contratoUnidadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.ContratoUnidad[ contratoUnidadId=" + contratoUnidadId + " ]";
    }
    
}
