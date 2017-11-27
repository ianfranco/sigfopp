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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "carga_trabajador", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargaTrabajador.findAll", query = "SELECT c FROM CargaTrabajador c")
    , @NamedQuery(name = "CargaTrabajador.findByCargaTrabajadorId", query = "SELECT c FROM CargaTrabajador c WHERE c.cargaTrabajadorId = :cargaTrabajadorId")
    , @NamedQuery(name = "CargaTrabajador.findByCargaTrabajadorApellidos", query = "SELECT c FROM CargaTrabajador c WHERE c.cargaTrabajadorApellidos = :cargaTrabajadorApellidos")
    , @NamedQuery(name = "CargaTrabajador.findByCargaTrabajadorNombres", query = "SELECT c FROM CargaTrabajador c WHERE c.cargaTrabajadorNombres = :cargaTrabajadorNombres")
    , @NamedQuery(name = "CargaTrabajador.findByCargaTrabajadorRut", query = "SELECT c FROM CargaTrabajador c WHERE c.cargaTrabajadorRut = :cargaTrabajadorRut")
    , @NamedQuery(name = "CargaTrabajador.findByCargaTrabajadorFechaNacimiento", query = "SELECT c FROM CargaTrabajador c WHERE c.cargaTrabajadorFechaNacimiento = :cargaTrabajadorFechaNacimiento")
    , @NamedQuery(name = "CargaTrabajador.findByCargaTrabajadorActiva", query = "SELECT c FROM CargaTrabajador c WHERE c.cargaTrabajadorActiva = :cargaTrabajadorActiva")
    })
public class CargaTrabajador extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "carga_trabajador_id")
    private Integer cargaTrabajadorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "carga_trabajador_apellidos")
    private String cargaTrabajadorApellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "carga_trabajador_nombres")
    private String cargaTrabajadorNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "carga_trabajador_rut")
    private String cargaTrabajadorRut;
    @Column(name = "carga_trabajador_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date cargaTrabajadorFechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "carga_trabajador_activa")
    private boolean cargaTrabajadorActiva;
    @JoinColumn(name = "carga_trabajador_id_parentesco", referencedColumnName = "parentesco_carga_id", nullable = false)
    @ManyToOne(optional = false)
    private ParentescoCarga cargaTrabajadorIdParentesco;
    @JoinColumn(name = "carga_trabajador_id_tipo", referencedColumnName = "tipo_carga_familiar_id", nullable = false)
    @ManyToOne(optional = false)
    private TipoCargaFamiliar cargaTrabajadorIdTipo;
    @JoinColumn(name = "carga_trabajador_id_trabajador", referencedColumnName = "trabajador_id", nullable = false)
    @ManyToOne(optional = false)
    private Trabajador cargaTrabajadorIdTrabajador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargaRetroactivaIdCargaTrabajador")
    private List<CargaRetroactiva> cargaRetroactivaList;

    public CargaTrabajador() {
    }

    public CargaTrabajador(Integer cargaTrabajadorId) {
        this.cargaTrabajadorId = cargaTrabajadorId;
    }

    public CargaTrabajador(Integer cargaTrabajadorId, String cargaTrabajadorApellidos, String cargaTrabajadorNombres, String cargaTrabajadorRut, boolean cargaTrabajadorActiva) {
        this.cargaTrabajadorId = cargaTrabajadorId;
        this.cargaTrabajadorApellidos = cargaTrabajadorApellidos;
        this.cargaTrabajadorNombres = cargaTrabajadorNombres;
        this.cargaTrabajadorRut = cargaTrabajadorRut;
        this.cargaTrabajadorActiva = cargaTrabajadorActiva;
    }

    public Integer getCargaTrabajadorId() {
        return cargaTrabajadorId;
    }

    public void setCargaTrabajadorId(Integer cargaTrabajadorId) {
        this.cargaTrabajadorId = cargaTrabajadorId;
    }

    public String getCargaTrabajadorApellidos() {
        return cargaTrabajadorApellidos;
    }

    public void setCargaTrabajadorApellidos(String cargaTrabajadorApellidos) {
        this.cargaTrabajadorApellidos = cargaTrabajadorApellidos;
    }

    public String getCargaTrabajadorNombres() {
        return cargaTrabajadorNombres;
    }

    public void setCargaTrabajadorNombres(String cargaTrabajadorNombres) {
        this.cargaTrabajadorNombres = cargaTrabajadorNombres;
    }

    public String getCargaTrabajadorRut() {
        return cargaTrabajadorRut;
    }

    public void setCargaTrabajadorRut(String cargaTrabajadorRut) {
        this.cargaTrabajadorRut = cargaTrabajadorRut;
    }

    public Date getCargaTrabajadorFechaNacimiento() {
        return cargaTrabajadorFechaNacimiento;
    }

    public void setCargaTrabajadorFechaNacimiento(Date cargaTrabajadorFechaNacimiento) {
        this.cargaTrabajadorFechaNacimiento = cargaTrabajadorFechaNacimiento;
    }

    public boolean getCargaTrabajadorActiva() {
        return cargaTrabajadorActiva;
    }

    public void setCargaTrabajadorActiva(boolean cargaTrabajadorActiva) {
        this.cargaTrabajadorActiva = cargaTrabajadorActiva;
    }
    
    public ParentescoCarga getCargaTrabajadorIdParentesco() {
        return cargaTrabajadorIdParentesco;
    }

    public void setCargaTrabajadorIdParentesco(ParentescoCarga cargaTrabajadorIdParentesco) {
        this.cargaTrabajadorIdParentesco = cargaTrabajadorIdParentesco;
    }

    public TipoCargaFamiliar getCargaTrabajadorIdTipo() {
        return cargaTrabajadorIdTipo;
    }

    public void setCargaTrabajadorIdTipo(TipoCargaFamiliar cargaTrabajadorIdTipo) {
        this.cargaTrabajadorIdTipo = cargaTrabajadorIdTipo;
    }

    public Trabajador getCargaTrabajadorIdTrabajador() {
        return cargaTrabajadorIdTrabajador;
    }

    public void setCargaTrabajadorIdTrabajador(Trabajador cargaTrabajadorIdTrabajador) {
        this.cargaTrabajadorIdTrabajador = cargaTrabajadorIdTrabajador;
    }

    @XmlTransient
    public List<CargaRetroactiva> getCargaRetroactivaList() {
        return cargaRetroactivaList;
    }

    public void setCargaRetroactivaList(List<CargaRetroactiva> cargaRetroactivaList) {
        this.cargaRetroactivaList = cargaRetroactivaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cargaTrabajadorId != null ? cargaTrabajadorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargaTrabajador)) {
            return false;
        }
        CargaTrabajador other = (CargaTrabajador) object;
        if ((this.cargaTrabajadorId == null && other.cargaTrabajadorId != null) || (this.cargaTrabajadorId != null && !this.cargaTrabajadorId.equals(other.cargaTrabajadorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.CargaTrabajador[ cargaTrabajadorId=" + cargaTrabajadorId + " ]";
    }

}
