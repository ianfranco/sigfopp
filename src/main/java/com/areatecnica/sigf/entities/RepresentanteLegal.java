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
@Table(name = "representante_legal", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepresentanteLegal.findAll", query = "SELECT r FROM RepresentanteLegal r")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalId", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalId = :representanteLegalId")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalNombre", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalNombre = :representanteLegalNombre")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalRut", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalRut = :representanteLegalRut")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalDireccion", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalDireccion = :representanteLegalDireccion")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalTelefono", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalTelefono = :representanteLegalTelefono")
    , @NamedQuery(name = "RepresentanteLegal.findByRepresentanteLegalEmail", query = "SELECT r FROM RepresentanteLegal r WHERE r.representanteLegalEmail = :representanteLegalEmail")
    })
public class RepresentanteLegal extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "representante_legal_id")
    private Integer representanteLegalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "representante_legal_nombre")
    private String representanteLegalNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "representante_legal_rut")
    private String representanteLegalRut;
    @Size(max = 45)
    @Column(name = "representante_legal_direccion")
    private String representanteLegalDireccion;
    @Size(max = 45)
    @Column(name = "representante_legal_telefono")
    private String representanteLegalTelefono;
    @Size(max = 45)
    @Column(name = "representante_legal_email")
    private String representanteLegalEmail;    
    @JoinColumn(name = "representante_legal_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta representanteLegalIdCuenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "representanteEmpresaIdRepresentanteLegal")
    private List<RepresentanteEmpresa> representanteEmpresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operadorTransporteIdRepresentante")
    private List<OperadorTransporte> operadorTransporteList;

    public RepresentanteLegal() {
    }

    public RepresentanteLegal(Integer representanteLegalId) {
        this.representanteLegalId = representanteLegalId;
    }

    public RepresentanteLegal(Integer representanteLegalId, String representanteLegalNombre, String representanteLegalRut) {
        this.representanteLegalId = representanteLegalId;
        this.representanteLegalNombre = representanteLegalNombre;
        this.representanteLegalRut = representanteLegalRut;
    }

    public Integer getRepresentanteLegalId() {
        return representanteLegalId;
    }

    public void setRepresentanteLegalId(Integer representanteLegalId) {
        this.representanteLegalId = representanteLegalId;
    }

    public String getRepresentanteLegalNombre() {
        return representanteLegalNombre;
    }

    public void setRepresentanteLegalNombre(String representanteLegalNombre) {
        this.representanteLegalNombre = representanteLegalNombre;
    }

    public String getRepresentanteLegalRut() {
        return representanteLegalRut;
    }

    public void setRepresentanteLegalRut(String representanteLegalRut) {
        this.representanteLegalRut = representanteLegalRut;
    }

    public String getRepresentanteLegalDireccion() {
        return representanteLegalDireccion;
    }

    public void setRepresentanteLegalDireccion(String representanteLegalDireccion) {
        this.representanteLegalDireccion = representanteLegalDireccion;
    }

    public String getRepresentanteLegalTelefono() {
        return representanteLegalTelefono;
    }

    public void setRepresentanteLegalTelefono(String representanteLegalTelefono) {
        this.representanteLegalTelefono = representanteLegalTelefono;
    }

    public String getRepresentanteLegalEmail() {
        return representanteLegalEmail;
    }

    public void setRepresentanteLegalEmail(String representanteLegalEmail) {
        this.representanteLegalEmail = representanteLegalEmail;
    }

    public Cuenta getRepresentanteLegalIdCuenta() {
        return representanteLegalIdCuenta;
    }

    public void setRepresentanteLegalIdCuenta(Cuenta representanteLegalIdCuenta) {
        this.representanteLegalIdCuenta = representanteLegalIdCuenta;
    }

    @XmlTransient
    public List<RepresentanteEmpresa> getRepresentanteEmpresaList() {
        return representanteEmpresaList;
    }

    public void setRepresentanteEmpresaList(List<RepresentanteEmpresa> representanteEmpresaList) {
        this.representanteEmpresaList = representanteEmpresaList;
    }

    @XmlTransient
    public List<OperadorTransporte> getOperadorTransporteList() {
        return operadorTransporteList;
    }

    public void setOperadorTransporteList(List<OperadorTransporte> operadorTransporteList) {
        this.operadorTransporteList = operadorTransporteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (representanteLegalId != null ? representanteLegalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepresentanteLegal)) {
            return false;
        }
        RepresentanteLegal other = (RepresentanteLegal) object;
        if ((this.representanteLegalId == null && other.representanteLegalId != null) || (this.representanteLegalId != null && !this.representanteLegalId.equals(other.representanteLegalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.RepresentanteLegal[ representanteLegalId=" + representanteLegalId + " ]";
    }
    
}
