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
@Table(name = "operador_transporte", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OperadorTransporte.findAll", query = "SELECT o FROM OperadorTransporte o")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteId", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteId = :operadorTransporteId")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteRut", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteRut = :operadorTransporteRut")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteNombre", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteNombre = :operadorTransporteNombre")
    })
public class OperadorTransporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "operador_transporte_id")
    private Integer operadorTransporteId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "operador_transporte_rut")
    private String operadorTransporteRut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "operador_transporte_nombre")
    private String operadorTransporteNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "operador_transporte_recauda_guias", nullable = false)
    private boolean operadorTransporteRecaudaGuias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "operador_transporte_recauda_combustible", nullable = false)
    private boolean operadorTransporteRecaudaCombustible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "operador_transporte_recauda_minuto", nullable = false)
    private boolean operadorTransporteRecaudaMinuto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "operador_transporte_recauda_boleto", nullable = false)
    private boolean operadorTransporteRecaudaBoleto;
    @JoinColumn(name = "operador_transporte_id_representante", referencedColumnName = "representante_legal_id", nullable = false)
    @ManyToOne(optional = false)
    private RepresentanteLegal operadorTransporteIdRepresentante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "relacionLaboralIdOperador")
    private List<RelacionLaboral> relacionLaboralList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadNegocioIdOperadorTransporte")
    private List<UnidadNegocio> unidadNegocioList;

    public OperadorTransporte() {
    }

    public OperadorTransporte(Integer operadorTransporteId) {
        this.operadorTransporteId = operadorTransporteId;
    }

    public OperadorTransporte(Integer operadorTransporteId, String operadorTransporteRut, String operadorTransporteNombre, boolean operadorTransporteRecaudaGuias, boolean operadorTransporteRecaudaCombustible, boolean operadorTransporteRecaudaMinuto, boolean operadorTransporteRecaudaBoleto) {
        this.operadorTransporteId = operadorTransporteId;
        this.operadorTransporteRut = operadorTransporteRut;
        this.operadorTransporteNombre = operadorTransporteNombre;
        this.operadorTransporteRecaudaGuias = operadorTransporteRecaudaGuias;
        this.operadorTransporteRecaudaCombustible = operadorTransporteRecaudaCombustible;
        this.operadorTransporteRecaudaMinuto = operadorTransporteRecaudaMinuto;
        this.operadorTransporteRecaudaBoleto = operadorTransporteRecaudaBoleto;
    }

    public Integer getOperadorTransporteId() {
        return operadorTransporteId;
    }

    public void setOperadorTransporteId(Integer operadorTransporteId) {
        this.operadorTransporteId = operadorTransporteId;
    }

    public String getOperadorTransporteRut() {
        return operadorTransporteRut;
    }

    public void setOperadorTransporteRut(String operadorTransporteRut) {
        this.operadorTransporteRut = operadorTransporteRut;
    }

    public String getOperadorTransporteNombre() {
        return operadorTransporteNombre;
    }

    public void setOperadorTransporteNombre(String operadorTransporteNombre) {
        this.operadorTransporteNombre = operadorTransporteNombre;
    }

    public boolean getOperadorTransporteRecaudaGuias() {
        return operadorTransporteRecaudaGuias;
    }

    public void setOperadorTransporteRecaudaGuias(boolean operadorTransporteRecaudaGuias) {
        this.operadorTransporteRecaudaGuias = operadorTransporteRecaudaGuias;
    }

    public boolean getOperadorTransporteRecaudaCombustible() {
        return operadorTransporteRecaudaCombustible;
    }

    public void setOperadorTransporteRecaudaCombustible(boolean operadorTransporteRecaudaCombustible) {
        this.operadorTransporteRecaudaCombustible = operadorTransporteRecaudaCombustible;
    }

    public boolean getOperadorTransporteRecaudaMinuto() {
        return operadorTransporteRecaudaMinuto;
    }

    public void setOperadorTransporteRecaudaMinuto(boolean operadorTransporteRecaudaMinuto) {
        this.operadorTransporteRecaudaMinuto = operadorTransporteRecaudaMinuto;
    }

    public boolean getOperadorTransporteRecaudaBoleto() {
        return operadorTransporteRecaudaBoleto;
    }

    public void setOperadorTransporteRecaudaBoleto(boolean operadorTransporteRecaudaBoleto) {
        this.operadorTransporteRecaudaBoleto = operadorTransporteRecaudaBoleto;
    }
    
    public RepresentanteLegal getOperadorTransporteIdRepresentante() {
        return operadorTransporteIdRepresentante;
    }

    public void setOperadorTransporteIdRepresentante(RepresentanteLegal operadorTransporteIdRepresentante) {
        this.operadorTransporteIdRepresentante = operadorTransporteIdRepresentante;
    }

    @XmlTransient
    public List<RelacionLaboral> getRelacionLaboralList() {
        return relacionLaboralList;
    }

    public void setRelacionLaboralList(List<RelacionLaboral> relacionLaboralList) {
        this.relacionLaboralList = relacionLaboralList;
    }

    @XmlTransient
    public List<UnidadNegocio> getUnidadNegocioList() {
        return unidadNegocioList;
    }

    public void setUnidadNegocioList(List<UnidadNegocio> unidadNegocioList) {
        this.unidadNegocioList = unidadNegocioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (operadorTransporteId != null ? operadorTransporteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperadorTransporte)) {
            return false;
        }
        OperadorTransporte other = (OperadorTransporte) object;
        if ((this.operadorTransporteId == null && other.operadorTransporteId != null) || (this.operadorTransporteId != null && !this.operadorTransporteId.equals(other.operadorTransporteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.OperadorTransporte[ operadorTransporteId=" + operadorTransporteId + " ]";
    }
    
}
