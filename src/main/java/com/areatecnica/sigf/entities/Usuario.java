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
@Table(name = "usuario", catalog = "sigf_v3", schema = "")
@EntityListeners({AuditListener.class})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findAllByCuenta", query = "SELECT u FROM Usuario u WHERE u.usuarioIdCuenta =:idCuenta")
    , @NamedQuery(name = "Usuario.findByUsuarioId", query = "SELECT u FROM Usuario u WHERE u.usuarioId = :usuarioId")
    , @NamedQuery(name = "Usuario.findByUsuarioRut", query = "SELECT u FROM Usuario u WHERE u.usuarioRut = :usuarioRut")
    , @NamedQuery(name = "Usuario.findByUsuarioRutAndPass", query = "SELECT u FROM Usuario u WHERE u.usuarioRut = :usuarioRut AND u.usuarioPass = :usuarioPass")
    , @NamedQuery(name = "Usuario.findByUsuarioPass", query = "SELECT u FROM Usuario u WHERE u.usuarioPass = :usuarioPass")
    , @NamedQuery(name = "Usuario.findByUsuarioNombres", query = "SELECT u FROM Usuario u WHERE u.usuarioNombres = :usuarioNombres")
    , @NamedQuery(name = "Usuario.findByUsuarioApellidoPaterno", query = "SELECT u FROM Usuario u WHERE u.usuarioApellidoPaterno = :usuarioApellidoPaterno")
    , @NamedQuery(name = "Usuario.findByUsuarioApellidoMaterno", query = "SELECT u FROM Usuario u WHERE u.usuarioApellidoMaterno = :usuarioApellidoMaterno")
    , @NamedQuery(name = "Usuario.findByUsuarioEmail", query = "SELECT u FROM Usuario u WHERE u.usuarioEmail = :usuarioEmail")
    , @NamedQuery(name = "Usuario.findByUsuarioActivo", query = "SELECT u FROM Usuario u WHERE u.usuarioActivo = :usuarioActivo")
    })
public class Usuario extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_id")
    private Integer usuarioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "usuario_rut")
    private String usuarioRut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usuario_pass")
    private String usuarioPass;
    @Size(max = 255)
    @Column(name = "usuario_nombres")
    private String usuarioNombres;
    @Size(max = 255)
    @Column(name = "usuario_apellido_paterno")
    private String usuarioApellidoPaterno;
    @Size(max = 255)
    @Column(name = "usuario_apellido_materno")
    private String usuarioApellidoMaterno;
    @Size(max = 255)
    @Column(name = "usuario_email")
    private String usuarioEmail;
    @Column(name = "usuario_activo")
    private Boolean usuarioActivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioSessionIdUsuario")
    private List<UsuarioSession> usuarioSessionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "despachoIdInspector")
    private List<Despacho> despachoList;
    @JoinColumn(name = "usuario_id_cuenta", referencedColumnName = "cuenta_id", nullable = false)
    @ManyToOne(optional = false)
    private Cuenta usuarioIdCuenta;
    @JoinColumn(name = "usuario_id_rol", referencedColumnName = "rol_id", nullable = false)
    @ManyToOne(optional = false)
    private Rol usuarioIdRol;
    @JoinColumn(name = "usuario_id_terminal", referencedColumnName = "terminal_id", nullable = false)
    @ManyToOne(optional = false)
    private Terminal usuarioIdTerminal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "logIdUsuario")
    private List<Log> logList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cajaRecaudacionIdUsuario")
    private List<CajaRecaudacion> cajaRecaudacionList;

    public Usuario() {
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario(Integer usuarioId, String usuarioRut, String usuarioPass) {
        this.usuarioId = usuarioId;
        this.usuarioRut = usuarioRut;
        this.usuarioPass = usuarioPass;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioRut() {
        return usuarioRut;
    }

    public void setUsuarioRut(String usuarioRut) {
        this.usuarioRut = usuarioRut;
    }

    public String getUsuarioPass() {
        return usuarioPass;
    }

    public void setUsuarioPass(String usuarioPass) {
        this.usuarioPass = usuarioPass;
    }

    public String getUsuarioNombres() {
        return usuarioNombres;
    }

    public void setUsuarioNombres(String usuarioNombres) {
        this.usuarioNombres = usuarioNombres;
    }

    public String getUsuarioApellidoPaterno() {
        return usuarioApellidoPaterno;
    }

    public void setUsuarioApellidoPaterno(String usuarioApellidoPaterno) {
        this.usuarioApellidoPaterno = usuarioApellidoPaterno;
    }

    public String getUsuarioApellidoMaterno() {
        return usuarioApellidoMaterno;
    }

    public void setUsuarioApellidoMaterno(String usuarioApellidoMaterno) {
        this.usuarioApellidoMaterno = usuarioApellidoMaterno;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public Boolean getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Boolean usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    @XmlTransient
    public List<UsuarioSession> getUsuarioSessionList() {
        return usuarioSessionList;
    }

    public void setUsuarioSessionList(List<UsuarioSession> usuarioSessionList) {
        this.usuarioSessionList = usuarioSessionList;
    }

    @XmlTransient
    public List<Despacho> getDespachoList() {
        return despachoList;
    }

    public void setDespachoList(List<Despacho> despachoList) {
        this.despachoList = despachoList;
    }

    public Cuenta getUsuarioIdCuenta() {
        return usuarioIdCuenta;
    }

    public void setUsuarioIdCuenta(Cuenta usuarioIdCuenta) {
        this.usuarioIdCuenta = usuarioIdCuenta;
    }

    public Rol getUsuarioIdRol() {
        return usuarioIdRol;
    }

    public void setUsuarioIdRol(Rol usuarioIdRol) {
        this.usuarioIdRol = usuarioIdRol;
    }

    public Terminal getUsuarioIdTerminal() {
        return usuarioIdTerminal;
    }

    public void setUsuarioIdTerminal(Terminal usuarioIdTerminal) {
        this.usuarioIdTerminal = usuarioIdTerminal;
    }

    @XmlTransient
    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    @XmlTransient
    public List<CajaRecaudacion> getCajaRecaudacionList() {
        return cajaRecaudacionList;
    }

    public void setCajaRecaudacionList(List<CajaRecaudacion> cajaRecaudacionList) {
        this.cajaRecaudacionList = cajaRecaudacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.sigf.entities.Usuario[ usuarioId=" + usuarioId + " ]";
    }

}
